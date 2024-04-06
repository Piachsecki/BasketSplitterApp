package org.example;

import org.example.basket.BasketSplitter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter(CONFIG_FILE_PATH);
        List<String> basket1 = new ArrayList<>(List.of(
                "Steak (300g)",
                "Carrots (1kg)",
                "Soda (24x330ml)",
                "AA Battery (4 Pcs.)",
                "Espresso Machine",
                "Garden Chair"));


        List<String> basket2 = new ArrayList<>(List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis",
                "Mushroom - Porcini Frozen",
                "Cake - Miini Cheesecake Cherry",
                "Sauce - Mint",
                "Longan",
                "Bag Clear 10 Lb",
                "Nantucket - Pomegranate Pear",
                "Puree - Strawberry",
                "Numi - Assorted Teas",
                "Apples - Spartan",
                "Garlic - Peeled",
                "Cabbage - Nappa",
                "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea"
));


        List<String> basket3 = new ArrayList<>(List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole"
        ));


        System.out.println(basketSplitter.split(basket3));
        System.out.println(basketSplitter.split(basket2));
    }
}