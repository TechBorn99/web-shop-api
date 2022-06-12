package com.webshop.webshop.utils.exceptions.types;

import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import org.springframework.http.HttpStatus;

public class EmailNotSentException extends BaseException {

    private static final long serialVersionUID = 1L;

    public EmailNotSentException(ExceptionErrorCodeType errorCodeType, String errorMessage) {
        super(errorCodeType, errorMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
