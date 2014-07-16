package dme.forecastiolib.enums;


/**
 * Defines the languages supported by the API.
 * 
 * @author Theo FIDRY <theo.fidry@gmail.com>
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
                               TETUM   = "test";

    /**
     * Test if the parameter passed is a valid 'enum'.
     * 
     * @param lang
     * 
     * @return
     */
    public static boolean isElement(String lang) {
        
        if (lang == null)
            return false;
        
        String[] langs = {ENGLISH, FRENCH, GERMAN, DUTCH, SPANISH, TETUM};
        
        for (int i = 0; i < langs.length; i++) {
            
            if (lang.equals(langs[i]))
                return true;
        }
        
        return false;
    }
    
    private FIOLangEnum() {}
}