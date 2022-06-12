package com.webshop.webshop.utils.exceptions.consts;

public enum ExceptionErrorCodeType {

    EMAIL_NOT_FOUND(101),
    EMAIL_NOT_SENT(401);


    public final int CODE;

    ExceptionErrorCodeType(int code) {
        CODE = code;
    }
}
