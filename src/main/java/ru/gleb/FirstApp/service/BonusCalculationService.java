package ru.gleb.FirstApp.service;

import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Positions;

@Service
public interface BonusCalculationService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
