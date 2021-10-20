package ua.knu.tarkhan.oop.lab1.domain.tariff;

import java.math.BigDecimal;

public class PrepayTariff extends Tariff {
    private final BigDecimal prepay;

    public PrepayTariff(String name, BigDecimal subscriptionFee, BigDecimal prepay) {
        super(name, subscriptionFee, TariffType.PREPAY);
        this.prepay = prepay;
    }

    public BigDecimal getPrepay() {
        return prepay;
    }
}
