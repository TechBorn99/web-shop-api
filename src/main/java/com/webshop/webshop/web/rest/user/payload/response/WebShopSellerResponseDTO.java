package com.webshop.webshop.web.rest.user.payload.response;

import com.webshop.webshop.web.rest.auth.payload.response.AccountResponseDto;
import lombok.Data;

@Data
public class WebShopSellerResponseDTO {

    private String uuid;
    private AccountResponseDto account;

}
