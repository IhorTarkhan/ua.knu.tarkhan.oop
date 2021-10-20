package ua.knu.tarkhan.oop.lab1.service;

import ua.knu.tarkhan.oop.lab1.dao.SubscriberDao;
import ua.knu.tarkhan.oop.lab1.domain.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriberServiceTest {
    SubscriberService subscriberService;
    @Mock
    SubscriberDao subscriberDao;

    @BeforeEach
    void setUp() {
        subscriberService = new SubscriberService(subscriberDao);
    }

    @Test
    void countAllSubscriberCallDaoTest() {
        subscriberService.countAllSubscriber();
        Mockito.verify(subscriberDao).findAll();
    }

    @Test
    void countAllSubscriberReturnTheSameAsDaoTest() {
        when(subscriberDao.findAll()).thenReturn(Collections.nCopies(17, new Subscriber("", "")));
        assertEquals(17, subscriberService.countAllSubscriber());
    }
}
