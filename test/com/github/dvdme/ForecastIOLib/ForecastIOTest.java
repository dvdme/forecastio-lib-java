package com.github.dvdme.ForecastIOLib;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

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
        assertNotNull("Got null FIOurrently object", cur);
        assertTrue("Got false hasCurrently()", fio.hasCurrently());
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