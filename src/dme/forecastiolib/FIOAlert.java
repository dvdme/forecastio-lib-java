package dme.forecastiolib;

import dme.forecastiolib.enums.FIOAlertPropertiesEnum;
import net.sf.json.JSONObject;

/**
 * Representation of an Alert.
 *
 * @author   Theo FIDRY <theo.fidry@gmail.com>
 * @creation 17 juil. 2014
 * @version  1.0.0
 *
 */
public class FIOAlert {

    
    //
    // FIELDS
    //
    private JSONObject data = new JSONObject();
    private String title       = "";    // short text summary of the alert
    private int    time        = -1;    // UNIX time at which the alert will cease to be valid
    private String description = "",    // detailed text description of the alert from the appropriate weather service
                   uri         = "";    // HTTP(S) URI that contains detailed information about the alert
    
    
    //
    // CONSTRUCTORS
    //    
    /**
     * Instanciate an alert with the given JSON.
     * 
     * @param data
     */
    public FIOAlert(JSONObject data) {
        
        if (data != null)
            this.data = data;
        else
            this.data.clear();
        setTitle(data.get(FIOAlertPropertiesEnum.TITLE).toString());
        setTime(Integer.parseInt(data.get(FIOAlertPropertiesEnum.EXPIRES).toString()));
        setDescription(data.get(FIOAlertPropertiesEnum.DESCRIPTION).toString());
        setUri(data.get(FIOAlertPropertiesEnum.URI).toString());
    }


    //
    // GETTERS & SETTERS
    //
    /**
     * Get the alert's title which is short text summary of the alert.
     * 
     * @return
     */
    public final String getTitle() { return title; }
    
    /**
     * Set the alert's title which is short text summary of the alert.
     * 
     * @param title the title to set
     */
    public final void setTitle(String title) {
        
        if (title == null)
            this.title = "";
        else
            this.title = title;
        }
    
    /**
     * Get the UNIX time at which the alert will cease to be valid.
     * 
     * @return 
     */
    public final int getTime() { return time; }
    
    /**
     * Set the UNIX time at which the alert will cease to be valid.
     * 
     * @param time the time to set
     */
    public final void setTime(int time) { this.time = time; }
    
    /**
     * Get the detailed text description of the alert from the appropriate weather service.
     * 
     * @return the description
     */
    public final String getDescription() { return description; }
    
    /**
     * Set the detailed text description of the alert from the appropriate weather service.
     * 
     * @param description the description to set
     */
    public final void setDescription(String description) {
        
        if (description == null)
            this.description = "";
        else
            this.description = description;
    }
    
    /**
     * Get the HTTP(S) URI that contains detailed information about the alert.
     * 
     * @return the uri
     */
    public final String getUri() { return uri; }
    
    /**
     * Set the HTTP(S) URI that contains detailed information about the alert.
     * 
     * @param uri the uri to set
     */
    public final void setUri(String uri) {
        
        if (uri == null)
            this.uri = "";
        else
            this.uri = uri;
    }
    
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Check if the JSON given has the required information for being processed as an alert.<br />
     * <br />
     * Data entries checked:
     * <ul>
     *  <li>title</li>
     *  <li>expires</li>
     *  <li>URI</li>
     * </ul>
     * Non optional data are required or the JSON is considered as invalid.
     * 
     * @param data
     * @return
     */
    public boolean isValid(JSONObject data) {
        
        if (data.get(FIOAlertPropertiesEnum.TITLE) != null
            && data.get(FIOAlertPropertiesEnum.EXPIRES) != null
            && data.get(FIOAlertPropertiesEnum.URI) != null) {
            
            return true;
        } else
            return false;
    }
}