package ua.knu.tarkhan.oop.lab1.service;

import ua.knu.tarkhan.oop.lab1.dao.tariff.TariffDao;
import ua.knu.tarkhan.oop.lab1.domain.tariff.ContractTariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.PrepayTariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TariffServiceTest {
    TariffService tariffService;
    @Mock
    TariffDao tariffDao;

    @BeforeEach
    void setUp() {
        tariffService = new TariffService(tariffDao);
    }

    @Test
    void findAllSortedBySubscriptionFeeCallDaoTest() {
        tariffService.findAllSortedBySubscriptionFee();
        Mockito.verify(tariffDao).findAll();
    }

    @Test
    void findAllSortedBySubscriptionFeeReturnTheSameAsDaoTest() {
        when(tariffDao.findAll()).thenReturn(Collections.nCopies(17, new PrepayTariff("", BigDecimal.ONE, BigDecimal.TEN)));
        assertEquals(17, tariffService.findAllSortedBySubscriptionFee().size());
    }

    @Test
    void findAllSortedBySubscriptionFeeReturnSortedTest() {
        when(tariffDao.findAll()).thenReturn(List.of(
                new ContractTariff("test", BigDecimal.valueOf(13), BigDecimal.ONE),
                new PrepayTariff("test", BigDecimal.valueOf(5), BigDecimal.ONE),
                new ContractTariff("test", BigDecimal.valueOf(7), BigDecimal.ONE),
                new PrepayTariff("test", BigDecimal.valueOf(21), BigDecimal.ONE)
        ));

        List<Tariff> result = tariffService.findAllSortedBySubscriptionFee();

        assertEquals(BigDecimal.valueOf(5), result.get(0).getSubscriptionFee());
        assertEquals(BigDecimal.valueOf(7), result.get(1).getSubscriptionFee());
        assertEquals(BigDecimal.valueOf(13), result.get(2).getSubscriptionFee());
        assertEquals(BigDecimal.valueOf(21), result.get(3).getSubscriptionFee());
    }

    @Test
    void findAllBySubscriptionFeeInRangeThrowTest() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> tariffService.findAllBySubscriptionFeeInRange(BigDecimal.TEN, BigDecimal.ONE)
        );
    }

    @Test
    void findAllBySubscriptionFeeInRangeCallDaoTest() {
        tariffService.findAllBySubscriptionFeeInRange(BigDecimal.ONE, BigDecimal.TEN);
        Mockito.verify(tariffDao).findAll();
    }

    @Test
    void findAllBySubscriptionFeeInRangeFilterByValueTest() {
        var var1 = new ContractTariff("test", BigDecimal.valueOf(13), BigDecimal.ONE);
        var var2 = new ContractTariff("test", BigDecimal.valueOf(5), BigDecimal.ONE);
        var var3 = new ContractTariff("test", BigDecimal.valueOf(7), BigDecimal.ONE);
        var var4 = new ContractTariff("test", BigDecimal.valueOf(21), BigDecimal.ONE);
        when(tariffDao.findAll()).thenReturn(List.of(var1, var2, var3, var4));

        List<Tariff> result = tariffService.findAllBySubscriptionFeeInRange(BigDecimal.ONE, BigDecimal.TEN);

        assertEquals(2, result.size());
        assertTrue(result.contains(var2));
        assertTrue(result.contains(var3));
    }
}
