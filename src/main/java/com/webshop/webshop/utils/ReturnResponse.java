package com.webshop.webshop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReturnResponse<V> {

    public static <T> ResponseEntity<T> entityGet(T obj) {
        return new ResponseEntity<T>(obj, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> entityCreated(T obj) {
        return new ResponseEntity<T>(obj, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> entityUpdated(T obj) {
        return new ResponseEntity<T>(obj, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> entityDeleted(T obj) {
        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }

}
