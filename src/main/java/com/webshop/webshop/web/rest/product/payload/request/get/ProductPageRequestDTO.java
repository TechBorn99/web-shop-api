package com.webshop.webshop.web.rest.product.payload.request.get;

import lombok.Data;

@Data
public class ProductPageRequestDTO {

    private int pageSize = 10;

    private int offset = 0;

    private int pageNumber = 1;

}
