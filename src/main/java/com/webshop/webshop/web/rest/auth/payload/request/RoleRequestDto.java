package com.webshop.webshop.web.rest.auth.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleRequestDto {

    @NotBlank
    String name;

}
