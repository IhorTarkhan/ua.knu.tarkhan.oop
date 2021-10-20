package ua.knu.tarkhan.oop.lab1.domain.tariff;

import java.math.BigDecimal;

public abstract class Tariff {
    private final String name;
    private final BigDecimal subscriptionFee;
    private final TariffType tariffType;

    protected Tariff(String name, BigDecimal subscriptionFee, TariffType tariffType) {
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.tariffType = tariffType;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSubscriptionFee() {
        return subscriptionFee;
    }

    public TariffType getTariffType() {
        return tariffType;
    }
}
