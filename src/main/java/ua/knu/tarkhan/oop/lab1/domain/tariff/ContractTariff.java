package ua.knu.tarkhan.oop.lab1.domain.tariff;

import java.math.BigDecimal;

public class ContractTariff extends Tariff {
    private final BigDecimal availableCredit;

    public ContractTariff(String name, BigDecimal subscriptionFee, BigDecimal availableCredit) {
        super(name, subscriptionFee, TariffType.CONTRACT);
        this.availableCredit = availableCredit;
    }

    public BigDecimal getAvailableCredit() {
        return availableCredit;
    }
}
