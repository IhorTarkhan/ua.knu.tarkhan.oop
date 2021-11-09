package ua.knu.tarkhan.oop.lab2.domain;

public record TouristVoucher(Type type,
                             String country,
                             Integer days,
                             Transport transport,
                             Hotel hotel,
                             Integer cost) {
}
