package dme.forecastiolib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.ranges.RangeException;

import dme.forecastiolib.alerts.FIOAlert;
import dme.forecastiolib.alerts.FIOAlerts;
import dme.forecastiolib.enums.FIODataPointPropertiesEnum;

/**
 * Representation of a Data block.<br />
 * <br />
 * Helper used for handling Data blocks objects. More information available <a href="https://developer.forecast.io/docs/v2">here</a>.
 *
 * @author   David Ervideira
 * @author   Theo FIDRY (theo.fidry@gmail.com)
 * @version  1.0.0
 */
public class FIODataBlock {

    
    //
    // FIELDS
    //
    private String         summary = "",
                           icon    = "";
    private FIODataPoint[] data    = new FIODataPoint[] {};
    
    
    //
    // CONSTRUCTORS
    //
    /**
     * Instantiate an empty data block (which contains no data blocks).
     */
    public FIODataBlock() {}
    
    /**
     * Instantiate an instance which contains the data points present in the JSON.
     * 
     * @param dataBlock JSON source | null
     */
    public FIODataBlock(JSONObject dataBlock) { update(dataBlock); }

    
    //
    // GETTERS
    //
    /**
     * Get the summary of this instance.
     * 
     * @return summary | empty
     */
    public final String getSummary() { return summary; }

    /**
     * Get the icon string for the given data block.
     * 
     * @return icon string | empty
     */
    public final String getIcon() { return icon; }
    
    /**
     * Get a data point from the this data block.
     * 
     * @param  index index of the data point
     * @return       data point
     * @throws ArrayIndexOutOfBoundsException
     */
    public final FIODataPoint getDataPoint(int index) throws ArrayIndexOutOfBoundsException { return data[index]; }
    
    /**
     * Get the list of available data points.<br />
     * <br />
     * If the is no data point is available, an empty array will be returned.
     * 
     * @return list of available data points | empty
     */
    public final FIODataPoint[] getDataPoints() { return data; }
    
    
    //
    // PUBLIC METHODS
    //
    /**
     * Get the number of data points available.
     * 
     * @return number of data points
     */
    public final int size() { return data.length; }
    
    /**
     * Check if the JSON given has the required information for being processed as a list of data points.<br />
     * <br />
     * To see what is a valid JSON for a data point check FIODataPoint.isValid.
     * 
     * @param  data JSON checked | null
     * @return true on success, false otherwise
     */
    public static boolean isValid(JSONObject data) {
        
        if (data == null)
            return false;
        
        if (data.isNullObject())
            return false;
        
        for (int i = 0; i < data.size(); i++)
            if (!FIODataPoint.isValid(data))
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
    public boolean update(JSONObject dataBlock) {

        if (isValid(dataBlock)) {
        
            try {
                summary = dataBlock.get(FIODataPointPropertiesEnum.SUMMARY).toString();
            } catch (NullPointerException exception) {
                summary = "";
            }

            try {
                icon = dataBlock.get(FIODataPointPropertiesEnum.ICON).toString();
            } catch (NullPointerException npl) {
                icon = "";
            }

            try {
                data  = toFIODataPointsArray(JSONArray.fromObject(dataBlock.get("data")));
            } catch (NullPointerException mpe) {
                data = new FIODataPoint[] {};
            }

            return true;
        }
        
        clear();
        return false;
    }
    
    /**
     * Empties this instance.
     */
    public void clear() { 
        
        summary = "";
        icon    = "";
        data    = new FIODataPoint[] {};
    }
    
    /**
     * Check if this instance has any alert available.
     * 
     * @return true if not empty, false otherwise
     */
    public boolean isEmpty() {
        
        return (data.length == 0)?
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

        //TODO
        return true;
    }
    
    
    //
    // PRIVATE METHODS
    //
    /**
     * Convert a JSONArray of data points to an array of FIODataPoints.
     * 
     * @param dataArray
     * @throws NullPointerException
     */
    private FIODataPoint[] toFIODataPointsArray(JSONArray dataArray) throws NullPointerException {
                
        FIODataPoint[] dataPointsArray  = new FIODataPoint[dataArray.size()];
        JSONObject     dataPointJSON;
        
        for (int i = 0; i < dataArray.size(); i++) {
            
            dataPointJSON = JSONObject.fromObject(dataArray.get(i));
            
            if (FIODataPoint.isValid(dataPointJSON))
                dataPointsArray[i] = new FIODataPoint(dataPointJSON);
        }
        
        return dataPointsArray;
    }
}
