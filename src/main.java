import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import junit.framework.Assert;
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
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
import dme.forecastiolib.json.JSONSlotNotFoundException;



public class main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        JSONObject json1 = FIOAlertTestCase.provideProperOptimizedJSON(),
                        json2 = FIOAlertTestCase.provideProperOptimizedJSON();
        JSONObject[] list = new JSONObject[2];
        
        list[0] = json1;
        list[1] = json2;
        
        JSONArray array = JSONArray.fromObject(list);
        
        Assert.assertTrue(array.get(0).equals(list[0]));
        System.out.println("fini");
    }

}
