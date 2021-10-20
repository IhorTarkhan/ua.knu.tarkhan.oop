package ua.knu.tarkhan.oop.lab1.dao;

import ua.knu.tarkhan.oop.lab1.domain.Subscriber;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriberDaoTest {
    SubscriberDao subscriberDao;
    @Mock
    SQLConnector sqlConnector;

    @BeforeEach
    void setUp() {
        subscriberDao = new SubscriberDao(sqlConnector);
    }

    @Test
    void saveCallSqlConnectorTest() {
        when(sqlConnector.insert(anyString())).thenReturn("responseId");

        assertEquals("responseId", subscriberDao.save(new Subscriber("aa", "bb")));
        Mockito.verify(sqlConnector).insert(anyString());
    }

    @Test
    void findAllCallSqlConnectorTest() {
        List<Object> response = List.of(new Subscriber("aa", "bb"));
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertIterableEquals(response, subscriberDao.findAll());
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdEmptyResultTest() {
        List<Object> response = List.of();
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.empty(), subscriberDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void findByIdNotEmptyResultTest() {
        List<Object> response = List.of(new Subscriber("aa", "bb"));
        when(sqlConnector.select(anyString(), any())).thenReturn(response);

        assertEquals(Optional.of(new Subscriber("aa", "bb")), subscriberDao.findById("test"));
        Mockito.verify(sqlConnector).select(anyString(), any());
    }

    @Test
    void deleteCallSqlConnectorTest() {
        subscriberDao.deleteById("test");
        Mockito.verify(sqlConnector, times(2)).update(anyString());
    }
}
