package dme.forecastiolib.enums;

/**
 * Class defining the units supported by the API.
 * 
 * @author Theo FIDRY <theo.fidry@gmail.com>
 */
public final class FIOUnitsEnum {

    /**
     * Supported units system.<br />
     * <br />
     * <ul>
     *     <li>US:   Default.</li>
     *     <li>SI:   International System of Units.</li>
     *     <li>CA:   Identical to SI, except that windSpeed is in kilometers per hour.</li>
     *     <li>UK:   United Kingdom System of Units.
     *     <li>AUTO: Selects the relevant units automatically, based on geographic location.
     * </ul>
     * <br />
     * Full documentation here {@link https://developer.forecast.io/docs/v2}.
     */
    public static final String US   = "us",
                               SI   = "si",
                               CA   = "ca",
                               UK   = "uk",
                               AUTO = "auto";

    private final static String[] enums = {US, SI, CA, UK, AUTO};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param units
     * 
     * @return
     */
    public static boolean isElement(String units) {
        
        if (units == null)
            return false;
        
        String[] unitsEnums = {US, SI, CA, UK, AUTO};
        
        for (int i = 0; i < unitsEnums.length; i++) {
            
            if (units.equals(unitsEnums[i]))
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