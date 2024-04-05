package org.example.basket;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Delivery{
    private String nameOfTheDelivery;

    public String toString() {
        return nameOfTheDelivery;
    }
}
