package dme.forecastiolib;

import java.util.HashMap;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;

public class FIOFlags {

	private HashMap<String, String[]> flags;
	private String units;

	public FIOFlags(ForecastIO fio){
		flags = new HashMap<String, String[]>();
		units = "";
		init(fio);

	}

	private void init(ForecastIO fio){

		if(fio.hasFlags()){
			if(fio.getFlags().names().contains("darksky-unavailable"))
				this.flags.put("darksky-unavailable", toStringArray(fio.getFlags().get("darksky-unavailable").asArray()));

			if(fio.getFlags().names().contains("darksky-stations"))
				this.flags.put("darksky-stations", toStringArray(fio.getFlags().get("darksky-stations").asArray()));

			if(fio.getFlags().names().contains("datapoint-stations"))
				this.flags.put("datapoint-stations", toStringArray(fio.getFlags().get("datapoint-stations").asArray()));

			if(fio.getFlags().names().contains("isd-stations"))
				this.flags.put("isd-stations", toStringArray(fio.getFlags().get("isd-stations").asArray()));

			if(fio.getFlags().names().contains("lamp-stations"))
				this.flags.put("lamp-stations", toStringArray(fio.getFlags().get("lamp-stations").asArray()));

			if(fio.getFlags().names().contains("metar-stations"))
				this.flags.put("metar-stations", toStringArray(fio.getFlags().get("metar-stations").asArray()));

			//TODO metno-licenses

			if(fio.getFlags().names().contains("sources"))
				this.flags.put("sources", toStringArray(fio.getFlags().get("sources").asArray()));

			try {
				this.units = fio.getFlags().get("units").asString();
			}
			catch (NullPointerException npe) {
				this.units = "no data";
			}
		}
	}

	private String [] toStringArray(JsonArray jsonarray){
		String [] out = new String[jsonarray.size()];
		for(int i=0; i<jsonarray.size(); i++)
			out[i] = jsonarray.get(i).asString();
		return out;
	}

	/**
	 * Returns an array with the available flags in the report.
	 * If the array's lenght is 0, there are none.
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return A String array with the available flags.
	 */
	public String [] availableFlags(){
		Iterator<String> it = flags.keySet().iterator();
		String [] out = new String[(flags.keySet().size())];
		int i = 0;
		while(it.hasNext()){
			out[i] = it.next();
			i++;
		}
		return out;
	}

	/**
	 * Returns an array with the unavailable darksky stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] darkskyUnavailable(){
		return flags.get("darksky-unavailable");
	}

	/**
	 * Returns an array with the available darksky stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] darkskyStations(){
		return flags.get("darksky-stations");
	}

	/**
	 * Returns an array with the data point stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] datapointStations(){
		return flags.get("datapoint-stations");
	}

	/**
	 * Returns an array with the isd stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] isdStations(){
		return flags.get("isd-stations");
	}

	/**
	 * Returns an array with the lamp stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] lampStations(){
		return flags.get("lamp-stations");
	}

	/**
	 * Returns an array with the metar stations
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the stations
	 */
	public String [] metarStations(){
		return flags.get("metar-stations");
	}

	//TODO metno-licenses

	/**
	 * Returns an array with the sources
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return array with the sources
	 */
	public String [] sources(){
		return flags.get("sources");
	}

	/**
	 * Returns a String with the units in use
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the units
	 */
	public String units(){
		return units;
	}

}
