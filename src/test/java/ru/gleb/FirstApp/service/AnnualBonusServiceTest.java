package ru.gleb.FirstApp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gleb.FirstApp.model.Positions;
import ru.gleb.FirstApp.model.Quarter;
import ru.gleb.FirstApp.provider.CustomChronoProvider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Проверка расчета годовой премии")
class AnnualBonusServiceTest {

    @Test
    @DisplayName("Високосный год")
    void testLeapYear() {
        var service = new AnnualBonusService(new CustomChronoProvider(Quarter.FIRST, 2024));
        var result = service.calculate(Positions.HR, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(361481.48148148146);
    }

    @Test
    @DisplayName("Невисокосный год")
    void testNonLeapYear() {
        var service = new AnnualBonusService(new CustomChronoProvider(Quarter.FIRST, 2023));
        var result = service.calculate(Positions.HR, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(360493.8271604938);
    }
}