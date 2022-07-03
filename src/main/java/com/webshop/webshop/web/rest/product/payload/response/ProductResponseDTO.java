package com.webshop.webshop.web.rest.product.payload.response;

import com.webshop.webshop.domain.user.WebShopSeller;
import lombok.Data;

@Data
public class ProductResponseDTO {

    private String name;

    private String description;

    private WebShopSeller seller;

    private boolean isAvailable;

    private Double price;

}
