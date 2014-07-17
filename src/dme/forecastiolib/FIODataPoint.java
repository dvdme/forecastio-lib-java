package dme.forecastiolib;

import java.util.HashMap;

import dme.forecastiolib.enums.FIODataPointPropertiesEnum;
import dme.forecastiolib.exceptions.JSONSlotNotFoundException;
import net.sf.json.JSONObject;

/**
 * This class represents a Forecast.io Data Point.<br />
 * It contains all the available fields in a forecast.
 * 
 * @author David Ervideira
 * @author Theo FIDRY <theo.fidry@gmail.com>
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
     * Instanciate a data point with the values contained in the JSON passed.
     * 
     * @param data
     */
    public FIODataPoint(JSONObject data) { update(data); }


    //
    // DATA MANIPULATORS
    //
    /**
     * Get the UNIX time at wich this data point occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TIME);
    }
    
    /**
     * Get a human-readable text summary of this data point.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final String getSummary() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIODataPointPropertiesEnum.SUMMARY);
    }
    
    /**
     * Get a machine-readable text summary of this data point, suitable for selecting an icon for display.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final String getIcon() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIODataPointPropertiesEnum.ICON);
    }
    
    /**
     * Get the UNIX time of the last sunrise before and first sunset after the solar noon closest to local noon on the given day.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getSunriseTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.SUNRISE_TIME);
    }
    
    /**
     * Get the UNIX time of the last sunset before and first sunset after the solar noon closest to local noon on the given day.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getSunsetTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.SUNSET_TIME);
    }
    
    /**
     * Get a number representing the fractional part of the lunation number of the given day. This can be thought of as the “percentage complete” of
     * the current lunar month: a value of 0 represents a new moon, a value of 0.25 represents a first quarter moon, a value of 0.5 represents a full
     * moon, and a value of 0.75 represents a last quarter moon
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getMoonPhase() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.MOONPHASE);
    }
    
    /**
     * Get numerical value representing the distance to the nearest storm in miles. (This value is very approximate and should not be used in
     * scenarios requiring accurate results. In particular, a storm distance of zero doesn’t necessarily refer to a storm at the requested location,
     * but rather a storm in the vicinity of that location.)
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getNearestStormDistance () throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.NEAREST_STORM_DISTANCE);
    }
    
    /**
     * Get a numerical value representing the direction of the nearest storm in degrees, with true north at 0° and progressing clockwise.
     * (If nearestStormDistance is zero, then this value will not be defined. The caveats that apply to nearestStormDistance also apply to this
     * value.)
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getNearestStormBearing() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING);
    }
    
    /**
     * Get a numerical value representing the average expected intensity (in inches of liquid water per hour) of precipitation occurring at the given
     * time conditional on probability (that is, assuming any precipitation occurs at all). A very rough guide is that a value of 0 in./hr.
     * corresponds to no precipitation, 0.002 in./hr. corresponds to very light precipitation, 0.017 in./hr. corresponds to light precipitation,
     * 0.1 in./hr. corresponds to moderate precipitation, and 0.4 in./hr. corresponds to heavy precipitation.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getPrecipitationIntensity() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY);
    }
    
    /**
     * Get the numerical value representing the maximum expected intensity of precipitation on the given day in inches of liquid water per hour.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getPrecipitationIntensityMax() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX);
    }

    /**
     * Get the UNIX time at which the maximum expected intensity of precipitation on the given day occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getPrecipitationIntensityMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX_TIME);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the probability of precipitation occuring at the given time.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getPrecipitationProbability() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRECIPITATION_PROBABILITY);
    }
    
    /**
     * Get the amount of snowfall accumulation expected to occur on the given day. (If no accumulation is expected, this property will not be defined.)
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getPrecipitationAccumulation() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRECIPITATION_ACCUMULATION);
    }
    
    /**
     * Get a numerical value representing the temperature at the given time in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getTemperature() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.TEMPERATURE);
    }
    
    /**
     * Get a numerical values representing maximum temperature on the given day in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getTemperatureMax() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.TEMPERATURE_MAX);
    }
    
    /**
     * Get the UNIX time at wich the maximum temperature on the given day occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getTemperatureMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TEMPERATURE_MAX_TIME);
    }
    
    /**
     * Get a numerical values representing maximum temperature on the given day in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getTemperatureMin() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.TEMPERATURE_MIN);
    }
    
    /**
     * Get the UNIX time at wich the maximum temperature on the given day occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getTemperatureMinTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.TEMPERATURE_MIN_TIME);
    }
    
    /**
     * Get a numerical value representing the apparent temperature ("feels like") at the given time in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getApparentTemperature() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE);
    }
    
    /**
     * Get a numerical values representing maximum apparent temperature on the given day in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getApparentTemperatureMax() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX);
    }
    
    /**
     * Get the UNIX time at wich the maximum apparent temperature on the given day occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getApparentTemperatureMaxTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX_TIME);
    }
    
    /**
     * Get a numerical values representing maximum apparent temperature on the given day in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getApparentTemperatureMin() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN);
    }
    
    /**
     * Get the UNIX time at wich the maximum apparent temperature on the given day occurs.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final int getApparentTemperatureMinTime() throws JSONSlotNotFoundException {
        
        return returnIntProperty(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN_TIME);
    }
    
    /**
     * Get a numerical value representing the dew point at the given time in degrees Fahrenheit.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getDewPoint() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.DEW_POINT);
    }
    
    /**
     * Get a numerical value representing the wind speed in miles per hour.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getWindSpeed() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.WIND_SPEED);
    }
    
    /**
     * Get a numerical value representing the direction that the wind is coming from in degrees, with true north at 0° and progressing clockwise.
     * (If windSpeed is zero, then this value will not be defined.)
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getWindBearing() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.WIND_BEARING);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the percentage of sky occluded by clouds. A value of 0 corresponds to clear sky,
     * 0.4 to scattered clouds, 0.75 to broken cloud cover, and 1 to completely overcast skies.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getCloudCover() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.CLOUD_COVER);
    }
    
    /**
     * Get a numerical value between 0 and 1 (inclusive) representing the relative humidity.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getHumidity() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.HUMIDITY);
    }
    
    /**
     * Get a numerical value representing the sea-level air pressure in millibars.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getPressure() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.PRESSURE);
    }
    
    /**
     * Get a numerical value representing the average visibility in miles, capped at 10 miles.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getVisibility() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.VISIBILITY);
    }
    
    /**
     * Get a numerical value representing the columnar density of total atmospheric ozone at the given time in Dobson units.
     * 
     * @return
     * @throws JSONSlotNotFoundException
     */
    public final long getOzone() throws JSONSlotNotFoundException {
        
        return returnLongProperty(FIODataPointPropertiesEnum.OZONE);
    }
    
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Updates the data point with the given JSON file.<br />
     * <br />
     * If the data passed is null, this instance will not be updated.
     * 
     * @param data
     */
    void update(JSONObject data) {

        if (data != null)
            for (int i = 0; i < data.names().size(); i++)
                this.data.put(data.names().get(i), data.get(data.names().get(i)));
    }

    /**
     * Returns a String with all the Forecast.io field's available in this data point.
     * 
     * @return
     * @see
     */
    public String getFields() { return data.keySet().toString(); }

    /**
     * Returns an array of strings with all the keys available.
     * 
     * @return the String array with the field's names.
     */
    public String [] getKeys() {

        Object [] obj = data.keySet().toArray();
        String [] out = new String[obj.length];

        for(int i = 0; i < obj.length; i++)
            out[i] = String.valueOf(obj[i]);

        return out;
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
    
    private int returnIntProperty(String property) throws JSONSlotNotFoundException {
        
        if (data.containsKey(property))
            return ((Integer)data.get(property)).intValue();
        
        throw new JSONSlotNotFoundException();
    }
}