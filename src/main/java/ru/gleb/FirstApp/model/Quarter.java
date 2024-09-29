package ru.gleb.FirstApp.model;

import java.time.Month;
import java.time.Year;

import static java.time.Month.*;

public enum Quarter {
    FIRST, SECOND, THIRD, FOURTH;

    public static int getDays(Quarter quarter, int year) {
        boolean isLeap = Year.isLeap(year);

        return switch (quarter) {
            case FIRST -> JANUARY.length(isLeap) + FEBRUARY.length(isLeap) + MARCH.length(isLeap);
            case SECOND -> APRIL.length(isLeap) + MAY.length(isLeap) + JUNE.length(isLeap);
            case THIRD -> JULY.length(isLeap) + AUGUST.length(isLeap) + SEPTEMBER.length(isLeap);
            case FOURTH -> OCTOBER.length(isLeap) + NOVEMBER.length(isLeap) + DECEMBER.length(isLeap);
        };
    }

    public static Quarter getQuarter(Month month) {
        return switch (month) {
            case JANUARY, FEBRUARY, MARCH -> FIRST;
            case APRIL, MAY, JUNE -> SECOND;
            case JULY, AUGUST, SEPTEMBER -> THIRD;
            case OCTOBER, NOVEMBER, DECEMBER -> FOURTH;
        };
    }
}
