package test.forecastiolib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import dme.forecastiolib.ForecastIO;
import dme.forecastiolib.enums.FIODataBlocksEnum;
import dme.forecastiolib.enums.FIOLangEnum;
import dme.forecastiolib.enums.FIOUnitsEnum;
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
        
        public ConstructorParametersContainer(String key, double latitude, double longitude, String lang, Date time, String units, String[] exclude, boolean extand) {

            this.key       = key;
            this.latitude  = latitude;
            this.longitude = longitude;
            this.lang      = lang;
            this.time      = time;
            this.units     = units;
            this.exclude   = exclude;
            this.extand    = extand;
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
}