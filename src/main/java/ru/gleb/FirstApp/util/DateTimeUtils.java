package ru.gleb.FirstApp.util;

import ru.gleb.FirstApp.model.Quarter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;

public class DateTimeUtils {

    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static int getDaysInYear(int year) {
        return Year.of(year).length();
    }
}
