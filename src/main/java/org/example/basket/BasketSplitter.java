package org.example.basket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.domain.Delivery;
import org.example.domain.Product;
import org.example.exceptions.NoSuchProductException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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


    public Map<Delivery, List<Product>> split(List<String> items) {
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
                    .findFirst().orElseThrow(() -> new NoSuchProductException("There is no product: " + item));
            List<Delivery> deliveryMethods = product.getDeliveryMethods();
            for (Delivery delivery : deliveryMethods) {
                if (productsForCertainDelivery.containsKey(delivery)) {
                    productsForCertainDelivery.get(delivery).add(product);
                }
            }
        }

        while (allListsEmpty(productsForCertainDelivery)) {
            Delivery theMostCommonDeliveryMethod = findTheMostCommonDeliveryMethod(productsForCertainDelivery);
            finalMap.put(theMostCommonDeliveryMethod, new ArrayList<>(productsForCertainDelivery.get(theMostCommonDeliveryMethod)));
            removeProductsForDelivery(productsForCertainDelivery, theMostCommonDeliveryMethod);
        }

        return finalMap;
    }

    private boolean allListsEmpty(Map<Delivery, List<Product>> productsForCertainDelivery) {
        for (List<Product> productList : productsForCertainDelivery.values()) {
            if (!productList.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    private void removeProductsForDelivery(Map<Delivery, List<Product>> productsForCertainDelivery, Delivery delivery) {
        if (Objects.nonNull(delivery)) {
            for (List<Product> productList : productsForCertainDelivery.values()) {
                productList.removeIf(product -> product.getDeliveryMethods().contains(delivery));
            }
        }
    }


    private Delivery findTheMostCommonDeliveryMethod(Map<Delivery, List<Product>> productsForCertainDelivery) {
        Delivery deliveryWithMaxProducts = null;
        int maxProducts = 0;

        for (Map.Entry<Delivery, List<Product>> entry : productsForCertainDelivery.entrySet()) {
            int currentSize = entry.getValue().size();
            if (currentSize > maxProducts) {
                maxProducts = currentSize;
                deliveryWithMaxProducts = entry.getKey();
            }
        }
        return deliveryWithMaxProducts;

    }

    private Stream<Product> getOrderedProductsStream(String item) {
        return products.stream()
                .filter(product -> product.getName().equals(item));
    }


}
