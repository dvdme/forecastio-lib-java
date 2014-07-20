package dme.forecastiolib.enums;

/**
 * Defines the data points properties used by the API.
 * 
 * @author Theo FIDRY <theo.fidry@gmail.com>
 */
public final class FIODataPointPropertiesEnum {

    /**
     * Supported properties.
     */
    public static final String TIME                             = "time",
                               SUMMARY                          = "summary",
                               ICON                             = "icon",
                               SUNRISE_TIME                     = "sunriseTime",
                               SUNSET_TIME                      = "sunsetTime",
                               MOONPHASE                        = "moonPhase",
                               NEAREST_STORM_DISTANCE           = "nearestStormDistance",
                               NEAREST_STORM_BEARING            = "nearestStormBearing",
                               PRECIPITATION_INTENSITY          = "precipIntensity",
                               PRECIPITATION_INTENSITY_MAX      = "precipIntensityMax",
                               PRECIPITATION_INTENSITY_MAX_TIME = "precipIntensityMaxTime",
                               PRECIPITATION_PROBABILITY        = "precipProbability",
                               PRECIPITATION_TYPE               = "precipType",
                               PRECIPITATION_ACCUMULATION       = "precipAccumulation",
                               TEMPERATURE                      = "temperature",
                               TEMPERATURE_MIN                  = "temperatureMin",
                               TEMPERATURE_MIN_TIME             = "temperatureMinTime",
                               TEMPERATURE_MAX                  = "temperatureMax",
                               TEMPERATURE_MAX_TIME             = "temperatureMaxTime",
                               APPARENT_TEMPERATURE             = "apparentTemperature",
                               APPARENT_TEMPERATURE_MIN         = "apparentTemperatureMin",
                               APPARENT_TEMPERATURE_MIN_TIME    = "apparentTemperatureMinTime",
                               APPARENT_TEMPERATURE_MAX         = "apparentTemperatureMax",
                               APPARENT_TEMPERATURE_MAX_TIME    = "apparentTemperatureMaxTime",
                               DEW_POINT                        = "dewPoint",
                               WIND_SPEED                       = "windSpeed",
                               WIND_BEARING                     = "windBearing",
                               CLOUD_COVER                      = "cloudCover",
                               HUMIDITY                         = "humidity",
                               PRESSURE                         = "pressure",
                               VISIBILITY                       = "visibility",
                               OZONE                            = "ozone";

    private final static String[] enums = {TIME, SUMMARY, ICON, SUNRISE_TIME, SUNSET_TIME, MOONPHASE, NEAREST_STORM_DISTANCE, NEAREST_STORM_BEARING, PRECIPITATION_INTENSITY, PRECIPITATION_INTENSITY_MAX, PRECIPITATION_INTENSITY_MAX_TIME, PRECIPITATION_PROBABILITY, PRECIPITATION_TYPE, PRECIPITATION_ACCUMULATION, TEMPERATURE, TEMPERATURE_MIN, TEMPERATURE_MIN_TIME, TEMPERATURE_MAX, TEMPERATURE_MAX_TIME, APPARENT_TEMPERATURE, APPARENT_TEMPERATURE_MIN, APPARENT_TEMPERATURE_MIN_TIME, APPARENT_TEMPERATURE_MAX, APPARENT_TEMPERATURE_MAX_TIME, DEW_POINT, WIND_SPEED, WIND_BEARING, CLOUD_COVER, HUMIDITY, PRESSURE, VISIBILITY, OZONE};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param property
     * @return
     */
    public static boolean isElement(String property) {
        
        if (property == null)
            return false;
        
        String[] propertyList = {TIME, SUMMARY, ICON, SUNRISE_TIME, SUNSET_TIME, MOONPHASE, NEAREST_STORM_DISTANCE, NEAREST_STORM_BEARING, PRECIPITATION_INTENSITY, PRECIPITATION_INTENSITY_MAX, PRECIPITATION_INTENSITY_MAX_TIME, PRECIPITATION_PROBABILITY, PRECIPITATION_TYPE, PRECIPITATION_ACCUMULATION, TEMPERATURE, TEMPERATURE_MIN, TEMPERATURE_MIN_TIME, TEMPERATURE_MAX, TEMPERATURE_MAX_TIME, APPARENT_TEMPERATURE, APPARENT_TEMPERATURE_MIN, APPARENT_TEMPERATURE_MIN_TIME, APPARENT_TEMPERATURE_MAX, APPARENT_TEMPERATURE_MAX_TIME, DEW_POINT, WIND_SPEED, WIND_BEARING, CLOUD_COVER, HUMIDITY, PRESSURE, VISIBILITY, OZONE};
        
        for (int i = 0; i < propertyList.length; i++) {
            
            if (property.equals(propertyList[i]))
                return true;
        }
        
        return false;
    }
    
    private FIOUnitsEnum() {}
    
    /**
     * Get the list of enums.
     * 
     * @return list of enums
     */
    public static String[] getEnums() { return enums; }
}