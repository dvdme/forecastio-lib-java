package com.github.dvdme.ForecastIOLib;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by david.ervideira on 31/10/2016.
 */
public class ForecastIOTest {

    private String fakeApiKey = "00000000000000000000000000000000";
    private String basePath = "src/test/resources/";
    private String jsonText;
    private ForecastIO fio;

    @Before
    public void setUp() throws Exception {
        jsonText = new String(Files.readAllBytes(Paths.get(basePath + "response.json")));
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
        assertEquals("Got wrong latitude", 38.7252993, lat, 0.0001);
    }

    @Test
    public void getLongitude() throws Exception {
        double lon = fio.getLongitude();
        assertEquals("Got wrong longitude", -9.1500364, lon, 0.0001);
    }

    @Test
    public void getTimezone() throws Exception {
        String timezone = fio.getTimezone();
        assertEquals("Got wrong timezone", "Europe/Lisbon", timezone);
    }

    @Test
    public void getTime() throws Exception {
        // TODO test set time with Date()
        String time = "2013-05-06T12:00:00-0400";
        fio.setTime(time);
        assertEquals("Got wrong time after setTime()", time, fio.getTime());
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
        assertEquals("Got wrong isExtend()", false, fio.isExtend());
        fio.setExtend(true);
        assertEquals("Got wrong isExtend() after setExtend(true)", true, fio.isExtend());
    }

    @Test
    public void getConnectTimeout() throws Exception {
        assertEquals("Got wrong connect timeout", 30000, fio.getConnectTimeout());
    }

    @Test
    public void setConnectTimeout() throws Exception {
        int timeout = 10000;
        fio.setConnectTimeout(timeout);
        assertEquals("Got wrong connect timeout after setConnectTimeout()", 10000, fio.getConnectTimeout());
    }

    @Test
    public void getReadTimeout() throws Exception {
        assertEquals("Got wrong read timeout", 30000, fio.getReadTimeout());
    }

    @Test
    public void setReadTimeout() throws Exception {
        int timeout = 10000;
        fio.setReadTimeout(timeout);
        assertEquals("Got wrong connect read after setConnectTimeout()", 10000, fio.getReadTimeout());
    }

    @Test
    public void offsetValue() throws Exception {
        double offset = fio.offsetValue();
        assertEquals("Got wrong offset value", 0.0, offset, 0.001);
    }

    @Test
    public void offset() throws Exception {
        String offset = fio.offset();
        assertEquals("Got wrong offset", "0", fio.offset());
    }

    @Test
    public void getUnits() throws Exception {
        String units = fio.getUnits();
        assertEquals("Got wrong units", "auto", fio.getUnits());
        fio.setUnits(ForecastIO.UNITS_SI);
        assertEquals("Got wrong units after setUnits()", "si", fio.getUnits());
    }

    @Test
    public void getLang() throws Exception {
        String lang = fio.getLang();
        assertEquals("Got wrong lang", "en", fio.getLang());
        fio.setLang(ForecastIO.LANG_GERMAN);
        assertEquals("Got wrong lang after setLang()", "de", fio.getLang());
    }

    @Test
    public void getCurrently() throws Exception {
        FIOCurrently cur = new FIOCurrently(fio);
        assertNotNull("Got null FIOCurrently object", cur);
        assertTrue("Got false hasCurrently()", fio.hasCurrently());
    }

    @Test
    public void getEmptyCurrently() throws Exception {
        String jsonText = getJsonText(basePath + "response_empty_currently.json");
        ForecastIO fio = new ForecastIO(fakeApiKey);
        fio.getForecast(jsonText);
        FIOCurrently cur = new FIOCurrently(fio);
        assertNotNull("Got null FIOCurrently object", cur);
        assertFalse("Got true hasCurrently()", fio.hasCurrently());
    }

    @Test
    public void getNullCurrently() throws Exception {
        String jsonText = getJsonText(basePath + "response_null_currently.json");
        ForecastIO fio = new ForecastIO(fakeApiKey);
        fio.getForecast(jsonText);
        FIOCurrently cur = new FIOCurrently(fio);
        assertNotNull("Got null FIOCurrently object", cur);
        assertFalse("Got true hasCurrently()", fio.hasCurrently());
    }

    @Test
    public void getMinutely() throws Exception {
        FIOMinutely min = new FIOMinutely(fio);
        assertNotNull("Got null FIOMinutely object", min);
        assertFalse("Got true hasMinutely()", fio.hasMinutely());
    }

    @Test
    public void getHourly() throws Exception {
        FIOHourly hour = new FIOHourly(fio);
        assertNotNull("Got null FIOHourly object", hour);
        assertTrue("Got false hasHourly()", fio.hasHourly());
    }

    @Test
    public void getFlags() throws Exception {
        FIOFlags flags = new FIOFlags(fio);
        assertNotNull("Got null FIOFlags object", flags);
        assertTrue("Got false hasFlags()", fio.hasFlags());
    }

    @Test
    public void getAlerts() throws Exception {
        FIOAlerts flags = new FIOAlerts(fio);
        assertNotNull("Got null FIOAlerts object", flags);
        assertFalse("Got true hasFlags()", fio.hasAlerts());
    }

    @Test
    public void getDaily() throws Exception {
        FIODaily day = new FIODaily(fio);
        assertNotNull("Got null FIODaily object", day);
        assertTrue("Got false hasDaily()", fio.hasDaily());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getForecast() throws Exception {
        // TODO this test
    }


    @Test
    public void getUrl() throws Exception {
        String url = "https://api.darksky.net/forecast/00000000000000000000000000000000/0.0,0.0?units=auto&lang=en";
        assertEquals("Got wrong url", url, fio.getUrl("0.0", "0.0"));
    }

    @Test
    public void getHeaderCache_Control() throws Exception {
        // TODO this test
    }

    @Test
    public void getHeaderExpires() throws Exception {
        // TODO this test
    }

    @Test
    public void getHeaderX_Forecast_API_Calls() throws Exception {
        // TODO this test
    }

    @Test
    public void getHeaderX_Response_Time() throws Exception {
        // TODO this test
    }

    @Test
    public void getRawResponse() throws Exception {
        assertEquals("Got differet raw string", jsonText, fio.getRawResponse());
    }

    private String getJsonText(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}