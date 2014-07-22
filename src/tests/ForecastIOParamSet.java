package tests;

import java.util.Date;

/**
 * Convenience class to ease the storing of ForecastIO constructor's parameters.
 *
 * @author Theo FIDRY (theo.fidry@gmail.com)
 */
public class ForecastIOParamSet {

    public String   key;
    public double   latitude,
    longitude;
    public String   lang;
    public Date     time;
    public String   units;
    public String[] exclude;
    public boolean  extand;

    public ForecastIOParamSet(String key, double latitude, double longitude) {

        this.key       = key;
        this.latitude  = latitude;
        this.longitude = longitude;
    }
}