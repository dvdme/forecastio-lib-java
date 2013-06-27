package dme.forecastiolib;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.eclipsesource.json.JsonObject;

public class ForecastIO {


	private static final String ForecastIOURL = "https://api.forecast.io/forecast/";
	private String ForecastIOApiKey = "";
	private String unitsURL;
	private String timeURL;
	private String excludeURL;
	private boolean extend;

	public static final String UNITS_US = "us";
	public static final String UNITS_SI = "si";
	public static final String UNITS_CA = "ca";
	public static final String UNITS_UK = "uk";
	public static final String UNITS_AUTO = "auto";


	private JsonObject forecast;

	private JsonObject currently;
	private JsonObject minutely;
	private JsonObject hourly;
	private JsonObject daily;
	private JsonObject flags;

	public ForecastIO(String API_KEY){	

		if (API_KEY.length()==32) {
			this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.timeURL = null;
			this.excludeURL = null;
			this.extend = false;
			this.unitsURL = UNITS_AUTO;
		}
		else {
			System.err.println("The API Key doesn't seam to be valid.");
		}
	}//construtor - end

	public ForecastIO(String LATITUDE, String LONGITUDE, String API_KEY){	

		if (API_KEY.length()==32) {
			this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.timeURL = null;
			this.excludeURL = null;
			this.extend = false;
			this.unitsURL = UNITS_AUTO;

			getForecast(LATITUDE, LONGITUDE);
		}
		else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end

	public ForecastIO(String LATITUDE, String LONGITUDE, String UNITS, String API_KEY){	

		if (API_KEY.length()==32) {
			this.ForecastIOApiKey = API_KEY;
			this.forecast = new JsonObject();
			this.currently = new JsonObject();
			this.minutely = new JsonObject();
			this.hourly = new JsonObject();
			this.daily = new JsonObject();
			this.timeURL = null;
			this.excludeURL = null;
			this.extend = false;
			this.setUnits(UNITS);
			getForecast(LATITUDE, LONGITUDE);
		} else {
			System.err.println("The API Key doesn't seam to be valid.");
		}

	}//construtor - end

	/**
	 * Returns the latitude that is setted for the request.
	 * @return A Double number with the latitude.
	 */
	public Double getLatitude(){
		return this.forecast.get("latitude").asDouble();
	}

	/**
	 * Returns the longitude that is setted for the request.
	 * @return A Double number with the longitude.
	 */
	public Double getLongitude(){
		return this.forecast.get("longitude").asDouble();
	}

	/**
	 * Returns the timezone that is setted.
	 * @return A String with the timezone.
	 */
	public String getTimezone(){
		return this.forecast.get("timezone").asString();
	}

	/**
	 * Returns the time that is setted for the request.
	 * @return A String with the time
	 */
	public String getTime() {
		return timeURL;
	}

	/**
	 * Sets the time for the request.
	 * Format should be passed as follows:<br>
	 * [YYYY]-[MM]-[DD]T[HH]:[MM]:[SS]{+,-}[HH][MM]<br>
	 * The last {+,-}[HH][MM] is the timezone<br>
	 * Example:<br>
	 * [2013-05-06T12:00:00-0400]<br>
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param time in the described format
	 */
	public void setTime(String time) {
		this.timeURL = time;
	}

	/**
	 * Returns the excluded fields that are setted for the request.
	 * @return A String with the fields excluded
	 */
	public String getExcludeURL() {
		return excludeURL;
	}

	/**
	 * Sets the fields to be excluded in the request.<br>
	 * Format should be as follows:<br>
	 * [field1,field2,fieldN] with no spaces
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param excludeURL in the described format
	 */
	public void setExcludeURL(String excludeURL) {
		this.excludeURL = excludeURL;
	}

	/**
	 * Returns if the hourly report should be extended in the request.
	 * @return true or false
	 */
	public boolean isExtend() {
		return extend;
	}

	/**
	 * Sets if the hourly report should be extended in the request.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param extend
	 */
	public void setExtend(boolean extend) {
		this.extend = extend;
	}

	/**
	 * Returns the timezone offset in an integer
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return integer with the offset
	 */
	public int offsetValue(){
		return this.forecast.get("offset").asInt();
	}

	/**
	 * Returns the offset as a String with '+' or '-' sign. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the offset
	 */
	public String offset(){
		if(this.forecast.get("offset").asInt()<0)
			return ""+"-"+this.forecast.get("offset").asInt();
		else if(this.forecast.get("offset").asInt()>0)
			return ""+"+"+this.forecast.get("offset").asInt();
		else
			return "";
	}

	/**
	 * Returns the units that are setted in the request.
	 * @return String with the units setted
	 */
	public String getUnits(){
		return this.unitsURL;
	}

	/**
	 * Sets the units to be passed in the request. If the units are unavailable, auto is setted.<br>
	 * Units can be setted with constants like  ForecastIO.UNITS_AUTO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param units
	 */
	public void setUnits(String units){
		if(units.equals("us"))
			this.unitsURL = "us";
		else if(units.equals("si"))
			this.unitsURL = "si";
		else if(units.equals("ca"))
			this.unitsURL = "ca";
		else if(units.equals("uk"))
			this.unitsURL = "uk";
		else if(units.equals("auto"))
			this.unitsURL = "auto";
		else
			this.unitsURL = "auto";
	}

	/**
	 * Returns the currently data point
	 * @return JsonObject with the data point
	 */
	public JsonObject getCurrently(){
		return this.currently;
	}

	/**
	 * Returns the minutely data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getMinutely(){
		return this.minutely;
	}

	/**
	 * Returns the hourly data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getHourly(){
		return this.hourly;
	}

	/**
	 * Returns the flags data 
	 * @return JsonObject with the data
	 */
	public JsonObject getFlags(){
		return this.flags;
	}

	/**
	 * Returns the daily data block
	 * @return JsonObject with the data block
	 */
	public JsonObject getDaily(){
		return this.daily;
	}

	/**
	 * Checks if there is any currently data available
	 * @return true or false
	 */
	public boolean hasCurrently(){
		if(this.currently == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any minutely data available
	 * @return true or false
	 */
	public boolean hasMinutely(){
		if(this.minutely == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any hourly data available
	 * @return true or false
	 */
	public boolean hasHourly(){
		if(this.hourly == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any daily data available
	 * @return true or false
	 */
	public boolean hasDaily(){
		if(this.daily == null)
			return false;
		else
			return true;
	}

	/**
	 * Checks if there is any flags data available
	 * @return true or false
	 */
	public boolean hasFlags(){
		if(this.flags == null)
			return false;
		else
			return true;
	}

	private String urlBuilder(String LATITUDE, String LONGITUDE){
		String url = "";
		url += ForecastIOURL;
		url += ForecastIOApiKey+"/";
		url += LATITUDE+","+LONGITUDE;
		if(timeURL!=null)
			url += ","+timeURL;
		url += "?units="+unitsURL;
		if(excludeURL!=null)
			url += "&exclude="+excludeURL;
		if(extend)
			url += "&extend=hourly";

		return url;
	}

	/**
	 * Does another query to the API and updates the data
	 * This only updates the data in ForecastIO class
	 * @return True if successful
	 */
	public boolean update(){
		boolean b = getForecast(String.valueOf(getLatitude()), String.valueOf(getLongitude()));
		return b;
	}

	/**
	 * Gets the forecast reports for the given coordinates with the setted options
	 * @param LATITUDE
	 * @param LONGITUDE
	 * @return True if successful 
	 */
	public boolean getForecast(String LATITUDE, String LONGITUDE) {


		try {
			String reply = httpGET( urlBuilder(LATITUDE, LONGITUDE) );
			if(reply == null)
				return false;
			this.forecast = JsonObject.readFrom(reply);
		} catch (NullPointerException e) {
			System.err.println("Unable to connect to the API: "+e.getMessage());
			return false;
		}

		return getForecast(this.forecast);


	}//getForecast - end

	/*
	 * This change was suggested and made by github user brobzilla to add
	 * the ability to use an external http library. I found this to be a
	 * nice suggestion and improvement. However, because http libraries 
	 * usually return the raw string response, I find that it would be
	 * useful to add a getForecast method that receives the response
	 * String as parameter.   
	 */

	/**
	 * Parses the forecast reports for the given coordinates with the setted options
	 * Useful to use with an external http library
	 * @param http_response String
	 * @return
	 */

	public boolean getForecast(String http_response) {

		this.forecast = JsonObject.readFrom(http_response);
		return getForecast(this.forecast);
	}

	/**
	 * Parses the forecast reports for the given coordinates with the setted options
	 * Useful to use with an external http library
	 * Hint: The getForecast(String http_response) could be more useful since it receives 
	 *       the raw response String instead of the JsonObect. 
	 * @param forecast JsonObject
	 * @return
	 */

	public boolean getForecast(JsonObject forecast) {
		this.forecast = forecast;
		try {
			this.currently = forecast.get("currently").asObject();
		} catch (NullPointerException e) {
			this.currently = null;
		}
		try {
			this.minutely = forecast.get("minutely").asObject();
		} catch (NullPointerException e) {
			this.minutely = null;
		}
		try {
			this.hourly = forecast.get("hourly").asObject();
		} catch (NullPointerException e) {
			this.hourly = null;
		}
		try {
			this.daily = forecast.get("daily").asObject();
		} catch (NullPointerException e) {
			this.daily = null;
		}
		try {
			this.flags = forecast.get("flags").asObject();
		} catch (NullPointerException e) {
			this.flags = null;
		}

		return true;
	}//getForecast - end

	/**
	 * Returns the url that is created by internal UrlBuilder method.
	 * Useful to use with an external http library
	 *
	 * @param LATITUDE
	 * @param LONGITUDE
	 * @return url string.
	 */
	public String getUrl(String LATITUDE, String LONGITUDE) {
		return urlBuilder(LATITUDE, LONGITUDE);
	}

	private String httpGET(String requestURL) {

		//Variables
		URL request = null;
		HttpURLConnection connection = null;
		Scanner scanner = null;
		String response = "";

		try {
			request = new URL(requestURL);
			connection = (HttpURLConnection) request.openConnection();

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.connect();
			if(connection.getResponseCode() == 400){
				System.out.println("Bad Responde. Maybe an invalid location was provided.\n");
				return null;
			}
			else {
				scanner = new Scanner(request.openStream());
				response = scanner.useDelimiter("\\Z").next();
				scanner.close();
			}
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());		
			response = null;
		} finally {		
			connection.disconnect();
		}

		return response;
	}//httpGET - end

}//public class - end

