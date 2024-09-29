package ru.gleb.FirstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Positions;
import ru.gleb.FirstApp.provider.ChronoProvider;

@Service
public class QuarterlyBonusService implements BonusCalculationService {

    private final ChronoProvider chrono;

    @Autowired
    public QuarterlyBonusService(
        @Qualifier("CurrentChronoProvider") ChronoProvider chrono
    ) {
        this.chrono = chrono;
    }

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        if (!positions.isManager()) return 0.0;

        return salary * bonus * chrono.getDaysInQuarter() * positions.getPositionCoefficient() / workDays;
    }
}
