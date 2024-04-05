package org.example;

import org.example.basket.BasketSplitter;

public class Main {
    public static final String CONFIG_FILE_PATH = "src/main/resources/config.json";
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter(CONFIG_FILE_PATH);
    }
}