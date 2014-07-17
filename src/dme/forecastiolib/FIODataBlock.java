package dme.forecastiolib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.ranges.RangeException;

import dme.forecastiolib.enums.FIODataPointPropertiesEnum;


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
    public FIODataBlock(JSONObject dataBlock) { update(dataBlock); }

    
    //
    // GETTERS
    //
    /**
     * Returns the summary for the given data block.
     * 
     * @return
     */
    public final String getSummary() { return summary; }

    /**
     * Returns the icon for the given data block.
     * 
     * @return String with the icon
     */
    public final String getIcon() { return icon; }
    
    /**
     * Returns a data point from the this data block.
     * 
     * @param index
     * @return A FIODataPoint
     * @throws RangeException
     */
    public final FIODataPoint getDataPoint(int index) throws RangeException { return data[index]; }
    
    /**
     * Returns the number of data points.
     * 
     * @return
     */
    public final int getNbrOfDataPoints() { return data.length; }
    
    
    //
    // PUBLIC METHODS
    //
    /**
     * Updates this instance with the given JSON file.
     * 
     * @param dataBlock
     */
    public void update(JSONObject dataBlock) {

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
            data  = toFIODataPointsArray(dataBlock.getJSONArray("data"));
        } catch (NullPointerException mpe) {
            data = new FIODataPoint[] {};
        }
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
        
        for (int i = 0; i < dataArray.size(); i++)
            dataPointsArray[i] = new FIODataPoint((JSONObject)dataArray.get(i));
        
        return dataPointsArray;
    }
}
