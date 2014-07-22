package main.enums;


/**
 * Defines the languages supported by the API.
 * 
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public final class FIOLangEnum {

    /**
     * Supported languages.<br />
     */
    public static final String ENGLISH = "en",
                               FRENCH  = "fr",
                               GERMAN  = "ge",
                               DUTCH   = "nl",
                               SPANISH = "es",
                               TETUM   = "tet",
                               DEFAULT = "default";

    private final static String[] enums = {ENGLISH, FRENCH, GERMAN, DUTCH, SPANISH, TETUM, DEFAULT};
    
    /**
     * Test if the parameter passed is a valid 'enum'.
     *  
     * @param  lang data element to be tested
     * @return true on success, false otherwise
     */
    public static boolean isElement(String lang) {
        
        if (lang == null)
            return false;
        
        for (int i = 0; i < enums.length; i++) {
            
            if (lang.equals(enums[i]))
                return true;
        }
        
        return false;
    }
    
    private FIOLangEnum() {}
    
    /**
     * Get the list of enums.
     * 
     * @return list of enums
     */
    public static String[] getEnums() { return enums; }
}