package com.webshop.webshop.web.rest.product.payload.response;

import com.webshop.webshop.web.rest.user.payload.response.WebShopSellerResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private String name;

    private String description;

    private WebShopSellerResponseDTO seller;

    private boolean isAvailable;

    private Double price;

    private LocalDateTime createdAt;

}
