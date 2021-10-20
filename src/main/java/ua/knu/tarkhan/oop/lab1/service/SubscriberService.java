package ua.knu.tarkhan.oop.lab1.service;

import ua.knu.tarkhan.oop.lab1.dao.SubscriberDao;

public class SubscriberService {
    private final SubscriberDao subscriberDao;

    public SubscriberService() {
        this.subscriberDao = new SubscriberDao();
    }

    public SubscriberService(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    public long countAllSubscriber() {
        return subscriberDao.findAll().size();
    }
}
