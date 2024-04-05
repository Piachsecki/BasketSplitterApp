package org.example.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Product {
    private String name;
    private List<Delivery> deliveryMethods;

    public String getName() {
        return this.name;
    }

    public List<Delivery> getDeliveryMethods() {
        return this.deliveryMethods;
    }


    @Override
    public String toString() {
        return name;

    }
}
