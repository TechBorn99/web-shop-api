package com.webshop.webshop.utils.exceptions.types;

import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import org.springframework.http.HttpStatus;

public class InvalidPhoneNumberException extends BaseException {

    private static final long serialVersionUID = 1L;

    public InvalidPhoneNumberException(ExceptionErrorCodeType errorCode, String errorMessage) {
        super(errorCode, errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
