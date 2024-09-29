package ru.gleb.FirstApp.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.gleb.FirstApp.model.Quarter;
import ru.gleb.FirstApp.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.Year;

@Component
@Qualifier("CurrentChronoProvider")
public class CurrentChronoProvider implements ChronoProvider {

    @Override
    public int getDaysInYear() {
        return DateTimeUtils.getDaysInYear( Year.now().getValue() );
    }

    @Override
    public int getDaysInQuarter() {
        LocalDate now = LocalDate.now();
        return Quarter.getDays(Quarter.getQuarter(now.getMonth()), now.getYear());
    }
}
