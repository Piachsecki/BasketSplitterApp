package org.example.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
public class Product {
    private String name;
    private List<Delivery> deliveryMethods;

}
