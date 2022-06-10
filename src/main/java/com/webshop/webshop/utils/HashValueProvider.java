package com.webshop.webshop.utils;

import java.util.UUID;

public class HashValueProvider {

    public static String generateHash() {
        return UUID.randomUUID().toString();
    }

}
