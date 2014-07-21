package test.forecastiolib;

import java.util.ArrayList;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;

import dme.forecastiolib.FIOFlags;
import dme.forecastiolib.enums.FIOFlagsObjectsEnum;


/**
 * Test case for the FIOFlags class.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class FIOFlagsTestCase extends TestCase {

    //
    // FIELDS
    //
    /**
     * A proper standard FIOAlert instance.
     */
    public FIOFlags flags;
    
    
    //
    // DATA PROVIDERS
    //
    /**
     * Provides a JSON containing well filled entries for an FIOAlert instance.
     * 
     * @return JSON
     */
    public static JSONObject provideProperOptimizedJSON() {

        return JSONObject.fromObject("{\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
    }
    
    public static JSONObject[] provideValidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = new JSONObject();
                        
        json = provideProperOptimizedJSON();
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"metno-license\":\"Based on data from the Norwegian Meteorological Institute. (http://api.met.no/)\",\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"sources\":[\"nwspa\",\"isd\",\"nearest-precip\",\"fnmoc\",\"cmc\",\"gfs\",\"sref\",\"rtma\",\"rap\",\"nam\",\"madis\",\"lamp\"],\"units\":\"us\"}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"units\":\"us\"}}");
        list.add(json);
        
        json = JSONObject.fromObject("{\"metar-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"],\"darksky-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"darksky-unavailable\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"datapoint-stations\":[\"uk-324386\",\"uk-324387\",\"uk-350150\",\"uk-350734\",\"uk-350928\",\"uk-350929\",\"uk-352036\",\"uk-352196\",\"uk-352409\",\"uk-352932\",\"uk-353331\",\"uk-353605\",\"uk-353669\",\"uk-353846\",\"uk-354160\",\"uk-354379\"],\"isd-stations\":[\"725033-94728\",\"725060-99999\",\"997271-99999\",\"999999-94706\",\"999999-94728\"],\"lamp-stations\":[\"KBLM\",\"KCDW\",\"KEWR\",\"KFRG\",\"KHPN\",\"KJFK\",\"KLGA\",\"KMMU\",\"KNYC\",\"KSMQ\",\"KTEB\"],\"madis-stations\":[\"BATN6\",\"C1099\",\"C8670\",\"C9714\",\"D0486\",\"D3216\",\"D6004\",\"E0405\",\"E1020\",\"E1296\",\"E2876\",\"KLGA\",\"KNYC\",\"KQ12\",\"KTEB\",\"ROBN4\"]}}");
        list.add(json);
        
        json.put("test", RandomStringUtils.randomAlphabetic(120));
        list.add(json);
        
        returnList = new JSONObject[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (JSONObject)list.get(i);
        
        return returnList;
    }
    
    public static JSONObject[] provideInvalidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = null;
        
        list.add(json);
        
        returnList = new JSONObject[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (JSONObject)list.get(i);
        
        return returnList;
    } 
    
    public static JSONObject[] provideJSON() {
        
        JSONObject[] source1 = provideValidJSON(),
                     source2 = provideInvalidJSON();
        
        JSONObject[] inputs = new JSONObject[source1.length + source2.length];
        
        for (int i = 0; i < source1.length; i++)
            inputs[i] = source1[i];
        
        for (int i = 0; i < source2.length; i++)
            inputs[source1.length + i] = source2[i];
        
        return inputs;
    }
    
    //
    // INHERITED METHODS
    //
    public String getName() { return "Test of the FIOFlags class."; }
  
    /**
     * Initialize a proper FIOAlert standard instance.
     * 
     * @throws Exception
     */
    protected void setUp() throws Exception {

        flags = new FIOFlags(provideProperOptimizedJSON());
        super.setUp();
    }
    
    
    //
    // TEST METHODS
    //
    
    // constructor tests
    public void testConstructor_withValidInputJSON_expectedNotEmptyInstance() {
        
        FIOFlags     flags;
        JSONObject[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++) {

            flags = new FIOFlags(inputs[i]);
            
            // test returns values of the properties        
            assertNotNull(flags.getDarkskyUnavailable());
            assertNotNull(flags.getDarkskyStations());
            assertNotNull(flags.getDataPointStations());
            assertNotNull(flags.getIsdStations());
            assertNotNull(flags.getLampStations());
            assertNotNull(flags.getMadisStations());
            assertNotNull(flags.getMetarStations());
            assertNotNull(flags.getMetnoLicence());
            assertNotNull(flags.getSources());
            assertNotNull(flags.getUnits());
            assertFalse(flags.size() < 0);
            
            // test methods
            if (!inputs[i].isEmpty())
                assertFalse(flags.isEmpty());
        }
        
        flags = new FIOFlags(provideProperOptimizedJSON());
        
        assertTrue(flags.getDarkskyUnavailable().length > 0);
        assertTrue(flags.getDarkskyStations().length > 0);
        assertTrue(flags.getDataPointStations().length > 0);
        assertTrue(flags.getIsdStations().length > 0);
        assertTrue(flags.getLampStations().length > 0);
        assertTrue(flags.getMadisStations().length > 0);
        assertTrue(flags.getMetarStations().length > 0);
        assertTrue(flags.getMetnoLicence().length() > 0);
        assertTrue(flags.getSources().length > 0);
        assertTrue(flags.getUnits().length() > 0);
        
        assertFalse(flags.isEmpty());
    }
    
    public void testConstructor_withInvalidInputJSON_expectedEmptyInstance() {
        
        FIOFlags     flags;
        JSONObject[] inputs = provideInvalidJSON();

        for (int i = 0; i < inputs.length; i++) {

            flags = new FIOFlags(inputs[i]);
            
            // test returns values of the properties        
            assertNotNull(flags.getDarkskyUnavailable());
            assertNotNull(flags.getDarkskyStations());
            assertNotNull(flags.getDataPointStations());
            assertNotNull(flags.getIsdStations());
            assertNotNull(flags.getLampStations());
            assertNotNull(flags.getMadisStations());
            assertNotNull(flags.getMetarStations());
            assertNotNull(flags.getMetnoLicence());
            assertNotNull(flags.getSources());
            assertNotNull(flags.getUnits());
            assertFalse(flags.size() < 0);
            
            // test methods
            assertTrue(flags.isEmpty());
        }
    }
    
    
    // test size method
    public void testSize_expectSuccess() {
        
        JSONObject[] inputs = provideValidJSON();
        FIOFlags     flags;
        
        for (int i = 0; i < inputs.length; i++) {
            
            flags = new FIOFlags(inputs[i]);
            assertTrue(flags.size() == inputs[i].size());
        }
    }
    
    
    // test getAvailableFlags method
    public void testGetAvailableFlags_withEmptyInstance_expectEmptyOutput() {
        
        FIOFlags flags = new FIOFlags(null);
        assertTrue(flags.getAvailableFlags().length == 0);
    }
    
    public void testGetAvailableFlags_withCompleteInstance_expectEmptyOutput() {
        
        FIOFlags flags = new FIOFlags(provideProperOptimizedJSON());
        assertTrue(flags.getAvailableFlags().length == FIOFlagsObjectsEnum.getEnums().length);
    }
    
    
    // test isValid method
    public void testIsValid_withValidInput_expectedSuccess() {
        
        JSONObject[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++)
            assertTrue(FIOFlags.isValid(inputs[i]));
    }
    
    public void testIsValid_withInvalidInput_expectedFail() {
        
        JSONObject[] inputs = provideInvalidJSON();
        
        for (int i = 0; i < inputs.length; i++)
            assertFalse(FIOFlags.isValid(inputs[i]));
    }
    
    
    // test update method
    public void testUpdate_withValidInput_expectInstanceUpdated() {
        
        JSONObject[] inputs = provideValidJSON();
        FIOFlags     flags;
        
        for (int i = 0; i < inputs.length; i++) {
            
            flags = new FIOFlags(null);
            flags.update(inputs[i]);
            
            if (!inputs[i].isEmpty())
                assertFalse(flags.isEmpty());
        }
    }
    
    public void testUpdate_withInvalidInput_expectInstanceEmpty() {

        JSONObject[] inputs = provideInvalidJSON();
        FIOFlags     flags;
        
        for (int i = 0; i < inputs.length; i++) {
            
            flags = new FIOFlags(provideProperOptimizedJSON());
            flags.update(inputs[i]);
            
            assertTrue(flags.isEmpty());
        }
    }
    
    
    // test clear method
    public void testClearMethod_expectSuccess() {
        
        JSONObject[] inputs = provideJSON();
        FIOFlags     flags;
        
        for (int i = 0; i < inputs.length; i++) {
            
            flags = new FIOFlags(inputs[i]);
            flags.clear();
            assertTrue(flags.isEmpty());
        }
    }
    
    
    // isEmpty method
    public void testIsEmpty_expectSuccess() {
        
        JSONObject[] inputs = provideJSON();
        FIOFlags     flags;
        
        for (int i = 0; i < inputs.length; i++) {
            
            flags = new FIOFlags(inputs[i]);
            
            if (flags.isEmpty()) {

                assertTrue(flags.getDarkskyUnavailable().length == 0);
                assertTrue(flags.getDarkskyStations().length == 0);
                assertTrue(flags.getDataPointStations().length == 0);
                assertTrue(flags.getIsdStations().length == 0);
                assertTrue(flags.getLampStations().length == 0);
                assertTrue(flags.getMadisStations().length == 0);
                assertTrue(flags.getMetarStations().length == 0);
                assertTrue(flags.getMetnoLicence().length() == 0);
                assertTrue(flags.getSources().length == 0);
                assertTrue(flags.getUnits().length() == 0);
            }
        }
    }
}