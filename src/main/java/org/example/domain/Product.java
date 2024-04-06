package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.Delivery;

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
