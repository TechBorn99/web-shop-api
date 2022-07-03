package com.webshop.webshop.web.rest.product.payload.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateProductRequestDTO {

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    @Min(value = 0)
    private Double price;

}
