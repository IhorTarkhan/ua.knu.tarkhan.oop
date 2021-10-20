package ua.knu.tarkhan.oop.lab1.dao.tariff;

import ua.knu.tarkhan.oop.lab1.domain.tariff.PrepayTariff;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrepayTariffDaoTest {
    public static final PrepayTariff TEST_TARIFF = new PrepayTariff("aa", BigDecimal.ONE, BigDecimal.TEN);
    PrepayTariffDao prepayTariffDao;
    @Mock
    SQLConnector sqlConnector;

    @BeforeEach
    void setUp() {
        prepayTariffDao = new PrepayTariffDao(sqlConnector);
    }

    @Test
    void saveCallSqlConnectorTest() {
        when(sqlConnector.insert(anyString())).thenReturn("responseId");

        assertEquals("responseId", prepayTariffDao.save(TEST_TARIFF));
        Mockito.verify(sqlConnector).insert(anyString());
    }

    @Test
    void findAllCallSqlConnectorTest() {
        List<Object> response = List.of(TEST_TARIFF);
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertIterableEquals(response, prepayTariffDao.findAll());
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdEmptyResultTest() {
        List<Object> response = List.of();
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.empty(), prepayTariffDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdNotEmptyResultTest() {
        List<Object> response = List.of(TEST_TARIFF);
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.of(TEST_TARIFF), prepayTariffDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void deleteCallSqlConnectorTest() {
        prepayTariffDao.deleteById("test");
        Mockito.verify(sqlConnector, times(2)).update(anyString());
    }
}
