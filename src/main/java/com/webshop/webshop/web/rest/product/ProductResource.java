package com.webshop.webshop.web.rest.product;

import com.webshop.webshop.configs.security.AuthoritiesConstants;
import com.webshop.webshop.configs.security.annotations.AuthenticatedUser;
import com.webshop.webshop.configs.security.models.UserPrincipal;
import com.webshop.webshop.services.product.ProductService;
import com.webshop.webshop.utils.ReturnResponse;
import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.web.rest.product.payload.request.CreateProductRequestDTO;
import com.webshop.webshop.web.rest.product.payload.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation("Endpoint for getting a paginated list of products.")
    public ResponseEntity<Page<ProductResponseDTO>> handleGetProducts(Pageable pageable) {
        try {
            return ReturnResponse.entityGet(this.productService.getProducts(pageable));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize(AuthoritiesConstants.AUTH_WEBSHOP_SELLER)
    @ApiOperation("Endpoint for creating a new product.")
    public ResponseEntity<ProductResponseDTO> handleCreateProduct(
            @RequestBody @Valid CreateProductRequestDTO requestDTO,
            @AuthenticatedUser UserPrincipal principal
    ) {
        try {
            return ReturnResponse.entityCreated(this.productService.createProduct(principal, requestDTO));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }

}
