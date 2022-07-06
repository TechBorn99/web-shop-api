package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

@Data
public class ProductSortersRequestDTO {

    private String attribute;

    private boolean ascending;

}
