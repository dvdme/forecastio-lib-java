package dme.forecastiolib.exceptions;

import net.sf.json.JSONException;

/**
 * Custom exception for the case where a JSON slot has not been found.
 */
public class JSONSlotNotFoundException extends JSONException {

    private String detailMessage = "No JSON such slot found.";
    
    public String getMessage() { return detailMessage; }
}
