package test.forecastiolib;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

    public static Test suite() {

        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(FIODataBlockTestCase.class);
        suite.addTestSuite(FIODataPointTestCase.class);
        suite.addTestSuite(FIOFlagsTestCase.class);
        suite.addTestSuite(ForecastIOTestCase.class);
        suite.addTestSuite(FIOAlertTestCase.class);
        suite.addTestSuite(FIOAlertsTestCase.class);
        //$JUnit-END$
        return suite;
    }
}