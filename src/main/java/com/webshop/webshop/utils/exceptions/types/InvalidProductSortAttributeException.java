package com.webshop.webshop.utils.exceptions.types;

import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import org.springframework.http.HttpStatus;

public class InvalidProductSortAttributeException extends BaseException {

    private static final long serialVersionUID = 1L;

    public InvalidProductSortAttributeException(ExceptionErrorCodeType errorCode, String errorMessage) {
        super(errorCode, errorMessage, HttpStatus.NOT_FOUND);
    }
}
