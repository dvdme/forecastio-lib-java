/**
 * This class represents a Forecast.io Data Point.
 * It contains all the available fields in a forecast.
 * 
 * @author David Ervideira

 */

package com.github.dvdme.ForecastIOLib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import com.eclipsesource.json.JsonObject;

public class FIODataPoint {

	HashMap<String, Object> datapoint;
	String timezone;

	public FIODataPoint(){
		datapoint = new HashMap<String, Object>();
		timezone = "GMT";
	}

	public FIODataPoint(JsonObject dp){
		datapoint = new HashMap<String, Object>();
		timezone = "GMT";
		update(dp);	
	}

	/**
	 * Updates the data point data
	 * @param dp JsonObect with the data
	 */
	void update(JsonObject dp){
		for(int i = 0; i < dp.names().size(); i++){
			datapoint.put(dp.names().get(i), dp.get(dp.names().get(i)));
		}
	}

	/**
	 * Returns a String with all the Forecast.io field's available
	 * in this data point.
	 * 
	 * @return the String with the field's names.
	 * @see String
	 */
	public String getFields(){
		return datapoint.keySet().toString();
	}

	/**
	 * Returns a String array with all the Forecast.io fields available
	 * in this data point. It can be usefull to iterate over all
	 * available fields in a data point.
	 * 
	 * @return the String array with the field's names.
	 */
	public String [] getFieldsArray(){
		Object [] obj = datapoint.keySet().toArray();
		String [] out = new String[obj.length];
		for(int i=0; i<obj.length; i++)
			out[i] = String.valueOf(obj[i]);
		return out;
	}

	/**
	 * Allows to set the timezone.
	 * If none is set, default is GMT.
	 * @param tz String with the timezone such as "GMT"
	 */
	public void setTimezone(String tz){
		this.timezone = tz;
	}

	/**
	 * Allows to get the timezone in use
	 * @return String with the timezone
	 */
	public String getTimezone(){
		return this.timezone;
	}

	/* Gets  */
	/**
	 * Return the data point field with the corresponding key
	 * 
	 * @param key name of the field in the data point
	 * @return the field value
	 */
	public String getByKey(String key){
		String out = "";
		if(key.equals("time"))
			return time();
		if(key.contains("Time")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			out = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get(key))) * 1000 );
		}
		else 
			out = String.valueOf( datapoint.get(key) );
		return out;
	}

	/**
	 * Returns the time at which this data point occurs.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [dd-MM-yyyy HH:mm:ss].  Returns "no data" if the field is not defined.
	 */
	public String time(){	
		DateFormat dfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dfm.setTimeZone(TimeZone.getTimeZone(timezone));
		Long t = Long.parseLong( String.valueOf(this.datapoint.get("time")) );
		String time = dfm.format( t * 1000 );
		return time;
	}
	
	/**
	 * Returns the UNIX timestamp at which this data point occurs (seconds since UNIX epoch).
	 * e.g. 1433361600 = Wed, 03 Jun 2015 20:00:00 GMT
	 * @return A machine-readable UNIX timestamp as a long. 
	 */
	public long timestamp() {
		return Long.parseLong( String.valueOf(this.datapoint.get("time")) );
	}

	/**
	 * Returns a human-readable text summary of the data point.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the summary text. Returns "no data" if the field is not defined.
	 */
	public String summary(){
		if(this.datapoint.containsKey("summary"))
			return asString(this.datapoint.get("summary"));
		else
			return "no data";
	}

	/**
	 * Returns a machine-readable text summary of the data point, 
	 * suitable for selecting an icon for display.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the icon text.  Returns "no data" if the field is not defined.
	 */
	public String icon(){
		if(this.datapoint.containsKey("icon"))
			return asString(this.datapoint.get("icon"));
		else
			return "no data";
	}

	/**
	 * Returns the sunset time for the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss].  Returns "no data" if the field is not defined.
	 */
	public String sunriseTime(){
		if(this.datapoint.containsKey("sunriseTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("sunriseTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * Returns the sunrise time for the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]. Returns "no data" if the field is not defined.
	 */
	public String sunsetTime(){
		if(this.datapoint.containsKey("sunsetTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("sunsetTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * A numerical value representing the average expected intensity.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the precipitation intensity. Returns -1 if the field is not defined.
	 */
	public Double precipIntensity(){
		if(this.datapoint.containsKey("precipIntensity"))
			return asDouble(this.datapoint.get("precipIntensity"));
		else
			return -1d;
	}

	/**
	 * A numerical values representing the maximum 
	 * expected intensity of precipitation.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the maximum precipitation intensity.  Returns -1 if the field is not defined.
	 */
	public Double precipIntensityMax(){
		if(this.datapoint.containsKey("precipIntensityMax"))
			return asDouble(this.datapoint.get("precipIntensityMax"));
		else
			return -1d;
	}
	/**
	 * A numerical values representing the time at which the maximum 
	 * expected intensity of precipitation occurs.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns "no data" if the field is not defined.
	 */
	public String precipIntensityMaxTime(){
		if(this.datapoint.containsKey("precipIntensityMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("precipIntensityMaxTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * A numerical value between 0 and 1 (inclusive) representing 
	 * the probability of precipitation occuring at the given time.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the probabily. Returns -1 if the field is not defined.
	 */
	public Double precipProbability(){
		if(this.datapoint.containsKey("precipProbability"))
			return asDouble(this.datapoint.get("precipProbability"));
		else
			return -1d;
	}

	/**
	 * A string representing the type of precipitation occurring at the given time. 
	 * If defined, this property will have one of the following values: rain, snow, 
	 * sleet or hail.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A string with the type of precipitation Returns "no data" if the field is not defined.
	 */
	public String precipType(){
		if(this.datapoint.containsKey("precipType"))
			return asString(this.datapoint.get("precipType"));
		else
			return "no data";
	} 

	/**
	 * The amount of snow fall accumulation expected to occur on the given day.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the expected value. Returns -1 if the field is not defined.
	 */
	public Double precipAccumulation(){
		if(this.datapoint.containsKey("precipAccumulation"))
			return asDouble(this.datapoint.get("precipAccumulation"));
		else
			return -1d;
	}

	/**
	 * The temperature at the given time using the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the temperature. Returns null if the field is not defined.
	 */
	public Double temperature(){
		if(this.datapoint.containsKey("temperature"))
			return asDouble(this.datapoint.get("temperature"));
		else
			return null;
	}
	
	/**
	 * The temperature error at the given time using the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the temperature. Returns null if the field is not defined.
	 */
	public Double temperatureError(){
		if(this.datapoint.containsKey("temperatureError"))
			return asDouble(this.datapoint.get("temperatureError"));
		else
			return null;
	}

	/**
	 * The minimum temperature expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the minimum temperature. Returns null if the field is not defined.
	 */
	public Double temperatureMin(){
		if(this.datapoint.containsKey("temperatureMin"))
			return asDouble(this.datapoint.get("temperatureMin"));
		else
			return null;
	}
	
	/**
	 * The minimum temperature error expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the minimum temperature. Returns null if the field is not defined.
	 */
	public Double temperatureMinError(){
		if(this.datapoint.containsKey("temperatureMinError"))
			return asDouble(this.datapoint.get("temperatureMinError"));
		else
			return null;
	}

	/**
	 * The time at with the minimum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns "no data" if the field is not defined.
	 */
	public String temperatureMinTime(){
		if(this.datapoint.containsKey("temperatureMinTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("temperatureMinTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * The maximum temperature expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the maximum temperature. Returns null if the field is not defined.
	 */
	public Double temperatureMax(){
		if(this.datapoint.containsKey("temperatureMax"))
			return asDouble(this.datapoint.get("temperatureMax"));
		else
			return null;
	}
	
	/**
	 * The maximum temperature error expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the maximum temperature. Returns null if the field is not defined.
	 */
	public Double temperatureMaxError(){
		if(this.datapoint.containsKey("temperatureMaxError"))
			return asDouble(this.datapoint.get("temperatureMaxError"));
		else
			return null;
	}

	/**
	 * The time at which the maximum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns "no data" if the field is not defined.
	 */
	public String temperatureMaxTime(){
		if(this.datapoint.containsKey("temperatureMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("temperatureMaxTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}
	
	/**
	 * The apparent temperature at the given time using the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the apparentTemperature. Returns null if the field is not defined.
	 */
	public Double apparentTemperature(){
		if(this.datapoint.containsKey("apparentTemperature"))
			return asDouble(this.datapoint.get("apparentTemperature"));
		else
			return null;
	}

	/**
	 * The minimum apparent temperature expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the minimum apparent temperature. Returns null if the field is not defined.
	 */
	public Double apparentTemperatureMin(){
		if(this.datapoint.containsKey("apparentTemperatureMin"))
			return asDouble(this.datapoint.get("apparentTemperatureMin"));
		else
			return null;
	}

	/**
	 * The time at with the minimum apparent temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns "no data" if the field is not defined.
	 */
	public String apparentTemperatureMinTime(){
		if(this.datapoint.containsKey("temperatureMinTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("apparentTemperatureMinTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * The maximum apparent temperature expected for a given day the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the apparent maximum temperature. Returns null if the field is not defined.
	 */
	public Double apparentTemperatureMax(){
		if(this.datapoint.containsKey("apparentTemperatureMax"))
			return asDouble(this.datapoint.get("apparentTemperatureMax"));
		else
			return null;
	}

	/**
	 * The time at which the apparent maximum temperature is expected to occur.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return An human-readable time string formated as [HH:mm:ss]  Returns "no data" if the field is not defined.
	 */
	public String apparentTemperatureMaxTime(){
		if(this.datapoint.containsKey("apparentTemperatureMaxTime")){
			DateFormat dfm = new SimpleDateFormat("HH:mm:ss");
			dfm.setTimeZone(TimeZone.getTimeZone(timezone));
			String time = dfm.format( Long.parseLong(String.valueOf(this.datapoint.get("apparentTemperatureMaxTime"))) * 1000 );
			return time;
		}
		else
			return "no data";
	}

	/**
	 * The dew point for a given time with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the dew point. Returns -1 if the field is not defined.
	 */
	public Double dewPoint(){
		if(this.datapoint.containsKey("dewPoint"))
			return asDouble(this.datapoint.get("dewPoint"));
		else
			return -1d;
	}
	
	/**
	 * The dew point error for a given time with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the dew point. Returns -1 if the field is not defined.
	 */
	public Double dewPointError(){
		if(this.datapoint.containsKey("dewPointError"))
			return asDouble(this.datapoint.get("dewPointError"));
		else
			return -1d;
	}

	/**
	 * The wind speed for a given time with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the wind speed. Returns -1 if the field is not defined.
	 */
	public Double windSpeed(){
		if(this.datapoint.containsKey("windSpeed"))
			return asDouble(this.datapoint.get("windSpeed"));
		else
			return -1d;
	}
	
	/**
	 * The wind speed error for a given time with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the wind speed. Returns -1 if the field is not defined.
	 */
	public Double windSpeedError(){
		if(this.datapoint.containsKey("windSpeedError"))
			return asDouble(this.datapoint.get("windSpeedError"));
		else
			return -1d;
	}

	/**
	 * The wind direction for a given time in degrees.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the wind direction. Returns -1 if the field is not defined.
	 */
	public Double windBearing(){
		if(this.datapoint.containsKey("windBearing"))
			return asDouble(this.datapoint.get("windBearing"));
		else
			return -1d;
	}
	
	/**
	 * The wind direction error for a given time in degrees.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the wind direction. Returns -1 if the field is not defined.
	 */
	public Double windBearingError(){
		if(this.datapoint.containsKey("windBearingError"))
			return asDouble(this.datapoint.get("windBearingError"));
		else
			return -1d;
	}

	/**
	 * A numerical value between 0 and 1 (inclusive) representing 
	 * the percentage of sky occluded by clouds. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the cloud cover. Returns -1 if the field is not defined.
	 */
	public Double cloudCover(){
		if(this.datapoint.containsKey("cloudCover"))
			return asDouble(this.datapoint.get("cloudCover"));
		else
			return -1d;
	}
	
	/**
	 * A numerical the percentage of sky occluded by clouds error. 
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the cloud cover. Returns -1 if the field is not defined.
	 */
	public Double cloudCoverError(){
		if(this.datapoint.containsKey("cloudCoverError"))
			return asDouble(this.datapoint.get("cloudCoverError"));
		else
			return -1d;
	}

	/**
	 * A numerical value representing the relative humidity error.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the humidity. Returns -1 if the field is not defined.
	 */
	public Double humidity(){
		if(this.datapoint.containsKey("humidity"))
			return asDouble(this.datapoint.get("humidity"));
		else
			return -1d;
	}
	
	/**
	 * A numerical value between 0 and 1 (inclusive) 
	 * representing the relative humidity.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the humidity. Returns -1 if the field is not defined.
	 */
	public Double humidityError(){
		if(this.datapoint.containsKey("humidityError"))
			return asDouble(this.datapoint.get("humidityError"));
		else
			return -1d;
	}

	/**
	 * A numerical value with the sea level pressure with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the pressure. Returns -1 if the field is not defined.
	 */
	public Double pressure(){
		if(this.datapoint.containsKey("pressure"))
			return asDouble(this.datapoint.get("pressure"));
		else
			return -1d;
	}
	
	/**
	 * A numerical value with the sea level pressure error with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the pressure. Returns -1 if the field is not defined.
	 */
	public Double pressureError(){
		if(this.datapoint.containsKey("pressureError"))
			return asDouble(this.datapoint.get("pressureError"));
		else
			return -1d;
	}

	/**
	 * A numerical value with the average visibility with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the visibility. Returns null if the field is not defined.
	 */
	public Double visibility(){
		if(this.datapoint.containsKey("visibility"))
			return asDouble(this.datapoint.get("visibility"));
		else
			return null;
	}
	
	/**
	 * A numerical value with the visibility error with the units defined
	 * in the class ForecastIO.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the visibility. Returns null if the field is not defined.
	 */
	public Double visibilityError(){
		if(this.datapoint.containsKey("visibilityError"))
			return asDouble(this.datapoint.get("visibilityError"));
		else
			return null;
	}

	/**
	 * A numerical value  representing the columnar density of total atmospheric
	 * ozone at the given time in Dobson units.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	public Double ozone(){

		if(this.datapoint.containsKey("ozone"))
			return asDouble(this.datapoint.get("ozone"));
		else
			return -1d;
	}
	
	/**
	 * Nesrest Storm Bearing
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	public Double nearestStormBearing(){

		if(this.datapoint.containsKey("nearestStormBearing"))
			return asDouble(this.datapoint.get("nearestStormBearing"));
		else
			return -1d;
	}
	
	/**
	 * Nesrest Storm Distance
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number with the ozone. Returns -1 if the field is not defined.
	 */
	public Double nearestStormDistance(){

		if(this.datapoint.containsKey("nearestStormDistance"))
			return asDouble(this.datapoint.get("nearestStormDistance"));
		else
			return -1d;
	}

	/**
	 * Percentage complete of the current lunar month
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A Double number representing the fractional part of the lunation number of the given day.
	 * Returns null if the field is not defined.
	 */
	public Double moonPhase() {
		if (this.datapoint.containsKey("moonPhase"))
			return asDouble(this.datapoint.get("moonPhase"));
		else
			return null;
	}

	private Double asDouble(Object obj){
		return Double.parseDouble( String.valueOf(obj) );
	}

	private String asString(Object obj){
		return String.valueOf(obj);
	}

}//public class - end

