package com.webshop.webshop.web.rest.product.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageResponseDTO {

    private Page<ProductResponseDTO> productPage;

    private int totalElements;
}
