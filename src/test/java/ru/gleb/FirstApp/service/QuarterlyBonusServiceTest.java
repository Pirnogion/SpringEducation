package ru.gleb.FirstApp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gleb.FirstApp.model.Positions;
import ru.gleb.FirstApp.model.Quarter;
import ru.gleb.FirstApp.provider.CustomChronoProvider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Проверка расчета квартальной премии")
class QuarterlyBonusServiceTest {

    @Test
    @DisplayName("Не менеджер")
    void testNonManager() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.FIRST, 2024));
        var result = service.calculate(Positions.HR, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Менеджер, первый квартал, високосный год")
    void testManagerFirstQuarterLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.FIRST, 2024));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(194732.51028806585);
    }

    @Test
    @DisplayName("Менеджер, второй квартал, високосный год")
    void testManagerSecondQuarterLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.SECOND, 2024));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(194732.51028806585);
    }

    @Test
    @DisplayName("Менеджер, третий квартал, високосный год")
    void testManagerThirdQuarterLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.THIRD, 2024));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(196872.4279835391);
    }

    @Test
    @DisplayName("Менеджер, четвертый квартал, високосный год")
    void testManagerFourthQuarterLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.FOURTH, 2024));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(196872.4279835391);
    }

    @Test
    @DisplayName("Менеджер, первый квартал, невисокосный год")
    void testManagerFirstQuarterNonLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.FIRST, 2023));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(192592.59259259258);
    }

    @Test
    @DisplayName("Менеджер, второй квартал, невисокосный год")
    void testManagerSecondQuarterNonLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.SECOND, 2023));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(194732.51028806585);
    }

    @Test
    @DisplayName("Менеджер, третий квартал, невисокосный год")
    void testManagerThirdQuarterNonLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.THIRD, 2023));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(196872.4279835391);
    }

    @Test
    @DisplayName("Менеджер, четвертый квартал, невисокосный год")
    void testManagerFourthQuarterNonLeapYear() {
        var service = new QuarterlyBonusService(new CustomChronoProvider(Quarter.FOURTH, 2023));
        var result = service.calculate(Positions.TL, 100000.00, 2.0, 243);

        assertThat(result).isEqualTo(196872.4279835391);
    }
}