package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import main.alerts.FIOAlerts;
import main.enums.FIODataBlocksEnum;
import main.enums.FIOLangEnum;
import main.enums.FIOUnitsEnum;
import main.json.JSONNotFoundException;
import main.json.JSONSlotNotFoundException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Wrapper for handling forecast calls.
 */
public class ForecastIO {

    
    //
    // FIELDS
    //

    // API call parameters
	private static final String APIURL        = "https://api.forecast.io/forecast";  // base URL used for the API call
	private static final int    LATITUDE_MAX  = 90,
	                            LATITUDE_MIN  = 0,
	                            LONGITUDE_MAX = 180,
	                            LONGITUDE_MIN = -180;
	
	private String apiKey,                             // API key
	               exclude = "",                       // used to exclude unneeded reports to reduce latency and saving cache space
	               lang = FIOLangEnum.DEFAULT,         // defines the language in which text summaries are returned
            	   units   = FIOUnitsEnum.DEFAULT;     // defines the units of the API response
	             
	private int time = -1;                             // optional, used for requesting a arbitrary point in time
	
	private double latitude,                           // geographic latitude coordinate of a location in decimal degrees
                   longitude;                          // geographic longitude coordinate of a location in decimal degrees
	
	private boolean extend = false;                    // request hourly data for the next seven days rather than the next two when set to true

	
	// Data blocks
	private FIODataPoint currently;                    // contains the current weather conditions at the requested location
	
	private FIODataBlock daily,                        // contains the weather conditions day-by-day for the next week
	                     hourly,                       // contains the weather conditions hour-by-hour for the next two days
	                     minutely;                     // contains the weather conditions minute-by-minute for the next hour
	private FIOFlags     flags;                        // contains miscellaneous metadata concerning this request
	private JSONObject   forecast  = null;             // array of alert objects, which, if present, contains any severe weather alerts, issued by a
    private FIOAlerts    alerts;                       // governmental weather authority, pertinent to the requested location

	
	//
    // CONSTRUCTORS
    //
    /**
     * Instantiate a ForecastIO wrapper with default options.
     * 
     * @param key
     * @param latitude
     * @param longitude
     * @throws IllegalArgumentException
     */
    public ForecastIO(String key, double latitude, double longitude) throws IllegalArgumentException { this(key, latitude, longitude, null, null, null, false); }
    
    /**
     * Convenience for the full constructor with the current time.
     * 
     * @param key
     * @param latitude
     * @param longitude
     * @param lang
     * @param units
     * @param exclude
     * @param extend
     * @throws IllegalArgumentException
     */
    public ForecastIO(String key, double latitude, double longitude, String lang, String units, String[] exclude, boolean extend) throws IllegalArgumentException {

        this(key, latitude, longitude, lang, -1, units, exclude, extend);
    }
    
    /**
     * Instantiate a ForecastIO wrapper.<br />
     * <br />
     * Invalid parameters are disregarded for default values.
     * 
     * @param key       API key.
     * @param latitude  Geographic latitude coordinate of a location in decimal degrees.
     * @param longitude Geographic longitude coordinate of a location in decimal degrees.
     * @param lang      Defines the language in which text summaries are returned.
     * @param time      Define the time for requesting a arbitrary point. (UNIX time)
     * @param units     Defines the units of the API response.
     * @param exclude   Used to exclude unneeded reports to reduce latency and saving cache space.
     * @param extend    Request hourly data for the next seven days rather than the next two when set to true.
     * 
     * @throws IllegalArgumentException Thrown when passed a non valid API key.
     */
    public ForecastIO(String key, double latitude, double longitude, String lang, int time, String units, String[] exclude, boolean extend) throws IllegalArgumentException {

        // check the API key
        if (!isKeyValid(key)) {
            
            String message = "Invalid API key.";
            
            if (key != null)
                if (key.length() != 32)
                    message += " Was expecting a key 32 characters long, found " + key.length() + " instead";
            
            throw new IllegalArgumentException(message);
        }
        apiKey = key;

        setLatitude(latitude);
        setLongitude(longitude);
        
        setLang(lang);
        setTime(time);
        setUnits(units);
        setExcludeList(exclude);
        setExtend(extend);
    }
    
	//
	// FIELDS MANIPULATORS & PUBLIC HELPERS
	//
	
    // coordinates: latitude and longitude fields
    /**
     * Get the latitude in decimal degrees.
     * 
     * @return latitude
     */
    public final double getLatitude() { return latitude; }

    /**
     * Set the latitude in decimal degrees.<br />
     * <br />
     * The latitude ranges from 0° to 90° (borders included). If the value is out of range, the nearest value in the range is took.
     * 
     * @param latitude latitude
     */
    public final void setLatitude(double latitude) { this.latitude = getInRangeValue(latitude, LATITUDE_MIN, LATITUDE_MAX); }
   
    /**
     * Get the longitude in decimal degrees.
     * 
     * @return longitude
     */
    public final double getLongitude() { return longitude; }

    /**
     * Set the longitude in decimal degrees.<br />
     * <br />
     * The longitude ranges from -180° to +180° (borders included). If the value is out of range, the nearest value in the range is took.
     * 
     * @param longitude longitude
     */
    public final void setLongitude(double longitude) { this.longitude = getInRangeValue(longitude, LONGITUDE_MIN, LONGITUDE_MAX);}
    
	// exclude field
	 /**
	  * Get the exclude string that will be used for the Forecast call.<br />
	  * <br />
	  * If the string returned is empty, it means that no data block will be excluded.
	  * 
	  * @return exclude string | empty
	  */
	public final String getExcludeList() { return exclude; }
   
    /**
     * Used to exclude unneeded reports to reduce latency and saving cache space.<br />
     * <br />
     * Invalid data blocks are not considered. A valid data block is a FIODataBlockEnum 'enum'.<br />
     * If there is not at least one valid data block or if the parameter is null, it will achieve the same effect as resetting this option.
     * 
     * @param excludeList array of data blocks | null
     */
    public final void setExcludeList(String[] excludeList) {
                
        if (excludeList != null) {

            //
            // clean the array passed
            //
            // empty non valid data blocks
            for (int i = 0; i < excludeList.length; i++)
                if (!FIODataBlocksEnum.isElement(excludeList[i]))
                    excludeList[i] = "";            
            
            // remove duplicates data block declaration
            for (int i = 0; i < excludeList.length; i++)
                for (int j = 0; j < excludeList.length; j++)
                    if (i != j && excludeList[j].equals(excludeList[i]))
                        excludeList[j] = "";

            //
            // extract the URL string from the array
            //
            String excludeURL = "";
            
            for (int i = 0; i < excludeList.length; i++)
                if (excludeList[i].length() != 0) {

                    if (excludeURL.length() == 0)
                        excludeURL = excludeList[i];
                    else
                        excludeURL += "," + excludeList[i];
                }
            
            exclude = excludeURL;
        } else
            exclude = "";
    }

    /**
     * Add a data block to the exclude list if is valid or not already included.
     * 
     * @param dataBlock data block | null
     */
    public final void addToExcludeList(String dataBlock) {
        
        // check if the data block is valid
        if (dataBlock != null) {
            if (FIODataBlocksEnum.isElement(dataBlock)) {
                
                // case where the data block is valid; check if not already included
                if (!exclude.contains(dataBlock)) {
                    
                    if (exclude.length() == 0)    // exclude either contains a data block or is null
                        exclude = dataBlock;
                    else
                        exclude += "," + dataBlock;
                }
                    
            }
        }
    }
    
    /**
     * Remove all data blocks from the exclude list.
     */
    public final void resetExcludeList() { exclude = ""; }
    
    // lang field
    /**
     * Get the language in which the text summaries are returned.
     * 
     * @return language
     */
    public final String getLang() { return lang; }

    /**
     * Set the language in which the text summaries are returned.<br />
     * <br />
     * If the language passed is invalid or null, the language will be set to its default value.<br />
     * The default language is the default language used by the API.
     * 
     * @param lang valid FIOLangEnum 'enum'
     */
    public final void setLang(String lang) {
    
        if (FIOLangEnum.isElement(lang))
            this.lang = lang;
        else
            resetLang();
    }
    
    /**
     * Rest the language to its default value.
     */
    public final void resetLang() { lang = FIOLangEnum.DEFAULT; }
    
    // time field
    /**
     * Get the UNIX time is used to request a forecast for an arbitrary point in time.<br />
     * <br />
     * If this option is not set, -1 is returned.
     * 
     * @return UNIX time | -1
     */
    public final int getTime() { return time; }

    /**
     * Set the time to request forecast for an arbitrary point in time.
     * 
     * @param time UNIX time
     */
    // TODO check time value
    public final void setTime(int time) { 
        
        this.time = time;
    }
    
    /**
     * Reset the time to its default value (-1).
     */
    public final void resetTime() { time = -1; }
    
    // units field
    /**
     * Get the units in which the values returned are.
     * 
     * @return units
     */
    public final String getUnits() { return this.units; }

    /**
     * Sets the units of the API response.<br />
     * <br />
     * If the units specified is invalid or unknown, it will fall back to its default value.<br />
     * The default value is the SI units.
     * 
     * @param units valid FIOUnitsEnum 'enum'
     */
    public final void setUnits(String units){
        
        if (FIOUnitsEnum.isElement(units))
            this.units = units;
        else
            resetUnits();
    }
    
    /**
     * Reset the units to their default value (AUTO).
     */
    public final void resetUnits() { units = FIOUnitsEnum.DEFAULT; }
    
    // extend field
    /**
     * Returns true if the hourly report is extended or not.
     * 
     * @return true if extented, false otherwise
     */
    public final boolean isExtended() { return extend; }

    /**
     * Defines if the hourly report should be extended or not.
     *
     * @param extend true for extending, false otherwise
     */
    public final void setExtend(boolean extend) { this.extend = extend; }
    
    /**
     * Reset the extend field to its default value (false).
     */
    public final void resetExtend() { extend = false; }
    
    // JSON data
    /**
     * Get the API response.<br />
     * <br />
     * If a response has yet to be requested or received, throws an exception.
     * 
     * @return API response
     * @throws JSONNotFoundException
     */
    public final String getResponse() throws JSONNotFoundException {
        
        if (forecast == null)
            throw new JSONNotFoundException();
        
        return forecast.toString();
    }
    
    /**
     * Get the currently data point which contains the current weather conditions at the requested location.<br />
     * <br />
     * If there is not such report, an exception is thrown.
     * 
     * @return currently data point
     * @throws JSONNotFoundException
     */
    public final FIODataPoint getCurrently() throws JSONNotFoundException {
        
        if (currently == null)
            throw new JSONNotFoundException("Report not found.");
        
        return currently;
    }

    /**
     * Get the minutely data block which contains the weather conditions minute-by-minute for the next hour.<br />
     * <br />
     * If there is not such report, an exception is thrown.
     * 
     * @return minutely data block
     * @throws JSONNotFoundException
     */
    public final FIODataBlock getMinutely() throws JSONNotFoundException {
        
        if (minutely == null)
            throw new JSONNotFoundException("Report not found.");
        
        return minutely;
    }

    /**
     * Get the hourly data block which contains the weather conditions hour-by-hour for the next two days.<br />
     * <br />
     * If there is not such report, an exception is thrown.
     * 
     * @return hourly data block
     * @throws JSONNotFoundException
     */
    public final FIODataBlock getHourly() throws JSONNotFoundException {
        
        if (hourly == null)
            throw new JSONNotFoundException("Report not found.");
        
        return hourly;
    }

    /**
     * Get the flags data which contains miscellaneous metadata such as the sources concerning this request.<br />
     * <br />
     * If there is not such report, an exception is thrown.
     * 
     * @return flags handler
     * @throws JSONNotFoundException
     */
    public final FIOFlags getFlags() throws JSONNotFoundException {
        
        if (flags == null)
            throw new JSONNotFoundException("Report not found.");
        
        return flags;
    }
    
    /**
     * Get the alerts data which, if present, contains any severe weather alerts, issued by a governmental weather authority, pertinent to the
     * requested location.<br />
     * <br />
     * If there is not such report, an exception is thrown.
     * 
     * @return alerts handler
     * @throws JSONNotFoundException
     */
    public final FIOAlerts getAlerts() { 
    
        if (alerts == null)
            throw new JSONNotFoundException("Report not found.");

        return alerts;
    }

    /**
     * Returns the daily data block which contains the weather conditions day-by-day for the next week.<br />
     * <br />
     * The first block contains the weather conditions of the day before the current day.<br />
     * If there is not such report, an exception is thrown.
     * 
     * @return daily data block
     * @throws JSONNotFoundException
     */
    public final FIODataBlock getDaily() throws JSONNotFoundException {
        
        if (daily == null)
            throw new JSONNotFoundException("Report not found.");
        
        return daily;
    }
    
    /**
     * Get the UNIX time of the last API response.<br />
     * <br />
     * If no request has been made yet or response received, an exception is thrown.<br />
     * If there is no such slot in the report, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONNotFoundException
     * @throws JSONSlotNotFoundException
     */
    public final String getResponseTime() throws JSONNotFoundException, JSONSlotNotFoundException {
        
        checkResponseJSON();
        
        try {
            return forecast.get("time").toString();
        } catch (NullPointerException exception) {
            throw new JSONSlotNotFoundException();
        }
        
    }
    
    /**
     * Get the IANA timezone name of the requested location of the API response.<br />
     * <br />
     * If no request has been made yet or response received, an exception is thrown.<br />
     * If there is no such slot in the report, an exception is thrown.
     * 
     * @return timezone
     * @throws JSONNotFoundException
     * @throws JSONSlotNotFoundException
     */
    public final String getResponseTimezone() throws JSONNotFoundException, JSONSlotNotFoundException { 
        
        checkResponseJSON();
        
        try {
            return forecast.get("timezone").toString();
        } catch (NullPointerException exception) {
            throw new JSONSlotNotFoundException();
        }
    }

    /**
     * Get the timezone offset of the API reponse in hours from GMT.<br />
     * <br />
     * If no request has been made yet or response received, an exception is thrown.<br />
     * If there is no such slot in the report, an exception is thrown.
     * 
     * @return response offset
     * @throws JSONNotFoundException
     * @throws JSONSlotNotFoundException
     */
    public final int getResponseOffset() throws JSONNotFoundException, JSONSlotNotFoundException {
        
        checkResponseJSON();
        
        try {
            return Integer.getInteger(this.forecast.get("offset").toString()).intValue();
        } catch (NullPointerException exception) {
            throw new JSONSlotNotFoundException();
        }
    }

    /**
     * Get the timezone offset of the API reponse in hours from GMT.<br />
     * <br />
     * If no request has been made yet or response received, an exception is thrown.<br />
     * If there is no such slot in the report, an exception is thrown.
     * 
     * @return response offset
     * @throws JSONNotFoundException
     * @throws JSONSlotNotFoundException
     */
    public final String getResponseOffsetString() throws JSONNotFoundException, JSONSlotNotFoundException {
        
        String string = "";
        
        if (getResponseOffset() != 0) {
            
            if (getResponseOffset() > 0)
                string = "+";
            else
                string = "-";
        }
        
        string += String.valueOf(getResponseOffset());
        
        return string;
    }
    
    
    //
    // METHODS
    //
    /**
     * Make the Forecast call to retrieve the data.
     * 
     * @return true if the call succeed, false otherwise
     */
    public boolean requestForecast() {

        String reply = requestJSON(getURL());

        if (reply == null)
            return false;

        forecast = JSONObject.fromObject(reply);

        return retrieveDataPoints(forecast);
    }
    
    /**
     * Make the Forecast call to retrive the data.<br />
     * <br />
     * WARNING: reset the time set !
     * 
     * @return true if successful, false otherwise
     */
    public boolean update() {
        
        setTime(-1);
        return requestForecast();
    }
    
    /**
     * Checks if there is any currently data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasCurrently() {
        
        return (currently == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any minutely data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasMinutely() {
        
        return (minutely == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any hourly data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasHourly() {
        
        return (hourly == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any daily data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasDaily() {
        
        return (daily == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any flags data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasFlags() {
        
        return (flags == null)?
                   false:
                   true;
    }
    
    /**
     * Checks if there is any flags data available.
     * 
     * @return true if report present, false otherwise
     */
    public final boolean hasAlerts() { return this.alerts.isEmpty(); }
    
    /**
     * Build and return the URL to use for the Forecast call.
     * 
     * @return API URL
     */
    public String getURL() {
        
        // default URL for Forecast call
        String url = APIURL + "/" + apiKey + "/" + Double.toString(latitude) + "," + Double.toString(longitude);
                
        // add optional parameters
        if (time != -1)
            url += "," + time;
        
        if (units != null)
            url += "?units=" + units;
        
        if (exclude.length() != 0)
            url += "&exclude=" + exclude;
        
        if (extend)
            url += "&extend=hourly";
          
        if (!lang.equals(FIOLangEnum.DEFAULT))
            url += "&lang=" + lang;
        
        return url;
    }
    
    
    //
    // PRIVATE HELPERS
    //
    /**
     * Check if the API key is valid.
     * 
     * @param key
     * 
     * @return
     */
    private boolean isKeyValid(String key) {
        
        if (key == null)
            return false;
        
        if (key.length() != 32)
            return false;
        
        return true;
    }
    
    /**
     * Do the request and return the JSON.
     * 
     * @param requestURL
     * 
     * @return Null if an error occured and the response otherwise.
     */
    private String requestJSON(String requestURL) {

        URL               request    = null;
        HttpURLConnection connection = null;
        
        BufferedReader reader   = null;
        String         s        = "",
                       response = "";

        try {
            
            request    = new URL(requestURL);
            connection = (HttpURLConnection)request.openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setRequestProperty("Accept-Encoding", "gzip");
            
            connection.connect();
            
            if (connection.getResponseCode() == 400) {
                
                System.out.println("Bad Responde. Maybe an invalid location was provided.");
                return null;
            } else {
                
                try {
                    
                    if (connection.getRequestProperty("Accept-Encoding") != null) {
                        
                        reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
                        while((s = reader.readLine()) != null)
                            response = s;
                    } else {
                        
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while( (s = reader.readLine()) != null )
                            response = s;
                    }
                } catch (IOException exception){
                    System.err.println("ERROR: " + exception.getMessage());
                } finally {
                    
                    if (reader != null) {
                        try {
                            reader.close();
                            reader = null;
                        } catch (IOException exception) {
                            System.err.println("ERROR: " + exception.getMessage());
                        }
                    }
                }
            }
        } catch (MalformedURLException exception) {
            System.err.println("ERROR: " + exception.getMessage());
            response = null;
        } catch (IOException exception) {
            System.err.println("ERROR: " + exception.getMessage());
            response = null;
        } finally {     
            connection.disconnect();
        }
        
        return response;
    }
    
    /**
     * Parses the forecast reports for the given coordinates with the setted options
     * Useful to use with an external http library
     * Hint: The getForecast(String http_response) could be more useful since it receives 
     *       the raw response String instead of the JsonObect. 
     * @param forecast JSONObject
     * @return true if successful
     */
    
    /**
     * Retrive the different data blocks from the source JSON.
     * 
     * @param forecast
     * 
     * @return True upon success, false otherwise.
     */
    private boolean retrieveDataPoints(JSONObject forecast) {
        
        this.forecast = forecast;
        
        // NullPointerException are ignored since some data block may be excluded from the report.
        try {
            
            Object json = forecast.get(FIODataBlocksEnum.ALERTS);
            
            if (json != null)
                alerts = new FIOAlerts(JSONArray.fromObject(json));
            else
                alerts = null;
        } catch (Exception e) {
            alerts = null;
        }
        
        try {
            
            Object json = forecast.get(FIODataBlocksEnum.CURRENTLY);
            
            if (json != null)
                currently = new FIODataPoint(JSONObject.fromObject(json));
            else
                currently = null;
        } catch (JSONException e) {
            currently = null;
        }
        
        try {
            
            Object json = forecast.get(FIODataBlocksEnum.DAILY);
            
            if (json != null)
                daily = new FIODataBlock(JSONObject.fromObject(json));
            else
                daily = null;
        } catch (JSONException e) {
            daily = null;
        }
        
        try {
            
            Object json = forecast.get(FIODataBlocksEnum.FLAGS);
            
            if (json != null)
                flags = new FIOFlags(JSONObject.fromObject(json));
            else
                flags = null;
        } catch (JSONException e) {
            flags = null;
        }
        
        try {

            Object json = forecast.get(FIODataBlocksEnum.HOURLY);
            
            if (json != null)
                hourly = new FIODataBlock(JSONObject.fromObject(json));
            else
                hourly = null;
        } catch (JSONException e) {
            hourly = null;
        }
        
        try {
            
            Object json = forecast.get(FIODataBlocksEnum.MINUTELY);
            
            if (json != null)
                minutely = new FIODataBlock(JSONObject.fromObject(json));
            else
                minutely = null;
        } catch (JSONException e) {
            minutely = null;
        }

        return true;
    }
    
    /**
     * Convenience helper for checking if there is a JSON file from the API response.
     * 
     * @param json
     * @throws JSONNotFoundException
     */
    private void checkResponseJSON() throws JSONNotFoundException {
        
        if (forecast.isEmpty())
            throw new JSONNotFoundException();
    }
    
    /**
     * Return the nearest value in the given range.
     * 
     * @param value
     * @param min
     * @param max
     * @return
     */
    private double getInRangeValue(double value, double min, double max) {
        
        if (value < min)
            value = min;
        else if (value > max)
            value = max;
        
        return value;
    }
}