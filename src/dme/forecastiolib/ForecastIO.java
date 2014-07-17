package dme.forecastiolib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import dme.forecastiolib.enums.FIODataBlocksEnum;
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
import dme.forecastiolib.exceptions.JSONSlotNotFoundException;
import dme.forecastiolib.exceptions.JSONNotFoundException;

/**
 * Wrapper for handling forecast calls.
 */
public class ForecastIO {

    
    //
    // FIELDS
    //

    // API call parameters
	private static final String APIURL = "https://api.forecast.io/forecast";  // base URL used for the API call
	
	private String apiKey,                 // API key
	               exclude  = "",          // used to exclude uneeded reports to reduce latency and saving cache space
	               lang,                   // defines the language in which text summaries are returned
            	   time,                   // optional, used for requesting a arbitrary point in time
            	   units    = "auto";      // defines the units of the API response
	             
	private double latitude,               // geographic latitude coordinate of a location in decimal degrees
                   longitude;              // geographic longitude coordinate of a location in decimal degrees
	
	private boolean extend = false;        // request hourly data for the next seven days rather than the next two when set to true

	
	// Data blocks
	private FIODataPoint currently;    // contains the current weather conditions at the requested location
	
	private FIODataBlock daily,   // contains the weather conditions day-by-day for the next week
	                     hourly,   // contains the weather conditions hour-by-hour for the next two days
	                     minutely;   // contains the weather conditions minute-by-minute for the next hour
	
	private FIOFlags flags;    // contains miscellaneous metadata concerning this request
	
	private JSONObject  
	                   forecast  = null,               // contains all the data
	                   
	                   alerts    = new JSONObject();   // array of alert objects, which, if present, contains any severe weather alerts, issued by a
	                                                   // governmental weather authority, pertinent to the requested location

	
	//
    // CONSTRUCTORS
    //
    /**
     * Instanciate a ForecastIO wrapper with default options.
     */
    public ForecastIO(String key, double latitude, double longitude) throws IllegalArgumentException { this(key, latitude, longitude, null, null, null, false); }
    
    /**
     * Convenience for the full constructor with the current time.
     * 
     * @throws IllegalArgumentException Thrown when passed a non valid API key.
     */
    public ForecastIO(String key, double latitude, double longitude, String lang, String units, String[] exclude, boolean extend) throws IllegalArgumentException {

        this(key, latitude, longitude, lang, null, units, exclude, extend);
    }
    
    /**
     * Instanciate a ForecastIO wrapper.<br />
     * <br />
     * Invalid parameters are disregarded for default values.
     * 
     * @param key       API key.
     * @param latitude  Geographic latitude coordinate of a location in decimal degrees.
     * @param longitude Geographic longitude coordinate of a location in decimal degrees.
     * @param lang      Defines the language in which text summaries are returned.
     * @param time      Define the time for requesting a arbitrary point.
     * @param units     Defines the units of the API response.
     * @param exclude   Used to exclude uneeded reports to reduce latency and saving cache space.
     * @param extend    Request hourly data for the next seven days rather than the next two when set to true.
     * 
     * @throws IllegalArgumentException Thrown when passed a non valid API key.
     */
    public ForecastIO(String key, double latitude, double longitude, String lang, Date time, String units, String[] exclude, boolean extend) throws IllegalArgumentException {

        // check the API key
        if (!isKeyValid(key))
            throw new IllegalArgumentException("Invalid API key.");
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
     * @return The latitude in decimal degrees.
     */
    public final double getLatitude() { return latitude; }

    /**
     * @param latitude The latitude to set in decimal degrees.
     */
    public final void setLatitude(double latitude) { this.latitude = latitude; }
   
    /**
     * @return The longitude in decimal degrees.
     */
    public final double getLongitude() { return longitude; }

    /**
     * @param longitude The longitude to set in decimal degrees.
     */
    public final void setLongitude(double longitude) { this.longitude = longitude;}
    
	// exclude field
	 /**
	  * Return the exclude string that will be used for the Forecast call.<br />
	  * <br />
	  * Empty string means that no report is excluded.
	  * 
	  * @return
	  */
	public final String getExcludeList() { return exclude; }
   
    /**
     * Used to exclude uneeded reports to reduce latency and saving cache space.<br />
     * <br />
     * Invalid data blocks are not considered. A valid data block is a FIODataBlockEnum 'enum'.<br />
     * If there is not at least one valid data block or if the parameter is null, it will be the same as specifying to not exclude reports at all.
     * 
     * @param Array of data blocks.
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
     * @param dataBlock
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
     * @return The lang in which the text summaries are returned.
     */
    public final String getLang() { return lang; }

    /**
     * Set the lang in which the text summaries are returned.<br />
     * <br />
     * If the lang passed is invalid or null, the lang will be set to its default value.
     * 
     * @param lang Valid FIOLangEnum 'enum'.
     */
    public final void setLang(String lang) {
    
        this.lang = (FIOLangEnum.isElement(lang))?
                        lang:
                        null;
    }
    
    // time field
    /**
     * Returns the time is used to request a forecast for an arbitrary point in time. Since it's an optional value, null value may be returned.
     * 
     * @return
     */
    public final String getTime() { return time; }

    /**
     * Set the time to request forecast for an arbitrary point in time.<br />
     * <br />
     * If the time specified is null or invalid, the current value will remain unchanged.
     * 
     * @param
     */
    public final void setTime(Date time) { 
        
        if (time != null)
            this.time = Long.toString(time.getTime());
    }
    
    /**
     * Reset the time to its default value (null).
     */
    public final void resetTime() { time = null; }
    
    // units field
    /**
     * Returns the units of the API response.
     * 
     * @return
     */
    public final String getUnits() { return this.units; }

    /**
     * Sets the units of the API response.<br />
     * <br />
     * If the units specified is invalid or unkown, the current value will remain unchanged.
     * 
     * @param units Valid FIOUnitsEnum 'enum'.
     */
    public final void setUnits(String units){
        
        if (FIOUnitsEnum.isElement(units))
            this.units = units;
    }
    
    /**
     * Reset the units to their default value (AUTO).
     */
    public final void resetUnits() { units = FIOUnitsEnum.AUTO; }
    
    // extend field
    /**
     * Returns if the hourly report should be extended in the request.
     * 
     * @return
     */
    public final boolean isExtended() { return extend; }

    /**
     * Sets if the hourly report should be extended in the request.
     *
     * @param extend
     */
    public final void setExtend(boolean extend) { this.extend = extend; }
    
    /**
     * Reset the extend field to its default value (false).
     */
    public final void resetExtend() { extend = false; }
    
    // JSON data
    /**
     * Get the API response.<br />
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet.
     */
    public final JSONObject getAPIResponse() throws JSONNotFoundException {
        
        if (forecast == null)
            throw new JSONNotFoundException();
        
        return forecast;
    }
    
    /**
     * Returns the currently data points
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet or when this report has been excluded of the API response.
     */
    public final FIODataPoint getCurrently() throws JSONNotFoundException {
        
        if (currently == null)
            throw new JSONNotFoundException("Report found.");
        
        return currently;
    }

    /**
     * Returns the minutely data block.
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet or when this report has been excluded of the API response.
     */
    public final FIODataBlock getMinutely() throws JSONNotFoundException {
        
        if (minutely == null)
            throw new JSONNotFoundException("Report found.");
        
        return minutely;
    }

    /**
     * Returns the hourly data block.
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet or when this report has been excluded of the API response.
     */
    public final FIODataBlock getHourly() throws JSONNotFoundException {
        
        if (hourly == null)
            throw new JSONNotFoundException("Report found.");
        
        return hourly;
    }

    /**
     * Returns the flags data.
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet or when this report has been excluded of the API response.
     */
    public final FIOFlags getFlags() throws JSONNotFoundException {
        
        if (flags == null)
            throw new JSONNotFoundException("Report found.");
        
        return flags;
    }
    
    /**
     * Returns the alerts data.
     * 
     * @return
     */
    public final JSONObject getAlerts() { return alerts; }

    /**
     * Returns the daily data block.
     * 
     * @return
     * @throws JSONNotFoundException Occurs when no response has been received yet or when this report has been excluded of the API response.
     */
    public final FIODataBlock getDaily() throws JSONNotFoundException {
        
        if (daily == null)
            throw new JSONNotFoundException("Report found.");
        
        return daily;
    }
    
    /**
     * Returns the UNIX time of the API response.
     * 
     * @return
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
     * Returns the IANA timezone name of the requested location of the API response.
     * 
     * @return
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
     * Returns the timezone offset of the API reponse in hours from GMT.
     * 
     * @return
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
     * Returns the timezone offset of the API reponse in hours from GMT.
     * 
     * @return
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
     * Make the Forecast call to retrive the data.
     * 
     * @return
     */
    public boolean requestForecast() {

        String reply = requestJSON(getRequestURL());

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
     * @return
     */
    public boolean update() {
        
        setTime(null);
        
        return requestForecast();
    }
    
    /**
     * Checks if there is any currently data available.
     * 
     * @return
     */
    public final boolean hasCurrently() {
        
        return (currently == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any minutely data available.
     * 
     * @return
     */
    public final boolean hasMinutely() {
        
        return (minutely == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any hourly data available.
     * 
     * @return
     */
    public final boolean hasHourly() {
        
        return (hourly == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any daily data available.
     * 
     * @return
     */
    public final boolean hasDaily() {
        
        return (daily == null)?
                   false:
                   true;
    }

    /**
     * Checks if there is any flags data available.
     * 
     * @return
     */
    public final boolean hasFlags() {
        
        return (flags == null)?
                   false:
                   true;
    }
    
    /**
     * Checks if there is any flags data available.
     * 
     * @return
     */
    public final boolean hasAlerts() { return this.alerts.isEmpty(); }
    
    
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
     * Build and return the URL to use for the Forecast call.
     * 
     * @return
     */
    private String getRequestURL() {
        
        // default URL for Forecast call
        String url = APIURL + "/" + apiKey + "/" + Double.toString(latitude) + "," + Double.toString(longitude);
        
        System.out.println("getRequestURL base URL: " + url);
        
        // add optional parameters
        if (time != null)
            url += "," + time;
        
        if (units != null)
            url += "?units=" + units;
        
        if (exclude.length() != 0)
            url += "&exclude=" + exclude;
        
        if (extend)
            url += "&extend=hourly";
        
        System.out.println("getRequestURL URL: " + url);
        
        return url;
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
            alerts = forecast.getJSONObject("alerts");
        } catch (NullPointerException e) {}
        
        try {
            currently = new FIODataPoint(forecast.getJSONObject(FIODataBlocksEnum.CURRENTLY));
        } catch (JSONException e) {
            currently = null;
        }
        
        try {
            daily = new FIODataBlock(forecast.getJSONObject(FIODataBlocksEnum.DAILY));
        } catch (JSONException e) {
            daily = null;
        }
        
        try {
            flags = new FIOFlags(forecast.getJSONObject(FIODataBlocksEnum.FLAGS));
        } catch (JSONException e) {
            flags = null;
        }
        
        try {
            hourly = new FIODataBlock(forecast.getJSONObject(FIODataBlocksEnum.HOURLY));
        } catch (JSONException e) {
            hourly = null;
        }
        
        try {
            minutely = new FIODataBlock(forecast.getJSONObject(FIODataBlocksEnum.MINUTELY));
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
}