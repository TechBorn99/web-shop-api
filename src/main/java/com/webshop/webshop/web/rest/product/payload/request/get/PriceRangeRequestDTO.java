package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

@Data
public class PriceRangeRequestDTO {

    private Double priceFrom;

    private Double priceTo;

}
