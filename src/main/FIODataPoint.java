package main;

import java.util.HashMap;

import main.enums.FIODataPointPropertiesEnum;
import main.json.JSONSlotNotFoundException;
import net.sf.json.JSONObject;

/**
 * Representation of a Data point.<br />
 * <br />
 * Helper used for handling Data points objects. More information available <a href="https://developer.forecast.io/docs/v2">here</a>.
 *
 * @author   David Ervideira
 * @author   Theo FIDRY (theo.fidry@gmail.com)
 * @version  1.0.0
 */
public class FIODataPoint {

    
    //
    // FIELDS
    //
    private HashMap data = new HashMap();

    
    //
    // CONSTRUCTORS
    //
    /**
     * Instantiate an empty data point.
     */
    public FIODataPoint() {}
    
    /**
     * Instantiate a data point with the values contained in the JSON passed.<br />
     * <br />
     * The JSON must be valid or this will be an empty alert.
     * 
     * @param data source JSON | null
     */
    public FIODataPoint(JSONObject data) { update(data); }


    //
    // DATA MANIPULATORS
    //
    /**
     * Get the UNIX time at which this data point occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TIME);
    }
    
    /**
     * Get a human-readable text summary of this data point.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return text summary
     * @throws JSONSlotNotFoundException
     */
    public final String getSummary() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIODataPointPropertiesEnum.SUMMARY);
    }
    
    /**
     * Get a machine-readable text summary of this data point, suitable for selecting an icon for display.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return icon name
     * @throws JSONSlotNotFoundException
     */
    public final String getIcon() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIODataPointPropertiesEnum.ICON);
    }
    
    /**
     * Get the UNIX time of the last sunrise before and first sunset after the solar noon closest to local noon on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getSunriseTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.SUNRISE_TIME);
    }
    
    /**
     * Get the UNIX time of the last sunset before and first sunset after the solar noon closest to local noon on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getSunsetTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.SUNSET_TIME);
    }
    
    /**
     * Get a number representing the fractional part of the lunation number of the given day. This can be thought of as the “percentage complete” of
     * the current lunar month: a value of 0 represents a new moon, a value of 0.25 represents a first quarter moon, a value of 0.5 represents a full
     * moon, and a value of 0.75 represents a last quarter moon.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return [0; 1]
     * @throws JSONSlotNotFoundException
     */
    public final double getMoonPhase() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.MOONPHASE);
    }
    
    /**
     * Get numerical value representing the distance to the nearest storm. (This value is very approximate and should not be used in scenarios
     * requiring accurate results. In particular, a storm distance of zero doesn’t necessarily refer to a storm at the requested location, but rather
     * a storm in the vicinity of that location.)<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return storm distance
     * @throws JSONSlotNotFoundException
     */
    public final int getNearestStormDistance () throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.NEAREST_STORM_DISTANCE);
    }
    
    /**
     * Get a numerical value representing the direction of the nearest storm in degrees, with true north at 0° and progressing clockwise. (If
     * nearestStormDistance is zero, then this value will not be defined. The caveats that apply to nearestStormDistance also apply to this
     * value.)<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return storm direction
     * @throws JSONSlotNotFoundException
     */
    public final long getNearestStormBearing() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING);
    }
    
    /**
     * Get a numerical value representing the average expected intensity of precipitation occurring at the given time conditional on probability
     * (that is, assuming any precipitation occurs at all). A very rough guide is that a value of 0 in./hr. corresponds to no precipitation,
     * 0.002 in./hr. corresponds to very light precipitation, 0.017 in./hr. corresponds to light precipitation, 0.1 in./hr. corresponds to moderate
     * precipitation, and 0.4 in./hr. corresponds to heavy precipitation.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return intensity of precipitation
     * @throws JSONSlotNotFoundException
     */
    public final double getPrecipitationIntensity() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY);
    }
    
    /**
     * Get the numerical value representing the maximum expected intensity of precipitation on the given day in inches of liquid water per hour.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return intensity of precipitation
     * @throws JSONSlotNotFoundException
     */
    public final double getPrecipitationIntensityMax() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX);
    }

    /**
     * Get the UNIX time at which the maximum expected intensity of precipitation on the given day occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getPrecipitationIntensityMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX_TIME);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the probability of precipitation occurring at the given time.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return probability of precipitation
     * @throws JSONSlotNotFoundException
     */
    public final double getPrecipitationProbability() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.PRECIPITATION_PROBABILITY);
    }
    
    /**
     * Get the type of precipitation occurring at the given time. If defined, this property will have one of the following values: rain, snow, sleet
     * (which applies to each of freezing rain, ice pellets, and “wintery mix”), or hail. (If precipIntensity is zero, then this property will not be
     * defined.)<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return type of precipitation
     * @throws JSONSlotNotFoundException
     */
    public final String getPrecipitationType() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIODataPointPropertiesEnum.PRECIPITATION_TYPE);
    }
    
    /**
     * Get the amount of snow fall accumulation expected to occur on the given day. (If no accumulation is expected, this property will not be 
     * defined.)<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return snow fall accumulation
     * @throws JSONSlotNotFoundException
     */
    public final long getPrecipitationAccumulation() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRECIPITATION_ACCUMULATION);
    }
    
    /**
     * Get a numerical value representing the temperature at the given time.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return temperature
     * @throws JSONSlotNotFoundException
     */
    public final long getTemperature() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.TEMPERATURE);
    }
    
    /**
     * Get a numerical values representing maximum temperature on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return maximum temperature
     * @throws JSONSlotNotFoundException
     */
    public final double getTemperatureMax() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.TEMPERATURE_MAX);
    }
    
    /**
     * Get the UNIX time at which the maximum temperature on the given day occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getTemperatureMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TEMPERATURE_MAX_TIME);
    }
    
    /**
     * Get a numerical values representing maximum temperature on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return minimum temperature
     * @throws JSONSlotNotFoundException
     */
    public final double getTemperatureMin() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.TEMPERATURE_MIN);
    }
    
    /**
     * Get the UNIX time at which the maximum temperature on the given day occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getTemperatureMinTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TEMPERATURE_MIN_TIME);
    }
    
    /**
     * Get a numerical value representing the apparent temperature ("feels like") at the given time.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return apparent temperature
     * @throws JSONSlotNotFoundException
     */
    public final double getApparentTemperature() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE);
    }
    
    /**
     * Get a numerical values representing maximum apparent temperature on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return maximum apparent temperature
     * @throws JSONSlotNotFoundException
     */
    public final double getApparentTemperatureMax() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX);
    }
    
    /**
     * Get the UNIX time at which the maximum apparent temperature on the given day occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getApparentTemperatureMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX_TIME);
    }
    
    /**
     * Get a numerical values representing maximum apparent temperature on the given day.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return maximum apparent temperature
     * @throws JSONSlotNotFoundException
     */
    public final double getApparentTemperatureMin() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN);
    }
    
    /**
     * Get the UNIX time at which the maximum apparent temperature on the given day occurs.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return UNIX time
     * @throws JSONSlotNotFoundException
     */
    public final int getApparentTemperatureMinTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN_TIME);
    }
    
    /**
     * Get a numerical value representing the dew point at the given time.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return dew point
     * @throws JSONSlotNotFoundException
     */
    public final double getDewPoint() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.DEW_POINT);
    }
    
    /**
     * Get a numerical value representing the wind speed.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return wind speed
     * @throws JSONSlotNotFoundException
     */
    public final double getWindSpeed() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.WIND_SPEED);
    }
    
    /**
     * Get a numerical value representing the direction that the wind is coming from in degrees, with true north at 0° and progressing clockwise.
     * (If windSpeed is zero, then this value will not be defined.)<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return wind direction
     * @throws JSONSlotNotFoundException
     */
    public final int getWindBearing() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.WIND_BEARING);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the percentage of sky occluded by clouds. A value of 0 corresponds to clear sky,
     * 0.4 to scattered clouds, 0.75 to broken cloud cover, and 1 to completely overcast skies.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return [0; 1]
     * @throws JSONSlotNotFoundException
     */
    public final double getCloudCover() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.CLOUD_COVER);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the relative humidity.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return relative humidity level
     * @throws JSONSlotNotFoundException
     */
    public final double getHumidity() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.HUMIDITY);
    }
    
    /**
     * Get a numerical value representing the sea-level air pressure.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return air pressure level
     * @throws JSONSlotNotFoundException
     */
    public final double getPressure() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.PRESSURE);
    }
    
    /**
     * Get a numerical value representing the average visibility.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return visibility
     * @throws JSONSlotNotFoundException
     */
    public final double getVisibility() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.VISIBILITY);
    }
    
    /**
     * Get a numerical value representing the columnar density of total atmospheric ozone at the given time in Dobson units.<br />
     * <br />
     * If no such slot has been found, an exception is thrown.
     * 
     * @return density in ozone
     * @throws JSONSlotNotFoundException
     */
    public final double getOzone() throws JSONSlotNotFoundException {
        
        return returnDoubleProperty(FIODataPointPropertiesEnum.OZONE);
    }
    
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Check if the JSON given has the required information for being processed as an alert.<br />
     * <br />
     * Data entries checked:
     * <ul>
     *  <li>title: short text summary of this alert</li>
     *  <li>expires: UNIX time at which this alert will cease to be valid</li>
     *  <li>description: detailed text description of this alert (optional)</li>
     *  <li>URI: HTTP(S) URI that contains detailed information about this alert</li>
     * </ul>
     * Non optional data are required or the JSON is considered as invalid.<br />
     * Check if the entries are convertible to strings.
     * 
     * @param  data JSON checked | null
     * @return true on success, false otherwise
     */
    public static boolean isValid(JSONObject data) {
        
        if (data == null)
            return false;
        
        if (data.isNullObject())
            return false;
        
        return true;
    }
    
    /**
     * Updates this instance with the given JSON.<br />
     * <br />
     * The JSON must be valid or this instance will be emptied.<br />
     * Unrecognized values are disregarded and not stored.
     * 
     * @param  data data JSON source | null
     * @return      true on success, false otherwise
     */
    public boolean update(JSONObject data) {
        
        if (isValid(data)) {

            for (int i = 0; i < data.names().size(); i++)
                if (FIODataPointPropertiesEnum.isElement(data.names().get(i).toString()))
                    this.data.put(data.names().get(i), data.get(data.names().get(i)));
            
            return true;
        }

        clear();
        return false;
    }
    
    /**
     * Empties this instance.
     */
    public void clear() { data.clear(); }
    
    /**
     * Check whether this alert is empty or not.<br />
     * <br />
     * A data point is considered empty when it contains no data.
     * 
     * @return true if not empty, false otherwise
     */
    public boolean isEmpty() { return data.isEmpty(); }

    /**
     * Compares this instance to another data point to determine if they are equals.<br />
     * <br />
     * Two dataPoints are considered as equals if they contains the same informations. String are case sensitive and an empty string is not equal
     * to a space. The order of the data does not matter.
     * 
     * @param  alert alert to which compare this instance | null
     * @return true if equal, false otherwise
     */
    public boolean equals(FIODataPoint dataPoint) {

        //TODO
        return true;
    }
    
    /**
     * Get the number of data elements available.
     * 
     * @return number of data elements
     */
    public int size() { return data.size(); }

    /**
     * Returns the list of the data elements available.
     * 
     * @return data elements available
     */
    public String[] getDataElements() { 
        
        Object[] elements   = data.keySet().toArray();
        String[] returnList = new String[elements.length];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = elements[i].toString();
        
        return returnList;
    }

    
    //
    // PRIVATE HELPERS
    //
    private String returnStringProperty(String property) throws JSONSlotNotFoundException {
        
        if (data.containsKey(property))
            return (String)data.get(property);
        
        throw new JSONSlotNotFoundException();
    }
    
    private long returnLongProperty(String property) throws JSONSlotNotFoundException {
        
        if (data.containsKey(property))
            return ((Long)data.get(property)).longValue();
        
        throw new JSONSlotNotFoundException();
    }
    
    private double returnDoubleProperty(String property) throws JSONSlotNotFoundException {
        
        if (data.containsKey(property))
            return ((Double)data.get(property)).doubleValue();
        
        throw new JSONSlotNotFoundException();
    }
    
    private int returnIntProperty(String property) throws JSONSlotNotFoundException {
        
        if (data.containsKey(property))
            return ((Integer)data.get(property)).intValue();
        
        throw new JSONSlotNotFoundException();
    }
}