package dme.forecastiolib.enums;

/**
 * Defines the flags objects used by the API.
 * 
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public final class FIOFlagsObjectsEnum {

    /**
     * Supported flags objects.
     */
    public static final String DARKSKY_UNAVAILABLE = "darksky-unavailable",
                               DARKSKY_STATIONS    = "darksky-stations",
                               DATAPOINT_STATIONS  = "datapoint-stations",
                               ISD_STATIONS        = "isd-stations",
                               LAMP_STATIONS       = "lamp-stations",
                               MADIS_STATIONS      = "madis-stations",
                               METAR_STATIONS      = "metar-stations",
                               METNO_LICENCE       = "metno-license",
                               SOURCES             = "sources",
                               UNITS               = "units";

    private final static String[] enums = {DARKSKY_UNAVAILABLE, DARKSKY_STATIONS, DATAPOINT_STATIONS, ISD_STATIONS, LAMP_STATIONS, MADIS_STATIONS, METAR_STATIONS, METNO_LICENCE, SOURCES, UNITS};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param  flagObject data element to be tested
     * @return true on success, false otherwise
     */
    public static boolean isElement(String flagObject) {
        
        if (flagObject == null)
            return false;
        
        for (int i = 0; i < enums.length; i++) {
            
            if (flagObject.equals(enums[i]))
                return true;
        }
        
        return false;
    }
    
    private FIOFlagsObjectsEnum() {}
    
    /**
     * Get the list of enums.
     * 
     * @return list of enums
     */
    public static String[] getEnums() { return enums; }
}
