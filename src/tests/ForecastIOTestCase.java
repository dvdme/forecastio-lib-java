package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import main.ForecastIO;
import main.enums.FIODataBlocksEnum;
import main.enums.FIOLangEnum;
import main.enums.FIOUnitsEnum;
import main.json.JSONNotFoundException;

import org.apache.commons.lang.RandomStringUtils;


/**
 * Test case for the ForecastIO class.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class ForecastIOTestCase extends TestCase {

    
    private final static String API_KEY = "757dcc57f4bb5b1c23adf950e20d64c7";      // valid API key
    
    
    
    //
    // DATA PROVIDERS
    //
    public static ForecastIOParamSet provideStandardInput() {

        return new ForecastIOParamSet(API_KEY, 13.0366, 101.4925);
    }
    
    public static ForecastIOParamSet[] provideValidInputs() {
        
        ForecastIOParamSet[] returnList;
        ArrayList            list       = new ArrayList();
        ForecastIOParamSet   paramSet   = provideStandardInput();
        
        // add values
        list.add(paramSet);

        // Lisbon
        paramSet = provideStandardInput();
        paramSet.latitude  = 38.7252993;
        paramSet.longitude = -9.1500364;
        list.add(paramSet);

        // Madrid
        paramSet = provideStandardInput();
        paramSet.latitude  = 40.41678;
        paramSet.longitude = -3.70379;
        list.add(paramSet);

        // Ceuta
        paramSet = provideStandardInput();
        paramSet.latitude  = 35.88838;
        paramSet.longitude = -5.32464;
        list.add(paramSet);

        // Paris
        paramSet = provideStandardInput();
        paramSet.latitude  = 48.85661;
        paramSet.longitude = 2.35222;
        list.add(paramSet);

        // Berlin
        paramSet = provideStandardInput();
        paramSet.latitude  = 52.51917;
        paramSet.longitude = 13.40609;
        list.add(paramSet);

        // Brasilia
        paramSet = provideStandardInput();
        paramSet.latitude  = -15.83454;
        paramSet.longitude = -47.98828;
        list.add(paramSet);

        // London
        paramSet = provideStandardInput();
        paramSet.latitude  = 51.51121;
        paramSet.longitude = -0.11982;
        list.add(paramSet);

        // Alcatraz
        paramSet = provideStandardInput();
        paramSet.latitude  = 37.8267;
        paramSet.longitude = -122.423;
        list.add(paramSet);
        
        paramSet = provideStandardInput();
        paramSet.latitude  = -1;
        list.add(paramSet);

        paramSet = provideStandardInput();
        paramSet.latitude  = 91;
        list.add(paramSet);

        paramSet = provideStandardInput();
        paramSet.longitude = -181;
        list.add(paramSet);

        paramSet = provideStandardInput();
        paramSet.longitude = 181;
        list.add(paramSet);
        
        returnList = new ForecastIOParamSet[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (ForecastIOParamSet)list.get(i);
        
        return returnList;
    }

    public static ForecastIOParamSet[] provideInvalidInputs() {

        ForecastIOParamSet[] returnList;
        ArrayList            list       = new ArrayList();
        ForecastIOParamSet   paramSet;
        
        paramSet = provideStandardInput();
        paramSet.key = "";
        list.add(paramSet);

        paramSet = provideStandardInput();
        paramSet.key = RandomStringUtils.randomAlphabetic(31);
        list.add(paramSet);

        String key;
        while (true) {
            
            key = RandomStringUtils.randomAlphabetic(64);
            
            if (key.length() > 32)
                break;
        }
        paramSet = provideStandardInput();
        paramSet.key = key;
        list.add(paramSet);

        paramSet = provideStandardInput();
        paramSet.key = null;
        list.add(paramSet);
        
        returnList = new ForecastIOParamSet[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (ForecastIOParamSet)list.get(i);
        
        return returnList;
    } 

    public static ForecastIOParamSet[] provideInputs() {

        ForecastIOParamSet[] source1 = provideValidInputs(),
                             source2 = provideInvalidInputs();

        ForecastIOParamSet[] inputs = new ForecastIOParamSet[source1.length + source2.length];

        for (int i = 0; i < source1.length; i++)
            inputs[i] = source1[i];

        for (int i = 0; i < source2.length; i++)
            inputs[source1.length + i] = source2[i];

        return inputs;
    }
    
    
    //
    // INHERITED METHODS
    //
    public String getName() { return "Test of the ForecastIO class."; }
  
    
    //
    // TEST METHODS
    //
    
    // constructor tests
    public void testConstructor_withValidInput_expectedSuccess() {
        
        ForecastIOParamSet[] inputs   = provideValidInputs();
        ForecastIO           forecast;
        
        for (int i = 0; i < inputs.length; i++) {
            
            forecast = new ForecastIO(inputs[i].key, inputs[i].latitude, inputs[i].longitude);
            assertNotNull(forecast);
        }
    }
    
    public void testConstructor_withInvalidInputJSON_expectedEmptyInstance() {
        
        ForecastIOParamSet[] inputs   = provideInvalidInputs();
        ForecastIO           forecast;
        
        for (int i = 0; i < inputs.length; i++) {
            
            forecast = null;
            
            try {
                forecast = new ForecastIO(inputs[i].key, inputs[i].latitude, inputs[i].longitude);
                fail("Was expecting an exception.");
            } catch (IllegalArgumentException exception) {
                // expected result if wrong key passed
            }
            
            assertNull(forecast);
        }
    }

    
    // Test the manipulation of the lang field
    public void testLangOption_withNoInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertTrue(forecast.getLang().equals(FIOLangEnum.DEFAULT));
    }
    
    public void testLangOption_withValidInput_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < FIOLangEnum.getEnums().length; i++) {
            
            forecast.setLang(FIOLangEnum.getEnums()[i]);
            assertTrue(forecast.getLang().equals(FIOLangEnum.getEnums()[i]));
        }
    }
    
    public void testLangOption_withInvalidInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < 100; i++) {
            
            forecast.setLang(RandomStringUtils.randomAlphabetic(5));
            assertTrue(forecast.getLang().equals(FIOLangEnum.DEFAULT));
        }
    }
    
    public void testResetLangOption_withRandomInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < FIOLangEnum.getEnums().length; i++) {
            
            forecast.setLang(FIOLangEnum.getEnums()[i]);
            forecast.resetLang();
            assertTrue(forecast.getLang().equals(FIOLangEnum.DEFAULT));
        }
    }
    
    
    // test time
    public void testTimeOption_withNoInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertTrue(forecast.getTime() == -1);
    }
    
    public void testTimeOption_withValidInput_expectDefaultValue() {

        //TODO
    }

    public void testTimeOption_withInvalidInput_expectDefaultValue() {

        //TODO
    }

    public void testRestTimeOption_expectDefaultValue() {

        //TODO
    }
    
    // TODO : test limits in time, point for old data and forecast
    
    
    // Test the manipulation of the units field
    public void testUnitsOption_withNoInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertTrue(forecast.getUnits().equals(FIOUnitsEnum.DEFAULT));
    }
    
    public void testUnitsOption_withValidInput_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < FIOUnitsEnum.getEnums().length; i++) {
            
            forecast.setUnits(FIOUnitsEnum.getEnums()[i]);
            assertTrue(forecast.getUnits().equals(FIOUnitsEnum.getEnums()[i]));
        }
    }
    
    public void testUnitsOption_withInvalidInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < 100; i++) {
            
            forecast.setLang(RandomStringUtils.randomAlphabetic(5));
            assertTrue(forecast.getUnits().equals(FIOUnitsEnum.DEFAULT));
        }
    }
    
    public void testResetUnitsOption_withRandomInput_expectDefaultValue() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < FIOUnitsEnum.getEnums().length; i++) {
            
            forecast.setUnits(FIOUnitsEnum.getEnums()[i]);
            forecast.resetUnits();
            assertTrue(forecast.getUnits().equals(FIOUnitsEnum.DEFAULT));
        }
    }
    
    // Test the manipulation of the exclude field
    public void testExcludeOption_withNoInput_expectDefaultValue() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertTrue(forecast.getExcludeList().length() == 0);
    }

    public void testExcludeOption_withValidInput_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.addToExcludeList(FIODataBlocksEnum.ALERTS);
        assertEquals(FIODataBlocksEnum.ALERTS, forecast.getExcludeList());
        
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
        
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY + "," + FIODataBlocksEnum.MINUTELY, forecast.getExcludeList());
        
        for (int i = 0; i < FIODataBlocksEnum.getEnums().length; i++) {
            
            forecast.setExcludeList(new String[] {FIODataBlocksEnum.getEnums()[i]});
            assertTrue(forecast.getExcludeList().equals(FIODataBlocksEnum.getEnums()[i]));
        }
    }

    public void testExcludeOption_withInvalidInput_expectDefaultValue() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        String randomString;
        while (true) {
            randomString = RandomStringUtils.randomAlphabetic(15);
            if (!FIODataBlocksEnum.isElement(randomString))
                break;
        }
        
        forecast.addToExcludeList(randomString);
        assertTrue(forecast.getExcludeList().length() == 0);
    }

    public void testExcludeOption_withValidRedondantInput_expectNoRedondancy() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.addToExcludeList(FIODataBlocksEnum.ALERTS);
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        
        forecast.addToExcludeList(FIODataBlocksEnum.ALERTS);
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY + "," + FIODataBlocksEnum.MINUTELY, forecast.getExcludeList());
    }

    public void testResetExcludeOption_expectDefaultValue() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        for (int i = 0; i < FIODataBlocksEnum.getEnums().length; i++) {
            
            forecast.setExcludeList(new String[] {FIODataBlocksEnum.getEnums()[i]});
            forecast.resetExcludeList();
            assertTrue(forecast.getExcludeList().length() == 0);
        }
    }
    
    public void testsetExcludeOption_withValidInput_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        String[] excludeList = new String[]{FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
    }
    
    public void testsetExcludeOption_withValidRedondantInput_expectNoDuplicates() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        String[] excludeList = new String[]{FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY, FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
    }
    
    public void testsetExcludeOption_withRandomInput_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        String[] excludeList = null;
        forecast.setExcludeList(excludeList);
        assertTrue(forecast.getExcludeList().length() == 0);
        
        // test empty entry
        excludeList = new String[10];
        forecast.setExcludeList(excludeList);
        assertTrue(forecast.getExcludeList().length() == 0);
        
        // test wrong values
        excludeList = new String[]{"azerty", "qwerty"};
        forecast.setExcludeList(excludeList);
        assertTrue(forecast.getExcludeList().length() == 0);
        
        // test wrong values with good values
        excludeList = new String[]{FIODataBlocksEnum.ALERTS, "azerty", FIODataBlocksEnum.CURRENTLY, "qwerty"};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
        
        // test wrong values with good values with duplicates
        excludeList = new String[]{FIODataBlocksEnum.ALERTS, "azerty", FIODataBlocksEnum.CURRENTLY, "qwerty", FIODataBlocksEnum.CURRENTLY, FIODataBlocksEnum.CURRENTLY};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
    }
    
    public void testExcludeOptionEffectiveness_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

     // test with no exclude
        forecast.resetExcludeList();
        forecast.update();
        assertTrue(forecast.hasCurrently());
        assertTrue(forecast.hasDaily());
        assertTrue(forecast.hasHourly());
        //assertTrue(forecast.hasMinutely());
        assertTrue(forecast.hasFlags());
        
        // test with excluding currently report
        forecast.resetExcludeList();
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        forecast.update();
        assertFalse(forecast.hasCurrently());

        // test with excluding daily report
        forecast.resetExcludeList();
        forecast.addToExcludeList(FIODataBlocksEnum.DAILY);
        forecast.update();
        assertFalse(forecast.hasDaily());

        // test with excluding flags report
        forecast.resetExcludeList();
        forecast.addToExcludeList(FIODataBlocksEnum.FLAGS);
        forecast.update();
        assertFalse(forecast.hasFlags());

        // test with excluding hourly report
        forecast.resetExcludeList();
        forecast.addToExcludeList(FIODataBlocksEnum.HOURLY);
        forecast.update();
        assertFalse(forecast.hasHourly());

        // test with excluding minutely report
        forecast.resetExcludeList();
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        forecast.update();
        assertFalse(forecast.hasMinutely());
    }
    
    
    // Test the manipulation of the extend field
    public void testExtendField_withNoInput_expectDefaultValue() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertFalse(forecast.isExtended());
    }

    public void testExtendField_withValidInput_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.setExtend(true);
        assertTrue(forecast.isExtended());

        forecast.setExtend(false);
        assertFalse(forecast.isExtended());

    }

    public void testResetExtendField_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.setExtend(true);
        forecast.resetExtend();
        assertFalse(forecast.isExtended());
    }
    
    
    // Test API response
    public void testRequestForecast_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        
        // test the access to the API response when no call has been made yet
        try {
            forecast.getAPIResponse();
            fail("Excepted exception.");
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
        
        assertTrue(forecast.requestForecast());
        
        // test the access to the API response once a call has been made
        try {
            forecast.getAPIResponse();
            assertNotNull(forecast.getAPIResponse());
        } catch (Exception exception) {
            fail("Expected a non null API response.");
        }
    }
    
    
    
    // test currently data blocks
    public void testCurrentlyDataBlock_defaultValue_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertFalse(forecast.hasCurrently());
    }
    
    public void testCurrentlyDataBlockAccess_withNotUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        try {
            forecast.getCurrently();
            fail("Excepted exception.");
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }

    public void testCurrentlyDataBlockAccess_withUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.requestForecast();
        assertTrue(forecast.hasCurrently());

        try {
            forecast.getCurrently().getTime();
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }
    
    
    // test minutely, hourly and daily data blocks (have the same pattern)
    public void testDailyDataBlock_defaultValue_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertFalse(forecast.hasHourly());
    }
    
    public void testDailyDataBlockAccess_withNotUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        try {
            forecast.getHourly();
            fail("Excepted exception.");
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }

    public void testDailyDataBlockAccess_withUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.requestForecast();
        assertTrue(forecast.hasHourly());

        try {
            assertFalse(forecast.getHourly().isEmpty());
            forecast.getHourly().getDataPoint(0).getTime();
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }
    
    
    // test flags
    public void testFlags_defaultValue_expectSuccess() {
        
        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);
        assertFalse(forecast.hasFlags());
    }
    
    public void testFlagsAccess_withNotUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        try {
            forecast.getFlags();
            fail("Excepted exception.");
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }

    public void testFlagsAccess_withUpdatedInstance_expectSuccess() {

        ForecastIO forecast = new ForecastIO(provideStandardInput().key, provideStandardInput().latitude, provideStandardInput().longitude);

        forecast.requestForecast();
        assertTrue(forecast.hasFlags());

        try {
            assertFalse(forecast.getFlags().isEmpty());
            forecast.getFlags().getUnits();
        } catch (Exception exception) {
            fail("Unexcepted exception.");
        }
    }
}