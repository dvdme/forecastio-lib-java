package dme.forecastiolib;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class FIODataBlock {

	private String summary;
	private String icon;
	private FIODataPoint [] data;
	private String timezone;

	public FIODataBlock(){
		this.summary = "";
		this.icon = "";
		this.data = null;
		this.timezone = "GMT";
	}

	public FIODataBlock(JsonObject db){
		this.summary = "";
		this.icon = "";
		this.data = null;
		this.timezone = "GMT";
		update(db);
	}

	private void update(JsonObject db){

		try {
			this.summary = db.get("summary").asString();
		} catch (NullPointerException npe) {
			this.summary = "no data";
		}

		try {
			this.icon = db.get("icon").asString();
		} catch (NullPointerException npl) {
			this.icon = "no data";
		}

		try {
			JsonArray arr;
			if(db.get("data").isArray()){
				arr = db.get("data").asArray();
				this.data = new FIODataPoint[arr.size()];
				for(int i = 0; i < arr.size(); i++){
					this.data[i] = new FIODataPoint();
					this.data[i].setTimezone(timezone);
					data[i].update(arr.get(i).asObject());
				}
			}
			else {
				System.err.println("Not an array. Maybe you're trying to feed \"currently\" to a datablock.");
			}
		} catch (NullPointerException mpe) {
			this.data = null;
		}
	}

	/**
	 * Returns the summary for the given data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the summary
	 */
	public String summary(){
		return this.summary;
	}

	/**
	 * Returns the icon for the given data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @return String with the icon
	 */
	public String icon(){
		return this.icon;
	}

	/**
	 * Returns a data point from the this data block
	 * For more information refer to the API Docs:
	 * <a href="https://developer.forecast.io">https://developer.forecast.io</a>
	 * @param index
	 * @return A FIODataPoint
	 */
	public FIODataPoint datapoint(int index){
		return this.data[index];
	}

	/**
	 * Returns the size of this data block which corresponds to the number of data points present
	 * @return integer with the size
	 */
	public int datablockSize(){
		return this.data.length;
	}

	/**
	 * Allows to set the timezone.
	 * If none is set, default is GMT.
	 * @param tz String with the timezone such as "GMT"
	 */
	public void setTimezone(String tz){
		this.timezone = tz;
	}

	/**
	 * Returns the timezone that is setted.
	 * @return A String with the timezone.
	 */
	public String getTimezone(){
		return this.timezone;
	}

}
