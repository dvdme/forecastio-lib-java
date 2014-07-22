package main.alerts;

import main.enums.FIOAlertPropertiesEnum;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Representation of an Alert.<br />
 * <br />
 * Helper used for handling Alerts objects. More information available <a href="https://developer.forecast.io/docs/v2">here</a>.
 *
 * @author   David Ervideira
 * @author   Theo FIDRY (theo.fidry@gmail.com)
 * @version  1.0.0
 */
public class FIOAlert {

    
    //
    // FIELDS
    //
    // JSON containing this instance data.
    private JSONObject data = new JSONObject();
    
    
    //
    // CONSTRUCTORS
    //
    /**
     * Instantiate an empty alert (which contains no data).
     */
    public FIOAlert() {}
    
    /**
     * Instantiate this instance with the given JSON.<br />
     * <br />
     * The JSON must be valid or this will be an empty alert.
     * 
     * @param data JSON source | null
     */
    public FIOAlert(JSONObject data) { update(data); }


    //
    // PUBLIC HELPERS
    //
    /**
     * Get the short text summary of this alert.<br />
     * <br />
     * An alert may have an empty title.<br />
     * If this alert is empty, the value returned is null.
     * 
     * @return short text summary | empty | null
     */
    public final String getTitle() {
     
        if (data.isEmpty())
            return null;

        return data.getString((FIOAlertPropertiesEnum.TITLE));
    }
    
    /**
     * Get this UNIX time at which this alert will cease to be valid.<br />
     * <br />
     * If this alert is empty, the value returned is -1.
     *         
     * @return UNIX time | -1
     */
    public final long getExpires() {
        
        if (data.isEmpty())
            return -1;
        
        return Long.parseLong(data.getString(FIOAlertPropertiesEnum.EXPIRES));
    }
    
    /**
     * Get the detailed text description of this alert from the appropriate weather service.<br />
     * <br />
     * An alert may have an empty description.<br />
     * If this alert is empty, the value returned is null.
     * 
     * @return detailed text description | empty | null
     */
    public final String getDescription() {
        
        try {
            return data.getString(FIOAlertPropertiesEnum.DESCRIPTION);
        } catch (Exception exception) {
            
            if (!data.isEmpty())
                return "";
            return null;
        }
    }
    
    /**
     * Get the HTTP(S) URI that contains detailed information about this alert.<br />
     * <br />
     * An alert may have an empty URI.<br />
     * If this alert is empty, the value returned is null.
     * 
     * @return HTTP(S) URI | empty | null
     */
    public final String getURI() {
        
        if (data.isEmpty())
            return null;
        
        return data.getString(FIOAlertPropertiesEnum.URI);
    }
    
    /**
     * Check if the JSON given has the required information for being processed as an alert.<br />
     * <br />
     * Data entries checked:
     * <ul>
     *  <li>title: short text summary of this alert</li>
     *  <li>expires: UNIX time at which this alert will cease to be valid</li>
     *  <li>description: detailed text description of this alert (optional)</li>
     *  <li>URI: HTTP(S) URI that contains detailed information about this alert</li>
     * </ul>
     * Non optional data are required or the JSON is considered as invalid.<br />
     * Check if the entries are convertible to strings.
     * 
     * @param  data JSON checked | null
     * @return true on success, false otherwise
     */
    public static boolean isValid(JSONObject data) {
        
        if (data == null)
            return false;
        
        if (data.isNullObject())
            return false;
        
        try {
            data.getString(FIOAlertPropertiesEnum.TITLE);
            data.getString(FIOAlertPropertiesEnum.EXPIRES);
            data.getString(FIOAlertPropertiesEnum.URI);
            
            return true;
        } catch (JSONException exception) {
            return false;
        }
    }
    
    /**
     * Updates this instance with the given JSON.<br />
     * <br />
     * The JSON must be valid or this instance will be emptied.<br />
     * Unrecognized values are disregarded and not stored.
     * 
     * @param  data data JSON source | null
     * @return      true on success, false otherwise
     */
    public boolean update(JSONObject data) {
        
        if (isValid(data)) {

            // create a JSON with minimal values
            JSONObject optimizedJSON = new JSONObject();

            optimizedJSON.put(FIOAlertPropertiesEnum.TITLE, data.get(FIOAlertPropertiesEnum.TITLE));
            optimizedJSON.put(FIOAlertPropertiesEnum.EXPIRES, data.get(FIOAlertPropertiesEnum.EXPIRES));
            try {
                optimizedJSON.put(FIOAlertPropertiesEnum.DESCRIPTION, data.get(FIOAlertPropertiesEnum.DESCRIPTION));
            } catch (JSONException exception) {
                // ignore this exception since it's an optional field
            }
            optimizedJSON.put(FIOAlertPropertiesEnum.URI, data.get(FIOAlertPropertiesEnum.URI));

            this.data = data;
            return true;
        }
            
        clear();
        return false;
    }
    
    /**
     * Empties this instance.
     */
    public void clear() { data.clear(); }
    
    /**
     * Check whether this alert is empty or not.<br />
     * <br />
     * An alert is considered empty when it contains no data.
     * 
     * @return true if not empty, false otherwise
     */
    public boolean isEmpty() { return data.isEmpty(); }

    /**
     * Compares this instance to another alert to determine if they are equals.<br />
     * <br />
     * Two alerts are considered as equals if they contains the same informations. String are case sensitive and an empty string is not equal
     * to a space.
     * 
     * @param  alert alert to which compare this instance | null
     * @return true if equal, false otherwise
     */
    public boolean equals(FIOAlert alert) {

        if (alert == null)
            return false;
        
        if (isEmpty() && alert.isEmpty())
            return true;
        else {
            
            if (!getTitle().equals(alert.getTitle()))
                return false;
            if (getExpires() != alert.getExpires())
                return false;
            if (!getDescription().equals(alert.getDescription()))
                return false;
            if (!getURI().equals(alert.getURI()))
                return false;
            
            return true;
        }
    }
}