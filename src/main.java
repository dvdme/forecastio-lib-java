import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import dme.forecastiolib.FIODataBlock;
import dme.forecastiolib.FIODataPoint;
import dme.forecastiolib.ForecastIO;
import dme.forecastiolib.enums.FIODataBlocksEnum;
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
import dme.forecastiolib.exceptions.JSONSlotNotFoundException;



public class main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ForecastIO forecast = new ForecastIO("757dcc57f4bb5b1c23adf950e20d64c7", 48.72273, 2.27115, FIOLangEnum.FRENCH, FIOUnitsEnum.SI, null, false);
        forecast.requestForecast();
        System.out.println("API response: " + forecast.getAPIResponse().toString());
        
        System.out.println("Currently: " + forecast.getCurrently().toString());
        System.out.println("Create data point with currently...");
        FIODataPoint currently = forecast.getCurrently();
        System.out.println("done");
        System.out.println("currently data: " + currently.getFieldsArray().length);
        System.out.println("time: " + currently.getTime());
        
//        currently.getNearestStormBearing();
//        
//        System.out.println("Hourly: " + forecast.getHourly().toString());
//        System.out.println("Create data point with currently...");
//        FIODataBlock hourly = new FIODataBlock(forecast.getAPIResponse().getJSONObject(FIODataBlocksEnum.HOURLY));
//        System.out.println("done");
//        System.out.println("hourly summary: " + hourly.getSummary());
//        System.out.println("hourly icon: " + hourly.getIcon());
//        System.out.println("nbr of data points: " + hourly.getNbrOfDataPoints());
    }

}
