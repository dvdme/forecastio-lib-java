package com.github.dvdme.ForecastIOLib;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by david.ervideira on 31/10/2016.
 */
public class ForecastIOTest {

    private String fakeApiKey = "00000000000000000000000000000000";
    private String jsonText;
    private ForecastIO fio;

    @Before
    public void setUp() throws Exception {
        jsonText = new String(Files.readAllBytes(Paths.get("test/resources/response.json")));
        fio = new ForecastIO(fakeApiKey);
        assertNotNull("ForecastIO object is null at setUp()", fio);
        fio.getForecast(jsonText);
        assertEquals("getForecast from json string not loaded correctly", jsonText, fio.getRawResponse());
    }

    @After
    public void tearDown() throws Exception {
        // do nothing
    }

    @Test
    public void getLatitude() throws Exception {
        double lat = fio.getLatitude();
        assertEquals(38.7252993, lat, 0.0001);
    }

    @Test
    public void getLongitude() throws Exception {

    }

    @Test
    public void getTimezone() throws Exception {

    }

    @Test
    public void getTime() throws Exception {

    }

    @Test
    public void setTime() throws Exception {

    }

    @Test
    public void setTime1() throws Exception {

    }

    @Test
    public void getExcludeURL() throws Exception {

    }

    @Test
    public void setExcludeURL() throws Exception {

    }

    @Test
    public void setHTTPProxy() throws Exception {

    }

    @Test
    public void isExtend() throws Exception {

    }

    @Test
    public void setExtend() throws Exception {

    }

    @Test
    public void getConnectTimeout() throws Exception {

    }

    @Test
    public void setConnectTimeout() throws Exception {

    }

    @Test
    public void getReadTimeout() throws Exception {

    }

    @Test
    public void setReadTimeout() throws Exception {

    }

    @Test
    public void offsetValue() throws Exception {

    }

    @Test
    public void offset() throws Exception {

    }

    @Test
    public void getUnits() throws Exception {

    }

    @Test
    public void setUnits() throws Exception {

    }

    @Test
    public void getLang() throws Exception {

    }

    @Test
    public void setLang() throws Exception {

    }

    @Test
    public void getCurrently() throws Exception {

    }

    @Test
    public void getMinutely() throws Exception {

    }

    @Test
    public void getHourly() throws Exception {

    }

    @Test
    public void getFlags() throws Exception {

    }

    @Test
    public void getAlerts() throws Exception {

    }

    @Test
    public void getDaily() throws Exception {

    }

    @Test
    public void hasCurrently() throws Exception {

    }

    @Test
    public void hasMinutely() throws Exception {

    }

    @Test
    public void hasHourly() throws Exception {

    }

    @Test
    public void hasDaily() throws Exception {

    }

    @Test
    public void hasFlags() throws Exception {

    }

    @Test
    public void hasAlerts() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getForecast() throws Exception {

    }

    @Test
    public void getForecast1() throws Exception {

    }

    @Test
    public void getForecast2() throws Exception {

    }

    @Test
    public void getUrl() throws Exception {

    }

    @Test
    public void getHeaderCache_Control() throws Exception {

    }

    @Test
    public void getHeaderExpires() throws Exception {

    }

    @Test
    public void getHeaderX_Forecast_API_Calls() throws Exception {

    }

    @Test
    public void getHeaderX_Response_Time() throws Exception {

    }

    @Test
    public void getRawResponse() throws Exception {

    }

}