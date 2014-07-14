package com.apptitive.ramadan.utilities;

/**
 * Created by Sharif on 7/14/2014.
 */
public class Config {
    private static final String BASE_URL = "http:\\sharif.com";

    public static String getDensityUrl() {
        return BASE_URL + DpUtil.getDeviceDensity(100, 200);
    }
}
