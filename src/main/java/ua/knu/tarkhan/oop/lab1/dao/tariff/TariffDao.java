package ua.knu.tarkhan.oop.lab1.dao.tariff;

import ua.knu.tarkhan.oop.lab1.domain.tariff.Tariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.TariffType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TariffDao implements TariffDaoInterface {
    @Override
    public String save(Tariff tariff) {
        return tariff.getTariffType().getDao().save(tariff);
    }

    @Override
    public List<Tariff> findAll() {
        return Arrays.stream(TariffType.values())
                .flatMap(tariffType -> tariffType.getDao().findAll().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Tariff> findById(String id) {
        return Arrays.stream(TariffType.values())
                .map(tariffType -> tariffType.getDao().findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Override
    public void deleteById(String id) {
        Arrays.stream(TariffType.values())
                .forEach(tariffType -> tariffType.getDao().deleteById(id));
    }
}
