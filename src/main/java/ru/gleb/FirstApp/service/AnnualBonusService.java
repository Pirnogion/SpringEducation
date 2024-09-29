package ru.gleb.FirstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Positions;
import ru.gleb.FirstApp.provider.ChronoProvider;

@Service
public class AnnualBonusService implements BonusCalculationService {

    private final ChronoProvider chrono;

    @Autowired
    public AnnualBonusService(
        @Qualifier("CurrentChronoProvider") ChronoProvider chrono
    ) {
        this.chrono = chrono;
    }

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        return salary * bonus * chrono.getDaysInYear() * positions.getPositionCoefficient() / workDays;
    }
}
