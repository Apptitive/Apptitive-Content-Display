package com.apptitive.content_display.helper;

/**
 * Created by rayhan on 6/2/2014.
 */
public class Helper {
    public static int getArrayIndex(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item))
                return i;
        }
        return 0;
    }
}
