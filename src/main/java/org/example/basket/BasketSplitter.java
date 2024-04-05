package org.example.basket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BasketSplitter {
    private List<Product> products;

    public BasketSplitter(String absolutePathToConfigFile) {

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, List<String>>>() {}.getType();
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
                if(Objects.nonNull(product)){
                    this.products.add(product);
                }else {
                    throw new RuntimeException("");
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, List<String>> split(List<String> items) {
        /* ... */

        return null;
    }


}
