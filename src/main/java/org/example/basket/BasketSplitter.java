package org.example.basket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasketSplitter {
    private List<Product> products;

    public BasketSplitter(String absolutePathToConfigFile) {
        products = new ArrayList<>();

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        try {
            String text = Files.readString(Paths.get(absolutePathToConfigFile));

            Map<String, List<String>> productMap = gson.fromJson(text, mapType);

            for (Map.Entry<String, List<String>> entry : productMap.entrySet()) {
                String productName = entry.getKey();
                List<Delivery> deliveryMethods = new ArrayList<>();

                for (String delivery : entry.getValue()) {
                    deliveryMethods.add(new Delivery(delivery));
                }
                Product product = new Product(productName, deliveryMethods);
                this.products.add(product);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, List<String>> split(List<String> items) {
        Set<Delivery> allAvailableDeliveries = new HashSet<>();
        Map<Delivery, List<Product>> finalMap = new HashMap<>();

        for (String item : items) {
            List<Delivery> deliveriesAvailableForSingleProduct = getOrderedProductsStream(item)
                    .map(Product::getDeliveryMethods)
                    .flatMap(List::stream)
                    .toList();

            allAvailableDeliveries.addAll(deliveriesAvailableForSingleProduct);
        }
        Map<Delivery, List<Product>> productsForCertainDelivery = new HashMap<>();
        for (Delivery delivery : allAvailableDeliveries) {
            productsForCertainDelivery.put(delivery, new ArrayList<>());
        }





        for (String item : items) {
            Product product = getOrderedProductsStream(item)
                    .findFirst().orElseThrow(() -> new RuntimeException("There is no product: " + item));
            for (Delivery delivery : product.getDeliveryMethods()) {
                if(productsForCertainDelivery.containsKey(delivery)){
                    productsForCertainDelivery.get(delivery).add(product);
                }
            }
        }

//        for (Map.Entry<Delivery, List<Product>> deliveryListEntry : productsForCertainDelivery.entrySet()) {
//            deliveryListEntry.
//            finalMap.put(deliveryListEntry.getKey(), deliveryListEntry.getValue());
//            productsForCertainDelivery.
//            System.out.println(deliveryListEntry.getValue().size());
//        }


        System.out.println(productsForCertainDelivery);
        System.out.println(allAvailableDeliveries);

        return null;
    }

    private Stream<Product> getOrderedProductsStream(String item) {
        return products.stream()
                .filter(product -> product.getName().equals(item));
    }


}
