package dme.forecastiolib.enums;


/**
 * Defines the excluded data blocks supported by the API.
 * 
 * @author Theo FIDRY <theo.fidry@gmail.com>
 */
public final class FIODataBlocksEnum {

    /**
     * Supported data blocks.
     */
    public static final String ALERTS    = "alerts",
                               CURRENTLY = "currently",
                               DAILY     = "daily",
                               FLAGS     = "flags",
                               HOURLY    = "hourly",
                               MINUTELY  = "minutely";

    private final static String[] enums = {ALERTS, CURRENTLY, DAILY, FLAGS, HOURLY, MINUTELY};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param dataBlock
     * @return
     */
    public static boolean isElement(String dataBlock) {
        
        if (dataBlock == null)
            return false;
        
        String[] dataBlocks = {ALERTS, CURRENTLY, DAILY, FLAGS, HOURLY, MINUTELY};
        
        for (int i = 0; i < dataBlocks.length; i++) {
            
            if (dataBlock.equals(dataBlocks[i]))
                return true;
        }
        
        return false;
    }
    
    private FIODataBlocksEnum() {}
    
    /**
     * Get the list of enums.
     * 
     * @return list of enums
     */
    public static String[] getEnums() { return enums; }
}