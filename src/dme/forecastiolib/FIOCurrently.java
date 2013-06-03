package dme.forecastiolib;

public class FIOCurrently {

	FIODataPoint currently;

	public FIOCurrently(ForecastIO fio){

		this.currently = null;
		init(fio);

	}

	private void init(ForecastIO fio){

		if(fio.hasCurrently()){
			this.currently = new FIODataPoint(fio.getCurrently()); 
			this.currently.setTimezone(fio.getTimezone());
		}
	}

	/**
	 * Returns the data point from the currently report
	 * @return A FIODataPoint containing the currently report
	 */
	public FIODataPoint get(){
		return this.currently;
	}

}
