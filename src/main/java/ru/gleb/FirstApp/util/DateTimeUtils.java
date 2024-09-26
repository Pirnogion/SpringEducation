package ru.gleb.FirstApp.util;

import java.text.SimpleDateFormat;

public class DateTimeUtils {

    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
}
