package com.github.einstein29.csv;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.FIOMinutely;
import com.github.dvdme.ForecastIOLib.ForecastIO;

public class FIOCsvTest {
	
	private static final String apikey = "APIKEY";

	public static void main(String[] args) {

		ForecastIO fio = new ForecastIO(apikey);
		fio.setUnits(ForecastIO.UNITS_SI);
		fio.setLang(ForecastIO.LANG_ENGLISH);
		fio.getForecast("38.7252993" , "-9.1500364"); //local
		//fio.getForecast("42.3601","-71.0589"); //online

		//Response Headers info
		System.out.println("Response Headers:");
		System.out.println("Cache-Control: "+fio.getHeaderCache_Control());
		System.out.println("Expires: "+fio.getHeaderExpires());
		System.out.println("X-Forecast-API-Calls: "+fio.getHeaderX_Forecast_API_Calls());
		System.out.println("X-Response-Time: "+fio.getHeaderX_Response_Time());
		System.out.println("\n");

		//ForecastIO info
		System.out.println("Latitude: "+fio.getLatitude());
		System.out.println("Longitude: "+fio.getLongitude());
		System.out.println("Timezone: "+fio.getTimezone());
		System.out.println("Offset: "+fio.offsetValue());
		System.out.println("\n");	
		
		

		//Currently data
		FIOCurrently currently = new FIOCurrently(fio);
		
		String filePathCurrently = "C:\\Users\\rui\\Desktop\\currently.csv";
		FIOCsv fioCsvCurrently = new FIOCsv(filePathCurrently);
		fioCsvCurrently.writeToCsv(currently);
		
		
		
		//Minutely data
		FIOMinutely minutely = new FIOMinutely(fio);
		
		String filePathMinutely = "C:\\Users\\rui\\Desktop\\minutely.csv";
		FIOCsv fioCsvMinutely = new FIOCsv(filePathMinutely);
		fioCsvMinutely.writeToCsv(minutely);
		
		
		
		//Hourly data
		FIOHourly hourly = new FIOHourly(fio);
		
		String filePathHourly = "C:\\Users\\rui\\Desktop\\hourly.csv";
		FIOCsv fioCsvHourly = new FIOCsv(filePathHourly);
		fioCsvHourly.writeToCsv(hourly);
		
		
		
		//Daily data
		FIODaily daily = new FIODaily(fio);
		
		String filePathDaily = "C:\\Users\\rui\\Desktop\\daily.csv";
		FIOCsv fioCsvDaily = new FIOCsv(filePathDaily);
		fioCsvDaily.writeToCsv(daily);
	}

}
