package ru.gleb.FirstApp.provider;

import org.springframework.stereotype.Component;

@Component
public interface ChronoProvider {

    int getDaysInYear();
    int getDaysInQuarter();
}
