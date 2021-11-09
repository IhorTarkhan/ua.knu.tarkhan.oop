package ua.knu.tarkhan.oop.lab2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Hotel {
    private Integer stars;
    private Integer sits;
    private Boolean isFood;
    private Boolean isTV;
}
