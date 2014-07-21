package dme.forecastiolib.enums;

/**
 * Defines the data elements used by the API for an Alert.
 * 
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class FIOAlertPropertiesEnum {

    public static final String TITLE       = "title",
                               EXPIRES     = "expires",
                               DESCRIPTION = "description",
                               URI         = "uri";

    private final static String[] enums = {TITLE, EXPIRES, DESCRIPTION, URI};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param  dataBlock data element to be tested
     * @return true on success, false otherwise
     */
    public static boolean isElement(String dataBlock) {
        
        if (dataBlock == null)
            return false;
        
        String[] dataBlocks = {TITLE, EXPIRES, DESCRIPTION, URI};
        
        for (int i = 0; i < dataBlocks.length; i++) {
            
            if (dataBlock.equals(dataBlocks[i]))
                return true;
        }
        
        return false;
    }
    
    private FIOAlertPropertiesEnum() {}
    
    /**
     * Get the list of enums.
     * 
     * @return list of enums
     */
    public static String[] getEnums() { return enums; }
}