package test.forecastiolib.alerts;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSONObject;
import dme.forecastiolib.FIOAlert;
import dme.forecastiolib.enums.FIOAlertPropertiesEnum;
import junit.framework.TestCase;


/**
 * Test case for the FIOAlert class.
 *
 * @author Theo FIDRY <theo.fidry@gmail.com>
 */
public class FIOAlertTestCase extends TestCase {

    
    //
    // FIELDS
    /**
     * A proper standard FIOAlert instance.
     */
    public FIOAlert alert;
    
    
    //
    // DATA PROVIDERS
    //
    /**
     * Provides a JSON containing well filled entries for an FIOAlert instance.
     * 
     * @return JSON
     */
    public JSONObject provideProperOptimizedJSON() {

        JSONObject data = new JSONObject();
        
        data.put(FIOAlertPropertiesEnum.TITLE, "Test alert.");
        data.put(FIOAlertPropertiesEnum.EXPIRES, (Object)new Long(new Date().getTime()));
        data.put(FIOAlertPropertiesEnum.DESCRIPTION, RandomStringUtils.random(120));
        data.put(FIOAlertPropertiesEnum.URI, "http//test-alert.forecast.io");
        
        return data;
    }
    
    public JSONObject[] provideValidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = provideProperOptimizedJSON();
        
        
        // add values
        list.add(json);
       
        // mess up some values
        json.element(FIOAlertPropertiesEnum.TITLE, "");
        list.add(json);
        
        json.element(FIOAlertPropertiesEnum.DESCRIPTION, "");
        list.add(json);
        
        json.element(FIOAlertPropertiesEnum.URI, "");
        list.add(json);
        
        // removes some values
        json.discard(FIOAlertPropertiesEnum.DESCRIPTION);
        list.add(json);
        
        // add unnaccessary values
        json.put("key1", "stringValue");
        list.add(json);
        
        json.element("key2", 10);
        list.add(json);
        
        json.element("key3", 100L);
        list.add(json);
        
        json.element("key4", 1000D);
        list.add(json);
        
        json.element("key5", new JSONObject());
        list.add(json);
        
        
        // return list
        returnList = new JSONObject[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (JSONObject)list.get(i);
        
        return returnList;
    }
    
    public JSONObject[] provideInvalidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = new JSONObject();
        
        
        // add values
        list.add(json);
       
        // mess up some values
        json = provideProperOptimizedJSON();
        json.discard(FIOAlertPropertiesEnum.TITLE);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.discard(FIOAlertPropertiesEnum.EXPIRES);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.discard(FIOAlertPropertiesEnum.URI);
        list.add(json);
        
        
        // return list
        returnList = new JSONObject[list.size()];
        
        for (int i = 0; i < returnList.length; i++)
            returnList[i] = (JSONObject)list.get(i);
        
        return returnList;
    } 
    
    
    //
    // INHERITED METHODS
    //
    public String getName() { return "Test of the FIOAlert class."; }
  
    /**
     * Initialize a proper FIOAlert standard instance.
     * 
     * @throws Exception
     */
    protected void setUp() throws Exception {

        alert = new FIOAlert(provideProperOptimizedJSON());
        super.setUp();
    }
    
    
    //
    // TEST METHODS
    //
    
    // constructor tests
    public void testConstructor_withNoInput_expectedEmptyAlert() {
        
        FIOAlert alert = new FIOAlert();
        
        // test if properties return the good value
        assertNull(alert.getTitle());
        assertTrue(alert.getExpires() < 0);
        assertNull(alert.getDescription());
        assertNull(alert.getURI());
        
        // test methods
        assertTrue(alert.isEmpty());
    }
    
    public void testConstructor_withValidInputJSON_expectedNotEmptyAlert() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(inputs[i]);
            
            // test returns values of the properties        
            assertNotNull(alert.getTitle());
            assertFalse(alert.getExpires() < 0);
            assertNotNull(alert.getDescription());
            assertNotNull(alert.getURI());
        }
    }

    // isEmpty method: already tested in the constructor

    // test equals method
    public void testEquals_withEqualsAlerts_expectSuccess() {
        
        FIOAlert     alert1,
                     alert2;
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert1 = new FIOAlert(inputs[i]);
            alert2 = new FIOAlert(inputs[i]);
            
            assertTrue(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }
    }

    // test isvalid method
    public void testIsValid_withValidInput_expectedSuccess() {
        
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++)
            assertTrue(FIOAlert.isValid(inputs[i]));
    }
    
    public void testIsValid_withInvalidInput_expectedFail() {
        
        JSONObject[] inputs = provideInvalidJSON();
        
        for (int i = 0; i < inputs.length; i++)
            assertFalse(FIOAlert.isValid(inputs[i]));
    }
}
