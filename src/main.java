import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import dme.forecastiolib.FIODataBlock;
import dme.forecastiolib.FIODataPoint;
import dme.forecastiolib.FIOFlags;
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

        JSONObject json = new JSONObject();
        
        if (json.get("test") == null)
            System.out.println("ISNULL");;
    }

}
