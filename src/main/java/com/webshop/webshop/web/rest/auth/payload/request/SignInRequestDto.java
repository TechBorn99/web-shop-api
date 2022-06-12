package com.webshop.webshop.web.rest.auth.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInRequestDto {

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(max = 64)
    private String password;

}
