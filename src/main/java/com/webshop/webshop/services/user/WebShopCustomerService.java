package com.webshop.webshop.services.user;

import com.webshop.webshop.domain.user.WebShopCustomer;
import com.webshop.webshop.repositories.WebShopCustomerRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopCustomerService {

    @Autowired
    private WebShopCustomerRepository webShopCustomerRepository;

    public WebShopCustomer save(WebShopCustomer webShopCustomer) {
        try {
            return this.webShopCustomerRepository.save(webShopCustomer);
        } catch (Exception ex) {
            throw new EntityNotSavedException(
                    ExceptionErrorCodeType.WEBSHOP_CLIENT_NOT_SAVED,
                    "Webshop client not saved"
            );
        }
    }

}
