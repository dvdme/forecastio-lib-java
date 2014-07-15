package dme.forecastiolib.exceptions;

import net.sf.json.JSONException;

/**
 * Custom exception for the case where the JSON file from the API response has not been found.
 */
public class ResponseJSONNotFoundException extends JSONException {

    private String detailMessage = "JSON from API response not found.";
    
    public String getMessage() { return detailMessage; }
}
