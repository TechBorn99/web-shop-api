package com.webshop.webshop.services.user;

import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.repositories.WebShopSellerRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopSellerService {

    @Autowired
    private WebShopSellerRepository webShopSellerRepository;

    public WebShopSeller save(WebShopSeller webShopSeller) {
        try {
            return this.webShopSellerRepository.save(webShopSeller);
        } catch (Exception ex) {
            throw new EntityNotSavedException(
                    ExceptionErrorCodeType.WEBSHOP_SELLER_NOT_SAVED,
                    "Webshop seller not saved"
            );
        }
    }

}
