package com.webshop.webshop.web.rest.auth.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordRequestDto {

    @NotBlank
    @Size(max = 64)
    private String password;

    @NotBlank
    private String token;

}
