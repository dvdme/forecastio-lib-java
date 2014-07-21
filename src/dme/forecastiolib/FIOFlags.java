package dme.forecastiolib;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import dme.forecastiolib.enums.FIOAlertPropertiesEnum;
import dme.forecastiolib.enums.FIOFlagsObjectsEnum;
import dme.forecastiolib.json.JSONSlotNotFoundException;

/**
 * Flags handler.<br />
 * <br />
 * Helper used for handling Flags objects. More information available <a href="https://developer.forecast.io/docs/v2">here</a>.
 *
 * @author   David Ervideira
 * @author   Theo FIDRY (theo.fidry@gmail.com)
 * @version  1.0.0
 */
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
     * station being down for maintenance) has made the data unavailable.
     * <br />
     * If no Dark Sky data source is unavailable, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getDarkskyUnavailable() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.DARKSKY_UNAVAILABLE);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each radar station utilized in servicing this request.<br />
     * <br />
     * If no radar station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getDarkskyStations() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.DARKSKY_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each DataPoint station utilized in servicing this request.<br />
     * <br />
     * If no DataPoint station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getDataPointStations() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.DATAPOINT_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each ISD station utilized in servicing this request.<br />
     * <br />
     * If no ISD station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getIsdStations() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.ISD_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each LAMP station utilized in servicing this request.<br />
     * <br />
     * If no LAMP station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getLampStations() throws JSONSlotNotFoundException {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.LAMP_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each MADIS station utilized in servicing this request.<br />
     * <br />
     * If no MADIS station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getMadisStations() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.MADIS_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get an array of IDs for each METAR station utilized in servicing this request.<br />
     * <br />
     * If no LAMP station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String[] getMetarStations() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.METAR_STATIONS);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get the Metno licence.<br />
     * <br />
     * The presence of this property indicates that data from api.met.no was utilized in order to facilitate this request(as per their license
     * agreement).<br />
     * <br />
     * If this property is not present, an empty string is returned.
     * 
     * @return Metno licence
     */
    public final String getMetnoLicence() {
        
        try {
            return returnStringProperty(FIOFlagsObjectsEnum.METNO_LICENCE);
        } catch (JSONSlotNotFoundException exception) {
            return "";
        }
    }
    
    /**
     * Get an array of IDs for each data source utilized in servicing this request.<br />
     * <br />
     * If no LAMP station has been used, an empty array is returned.
     * 
     * @return list the stations' id | empty
     */
    public final String [] getSources() {
        
        try {
            return returnStringArrayProperty(FIOFlagsObjectsEnum.SOURCES);
        } catch (JSONSlotNotFoundException exception) {
            return new String[] {};
        }
    }
    
    /**
     * Get the units that were used for the data in this request.<br />
     * <br />
     * If this property is not present, an empty string is returned.
     * 
     * @return units | empty
     */
    public final String getUnits() throws JSONSlotNotFoundException {
        
        try {
            return returnStringProperty(FIOFlagsObjectsEnum.UNITS);
        } catch (JSONSlotNotFoundException exception) {
            return "";
        }
    }

    /**
     * Return the number of flags available.
     * 
     * @return number of flags
     */
    public int size() { return flags.size(); }

    /**
     * Get the names of the flags objects available.
     * 
     * @return names | empty
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
     * Check if the JSON given has the required information for being processed as a source of flags.
     * 
     * @param  data JSON checked | null
     * @return true on success, false otherwise
     */
    public static boolean isValid(JSONObject data) {
        
        if (data == null)
            return false;
        
        return true;
    }
    
    /**
     * Updates the flags report with the given JSON file.<br />
     * <br />
     * If the data passed is null, this instance will not be updated.
     * 
     * @param data
     */
    public void update(JSONObject data) {
        
        if (isValid(data)) {

            for (int i = 0; i < data.names().size(); i++) {
                
                try {

                    // case when the JSON element is an array
                    flags.put(data.names().get(i), data.getJSONArray(data.names().get(i).toString()));
                } catch (JSONException exception) {
                    
                    // case when the JSON element is not an array
                    flags.put(data.names().get(i), data.get(data.names().get(i)));
                }
            }
        } else
            clear();
    }   
    
    /**
     * Empties this instance.
     */
    public void clear() { flags.clear(); }
    
    /**
     * Check if there is any flags objects available or not.
     * 
     * @return true if not empty, false otherwise
     */
    public boolean isEmpty() { return flags.isEmpty(); }
    
    //
    // PRIVATE HELPERS
    //
    private String returnStringProperty(String property) throws JSONSlotNotFoundException {
        
        if (flags.containsKey(property))
            return (String)flags.get(property);
        
        throw new JSONSlotNotFoundException();
    }
    
    private String[] returnStringArrayProperty(String property) throws JSONSlotNotFoundException {

        if (flags.containsKey(property)) {
            
            JSONArray array = JSONArray.fromObject(flags.get(property));
            String[]  value = new String[array.size()];
            
            for (int i = 0; i < value.length; i++)
                value[i] = array.get(i).toString();
            
            return value;
        }

        throw new JSONSlotNotFoundException();
    }
}