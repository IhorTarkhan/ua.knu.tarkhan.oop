package ua.knu.tarkhan.oop.lab1.dao.tariff;

import ua.knu.tarkhan.oop.lab1.domain.tariff.ContractTariff;
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
class ContractTariffDaoTest {
    public static final ContractTariff TEST_TARIFF = new ContractTariff("aa", BigDecimal.ONE, BigDecimal.TEN);
    ContractTariffDao contractTariffDao;
    @Mock
    SQLConnector sqlConnector;

    @BeforeEach
    void setUp() {
        contractTariffDao = new ContractTariffDao(sqlConnector);
    }

    @Test
    void saveCallSqlConnectorTest() {
        when(sqlConnector.insert(anyString())).thenReturn("responseId");

        assertEquals("responseId", contractTariffDao.save(TEST_TARIFF));
        Mockito.verify(sqlConnector).insert(anyString());
    }

    @Test
    void findAllCallSqlConnectorTest() {
        List<Object> response = List.of(TEST_TARIFF);
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertIterableEquals(response, contractTariffDao.findAll());
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdEmptyResultTest() {
        List<Object> response = List.of();
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.empty(), contractTariffDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdNotEmptyResultTest() {
        List<Object> response = List.of(TEST_TARIFF);
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.of(TEST_TARIFF), contractTariffDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void deleteCallSqlConnectorTest() {
        contractTariffDao.deleteById("test");
        Mockito.verify(sqlConnector, times(2)).update(anyString());
    }
}
