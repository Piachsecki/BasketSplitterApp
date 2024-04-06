package util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.domain.Delivery;
import org.example.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class DataCreator {
    public static Map<Delivery, List<Product>> createNonEmptyMap() {
        return Map.of(
                new Delivery(
                        RandomStringUtils.randomAlphabetic(3)),
                createNonEmptyListOfProducts());
    }


    public static List<Product> createNonEmptyListOfProducts() {
        return new ArrayList<>(
                List.of(
                        new Product(
                                RandomStringUtils.randomAlphabetic(3),
                                null)));
    }

    public static Map<Delivery, List<Product>> createEmptyMap() {
        return Map.of(
                new Delivery(
                        RandomStringUtils.randomAlphabetic(3)),
                List.of());
    }

    public static List<String> createBasket1() {
        return List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis", "Mushroom - Porcini Frozen",
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
        );
    }


    public static List<String> createBasket2() {
        return List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole"
        );
    }



    public static List<String> createIncorrectBasket() {
        return List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Not existing product",
                "Nut - Almond, Blanched, Whole"
        );
    }


    public static Map<Delivery, List<Product>> createSolutionMapForBasket1() {
        return new HashMap<>(Map.of(
                new Delivery("Same day delivery"), List.of(
                        new Product("Sauce - Mint", null),
                        new Product("Numi - Assorted Teas", null),
                        new Product("Garlic - Peeled", null)),

                new Delivery("Express Collection"), List.of(
                        new Product("Fond - Chocolate", null),
                        new Product("Chocolate - Unsweetened", null),
                        new Product("Nut - Almond, Blanched", null),
                        new Product("Nut - Almond, Blanched, Whole", null),
                        new Product("Haggis", null),
                        new Product("Mushroom - Porcini Frozen", null),
                        new Product("Longan", null),
                        new Product("Bag Clear 10 Lb", null),
                        new Product("Nantucket - Pomegranate Pear", null),
                        new Product("Puree - Strawberry", null),
                        new Product("Apples - Spartan", null),
                        new Product("Bagel - Whole White Sesame", null),
                        new Product("Tea - Apple Green Tea", null)),

                new Delivery("Courier"), List.of(
                        new Product("Cake - Miini Cheesecake Cherry", null)
                )));

    }

    public static Map<Delivery, List<Product>> createSolutionMapForBasket2() {
        return new HashMap<>(Map.of(
                new Delivery("Pick-up point"), List.of(
                        new Product("Fond - Chocolate", null),
                        new Product("Chocolate - Unsweetened", null),
                        new Product("Nut - Almond, Blanched, Whole", null))));

    }


    public static Map<Delivery, List<Product>> createMapForBasket1() {

        return null;
    }


    public static Map<Delivery, List<Product>> createMapForBasket2() {
        return new HashMap<>(Map.of(
                new Delivery("Pick-up point"), List.of(
                        new Product("Fond - Chocolate", null),
                        new Product("Chocolate - Unsweetened", null),
                        new Product("Nut - Almond, Blanched, Whole", null)),

                new Delivery("Parcel locker"), List.of(
                        new Product("Chocolate - Unsweetened", null),
                        new Product("Nut - Almond, Blanched, Whole", null)),

                new Delivery("Same day delivery"), List.of(
                        new Product("Chocolate - Unsweetened", null)),

                new Delivery("Mailbox delivery"), List.of(
                        new Product("Fond - Chocolate", null),
                        new Product("Chocolate - Unsweetened", null)),

                new Delivery("Express Collection"), List.of(
                        new Product("Fond - Chocolate", null),
                        new Product("Chocolate - Unsweetened", null),
                        new Product("Nut - Almond, Blanched, Whole", null)),

                new Delivery("Courier"), List.of(
                        new Product("Chocolate - Unsweetened", null)),

                new Delivery("Next day shipping"), List.of(
                        new Product("Chocolate - Unsweetened", null)),

                new Delivery("In-store pick-up"), List.of(
                        new Product("Chocolate - Unsweetened", null))

        ));

    }


}
