package ua.knu.tarkhan.oop.lab1.domain.tariff;

import ua.knu.tarkhan.oop.lab1.dao.tariff.ContractTariffDao;
import ua.knu.tarkhan.oop.lab1.dao.tariff.PrepayTariffDao;
import ua.knu.tarkhan.oop.lab1.dao.tariff.TariffDaoInterface;

public enum TariffType {
    CONTRACT(new ContractTariffDao()),
    PREPAY(new PrepayTariffDao());

    private final TariffDaoInterface dao;

    TariffType(TariffDaoInterface dao) {
        this.dao = dao;
    }

    public TariffDaoInterface getDao() {
        return dao;
    }
}
