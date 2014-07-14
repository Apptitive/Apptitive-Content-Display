package com.apptitive.ramadan.utilities;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Sharif on 7/14/2014.
 */
public class DpUtil {

    public static final String DENSITY_MDPI = "mdpi";
    public static final String DENSITY_HDPI = "hdpi";
    public static final String DENSITY_XHDPI = "xhdpi";
    public static final String DENSITY_XXHDPI = "xxhdpi";
    public static final String DENSITY_SW600 = "sw600";
    public static final String DENSITY_SW720 = "sw720";
    public static final String DENSITY_DEFAULT = DENSITY_MDPI;


    public static String getDeviceDensity(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();

        if (600 <= displaymetrics.widthPixels && displaymetrics.widthPixels < 720) {
            return DENSITY_SW600;
        } else if (displaymetrics.widthPixels >= 720) {
            return DENSITY_SW720;
        }

        switch (displaymetrics.densityDpi) {
            case DisplayMetrics.DENSITY_HIGH:
                return DENSITY_HDPI;
            case DisplayMetrics.DENSITY_MEDIUM:
                return DENSITY_MDPI;
            case DisplayMetrics.DENSITY_XHIGH:
                return DENSITY_XHDPI;
            case DisplayMetrics.DENSITY_XXHIGH:
                return DENSITY_XXHDPI;
            default:
                return DENSITY_DEFAULT;
        }
    }
}
