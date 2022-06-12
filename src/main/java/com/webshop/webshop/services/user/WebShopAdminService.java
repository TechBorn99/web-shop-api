package com.webshop.webshop.services.user;

import com.webshop.webshop.domain.user.WebShopAdmin;
import com.webshop.webshop.repositories.WebShopAdminRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopAdminService {

    @Autowired
    private WebShopAdminRepository webShopAdminRepository;

    public WebShopAdmin save(WebShopAdmin webShopAdmin) {
        try {
            return this.webShopAdminRepository.save(webShopAdmin);
        } catch (Exception ex) {
            throw new EntityNotSavedException(
                    ExceptionErrorCodeType.WEBSHOP_ADMIN_NOT_SAVED,
                    "Webshop admin not saved"
            );
        }
    }
}
