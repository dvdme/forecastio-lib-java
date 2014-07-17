package dme.forecastiolib.exceptions;

import net.sf.json.JSONException;

/**
 * Custom exception for the case where the JSON file from the API response has not been found.
 */
public class JSONNotFoundException extends JSONException {

    private String detailMessage = "JSON not found.";
    
    public JSONNotFoundException() { super(); }

    public JSONNotFoundException(String message) {

        super(message);
        detailMessage = message;
    }
    
    public String getMessage() { return detailMessage; }
}
