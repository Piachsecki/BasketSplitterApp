package org.example.basket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.example.domain.Delivery;
import org.example.domain.Product;
import org.example.exceptions.NoSuchProductException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class BasketSplitter {
    private final List<Product> products;

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


    public Map<String, List<String>>  split(List<String> items) {
        Map<Delivery, List<Product>> finalMap = new HashMap<>();
        Map<Delivery, List<Product>> productsForCertainDelivery = makeMapOutOfStringList(items);

        while (theListIsNotEmpty(productsForCertainDelivery)) {
            Delivery theMostCommonDeliveryMethod = findTheMostCommonDeliveryMethod(productsForCertainDelivery);
            finalMap.put(theMostCommonDeliveryMethod, new ArrayList<>(productsForCertainDelivery.get(theMostCommonDeliveryMethod)));
            removeProductsForDelivery(productsForCertainDelivery, theMostCommonDeliveryMethod);
        }

        return mapToExpectedStringMap(finalMap);
    }
    private Map<String, List<String>> mapToExpectedStringMap(Map<Delivery, List<Product>> finalMap) {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<Delivery, List<Product>> deliveryListEntry : finalMap.entrySet()) {
            List<String> listOfProducts = changeListOfProductsToListOfStrings(deliveryListEntry.getValue());
            result.put(
                    deliveryListEntry.getKey().toString(),
                    listOfProducts);
        }
        return result;
    }

    private List<String> changeListOfProductsToListOfStrings(List<Product> value) {
        return value.stream().map(Product::getName).collect(Collectors.toList());
    }

    private Map<Delivery, List<Product>> makeMapOutOfStringList(List<String> items) {
        Map<Delivery, List<Product>> result = new HashMap<>();
        Set<Delivery> allAvailableDeliveries = new HashSet<>();
        for (String item : items) {
            List<Delivery> deliveriesAvailableForSingleProduct = getOrderedProductsStream(item)
                    .map(Product::getDeliveryMethods)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            allAvailableDeliveries.addAll(deliveriesAvailableForSingleProduct);
        }


        for (Delivery delivery : allAvailableDeliveries) {
            result.put(delivery, new ArrayList<>());
        }


        for (String item : items) {
            Product product = getOrderedProductsStream(item)
                    .findFirst().orElseThrow(() -> new NoSuchProductException("There is no product: " + item));
            List<Delivery> deliveryMethods = product.getDeliveryMethods();
            for (Delivery delivery : deliveryMethods) {
                if (result.containsKey(delivery)) {
                    result.get(delivery).add(product);
                }
            }
        }
        return result;
    }

    private boolean theListIsNotEmpty(Map<Delivery, List<Product>> productsForCertainDelivery) {
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
