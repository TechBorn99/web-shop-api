package com.webshop.webshop.web.rest.auth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {

    private String token;
    private AccountResponseDto account;

}
