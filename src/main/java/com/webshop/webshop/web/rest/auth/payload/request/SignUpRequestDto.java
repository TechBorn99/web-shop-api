package com.webshop.webshop.web.rest.auth.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpRequestDto {

    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @NotBlank
    @Size(max = 128)
    @Email
    private String email;

    @NotNull
    private String role;

    @Size(max = 15)
    @NotNull
    private String phoneNumber;

}
