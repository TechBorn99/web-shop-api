package com.webshop.webshop.web.rest.product.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDTO {

    @NotNull
    @NotEmpty
    private String uuid;

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
