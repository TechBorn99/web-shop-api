package com.webshop.webshop.utils.exceptions;

import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private ExceptionErrorCodeType errorCode;

    public BaseException(ExceptionErrorCodeType errorCode, String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
        this.errorCode = errorCode;
    }

}
