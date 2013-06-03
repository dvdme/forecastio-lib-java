package dme.forecastiolib;

public class FIOMinutely {

	FIODataBlock minutely;

	public FIOMinutely(ForecastIO fio){

		init(fio);

	}

	private void init(ForecastIO fio){

		if(fio.hasMinutely()){
			this.minutely = new FIODataBlock(fio.getMinutely());
			this.minutely.setTimezone(fio.getTimezone());
		}
		else {
			this.minutely = null;
		}
	}

	/**
	 * Returns the data point for the given minute in the minutely report
	 * @param minute
	 * @return return the data point with the report
	 */
	public FIODataPoint getMinute(int minute){

		return this.minutely == null ? null : this.minutely.datapoint(minute);  
	}

	/**
	 * Returns the minutes in the minutely report
	 * @return integer with the number of minute. Returns -1 if there is not data in the report.
	 */
	public int minutes(){
		return this.minutely == null ? -1 : this.minutely.datablockSize();
	}

}
