package com.apptitive.content_display.utilities;

import android.content.Context;

/**
 * Created by Sharif on 7/14/2014.
 */
public class Config {
    private static final String BASE_URL = "http://www.json-generator.com/api/json/get/chGFSfyFBu?indent=2";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getDensityUrl(Context context) {
        return BASE_URL + DpUtil.getDeviceDensity(context);
    }
}
