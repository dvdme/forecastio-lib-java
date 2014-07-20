package dme.forecastiolib.enums;

/**
 * Defines the icon names used by the API.
 * 
 * @author Theo FIDRY <theo.fidry@gmail.com>
 */
public final class FIOIconsEnum {

    /**
     * Supported icons.
     */
    public static final String CLEAR_DAY           = "clear-day",
                               CLEAR_NIGHT         = "clear-night",
                               RAIN                = "rain",
                               SNOW                = "snow",
                               SLEET               = "sleet",
                               WIND                = "wind",
                               FOG                 = "fog",
                               CLOUDY              = "cloudy",
                               PARTLY_CLOUDY_DAY   = "partly-cloudy-day",
                               PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";

    private final static String[] enums = {CLEAR_DAY, CLEAR_NIGHT, RAIN, SNOW, SLEET, WIND, FOG, CLOUDY, PARTLY_CLOUDY_DAY, PARTLY_CLOUDY_NIGHT};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param iconName
     * @return
     */
    public static boolean isElement(String iconName) {
        
        if (iconName == null)
            return false;
        
        String[] iconsList = {CLEAR_DAY, CLEAR_NIGHT, RAIN, SNOW, SLEET, WIND, FOG, CLOUDY, PARTLY_CLOUDY_DAY, PARTLY_CLOUDY_NIGHT};
        
        for (int i = 0; i < iconsList.length; i++) {
            
            if (iconName.equals(iconsList[i]))
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