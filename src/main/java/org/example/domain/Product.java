package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

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


    public String toString() {

        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
