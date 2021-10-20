package ua.knu.tarkhan.oop.lab1.service;

import ua.knu.tarkhan.oop.lab1.dao.tariff.TariffDao;
import ua.knu.tarkhan.oop.lab1.domain.tariff.Tariff;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    private final TariffDao tariffDao;

    public TariffService() {
        this.tariffDao = new TariffDao();
    }

    public TariffService(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    public List<Tariff> findAllSortedBySubscriptionFee() {
        return tariffDao.findAll().stream()
                .sorted(Comparator.comparing(Tariff::getSubscriptionFee))
                .collect(Collectors.toList());
    }

    public List<Tariff> findAllBySubscriptionFeeInRange(BigDecimal min, BigDecimal max) {
        if (max.compareTo(min) < 0) {
            throw new IllegalArgumentException();
        }
        return tariffDao.findAll().stream()
                .filter(it -> min.compareTo(it.getSubscriptionFee()) < 0)
                .filter(it -> max.compareTo(it.getSubscriptionFee()) > 0)
                .collect(Collectors.toList());
    }
}
