package test.forecastiolib;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.w3c.dom.ranges.RangeException;

import dme.forecastiolib.alerts.FIOAlert;
import dme.forecastiolib.alerts.FIOAlerts;
import dme.forecastiolib.enums.FIOAlertPropertiesEnum;

/**
 * Test case for the FIOAlerts class.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class FIOAlertsTestCase extends TestCase {

    
    //
    // FIELDS
    //
    // A proper standard FIOAlert instance.
    public FIOAlerts alerts;
    
    // Standard provider list length.
    protected final static int N = 50;
    
    
    //
    // DATA PROVIDERS
    //
    public static JSONArray provideProperOptimizedJSON() {

        JSONObject[] data = new JSONObject[N];
        
        for (int i = 0; i < N; i++)
            data[i] = FIOAlertTestCase.provideProperOptimizedJSON();

        return JSONArray.fromObject(data);
    }

    public static JSONArray[] provideValidJSON() {

        JSONArray[] data = new JSONArray[N];
        
        for (int i = 0; i < N; i++)
            data[i] = JSONArray.fromObject(FIOAlertTestCase.provideValidJSON());

        return data;
    }

    public static JSONArray[] provideInvalidJSON() {
        
        JSONArray[] data = new JSONArray[N];
        
        for (int i = 0; i < N; i++)
            data[i] = JSONArray.fromObject(FIOAlertTestCase.provideInvalidJSON());

        return data;
    }
    
    public static JSONArray[] provideJSON() {
        
        JSONArray[] data = new JSONArray[N];
        
        for (int i = 0; i < N; i++)
            data[i] = JSONArray.fromObject(FIOAlertTestCase.provideJSON());

        return data;
    }
    
    
    //
    // INHERITED METHODS
    //
    public String getName() { return "Test of the FIOAlerts class."; }
  
    protected void setUp() throws Exception {

        alerts = new FIOAlerts(provideProperOptimizedJSON());
        super.setUp();
    }
    
    
    //
    // TEST METHODS
    //
    
    // constructor tests
    public void testConstructor_withNoInput_expectedEmptyInstance() {

        FIOAlerts alerts = new FIOAlerts();
        assertTrue(alerts.isEmpty());
    }

    public void testConstructor_withValidInputJSONWithoutDuplicates_expectedNotEmptyInstance() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(inputs[i]);
            assertFalse(alerts.isEmpty());
        }
    }
    
    public void testConstructor_withValidInputJSONWithDuplicates_expectedNotEmptyAlertWithoutDuplicates() {

        FIOAlerts    alerts;
        JSONObject   source = FIOAlertTestCase.provideProperOptimizedJSON();
        JSONObject[] sourceList = {source, source, source};
        JSONArray    input  = JSONArray.fromObject(sourceList); // Array containing 3 identical JSON alerts objects
        
        alerts = new FIOAlerts(input);
        assertTrue(alerts.size() == 1);
    }

    public void testConstructor_withInvalidInputJSON_expectedEmptyInstance() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideInvalidJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(inputs[i]);
            assertTrue(alerts.isEmpty());
        }
    }

    
    // tests getAlert
    public void testGetAlert_withEmptyInstance_expectOutOfBoundExceptionRaised() {

        FIOAlerts alerts = new FIOAlerts();
        
        try {
            alerts.getAlert(0);
        } catch (ArrayIndexOutOfBoundsException exception) {
            // expected result
        } catch (Exception exception) {
            fail("Unexpected type of exception;\n\texception class: " + exception.getClass().toString() +
                 "\n\t exception message: " + exception.getMessage() +
                 "\n\texception cause: " + exception.getCause());
        }
    }

    public void testGetAlert_withNotEmptyInstance_withDataPointInRange_expectSuccess() {

        JSONArray[] inputs = provideValidJSON();
        FIOAlerts   alerts;
        
        for (int i = 0; i < inputs.length; i++) {
            
            alerts = new FIOAlerts(inputs[i]);
            
            for (int j = 0; j < alerts.size(); j++) {
            
                try {
                    alerts.getAlert(j);
                } catch (Exception exception) {
                    fail("Unexpected exception.");
                }
            }
        }
    }

    public void testGetAlert_withNotEmptyInstance_withDataPointOutOfRange_expectOutOfBoundExceptionRaised() {

        JSONArray[] inputs = provideValidJSON();
        FIOAlerts   alerts;
        
        for (int i = 0; i < inputs.length; i++) {
            
            alerts = new FIOAlerts(inputs[i]);
            
            try {
                alerts.getAlert(-1);
            } catch (ArrayIndexOutOfBoundsException exception) {
                // expected result
            } catch (Exception exception) {
                fail("Unexpected type of exception;\n\texception class: " + exception.getClass().toString() +
                     "\n\t exception message: " + exception.getMessage() +
                     "\n\texception cause: " + exception.getCause());
            }
            
            try {
                alerts.getAlert(alerts.size());
            } catch (ArrayIndexOutOfBoundsException exception) {
                // expected result
            } catch (Exception exception) {
                fail("Unexpected type of exception;\n\texception class: " + exception.getClass().toString() +
                     "\n\t exception message: " + exception.getMessage() +
                     "\n\texception cause: " + exception.getCause());
            }
        }
    }
    
    
    // tests size
    public void testSize_withEmptyInstance_expectZeroAlertFound() {
        
        FIOAlerts alerts = new FIOAlerts();
        assertTrue(alerts.size() == 0);
    }
    
    public void testSize_withNotEmptyInstance_expectSuccess() {

        JSONObject[] sourceList = {FIOAlertTestCase.provideProperOptimizedJSON(), null};
        FIOAlerts    alerts;
        
        while (true) {
            
            sourceList[1] = FIOAlertTestCase.provideProperOptimizedJSON();
            if (!sourceList[0].equals(sourceList[1]))
                break;
        }
        
        alerts = new FIOAlerts(JSONArray.fromObject(FIOAlertTestCase.provideProperOptimizedJSON()));
        assertTrue(alerts.size() == 1);
        
        alerts = new FIOAlerts(JSONArray.fromObject(sourceList));
        assertTrue(alerts.size() == 2);
    }


    // test update method
    //TODO
    public void testUpdate_withValidInput_expectSuccess() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideValidJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(provideProperOptimizedJSON());
            assertTrue(alerts.update(inputs[i]));
        }
    }

    public void testUpdate_withInvalidInput_expectFailure() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideInvalidJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(provideProperOptimizedJSON());
            assertFalse(alerts.update(inputs[i]));
        }
    }


    // test clear method
    public void testClearMethod_expectSuccess() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(inputs[i]);
            alerts.clear();
            assertTrue(alerts.isEmpty());
        }
    }

 
    // isEmpty method
    public void testIsEmpty_expectSuccess() {

        FIOAlerts   alerts;
        JSONArray[] inputs = provideJSON();

        for (int i = 0; i < inputs.length; i++) {

            alerts = new FIOAlerts(inputs[i]);

            if (alerts.size() > 0)
                assertFalse(alerts.isEmpty());
            else
                assertTrue(alerts.isEmpty());
        }
    }


    // test equals method
    public void testEquals_withNullInstance_expectFail() {

        FIOAlerts alert1 = new FIOAlerts();

        assertFalse(alert1.equals(null));
        assertFalse(alert1 == null);
    }
    
    public void testEquals_withNoInput_expectSuccess() {

        FIOAlerts alert1 = new FIOAlerts(),
                  alert2 = new FIOAlerts();

        assertTrue(alert1.equals(alert2));
        assertFalse(alert1 == alert2);
    }

    public void testEquals_withRandomInput() {

        FIOAlerts   alert1,
                    alert2;
        JSONArray[] inputs   = provideJSON();
        boolean     equals   = true;
        boolean     contains;
        
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs.length; j++) {
                
                alert1 = new FIOAlerts(inputs[i]);
                alert2 = new FIOAlerts(inputs[j]);

                if (alert1.size() != alert2.size())
                    assertFalse(alert1.equals(alert2));
                else {
                    
                    equals = true;
                    for (int k = 0; k < alert1.size(); k++) {
                        
                        contains = false;
                        // check if there is an identical alert in alerts2
                        for (int l = 0; l < alert1.size(); l++) {
                            
                            if (alert1.getAlert(k).equals(alert2.getAlert(l))) {
                                
                                contains = true;
                                break;
                            }
                        }
                        
                        if (contains = false) {
                            
                            equals = false;
                            break;
                        }
                    }
                    
                    // content of both alerts has been scanned
                    assertTrue(alert1.equals(alert2) == equals);
                }
            }
        }
    }
}