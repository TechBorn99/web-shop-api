package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetProductPageWithFiltersRequestDTO {

    @NotNull
    private ProductPageRequestDTO pageable;

    private ProductFilterRequestDTO filters;

    private ProductSortersRequestDTO sorters;
}
