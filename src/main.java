import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import junit.framework.Assert;
import test.forecastiolib.FIOFlagsTestCase;
import test.forecastiolib.alerts.FIOAlertTestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import dme.forecastiolib.FIODataBlock;
import dme.forecastiolib.FIODataPoint;
import dme.forecastiolib.FIOFlags;
import dme.forecastiolib.ForecastIO;
import dme.forecastiolib.alerts.FIOAlert;
import dme.forecastiolib.enums.FIODataBlocksEnum;
import dme.forecastiolib.enums.FIOFlagsObjectsEnum;
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
import dme.forecastiolib.json.JSONSlotNotFoundException;



public class main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        JSONArray json = JSONArray.fromObject("[{\"time\":1405875600,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1405897078,\"sunsetTime\":1405943104,\"moonPhase\":0.82,\"precipIntensity\":0.0092,\"precipIntensityMax\":0.0258,\"precipIntensityMaxTime\":1405933200,\"precipProbability\":1,\"precipType\":\"rain\",\"temperatureMin\":76.74,\"temperatureMinTime\":1405886400,\"temperatureMax\":88.54,\"temperatureMaxTime\":1405918800,\"apparentTemperatureMin\":76.74,\"apparentTemperatureMinTime\":1405886400,\"apparentTemperatureMax\":99.64,\"apparentTemperatureMaxTime\":1405918800,\"dewPoint\":75.35,\"humidity\":0.82,\"windSpeed\":3.98,\"windBearing\":218,\"cloudCover\":0.75,\"pressure\":1007.91,\"ozone\":268.27},{\"time\":1405962000,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1405983493,\"sunsetTime\":1406029495,\"moonPhase\":0.85,\"precipIntensity\":0.0098,\"precipIntensityMax\":0.0157,\"precipIntensityMaxTime\":1406019600,\"precipProbability\":0.93,\"precipType\":\"rain\",\"temperatureMin\":77.9,\"temperatureMinTime\":1405972800,\"temperatureMax\":88.85,\"temperatureMaxTime\":1406005200,\"apparentTemperatureMin\":77.9,\"apparentTemperatureMinTime\":1405972800,\"apparentTemperatureMax\":101.85,\"apparentTemperatureMaxTime\":1406005200,\"dewPoint\":75.94,\"humidity\":0.83,\"windSpeed\":5.23,\"windBearing\":241,\"cloudCover\":0.89,\"pressure\":1007.61,\"ozone\":263.73},{\"time\":1406048400,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1406069909,\"sunsetTime\":1406115885,\"moonPhase\":0.89,\"precipIntensity\":0.0076,\"precipIntensityMax\":0.0112,\"precipIntensityMaxTime\":1406116800,\"precipProbability\":0.41,\"precipType\":\"rain\",\"temperatureMin\":77.49,\"temperatureMinTime\":1406059200,\"temperatureMax\":88.92,\"temperatureMaxTime\":1406095200,\"apparentTemperatureMin\":77.49,\"apparentTemperatureMinTime\":1406059200,\"apparentTemperatureMax\":100.4,\"apparentTemperatureMaxTime\":1406095200,\"dewPoint\":75.16,\"humidity\":0.8,\"windSpeed\":6.21,\"windBearing\":237,\"cloudCover\":0.82,\"pressure\":1006.74,\"ozone\":260.98},{\"time\":1406134800,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1406156324,\"sunsetTime\":1406202273,\"moonPhase\":0.92,\"precipIntensity\":0.0131,\"precipIntensityMax\":0.0155,\"precipIntensityMaxTime\":1406217600,\"precipProbability\":0.57,\"precipType\":\"rain\",\"temperatureMin\":78.09,\"temperatureMinTime\":1406145600,\"temperatureMax\":88.66,\"temperatureMaxTime\":1406181600,\"apparentTemperatureMin\":78.09,\"apparentTemperatureMinTime\":1406145600,\"apparentTemperatureMax\":100.87,\"apparentTemperatureMaxTime\":1406181600,\"dewPoint\":75.39,\"humidity\":0.79,\"windSpeed\":7.37,\"windBearing\":230,\"cloudCover\":0.94,\"pressure\":1005.65,\"ozone\":262.62},{\"time\":1406221200,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1406242740,\"sunsetTime\":1406288661,\"moonPhase\":0.95,\"precipIntensity\":0.0118,\"precipIntensityMax\":0.0171,\"precipIntensityMaxTime\":1406224800,\"precipProbability\":0.56,\"precipType\":\"rain\",\"temperatureMin\":79.43,\"temperatureMinTime\":1406232000,\"temperatureMax\":89.7,\"temperatureMaxTime\":1406271600,\"apparentTemperatureMin\":79.43,\"apparentTemperatureMinTime\":1406232000,\"apparentTemperatureMax\":102.13,\"apparentTemperatureMaxTime\":1406271600,\"dewPoint\":75.82,\"humidity\":0.78,\"windSpeed\":7.64,\"windBearing\":224,\"cloudCover\":0.92,\"pressure\":1006.09,\"ozone\":264.62},{\"time\":1406307600,\"summary\":\"Light rain in the morning and afternoon.\",\"icon\":\"rain\",\"sunriseTime\":1406329155,\"sunsetTime\":1406375048,\"moonPhase\":0.97,\"precipIntensity\":0.0087,\"precipIntensityMax\":0.0116,\"precipIntensityMaxTime\":1406322000,\"precipProbability\":0.7,\"precipType\":\"rain\",\"temperatureMin\":79.46,\"temperatureMinTime\":1406314800,\"temperatureMax\":90.01,\"temperatureMaxTime\":1406354400,\"apparentTemperatureMin\":79.46,\"apparentTemperatureMinTime\":1406314800,\"apparentTemperatureMax\":103.3,\"apparentTemperatureMaxTime\":1406354400,\"dewPoint\":75.97,\"humidity\":0.78,\"windSpeed\":7.53,\"windBearing\":229,\"cloudCover\":0.99,\"pressure\":1007.01,\"ozone\":266.71},{\"time\":1406394000,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1406415569,\"sunsetTime\":1406461435,\"moonPhase\":0.02,\"precipIntensity\":0.0081,\"precipIntensityMax\":0.0111,\"precipIntensityMaxTime\":1406397600,\"precipProbability\":0.56,\"precipType\":\"rain\",\"temperatureMin\":80.18,\"temperatureMinTime\":1406401200,\"temperatureMax\":89.07,\"temperatureMaxTime\":1406444400,\"apparentTemperatureMin\":85.27,\"apparentTemperatureMinTime\":1406401200,\"apparentTemperatureMax\":100.9,\"apparentTemperatureMaxTime\":1406444400,\"dewPoint\":75.64,\"humidity\":0.77,\"windSpeed\":7.71,\"windBearing\":236,\"cloudCover\":0.96,\"pressure\":1006.57,\"ozone\":269.78},{\"time\":1406480400,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1406501984,\"sunsetTime\":1406547820,\"moonPhase\":0.04,\"precipIntensity\":0.0101,\"precipIntensityMax\":0.0121,\"precipIntensityMaxTime\":1406512800,\"precipProbability\":0.63,\"precipType\":\"rain\",\"temperatureMin\":79.84,\"temperatureMinTime\":1406491200,\"temperatureMax\":91.16,\"temperatureMaxTime\":1406527200,\"apparentTemperatureMin\":79.84,\"apparentTemperatureMinTime\":1406491200,\"apparentTemperatureMax\":104.7,\"apparentTemperatureMaxTime\":1406527200,\"dewPoint\":75.91,\"humidity\":0.77,\"windSpeed\":7.99,\"windBearing\":238,\"cloudCover\":0.63,\"pressure\":1005.39,\"ozone\":266.34}]");
        
        System.out.println(json.get(0));
        
        System.out.println("good");
    }

}
