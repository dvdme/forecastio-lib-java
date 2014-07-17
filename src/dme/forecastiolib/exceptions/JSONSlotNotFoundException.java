package dme.forecastiolib.exceptions;

import net.sf.json.JSONException;

/**
 * Custom exception for the case where a JSON slot has not been found.
 */
public class JSONSlotNotFoundException extends JSONException {

    private String detailMessage = "No such slot found in the JSON file.";
    
    public JSONSlotNotFoundException() { super(); }
    
    public JSONSlotNotFoundException(String message) {

        super(message);
        detailMessage = message;
    }

    public String getMessage() { return detailMessage; }
}
