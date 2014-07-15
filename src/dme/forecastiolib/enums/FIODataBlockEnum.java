package dme.forecastiolib.enums;


/**
 * Defines the excluded data blocks supported by the API.
 */
public final class FIODataBlockEnum {

    /**
     * Supported data blocks.
     */
    public static final String ALERTS    = "alerts",
                               CURRENTLY = "currently",
                               DAILY     = "daily",
                               FLAGS     = "flags",
                               HOURLY    = "hourly",
                               MINUTELY  = "minutely";

    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param dataBlock
     * 
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
    
    private FIODataBlockEnum() {}
}