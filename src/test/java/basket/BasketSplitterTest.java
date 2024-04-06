package basket;

import com.google.common.collect.Maps;
import org.example.basket.BasketSplitter;
import org.example.domain.Delivery;
import org.example.domain.Product;
import org.example.exceptions.NoSuchProductException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DataCreator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Main.CONFIG_FILE_PATH;
import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {
    private BasketSplitter bankSplitter;


    //done
    @Test
    @BeforeEach
    public void init() {
        bankSplitter = new BasketSplitter(CONFIG_FILE_PATH);
        Assertions.assertNotNull(bankSplitter);
        Assertions.assertNotNull(bankSplitter.getProducts());
    }


    //done
    @Test
    void theListIsNotEmpty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Map<Delivery, List<Product>> nonEmptyMap = DataCreator.createEmptyMap();


        //when
        Method method = BasketSplitter.class.getDeclaredMethod("theListIsNotEmpty", Map.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(bankSplitter, nonEmptyMap);

        //then
        assertFalse(result);

    }

    //done
    @Test
    void theListIsEmpty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Map<Delivery, List<Product>> nonEmptyMap = DataCreator.createNonEmptyMap();

        //when
        Method method = BasketSplitter.class.getDeclaredMethod("theListIsNotEmpty", Map.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(bankSplitter, nonEmptyMap);


        //then
        assertTrue(result);

    }


    @Test
    void findTheMostCommonDeliveryMethodForBasket1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Map<Delivery, List<Product>> nonEmptyMap = DataCreator.createSolutionMapForBasket1();

        Delivery expected = null;
        int maxProducts = 0;

        for (Map.Entry<Delivery, List<Product>> entry : nonEmptyMap.entrySet()) {
            int currentSize = entry.getValue().size();
            if (currentSize > maxProducts) {
                maxProducts = currentSize;
                expected = entry.getKey();
            }
        }

        //when
        Method method = BasketSplitter.class.getDeclaredMethod("findTheMostCommonDeliveryMethod", Map.class);
        method.setAccessible(true);
        Delivery result = (Delivery) method.invoke(bankSplitter, nonEmptyMap);


        //then
        Assertions.assertEquals(expected, result);

    }


    //done
    @Test
    void findTheMostCommonDeliveryMethodForBasket2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Map<Delivery, List<Product>> nonEmptyMap = DataCreator.createSolutionMapForBasket2();

        Delivery expected = null;
        int maxProducts = 0;

        for (Map.Entry<Delivery, List<Product>> entry : nonEmptyMap.entrySet()) {
            int currentSize = entry.getValue().size();
            if (currentSize > maxProducts) {
                maxProducts = currentSize;
                expected = entry.getKey();
            }
        }

        //when
        Method method = BasketSplitter.class.getDeclaredMethod("findTheMostCommonDeliveryMethod", Map.class);
        method.setAccessible(true);
        Delivery result = (Delivery) method.invoke(bankSplitter, nonEmptyMap);


        //then
        Assertions.assertEquals(expected, result);

    }


    @Test
    void makeMapOutOfStringList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        List<String> basket1 = DataCreator.createBasket2();
        Map<Delivery, List<Product>> expected = DataCreator.createMapForBasket2();

        //when
        Method method = BasketSplitter.class.getDeclaredMethod("makeMapOutOfStringList", List.class);
        method.setAccessible(true);
        Map<Delivery, List<Product>> result = (Map<Delivery, List<Product>>) method.invoke(bankSplitter, basket1);

        System.out.println(expected);
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println(result);


        assertTrue(Maps.difference(expected, result).areEqual());
        assertEquals(expected.size(), result.size());


    }

    //done
    @Test
    void checkSplitFunctionWithCorrectBasket2() {
        //given
        List<String> basket2 = DataCreator.createBasket2();
        //when
        Map<String, List<String>> result = bankSplitter.split(basket2);

        //then
        Assertions.assertEquals(1, result.size());
    }


    //done
    @Test
    void checkSplitFunctionWithIncorrectProductsInside() {
        //given
        List<String> incorrectBasket = DataCreator.createIncorrectBasket();
        String incorrectProduct = "Not existing product";


        //when
        Exception exception = assertThrows(NoSuchProductException.class, () ->
                bankSplitter.split(incorrectBasket));

        //then
        System.out.println(exception.getMessage());
        assertEquals(String.format("There is no product: %s", incorrectProduct), exception.getMessage());
    }


    //done
    @Test
    void checkSplitFunctionWithEmptyList() {
        //given
        List<String> emptyList = new ArrayList<>();
        Map<String, List<String>> expected = new HashMap<>();


        //when
        Map<String, List<String>> result = bankSplitter.split(emptyList);

        //then
        assertTrue(Maps.difference(expected, result).areEqual());
        assertEquals(expected.size(), result.size());
    }


}