package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

@Data
public class GetProductPageWithFiltersRequestDTO {

    private ProductPageRequestDTO pageable;

    private ProductFilterRequestDTO filters;

    private ProductSortersRequestDTO sorters;
}
