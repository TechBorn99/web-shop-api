package com.webshop.webshop.utils.exceptions.consts;

public enum ExceptionErrorCodeType {

    EMAIL_NOT_FOUND(101), EMAIL_ALREADY_EXISTS(102), SIGNIN_WRONG_PASSWORD(103), PHONE_NUMBER_INVALID(104),
    WEBSHOP_CLIENT_NOT_SAVED(201),
    WEBSHOP_SELLER_NOT_SAVED(301),
    WEBSHOP_ADMIN_NOT_SAVED(401),
    EMAIL_NOT_SENT(501),
    ACCOUNT_NOT_FOUND_BY_HASH(601), ACCOUNT_NOT_SAVED(602),
    ROLE_NOT_FOUND(702);

    public final int CODE;

    ExceptionErrorCodeType(int code) {
        CODE = code;
    }
}
