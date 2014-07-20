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

//        JSONObject json = FIOFlagsTestCase.provideProperOptimizedJSON();
//
//        ForecastIO forecast = new ForecastIO("757dcc57f4bb5b1c23adf950e20d64c7", 48.8667, 2.3333);
//        forecast.requestForecast();
//        forecast.getFlags();
//        
//        json = forecast.getAPIResponse().getJSONObject(FIODataBlocksEnum.FLAGS);
//        
//        FIOFlags flags = new FIOFlags(json);
//        
//        Object obj = flags.flags.get("sources");
//        
//        HashMap map = flags.flags;
//        
//        String[] array = flags.returnStringArrayProperty(FIOFlagsObjectsEnum.ISD_STATIONS);
//        
//        //System.out.println(obj.getClass());
//        
////        JSONArray array = JSONArray.fromObject(obj);
////        
//        for (int i = 0; i < array.length; i++)
//            System.out.println("#" + i + ": " + array[i]);
////        
////        flags.getDarkskyStations();
//        //System.out.println(json1.toString());
    }

}
