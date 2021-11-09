package ua.knu.tarkhan.oop.lab2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Hotel {
    private Integer stars;
    private Integer sits;
    private Boolean isFood;
    private Boolean isTV;
}
