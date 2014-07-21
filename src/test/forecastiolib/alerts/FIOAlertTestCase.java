package test.forecastiolib.alerts;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;

import dme.forecastiolib.alerts.FIOAlert;
import dme.forecastiolib.enums.FIOAlertPropertiesEnum;


/**
 * Test case for the FIOAlert class.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
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
    public static JSONObject provideProperOptimizedJSON() {

        JSONObject data = new JSONObject();
        
        data.put(FIOAlertPropertiesEnum.TITLE, "Test alert.");
        data.put(FIOAlertPropertiesEnum.EXPIRES, (Object)new Long(new Date().getTime()));
        data.put(FIOAlertPropertiesEnum.DESCRIPTION, RandomStringUtils.randomAlphabetic(120));
        data.put(FIOAlertPropertiesEnum.URI, "http//test-alert.forecast.io");
        
        return data;
    }
    
    public static JSONObject[] provideValidJSON() {
        
        JSONObject[] returnList;
        
        ArrayList  list = new ArrayList();
        JSONObject json = provideProperOptimizedJSON();
        
        
        // add values
        list.add(json);
       
        // mess up some values
        json = provideProperOptimizedJSON();
        json.element(FIOAlertPropertiesEnum.TITLE, "");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIOAlertPropertiesEnum.DESCRIPTION, "");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element(FIOAlertPropertiesEnum.URI, "");
        list.add(json);
        
        // removes some values
        json = provideProperOptimizedJSON();
        json.discard(FIOAlertPropertiesEnum.DESCRIPTION);
        list.add(json);
        
        // add unnaccessary values
        json = provideProperOptimizedJSON();
        json.put("key1", "stringValue");
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element("key2", 10);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element("key3", 100L);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element("key4", 1000D);
        list.add(json);
        
        json = provideProperOptimizedJSON();
        json.element("key5", new JSONObject());
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
        
        json = new JSONObject();
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
    public void testConstructor_withNoInput_expectedEmptyInstance() {
        
        FIOAlert alert = new FIOAlert();
        
        // test if properties return the good value
        assertNull(alert.getTitle());
        assertTrue(alert.getExpires() < 0);
        assertNull(alert.getDescription());
        assertNull(alert.getURI());
        
        // test methods
        assertTrue(alert.isEmpty());
    }
    
    public void testConstructor_withValidInputJSON_expectedNotEmptyInstance() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideValidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(inputs[i]);
            
            // test returns values of the properties        
            assertNotNull(alert.getTitle());
            assertFalse(alert.getExpires() < 0);
            assertNotNull(alert.getDescription());
            assertNotNull(alert.getURI());
            
            // test methods
            assertFalse(alert.isEmpty());
        }
    }
    
    public void testConstructor_withInvalidInputJSON_expectedEmptyInstance() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideInvalidJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(inputs[i]);
            
            // test returns values of the properties        
            assertNull(alert.getTitle());
            assertTrue(alert.getExpires() < 0);
            assertNull(alert.getDescription());
            assertNull(alert.getURI());
            
            // test methods
            assertTrue(alert.isEmpty());
        }
    }
    
    
    // test isValid method
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
    
    
    // test update method
    public void testUpdate_withValidInput_expectSuccess() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(provideProperOptimizedJSON());
            assertTrue(alert.update(inputs[i]));
        }
    }
    
    public void testUpdate_withInvalidInput_expectFailure() {

        FIOAlert     alert;
        JSONObject[] inputs = provideInvalidJSON();

        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(provideProperOptimizedJSON());
            assertFalse(alert.update(inputs[i]));
        }
    }
    
    
    // test clear method
    public void testClearMethod_expectSuccess() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(inputs[i]);
            alert.clear();
            assertTrue(alert.isEmpty());
        }
    }
    
    
    // isEmpty method
    public void testIsEmpty_expectSuccess() {
        
        FIOAlert     alert;
        JSONObject[] inputs = provideJSON();
        
        for (int i = 0; i < inputs.length; i++) {
            
            alert = new FIOAlert(inputs[i]);
            
            if (alert.getTitle() == null
                   && alert.getExpires() < 0
                   && alert.getDescription() == null
                   && alert.getURI() == null) {
                
                assertTrue(alert.isEmpty());
            } else
                assertFalse(alert.isEmpty());
        }
    }
    
    
    // test equals method
    public void testEquals_withNullInstance_expectFail() {

        FIOAlert alert1 = new FIOAlert();

        assertFalse(alert1.equals(null));
        assertFalse(alert1 == null);
    }
    
    public void testEquals_withNoInput_expectSuccess() {
        
        FIOAlert alert1 = new FIOAlert(),
                 alert2 = new FIOAlert();

        assertTrue(alert1.equals(alert2));
        assertFalse(alert1 == alert2);
    }
    
    public void testEquals_withSameInput_expectSuccess() {
        
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

    public void testEquals_withSameStringAsideTrailingSpace_expectFail() {
        
        FIOAlert     alert1,
                     alert2;
        JSONObject[] inputs = provideValidJSON();
        JSONObject   input;
        
        for (int i = 0; i < inputs.length; i++) {
                
                alert1 = new FIOAlert(inputs[i]);
                
                input = JSONObject.fromObject(inputs[i]);
                input.element(FIOAlertPropertiesEnum.TITLE, input.getString(FIOAlertPropertiesEnum.TITLE) + " ");
                
                alert2 = new FIOAlert(input);
                
                assertFalse(alert1.equals(alert2));
                assertFalse(alert1 == alert2);
        }
        
        for (int i = 0; i < inputs.length; i++) {

            alert1 = new FIOAlert(inputs[i]);

            input = JSONObject.fromObject(inputs[i]);
            input.element(FIOAlertPropertiesEnum.EXPIRES, (input.getLong(FIOAlertPropertiesEnum.EXPIRES) + 1));
            alert2 = new FIOAlert(input);

            assertFalse(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }
        
        for (int i = 0; i < inputs.length; i++) {

            alert1 = new FIOAlert(inputs[i]);

            input = JSONObject.fromObject(inputs[i]);
            input.element(FIOAlertPropertiesEnum.DESCRIPTION, input.get(FIOAlertPropertiesEnum.DESCRIPTION) + " ");
            alert2 = new FIOAlert(input);

            assertFalse(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }

        for (int i = 0; i < inputs.length; i++) {

            alert1 = new FIOAlert(inputs[i]);

            input = JSONObject.fromObject(inputs[i]);
            input.element(FIOAlertPropertiesEnum.URI, input.getString(FIOAlertPropertiesEnum.URI) + " ");
            alert2 = new FIOAlert(input);

            assertFalse(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }
    }

    public void testEquals_withSameStringsIfCaseIsensitive_expectFail() {
        
        FIOAlert     alert1,
                     alert2;
        JSONObject[] inputs = provideValidJSON();
        JSONObject   input1,
                     input2;
        
        for (int i = 0; i < inputs.length; i++) {
                
                input1 = JSONObject.fromObject(inputs[i]);
                input1.element(FIOAlertPropertiesEnum.TITLE, input1.get(FIOAlertPropertiesEnum.TITLE) + "a");
                
                alert1 = new FIOAlert(inputs[i]);
                
                input2 = JSONObject.fromObject(inputs[i]);
                input2.element(FIOAlertPropertiesEnum.TITLE, input2.get(FIOAlertPropertiesEnum.TITLE) + "A");
                
                alert2 = new FIOAlert(input2);
                
                assertFalse(alert1.equals(alert2));
                assertFalse(alert1 == alert2);
        }
        
        for (int i = 0; i < inputs.length; i++) {

            input1 = JSONObject.fromObject(inputs[i]);
            input1.element(FIOAlertPropertiesEnum.DESCRIPTION, input1.get(FIOAlertPropertiesEnum.DESCRIPTION) + "a");
            
            alert1 = new FIOAlert(inputs[i]);
            
            input2 = JSONObject.fromObject(inputs[i]);
            input2.element(FIOAlertPropertiesEnum.DESCRIPTION, input2.get(FIOAlertPropertiesEnum.DESCRIPTION) + "A");
            
            alert2 = new FIOAlert(input2);
            
            assertFalse(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }

        for (int i = 0; i < inputs.length; i++) {

            input1 = JSONObject.fromObject(inputs[i]);
            input1.element(FIOAlertPropertiesEnum.URI, input1.get(FIOAlertPropertiesEnum.URI) + "a");
            
            alert1 = new FIOAlert(inputs[i]);
            
            input2 = JSONObject.fromObject(inputs[i]);
            input2.element(FIOAlertPropertiesEnum.URI, input2.get(FIOAlertPropertiesEnum.URI) + "A");
            
            alert2 = new FIOAlert(input2);
            
            assertFalse(alert1.equals(alert2));
            assertFalse(alert1 == alert2);
        }
    }
}
