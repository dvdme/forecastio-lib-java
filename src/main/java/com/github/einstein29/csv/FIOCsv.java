/**
 * This class exports data to csv file.
 * 
 * @author Rui Gong
 */

package com.github.einstein29.csv;

import java.io.FileWriter;
import java.io.IOException;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.FIOMinutely;

public class FIOCsv {
	
	private String delimiter;
	private String filePath; 
	
	private static final String[] namesForCurrently = { "time", "summary",
			"icon", "nearestStormDistance", "precipIntensity",
			"precipIntensityError", "precipProbability", "precipType",
			"temperature", "apparentTemperature", "dewPoint", "humidity",
			"windSpeed", "windBearing", "visibility", "cloudCover", "pressure",
			"ozone" };

	private final String[] namesForMinutely = { "time", "precipIntensity",
			"precipIntensityError", "precipProbability", "precipType" };

	private final String[] namesForHourly = { "time", "summary", "icon",
			"precipIntensity", "precipProbability", "precipType",
			"temperature", "apparentTemperature", "dewPoint", "humidity",
			"windSpeed", "windBearing", "visibility", "cloudCover", "pressure",
			"ozone" };

	private final String[] namesForDaily = { "time", "summary", "icon",
			"sunriseTime", "sunsetTime", "moonPhase", "precipIntensity",
			"precipIntensityMax", "precipIntensityMaxTime",
			"precipProbability", "precipType", "temperatureMin",
			"temperatureMinTime", "temperatureMax", "temperatureMaxTime",
			"apparentTemperatureMin", "apparentTemperatureMinTime",
			"apparentTemperatureMax", "apparentTemperatureMaxTime", "dewPoint",
			"humidity", "windSpeed", "windBearing", "visibility", "cloudCover",
			"pressure", "ozone" };
	
	public FIOCsv(String filePath){
		this.filePath = filePath;
		this.delimiter = ",";
	}
	
	public FIOCsv(String filePath, String delimiter){
		this.filePath = filePath;
		this.delimiter = delimiter;
	}
	
	/**
	 * Writes header into csv file.
	 * 
	 * @param writer FileWriter, namesForTimeInterval String[].
	 */
	private void writeHeader(FileWriter writer, String[] namesForTimeInterval){
		String name = "";
		try{
			for (int i = 0; i < namesForTimeInterval.length; i++){
				name = namesForTimeInterval[i];
				if(i != namesForTimeInterval.length-1){
					writer.write(name+delimiter);
				}else{
					writer.write(name);
				}
			}
			writer.write("\n");
		}catch (IOException e) {
			System.err.println("Failed when writing header to CSV file.");
			e.printStackTrace();
		}
	}
	
	/**
     * Writes currently data into csv file.
     *
     * @param  currently
     *         A FIOCurrently object.
     * @return
     */
	public boolean writeToCsv(FIOCurrently currently) {
		String data = "";
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);
			writeHeader(writer, namesForCurrently);

			// Writes contents.
			for (int j = 0; j < namesForCurrently.length; j++) {
				data = currently.get().getByKey(namesForCurrently[j]);
				if (j != namesForCurrently.length - 1) {
					writer.write(data + delimiter);
				} else {
					writer.write(data);
				}
			}
		} catch (IOException e) {
			System.err.println("Failed when writing contents to CSV file.");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("FileWriter does not close properly.");
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Done. Current weather data has been written into csv file.");
		return true;
	}
	
	/**
     * Writes minutely data into csv file.
     *
     * @param  minutely
     *         A FIOMinutely object.
     *
     * @return
     */
	public boolean writeToCsv(FIOMinutely minutely){
		if(minutely.minutes()<0){
			System.out.println("No minutely data, nothing will be written.");
			return false;
		}
		
		String data = "";
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);
			writeHeader(writer, namesForMinutely);

			// Writes contents.
			for(int i = 0; i<minutely.minutes(); i++){
				for(int j=0; j<namesForMinutely.length; j++){
					data = minutely.getMinute(i).getByKey(namesForMinutely[j]);
					if(j != namesForMinutely.length-1){
						writer.write(data+delimiter);
					}else{
						writer.write(data);
					}
				}
				writer.write("\n");
			}

		} catch (IOException e) {
			System.err.println("Failed when writing contents to CSV file.");
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("FileWriter does not close properly.");
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Done. Minutely weather data has been written into csv file.");
		return true;
	}
	
	/**
     * Writes hourly data into csv file.
     *
     * @param  hourly
     *         A FIOHourly object.
     *
     * @return
     */
	public boolean writeToCsv(FIOHourly hourly){
		if(hourly.hours()<0){
			System.out.println("No hourly data, nothing will be written.");
			return false;
		}
		
		String data = "";
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);
			writeHeader(writer, namesForHourly);
			
			// Write contents.
			for(int i = 0; i<hourly.hours(); i++){
				for(int j=0; j<namesForHourly.length; j++){
					data = hourly.getHour(i).getByKey(namesForHourly[j]);
					if(j != namesForHourly.length-1){
						writer.write(data+delimiter);
					}else{
						writer.write(data);
					}
				}
				writer.write("\n");
			}
		} catch (IOException e) {
			System.err.println("Failed when writing contents to CSV file.");
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("FileWriter does not close properly.");
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Done. Hourly weather data has been written into csv file.");
		return true;
	}
		
	/**
     * Writes daily data into csv file.
     *
     * @param  daily
     *         A FIODaily object.
     *
     * @return
     */
	public boolean writeToCsv(FIODaily daily){
		if(daily.days()<0){
			System.out.println("No daily data, nothing will be written.");
			return false;
		}
		
		String data = "";
		FileWriter writer = null;
        try {
			writer = new FileWriter(filePath);
			writeHeader(writer, namesForDaily);
			
			// Writes contents.
			for(int i = 0; i<daily.days(); i++){
				for(int j=0; j<namesForDaily.length; j++){
					data = daily.getDay(i).getByKey(namesForDaily[j]);
					if(j != namesForDaily.length-1){
						writer.write(data+delimiter);
					}else{
						writer.write(data);
					}
				}
				writer.write("\n");
			}
			
		} catch (IOException e) {
			System.err.println("Failed when writing contents to CSV file.");
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("FileWriter does not close properly.");
				e.printStackTrace();
				return false;
			}
		}
        System.out.println("Done. Daily weather data has been written into csv file.");
        return true;
	}
}
