import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
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
        
        JSONObject currently = (JSONObject)forecast.forecast.get("currently");
        
        System.out.println(currently.toString());
        System.out.println(forecast.forecast.get("currently").toString());
        
        FIODataPoint currentlyData = new FIODataPoint(forecast.getCurrently());
        //FIODataPoint currentlyData = new FIODataPoint(forecast.getCurrently());
        
        currentlyData.getNearestStormBearing();
        
        System.out.println(currentlyData.getSummary());
        
        System.out.println("fine.");
        
        
    }

}
