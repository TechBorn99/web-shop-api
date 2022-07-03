package com.webshop.webshop.services.user;

import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.repositories.WebShopSellerRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityNotFoundException;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import com.webshop.webshop.web.rest.user.payload.response.WebShopSellerResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebShopSellerService {

    @Autowired
    private WebShopSellerRepository webShopSellerRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public WebShopSeller findOneByUuid(String uuid) {
        return this.webShopSellerRepository.findOneByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException(ExceptionErrorCodeType.WEBSHOP_SELLER_NOT_FOUND_BY_UUID,
                "WebShop seller with the uuid: " + uuid + " not found.")
        );
    }

    public WebShopSellerResponseDTO getByUuid(String uuid) {
        return this.modelMapper.map(this.findOneByUuid(uuid), WebShopSellerResponseDTO.class);
    }

}
