package test.forecastiolib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.w3c.dom.ranges.RangeException;

import dme.forecastiolib.ForecastIO;
import dme.forecastiolib.enums.FIODataBlocksEnum;
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
import dme.forecastiolib.json.JSONNotFoundException;
import dme.forecastiolib.json.JSONSlotNotFoundException;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;


public class ForecastIOTestCase extends TestCase {

    // Convenience class to ease the storing of ForecastIO constructor's parameters.
    private class ConstructorParametersContainer {
        
        public String   key;
        public double   latitude,
                        longitude;
        public String   lang;
        public Date     time;
        public String   units;
        public String[] exclude;
        public boolean  extand;
        
        public ConstructorParametersContainer(String key, double latitude, double longitude) {

            this.key       = key;
            this.latitude  = latitude;
            this.longitude = longitude;
        }
    }

    private final String                         API_KEY = "757dcc57f4bb5b1c23adf950e20d64c7";      // valid API key
    private       List                           constructorParameters = new ArrayList();           // constructor data provider
    private       ConstructorParametersContainer container;
    private       ForecastIO                     forecast;
    private final int                            MAX_ITERATION = 100;                               // constant for maximal iterations
    private final Random                         randomGenerator = new Random();
    
    
    protected void setUp() throws Exception {

        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 38.7252993, -9.1500364));     // Lisbon
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 40.41678, -3.70379));         // Madrid
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 35.88838, -5.32464));         // Ceuta
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 48.85661, 2.35222));          // Paris
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 52.51917, 13.40609));         // Berlin
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, -15.83454, -47.98828));       // Brasilia
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 51.51121, -0.11982));         // London
        constructorParameters.add(new ConstructorParametersContainer(API_KEY, 37.8267, -122.423));          // Alcatraz

        super.setUp();
    }

    protected ConstructorParametersContainer getRandomConstructorParametersContainer() {
        
        return (ConstructorParametersContainer)constructorParameters.get(randomGenerator.nextInt(constructorParameters.size()));
    }
    
    //
    // Test the constructor with minimal parameters.
    //
    public void testMinimalConstructor() {
             
      // test with real valid values
      for (int i = 0; i < constructorParameters.size(); i++) {
          
          forecast  = null;
          container = (ConstructorParametersContainer)constructorParameters.get(i);
          
          forecast = new ForecastIO(container.key, container.latitude, container.longitude);
          assertNotNull(forecast);
          assertNull(forecast.getLang());
          assertTrue(forecast.getExcludeList().length() == 0);
          assertNull(forecast.getTime());
          assertEquals(FIOUnitsEnum.AUTO, forecast.getUnits());
          assertTrue(forecast.isExtended() == false);
      }
      
      // test with random locations
      for (int i = 0; i < MAX_ITERATION; i++) {
          
          forecast  = null;          
          forecast  = new ForecastIO(API_KEY, randomGenerator.nextLong(), randomGenerator.nextLong());
          assertNotNull(forecast);
          assertNull(forecast.getLang());
          assertTrue(forecast.getExcludeList().length() == 0);
          assertNull(forecast.getTime());
          assertEquals(FIOUnitsEnum.AUTO, forecast.getUnits());
          assertTrue(forecast.isExtended() == false);
      }
      
      // test the API key: expected to work with a string 32 characters long      
      for (int i = 0; i < MAX_ITERATION; i++) {
          
          forecast  = null;
          container = getRandomConstructorParametersContainer();
          
          forecast = new ForecastIO(RandomStringUtils.random(32), container.latitude, container.longitude);
          assertNotNull(forecast);
          assertNull(forecast.getLang());
          assertTrue(forecast.getExcludeList().length() == 0);
          assertNull(forecast.getTime());
          assertEquals(FIOUnitsEnum.AUTO, forecast.getUnits());
          assertTrue(forecast.isExtended() == false);
      }
      
      // test with real valid values for coordinates but wrong string length for the API key
      for (int i = 0; i < constructorParameters.size(); i++) {
          
          forecast  = null;
          container = (ConstructorParametersContainer)constructorParameters.get(i);
          
          try {
              forecast = new ForecastIO(RandomStringUtils.random(randomGenerator.nextInt(32)), container.latitude, container.longitude);
          } catch (IllegalArgumentException exception) {
              assertNull(forecast);
          }
          
          try {
              forecast = new ForecastIO(RandomStringUtils.random(randomGenerator.nextInt(32) + 33), container.latitude, container.longitude);
          } catch (IllegalArgumentException exception) {
              assertNull(forecast);
          }
      }
      
      // same test with random values
      for (int i = 0; i < MAX_ITERATION; i++) {
          
          forecast  = null;
          container = getRandomConstructorParametersContainer();
          
          try {
              forecast = new ForecastIO(RandomStringUtils.random(randomGenerator.nextInt(32)), randomGenerator.nextLong(), randomGenerator.nextLong());
          } catch (IllegalArgumentException exception) {
              assertNull(forecast);
          }
          
          try {
              forecast = new ForecastIO(RandomStringUtils.random(randomGenerator.nextInt(32) + 33), randomGenerator.nextLong(), randomGenerator.nextLong());
          } catch (IllegalArgumentException exception) {
              assertNull(forecast);
          }
      }
    }

    
    //
    // Test the manipulation of the exclude field
    //
    public void testExcludeField() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        // test if the default value is correct
        assertTrue(forecast.getExcludeList().length() == 0);
        
        
        // test if data blocks are properly added
        forecast.addToExcludeList(FIODataBlocksEnum.ALERTS);
        assertEquals(FIODataBlocksEnum.ALERTS, forecast.getExcludeList());
        
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
        
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY + "," + FIODataBlocksEnum.MINUTELY, forecast.getExcludeList());
        
        
        // test if non valid data blocks are properly ignored
        // get random string which is not a data block
        String randomString;
        while (true) {
            randomString = RandomStringUtils.random(15);
            if (!FIODataBlocksEnum.isElement(randomString))
                break;
        }
        forecast.addToExcludeList(randomString);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY + "," + FIODataBlocksEnum.MINUTELY, forecast.getExcludeList());
        

        // test if redondant data block are not added.
        forecast.addToExcludeList(FIODataBlocksEnum.ALERTS);
        forecast.addToExcludeList(FIODataBlocksEnum.CURRENTLY);
        forecast.addToExcludeList(FIODataBlocksEnum.MINUTELY);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY + "," + FIODataBlocksEnum.MINUTELY, forecast.getExcludeList());
        
        
        // test if exclude reset works properly
        forecast.resetExcludeList();
        assertTrue(forecast.getExcludeList().length() == 0);
        
        
        // test the set exclude method
        // test correct entry
        String[] excludeList = new String[]{FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
        
        // test duplicate value
        excludeList = new String[]{FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY, FIODataBlocksEnum.ALERTS, FIODataBlocksEnum.CURRENTLY};
        forecast.setExcludeList(excludeList);
        assertEquals(FIODataBlocksEnum.ALERTS + "," + FIODataBlocksEnum.CURRENTLY, forecast.getExcludeList());
        
        // test null entry
        excludeList = null;
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
        
        
        // the the exclude effect
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


    //
    // Test the manipulation of the lang field
    //
    public void testLangField() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        assertNull(forecast.getLang());
        
        forecast.setLang(FIOLangEnum.DUTCH);
        assertEquals(FIOLangEnum.DUTCH, forecast.getLang());
    }
    
    
    //
    // Test the manipulation of the time field
    //
    public void testTimeField() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        assertNull(forecast.getTime());
        
        Date time = new Date();
        forecast.setTime(time);
        assertEquals(Long.toString(time.getTime()), forecast.getTime());
        
        forecast.setTime(null);
        assertEquals(Long.toString(time.getTime()), forecast.getTime());
        
        forecast.resetTime();
        assertNull(forecast.getTime());
    }
    
    
    //
    // Test the manipulation of the units field
    //
    public void testUnitsField() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        assertEquals(FIOUnitsEnum.AUTO, forecast.getUnits());
        
        forecast.setUnits(FIOUnitsEnum.SI);
        assertEquals(FIOUnitsEnum.SI, forecast.getUnits());
        
        forecast.setUnits("incorrectString");
        assertEquals(FIOUnitsEnum.SI, forecast.getUnits());
        
        forecast.setUnits(null);
        assertEquals(FIOUnitsEnum.SI, forecast.getUnits());
        
        forecast.resetUnits();
        assertEquals(FIOUnitsEnum.AUTO, forecast.getUnits());
    }
    
    
    //
    // Test the manipulation of the extend field
    //
    public void testExtendField() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        assertTrue(forecast.isExtended() == false);
        
        forecast.setExtend(true);
        assertTrue(forecast.isExtended() == true);
        
        forecast.resetExtend();
        assertTrue(forecast.isExtended() == false);
    }
    
    
    //
    // Test API response
    //
    public void testRequestForecast() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
                
        // test the access to the API response when no call has been made yet
        try {
            forecast.getAPIResponse();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        
        forecast.requestForecast();
        
        // test the access to the API response once a call has been made
        try {
            forecast.getAPIResponse();
            assertNotNull(forecast.getAPIResponse());
        } catch (Exception exception) {
            fail("Expected a non null API response.");
        }
    }
    
    public void testCurrentlyDataBlock() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        // test if the hasReport method when no call has been made yet
        // expect not to find the report
        assertFalse(forecast.hasCurrently());
        
        // test the access to the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            forecast.getCurrently();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        
        // test the access to data of the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            forecast.getCurrently().getTime();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
       
        forecast.requestForecast();
        
        // test if the hasReport method once a call has been made
        // expect to find the report
        assertTrue(forecast.hasCurrently());
        
        // test the access of the data once a call has been made
        // expect proper access to the data
        try {
            forecast.getCurrently().getTime();
        } catch (JSONSlotNotFoundException exception) {
            fail("Invalid data point.");
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
    }
    
    // Since minutely, hourly and daily have the same patterns, just one of them is tested.
    public void testDataBlock() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        // test if the hasReport method when no call has been made yet
        // expect not to find the report
        assertFalse(forecast.hasHourly());

        // test the access to the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            forecast.getHourly();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }

        // test the access to data of the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            assertTrue(forecast.getHourly().getNbrOfDataPoints() == 0);
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        try {
            forecast.getHourly().getDataPoint(0);
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        try {
            forecast.getHourly().getDataPoint(0).getTime();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }

        forecast.requestForecast();

        // test if the hasReport method once a call has been made
        // expect to find the report with at least a data point
        assertTrue(forecast.hasHourly());
        assertTrue(forecast.getHourly().getNbrOfDataPoints() != 0);

        // test the access of the data once a call has been made
        // expect proper access to the data
        try {
            forecast.getHourly().getDataPoint(0).getTime();
        } catch (JSONSlotNotFoundException exception) {
            fail("Invalid data point.");
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
    }

    public void testFlags() {
        
        container = getRandomConstructorParametersContainer();
        forecast  = new ForecastIO(container.key, container.latitude, container.longitude);
        
        // test if the hasReport method when no call has been made yet
        // expect not to find the report
        assertFalse(forecast.hasFlags());

        // test the access to the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            forecast.getFlags();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }

        // test the access to data of the report when no call has been made yet
        // expect JSONNotFoundException thrown
        try {
            assertTrue(forecast.getFlags().size() == 0);
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        try {
            forecast.getHourly().getDataPoint(0);
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
        try {
            forecast.getHourly().getDataPoint(0).getTime();
        } catch (JSONNotFoundException exception) {
            // is the result expected
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }

        forecast.requestForecast();

        // test if the hasReport method once a call has been made
        // expect to find the report with at least a data point
        assertTrue(forecast.hasFlags());
        assertTrue(forecast.getFlags().size() != 0);

        // test the access of the data once a call has been made
        // expect proper access to the data
        try {
            forecast.getFlags().getUnits();
        } catch (JSONSlotNotFoundException exception) {
            fail("Invalid data point.");
        } catch (Exception exception) {
            fail("Wrong type of exception thrown.");
        }
    }
}