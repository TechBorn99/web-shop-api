package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

@Data
public class ProductFilterRequestDTO {

    private String containsChars;

    private PriceRangeRequestDTO priceRange;

    private DateRangeRequestDTO dateRange;

}
