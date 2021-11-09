package ua.knu.tarkhan.oop.lab2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TouristVoucher {
    private Type type;
    private String country;
    private Integer days;
    private Transport transport;
    private Hotel hotel;
    private Integer cost;
}
