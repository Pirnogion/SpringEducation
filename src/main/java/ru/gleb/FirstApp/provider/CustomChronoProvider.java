package ru.gleb.FirstApp.provider;

import ru.gleb.FirstApp.model.Quarter;
import ru.gleb.FirstApp.util.DateTimeUtils;

public class CustomChronoProvider implements ChronoProvider {

    private final Quarter quarter;
    private final int year;

    public CustomChronoProvider(Quarter quarter, int year) {
        this.quarter = quarter;
        this.year = year;
    }

    @Override
    public int getDaysInYear() {
        return DateTimeUtils.getDaysInYear(year);
    }

    @Override
    public int getDaysInQuarter() {
        return Quarter.getDays(quarter, year);
    }
}
