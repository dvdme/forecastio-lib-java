package tests;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import main.FIODataPoint;
import main.enums.FIODataPointPropertiesEnum;
import main.json.JSONSlotNotFoundException;
import net.sf.json.JSONObject;

/**
 * Test case for the FIOFlags class.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class FIODataPointTestCase extends TestCase {

    
    //
    // FIELDS
    //
    /**
     * A proper standard FIOAlert instance.
     */
    public FIODataPoint dataPoint;
    
    
    //
    // DATA PROVIDERS
    //
    /**
     * Provides a JSON containing well filled entries for an FIOAlert instance.
     * 
     * @return JSON
     */
    public static JSONObject provideProperOptimizedJSON() {

        return JSONObject.fromObject("{\"time\":1405893600,\"summary\":\"Light rain starting in the afternoon.\",\"icon\":\"rain\",\"sunriseTime\":1405915854,\"sunsetTime\":1405971926,\"moonPhase\":0.83,\"nearestStormDistance\":88,\"nearestStormDistanceBearing\":\"23\",\"precipIntensity\":0.0047,\"precipIntensityMax\":0.013,\"precipIntensityMaxTime\":1405965600,\"precipProbability\":0.84,\"precipType\":\"rain\",\"temperature\":77.97,\"temperatureMin\":61.35,\"temperatureMinTime\":1405911600,\"temperatureMax\":80.05,\"temperatureMaxTime\":1405951200,\"apparentTemperature\":64.3,\"apparentTemperatureMin\":61.35,\"apparentTemperatureMinTime\":1405911600,\"apparentTemperatureMax\":83.24,\"apparentTemperatureMaxTime\":1405951200,\"dewPoint\":64.95,\"humidity\":0.85,\"windSpeed\":10.02,\"windBearing\":315,\"visibility\":5.59,\"cloudCover\":0.54,\"pressure\":1015.78,\"ozone\":331.37}");
    }
    
    public static JSONObject[] provideValidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = new JSONObject();
        
        
        // add values
        json = provideProperOptimizedJSON();
        list.add(json);
       
        // mess up some values
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.SUMMARY);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.SUMMARY, "");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.ICON);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.ICON, "");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.ICON, "unsupported icon");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.SUNRISE_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.SUNSET_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.MOONPHASE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.MOONPHASE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.MOONPHASE, 1);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.NEAREST_STORM_DISTANCE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.NEAREST_STORM_DISTANCE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING, 360);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRECIPITATION_PROBABILITY);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_PROBABILITY, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRECIPITATION_TYPE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_TYPE, "");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_TYPE, "unsupported type");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRECIPITATION_ACCUMULATION);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRECIPITATION_ACCUMULATION, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TEMPERATURE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TEMPERATURE_MAX);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MAX, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MAX, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MAX, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TEMPERATURE_MAX_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TEMPERATURE_MIN);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MIN, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MIN, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.TEMPERATURE_MIN, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.TEMPERATURE_MIN_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN, -10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN, 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN_TIME);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.DEW_POINT);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.DEW_POINT, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.WIND_SPEED);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.WIND_SPEED, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.WIND_BEARING);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.WIND_SPEED, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.WIND_SPEED, 360);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.CLOUD_COVER);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.CLOUD_COVER, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.CLOUD_COVER, 1);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.HUMIDITY);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.HUMIDITY, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.HUMIDITY, 1);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.PRESSURE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.PRESSURE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.VISIBILITY);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.VISIBILITY, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.remove(FIODataPointPropertiesEnum.OZONE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIODataPointPropertiesEnum.OZONE, 0);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element("key1", 0);
        json.element("key2", "value2");
        list.add(json);
        
        
        // return list
        returnList = new JSONObject[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (JSONObject)list.get(i);
        
        return returnList;
    }

    public static JSONObject[] provideInvalidJSON() {

        JSONObject[] returnList;

        ArrayList  list = new ArrayList();
        JSONObject json = null;


        // add values
        list.add(json);

        // return list
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
    public String getName() { return "Test of the FIODataPoint class."; }
  
    /**
     * Initialize a proper FIOAlert standard instance.
     * 
     * @throws Exception
     */
    protected void setUp() throws Exception {

        dataPoint = new FIODataPoint(provideProperOptimizedJSON());
        super.setUp();
    }
    
    
    //
    // TEST METHODS
    //
    
    // constructor tests
    public void testConstructor_withNoInput_expectedEmptyInstance() {
        
        FIODataPoint dataPoint = new FIODataPoint();
        assertTrue(dataPoint.isEmpty());
    }
    
    public void testConstructor_withValidInputJSON_expectedSuccess() {
        
        FIODataPoint dataPoint;
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            dataPoint = new FIODataPoint(inputs[i]);
            
            if (!inputs[i].isEmpty())
                assertFalse(dataPoint.isEmpty());
        }
    }
    
    public void testConstructor_withInvalidInputJSON_expectedEmptyInstance() {
        
        FIODataPoint dataPoint;
        JSONObject[] inputs = provideInvalidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            dataPoint = new FIODataPoint(inputs[i]);
            assertTrue(dataPoint.isEmpty());
        }
    }
    
    
    // test values access
    public void testValues_expectProperValues() {
        
        JSONObject   input     = provideProperOptimizedJSON();
        FIODataPoint dataPoint = new FIODataPoint();
        
        input.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getApparentTemperature());
        
        input.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getApparentTemperatureMax());
        
        input.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MAX_TIME, 1407502800);
        dataPoint.update(input);
        assertTrue(1407502800 == dataPoint.getApparentTemperatureMaxTime());
        
        input.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getApparentTemperatureMin());
        
        input.element(FIODataPointPropertiesEnum.APPARENT_TEMPERATURE_MIN_TIME, 1407502800);
        dataPoint.update(input);
        assertTrue(1407502800 == dataPoint.getApparentTemperatureMinTime());
        
        input.element(FIODataPointPropertiesEnum.CLOUD_COVER, 0.27);
        dataPoint.update(input);
        assertTrue(0.27 == dataPoint.getCloudCover());
        
        input.element(FIODataPointPropertiesEnum.DEW_POINT, 54.37);
        dataPoint.update(input);
        assertTrue(54.37 == dataPoint.getDewPoint());
        
        input.element(FIODataPointPropertiesEnum.HUMIDITY, 0.75);
        dataPoint.update(input);
        assertTrue(0.75 == dataPoint.getHumidity());
        
        input.element(FIODataPointPropertiesEnum.ICON, "clear-day");
        dataPoint.update(input);
        assertEquals("clear-day", dataPoint.getIcon());
        
        input.element(FIODataPointPropertiesEnum.MOONPHASE, 0.42);
        dataPoint.update(input);
        assertTrue(0.42 == dataPoint.getMoonPhase());
        
        input.element(FIODataPointPropertiesEnum.NEAREST_STORM_BEARING, 58);
        dataPoint.update(input);
        assertTrue(58 == dataPoint.getNearestStormBearing());
        
        input.element(FIODataPointPropertiesEnum.NEAREST_STORM_DISTANCE, 27);
        dataPoint.update(input);
        assertTrue(27 == dataPoint.getNearestStormDistance());
        
        input.element(FIODataPointPropertiesEnum.OZONE, 310.53);
        dataPoint.update(input);
        assertTrue(310.53 == dataPoint.getOzone());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_ACCUMULATION, 310.53);
        dataPoint.update(input);
        assertTrue(310.53 == dataPoint.getPrecipitationAccumulation());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY, 0.0008);
        dataPoint.update(input);
        assertTrue(0.0008 == dataPoint.getPrecipitationIntensity());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX, 0.0026);
        dataPoint.update(input);
        assertTrue(0.0026 == dataPoint.getPrecipitationIntensityMax());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_INTENSITY_MAX_TIME, 1407758400);
        dataPoint.update(input);
        assertTrue(1407758400 == dataPoint.getPrecipitationIntensityMaxTime());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_PROBABILITY, 0.03);
        dataPoint.update(input);
        assertTrue(0.03 == dataPoint.getPrecipitationProbability());
        
        input.element(FIODataPointPropertiesEnum.PRECIPITATION_TYPE, "rain");
        dataPoint.update(input);
        assertEquals("rain", dataPoint.getPrecipitationType());
        
        input.element(FIODataPointPropertiesEnum.PRESSURE, 1013.19);
        dataPoint.update(input);
        assertTrue(1013.19 == dataPoint.getPressure());
        
        input.element(FIODataPointPropertiesEnum.SUMMARY, "Partly cloudy until afternoon.");
        dataPoint.update(input);
        assertEquals("Partly cloudy until afternoon.", dataPoint.getSummary());
        
        input.element(FIODataPointPropertiesEnum.SUNRISE_TIME, 1407763387);
        dataPoint.update(input);
        assertTrue(1407763387 == dataPoint.getSunriseTime());
        
        input.element(FIODataPointPropertiesEnum.SUNSET_TIME, 1407812960);
        dataPoint.update(input);
        assertTrue(1407812960 == dataPoint.getSunsetTime());
        
        input.element(FIODataPointPropertiesEnum.TEMPERATURE, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getTemperature());
        
        input.element(FIODataPointPropertiesEnum.TEMPERATURE_MAX, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getTemperatureMax());
        
        input.element(FIODataPointPropertiesEnum.TEMPERATURE_MAX_TIME, 1407502800);
        dataPoint.update(input);
        assertTrue(1407502800 == dataPoint.getTemperatureMaxTime());
        
        input.element(FIODataPointPropertiesEnum.TEMPERATURE_MIN, 57.37);
        dataPoint.update(input);
        assertTrue(57.37 == dataPoint.getTemperatureMin());
        
        input.element(FIODataPointPropertiesEnum.TEMPERATURE_MIN_TIME, 1407502800);
        dataPoint.update(input);
        assertTrue(1407502800 == dataPoint.getTemperatureMinTime());
        
        input.element(FIODataPointPropertiesEnum.TIME, 1407740400);
        dataPoint.update(input);
        assertTrue(1407740400 == dataPoint.getTime());
        
        input.element(FIODataPointPropertiesEnum.VISIBILITY, 10);
        dataPoint.update(input);
        assertTrue(10 == dataPoint.getVisibility());
        
        input.element(FIODataPointPropertiesEnum.WIND_BEARING, 237);
        dataPoint.update(input);
        assertTrue(237 == dataPoint.getWindBearing());
        
        input.element(FIODataPointPropertiesEnum.WIND_SPEED, 6.37);
        dataPoint.update(input);
        assertTrue(6.37 == dataPoint.getWindSpeed());
    }
    
    
    // test isValid method
    public void testIsValid_withValidInput_expectedSuccess() {
        
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++)
            assertTrue(FIODataPoint.isValid(inputs[i]));
    }
    
    public void testIsValid_withInvalidInput_expectedFail() {
        
        JSONObject[] inputs = provideInvalidJSON();
        
        for (int i = 0; i < inputs.length; i++)
            assertFalse(FIODataPoint.isValid(inputs[i]));
    }
    
    
    // test update method
    public void testUpdate_withValidInput_expectSuccess() {
        
        FIODataPoint dataPoint;
        JSONObject[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++) {
            
            dataPoint = new FIODataPoint(provideProperOptimizedJSON());
            assertTrue(dataPoint.update(inputs[i]));
        }
    }
    
    public void testUpdate_withInvalidInput_expectInstanceEmpty() {

        FIODataPoint dataPoint;
        JSONObject[] inputs = provideInvalidJSON();

        for (int i = 0; i < inputs.length; i++) {
            
            dataPoint = new FIODataPoint(provideProperOptimizedJSON());
            assertFalse(dataPoint.update(inputs[i]));
        }
    }
    

    // test clear method
    public void testClearMethod_expectSuccess() {

        FIODataPoint dataPoint;
        JSONObject[] inputs = provideJSON();

        for (int i = 0; i < inputs.length; i++) {

            dataPoint = new FIODataPoint(inputs[i]);
            dataPoint.clear();
            assertTrue(dataPoint.isEmpty());
        }
    }
    
    
    // test size method
    public void testSizeMethod_expectSuccess() {
        
        FIODataPoint dataPoint;
        JSONObject[] inputs = provideJSON();

        for (int i = 0; i < inputs.length; i++) {

            dataPoint = new FIODataPoint(inputs[i]);
            assertTrue(dataPoint.size() == dataPoint.getDataElements().length);
            assertTrue(dataPoint.size() <= FIODataPointPropertiesEnum.getEnums().length);
        }
    }
    
    
    // test getDataElements
    public void testGetDataElements_expectSuccess() {
        
        FIODataPoint dataPoint;
        JSONObject   input = new JSONObject();
        
        dataPoint = new FIODataPoint();
        assertTrue(dataPoint.getDataElements().length == 0);
        
        input.element(FIODataPointPropertiesEnum.TIME, new Date().getTime());
        input.element(FIODataPointPropertiesEnum.SUNRISE_TIME, new Date().getTime());
        
        dataPoint.update(input);
        
        assertTrue(dataPoint.getDataElements()[1].equals(FIODataPointPropertiesEnum.TIME));
        assertTrue(dataPoint.getDataElements()[0].equals(FIODataPointPropertiesEnum.SUNRISE_TIME));
    }


    // isEmpty method
    public void testIsEmpty_expectSuccess() {

        FIODataPoint dataPoint;
        JSONObject[] inputs    = provideJSON();
        boolean      isEmpty;

        for (int i = 0; i < inputs.length; i++) {

            dataPoint = new FIODataPoint(inputs[i]);
            isEmpty   = true;
            
            try {
                dataPoint.getTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getSummary();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getIcon();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getSunriseTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getSunsetTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getMoonPhase();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getNearestStormDistance();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getNearestStormBearing();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationIntensity();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationIntensityMax();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationIntensityMaxTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationProbability();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationType();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPrecipitationAccumulation();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getTemperature();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getTemperatureMax();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getTemperatureMaxTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getTemperatureMin();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getTemperatureMinTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getApparentTemperature();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getApparentTemperatureMax();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getApparentTemperatureMaxTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getApparentTemperatureMin();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getApparentTemperatureMinTime();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getDewPoint();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getWindSpeed();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getWindBearing();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getCloudCover();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getHumidity();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getPressure();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getVisibility();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            try {
                dataPoint.getOzone();
                isEmpty = false;
            } catch (JSONSlotNotFoundException exception) {}
            
            assertTrue(dataPoint.isEmpty() == isEmpty);
        }
    }

    
    // test equals method
    public void testEquals_withNullInstance_expectFail() {

        //TODO
    }

    public void testEquals_withNoInput_expectSuccess() {

        //TODO
    }

    public void testEquals_withSameInput_expectSuccess() {

        //TODO
    }

    public void testEquals_withSameStringAsideTrailingSpace_expectFail() {

        //TODO
    }

    public void testEquals_withSameStringsIfCaseIsensitive_expectFail() {

        //TODO
    }
}