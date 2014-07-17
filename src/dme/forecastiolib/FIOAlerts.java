package dme.forecastiolib;

import org.w3c.dom.ranges.RangeException;

import net.sf.json.JSONArray;


public class FIOAlerts {

    
    //
    // FIELDS
    //
    private FIOAlert[] alerts = new FIOAlert[]{};

    
    //
    // CONSTRUCTORS
    //
    /**
     * Instanciate an list of alerts with the given JSON.
     * 
     * @param data
     */
    public FIOAlerts(JSONArray data) { update(data); }
    
    
    //
    // GETTERS
    //
    /**
     * Returns a data point from the this data block.
     * 
     * @param  index
     * @return FIOAlert
     * @throws RangeException
     */
    public final FIOAlert getDataPoint(int index) throws RangeException { return alerts[index]; }
    
    /**
     * Returns the number of alerts in the API response.
     * 
     * @return
     */
    public int getNbrOfAlerts() { return alerts.length; }
    
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Updates this instance with the given JSON file.<br />
     * <br />
     * If the JSON passed is null, this instance will be reset.
     * 
     * @param dataBlock
     */
    private void update(JSONArray alertsList) {
        
        if (alertsList != null) {
            
            alerts = new FIOAlert[alertsList.size()];
            for (int i = 0; i < alertsList.size(); i++)
                alerts[i] = new FIOAlert(alertsList.getJSONObject(i));
        } else
            alerts = new FIOAlert[]{};
    }
}