package test.forecastiolib;

import dme.forecastiolib.FIOCurrently;
import dme.forecastiolib.FIODaily;
import dme.forecastiolib.FIOFlags;
import dme.forecastiolib.FIOHourly;
import dme.forecastiolib.FIOMinutely;
import dme.forecastiolib.ForecastIO;

public class FIOLibTest {
	
	private static final String apikey = "YOUR_API_KEY";
	  	   
	public static void main(String[] args) {
	
		//Some coordinates for testing
		//Lisbon:   38.7252993 , -9.1500364
		//Madrid:   40.41678 , -3.70379
		//Ceuta:    35.88838 , -5.32464
		//Paris:    48.85661 , 2.35222 
		//Berlin:   52.51917 , 13.40609
		//Brasilia: -15.83454 , -47.98828
		//London:   51.51121 , -0.11982
		//Alcatraz: 37.8267 , -122.423
		
		ForecastIO fio = new ForecastIO(apikey);
		fio.setUnits(ForecastIO.UNITS_SI);
		fio.getForecast("38.7252993", "-9.1500364");
		
		//ForecastIO info
		System.out.println("Latitude: "+fio.getLatitude());
		System.out.println("Longitude: "+fio.getLongitude());
		System.out.println("Timezone: "+fio.getTimezone());
		System.out.println("Offset: "+fio.offsetValue());
		System.out.println("\n");
		
		//Currently data
		FIOCurrently currently = new FIOCurrently(fio);
		
		System.out.println("\nCurrently\n");
		String [] f  = currently.get().getFieldsArray();
		for(int i = 0; i<f.length;i++)
			System.out.println(f[i]+": "+currently.get().getByKey(f[i]));
		System.out.println("\n");
		
		//Minutely data
		FIOMinutely minutely = new FIOMinutely(fio);
		if(minutely.minutes()<0)
			System.out.println("No minutely data.");
		else
			System.out.println("\nMinutely\n");
		for(int i = 0; i<minutely.minutes(); i++){
			String [] m = minutely.getMinute(i).getFieldsArray();
			System.out.println("Minute #"+(i+1));
			for(int j=0; j<m.length; j++)
				System.out.println(m[j]+": "+minutely.getMinute(i).getByKey(m[j]));
			System.out.println("\n");
		}
		
		//Hourly data
		FIOHourly hourly = new FIOHourly(fio);
		if(hourly.hours()<0)
			System.out.println("No hourly data.");
		else
			System.out.println("\nHourly:\n");
		for(int i = 0; i<hourly.hours(); i++){
			String [] h = hourly.getHour(i).getFieldsArray();
			System.out.println("Hour #"+(i+1));
			for(int j=0; j<h.length; j++)
				System.out.println(h[j]+": "+hourly.getHour(i).getByKey(h[j]));
			System.out.println("\n");
		}
		
		//Daily data
		FIODaily daily = new FIODaily(fio);
		if(daily.days()<0)
			System.out.println("No daily data.");
		else
			System.out.println("\nDaily:\n");
		for(int i = 0; i<daily.days(); i++){
			String [] h = daily.getDay(i).getFieldsArray();
			System.out.println("Day #"+(i+1));
			for(int j=0; j<h.length; j++)
				System.out.println(h[j]+": "+daily.getDay(i).getByKey(h[j]));
			System.out.println("\n");
		}
		
		//Flags data
		FIOFlags flags = new FIOFlags(fio);
		for(int i=0; i<flags.metarStations().length; i++)
			System.out.println("Metar Stations: "+flags.metarStations()[i]);
		System.out.println("\n");
		for(int i=0; i<flags.availableFlags().length; i++)
			System.out.println(flags.availableFlags()[i]);
		
	}//main

}
