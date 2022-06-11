package com.webshop.webshop.utils.exceptions.consts;

public enum ExceptionErrorCodeType {

    EMAIL_NOT_FOUND(101), EMAIL_ALREADY_EXISTS(102),
    WEBSHOP_CLIENT_NOT_SAVED(201),
    WEBSHOP_SELLER_NOT_SAVED(301),
    WEBSHOP_ADMIN_NOT_SAVED(401);

    public final int CODE;

    ExceptionErrorCodeType(int code) {
        CODE = code;
    }
}
