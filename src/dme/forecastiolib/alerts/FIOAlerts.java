package dme.forecastiolib.alerts;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.ranges.RangeException;

/**
 * Representation of a list of Alerts.<br />
 * <br />
 * Helper used for handling Alerts objects. More information available <a href="https://developer.forecast.io/docs/v2">here</a>.
 *
 * @author   Theo FIDRY (theo.fidry@gmail.com)
 * @version  1.0.0
 */
public class FIOAlerts {

    
    //
    // FIELDS
    //
    private FIOAlert[] alerts = new FIOAlert[] {};   // list of alerts available

    
    //
    // CONSTRUCTORS
    //
    /**
     * Instantiate an empty instance (which contains no alerts).
     */
    public FIOAlerts() {}
    
    /**
     * Instantiate an instance which contains the alerts present in the JSON.
     * 
     * @param data JSON source | null
     */
    public FIOAlerts(JSONArray data) { update(data); }
    
    
    //
    // GETTERS
    //
    /**
     * Get an alert of the available list.
     * 
     * @param  index    index of the alert
     * @return FIOAlert alert returned
     * @throws ArrayIndexOutOfBoundsException
     */
    public final FIOAlert getAlert(int index) throws ArrayIndexOutOfBoundsException { return alerts[index]; }
    
    /**
     * Get the list of available alerts.<br />
     * <br />
     * If the is no alerts available, an empty array will be returned.
     * 
     * @return list of available alerts
     */
    public final FIOAlert[] getAlerts() { return alerts; }
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Get the number of alerts available.
     * 
     * @return number of alerts
     */
    public int size() { return alerts.length; }
    
    /**
     * Check if the JSON given has the required information for being processed as a list of alert.<br />
     * <br />
     * To see what is a valid JSON for an alert check FIOAlert.isValid.
     * 
     * @param  data JSON checked | null
     * @return true on success, false otherwise
     */
    public static boolean isValid(JSONArray data) {
        
        if (data == null)
            return false;
        
        if (data.isEmpty())
            return false;
        
        for (int i = 0; i < data.size(); i++)
            if (!FIOAlert.isValid(JSONObject.fromObject(data.get(i))))
                return false;
        
        return true;
    }
    
    /**
     * Updates this instance with the given JSON file.<br />
     * <br />
     * If the JSON passed is invalid, this instance will be emptied.
     * 
     * @param  dataBlock JSON source | null
     * @return true on success, false otherwise
     */
    public boolean update(JSONArray data) {
        
        if (data != null) {
            
            alerts = new FIOAlert[data.size()];
            for (int i = 0; i < data.size(); i++)
                alerts[i] = new FIOAlert(data.getJSONObject(i));
        } else
            alerts = new FIOAlert[]{};
        
        if (isValid(data)) {

            // add values to a buffer array
            FIOAlert[] alerts = new FIOAlert[data.size()];
            
            for (int i = 0; i < alerts.length; i++)
                alerts[i] = new FIOAlert(JSONObject.fromObject(data.get(i)));

            
            // remove duplicates from buffer
            for (int i = 0; i < alerts.length; i++)
                for (int j = 0; j < alerts.length; j++)
                    if (alerts[i] != null)
                        if (i != j && alerts[i].equals(alerts[j]))
                            alerts[j] = null;
            
            
            // get buffer real dimension
            int n = 0;
            for (int i = 0; i < alerts.length; i++)
                if (alerts[i] != null)
                    n++;
            
            
            // retrieve cleaned buffer
            this.alerts = new FIOAlert[n];
            n           = 0;
            
            for (int i = 0; i < alerts.length; i++) {

                if (alerts[i] != null) {

                    this.alerts[n] = alerts[i];
                    n++;
                }
            }
            
            return true;
        } else {
            
            clear();
            return false;
        }
    }
    
    /**
     * Empties this instance.
     */
    public void clear() { alerts = new FIOAlert[] {}; }
    
    /**
     * Check if this instance has any alert available.
     * 
     * @return true if not empty, false otherwise
     */
    public boolean isEmpty() {
        
        return (alerts.length == 0)?
                   true:
                   false;
    }

    /**
     * Compares this instance to another alert list to determine if they are equals.<br />
     * <br />
     * Two alert lists are considered as equals if they contains the same list of alerts. The order doesn't matter.<br />
     * To see in which conditions two alerts are equals, check FIOAlert.equals.
     * 
     * @param  alerts alert list to which compare this instance | null
     * @return true if equal, false otherwise
     */
    public boolean equals(FIOAlerts alerts) {

        if (alerts == null) 
            return false;
            
        if (isEmpty() && alerts.isEmpty())
            return true;
        else if (size() != alerts.size())
            return false;
        else {
            
            boolean contains;
            for (int i = 0; i < size(); i++) {
                
                contains = false;
                
                // check if there is an alert in the other alerts to which this one is equal
                for (int j = 0; i < size(); j++) {
                
                    if (getAlert(i).equals(alerts.getAlert(j))) {
                        
                        contains = true;
                        break;
                    }
                }
                
                if (!contains)
                    return false;
            }
            
            return true;
        }
    }
}