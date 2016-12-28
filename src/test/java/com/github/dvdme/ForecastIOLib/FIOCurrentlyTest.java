package com.github.dvdme.ForecastIOLib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by david on 28/12/2016.
 */
public class FIOCurrentlyTest {

    private String fakeApiKey = "00000000000000000000000000000000";
    private String basePath = "src/test/resources/";
    private FIOCurrently cur;

    @Before
    public void setUp() throws Exception {
        String jsonText = getJsonText(basePath + "response.json");
        ForecastIO fio = new ForecastIO(fakeApiKey);
        fio.getForecast(jsonText);
        cur = new FIOCurrently(fio);
        assertNotNull("Got null FIOCurrently object", cur);
        assertTrue("Got false hasCurrently()", fio.hasCurrently());
    }

    @Test
    public void test_precipIntensity() throws Exception {
        assertEquals(cur.get().precipIntensity(), 0, 0.1);
    }

    @Test
    public void test_precipProbability() throws Exception {
        assertEquals(cur.get().precipProbability(), 0, 0.1);
    }

    @Test
    public void test_dewPoint() throws Exception {
        assertEquals(cur.get().dewPoint(), 50.3, 0.1);
    }

    @Test
    public void test_humidity() throws Exception {
        assertEquals(cur.get().humidity(), 0.44, 0.1);
    }

    @Test
    public void test_windSpeed() throws Exception {
        assertEquals(cur.get().windSpeed(), 6.94, 0.1);
    }

    @Test
    public void test_windBearing() throws Exception {
        assertEquals(cur.get().windBearing(), 135, 0.1);
    }

    @Test
    public void test_visibility() throws Exception {
        assertEquals(cur.get().visibility(), 6.21, 0.1);
    }

    @Test
    public void test_cloudCover() throws Exception {
        assertEquals(cur.get().cloudCover(), 0.04, 0.1);
    }

    @Test
    public void test_pressure() throws Exception {
        assertEquals(cur.get().pressure(), 1017.6, 0.1);
    }

    @Test
    public void test_ozone() throws Exception {
        assertEquals(cur.get().ozone(), 247.7, 0.1);
    }

    @Test
    public void test_apparentTemperature() throws Exception {
        assertEquals(cur.get().apparentTemperature(), 73.65, 0.1);
    }

    @Test
    public void get_temperature() throws Exception {
        assertEquals(cur.get().temperature(), 73.65, 0.1);
    }

    private String getJsonText(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}