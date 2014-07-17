package dme.forecastiolib;

import java.util.HashMap;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import dme.forecastiolib.enums.FIOFlagsObjectsEnum;
import dme.forecastiolib.exceptions.JSONSlotNotFoundException;


public class FIOFlags {

    
    //
    // FIELDS
    //
    private HashMap flags = new HashMap();

    
    //
    // CONSTRUCTORS
    //
    /**
     * Instanciate a flags report with the values contained in the JSON passed.
     * 
     * @param data
     */
    public FIOFlags(JSONObject data) { update(data); }

    
    //
    // GETTERS
    //
    /**
     * Get the list of Dark Sky data source unavailable.<br />
     * <br />
     * The presence of this property indicates that the Dark Sky data source supports the given location, but a temporary error (such as a radar
     * station being down for maintenace) has made the data unavailable.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String[] getDarkskyUnavailable() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.DARKSKY_UNAVAILABLE);
    }
    
    /**
     * Get an array of IDs for each radar station utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String[] getDarkskyStations() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.DARKSKY_STATIONS);
    }
    
    /**
     * Get an array of IDs for each DataPoint station utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String [] getDatapointStations() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.DATAPOINT_STATIONS);
    }
    
    /**
     * Get an array of IDs for each ISD station utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String[] getIsdStations() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.ISD_STATIONS);
    }
    
    /**
     * Get an array of IDs for each LAMP station utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String[] getlampStations() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.LAMP_STATIONS);
    }
    
    /**
     * Get an array of IDs for each METAR station utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String[] getMetarStations() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.METAR_STATIONS);
    }
    
    /**
     * Get the metno licence.<br />
     * <br />
     * The presence of this property indicates that data from api.met.no was utilized in order to facilitate this request (as per their license agreement).
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String getMetnoLicence() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIOFlagsObjectsEnum.METNO_LICENCE);
    }
    
    /**
     * Get an array of IDs for each data source utilized in servicing this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String [] getSources() throws JSONSlotNotFoundException {
        
        return returnStringArrayProperty(FIOFlagsObjectsEnum.SOURCES);
    }
    
    /**
     * Get the units that were used for the data in this request.
     * 
     * @return array with the stations
     * @throws JSONSlotNotFoundException
     */
    public final String getUnits() throws JSONSlotNotFoundException {
        
        return returnStringProperty(FIOFlagsObjectsEnum.UNITS);
    }

    /**
     * Return the number of flags available.
     * 
     * @return
     */
    public int getNbrOfFlags() { return flags.size(); }

    /**
     * Returns an array of strings with all the keys available.
     * 
     * @return
     */
    public String[] getAvailableFlags() {

        Object[] flagsKeys      = flags.keySet().toArray();
        String[] availableFlags = new String[flags.size()];

        for (int i = 0; i < flags.size(); i++)
            availableFlags[i] = flagsKeys[i].toString();

        return availableFlags;
    }
    
    
    //
    // PUBLIC HELPERS
    //
    /**
     * Updates the flags report with the given JSON file.<br />
     * <br />
     * If the data passed is null, this instance will not be updated.
     * 
     * @param data
     */
    public void update(JSONObject data) {
        
        if (data != null) {

            for (int i = 0; i < data.names().size(); i++) {
                
                try {
                    
                    // case when the JSON element is an array
                    flags.put(data.names().get(i), data.getJSONArray(data.names().get(i).toString()).toArray());
                } catch (JSONException exception) {
                    
                    // case when the JSON element is not an array
                    flags.put(data.names().get(i), data.get(data.names().get(i)));
                }
            }            
        }
    }   
    
    
    //
    // PRIVATE HELPERS
    //
    private String returnStringProperty(String property) throws JSONSlotNotFoundException {
        
        if (flags.containsKey(property))
            return (String)flags.get(property);
        
        throw new JSONSlotNotFoundException();
    }
    
    private String[] returnStringArrayProperty(String property) throws JSONSlotNotFoundException {

        if (flags.containsKey(property))
            return (String[])flags.get(property);

        throw new JSONSlotNotFoundException();
    }
}