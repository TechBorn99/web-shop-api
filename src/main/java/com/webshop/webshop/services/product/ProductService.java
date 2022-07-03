package com.webshop.webshop.services.product;

import com.webshop.webshop.configs.security.models.UserPrincipal;
import com.webshop.webshop.domain.product.Product;
import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.repositories.ProductRepository;
import com.webshop.webshop.services.user.WebShopSellerService;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityAlreadyExistsException;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import com.webshop.webshop.web.rest.product.payload.request.CreateProductRequestDTO;
import com.webshop.webshop.web.rest.product.payload.response.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebShopSellerService webShopSellerService;

    @Autowired
    private ModelMapper modelMapper;

    private Page<Product> findProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<ProductResponseDTO> getProducts(Pageable pageable) {
        Page<Product> products = this.findProducts(pageable);
        return products.map(product -> this.modelMapper.map(product, ProductResponseDTO.class));
    }

    private List<Product> findAllBySellerUuidAndName(String sellerUuid, String productName) {
        return this.productRepository.findAllBySellerUuidAndName(sellerUuid, productName);
    }

    private void productWithSellerUuidAndNameDoesNotExistAndThrowIfItDoes(String sellerUuid, String name) {
        if (this.findAllBySellerUuidAndName(sellerUuid, name).size() != 0) {
            throw new EntityAlreadyExistsException(
                    ExceptionErrorCodeType.PRODUCT_NAME_ALREADY_EXISTS,
                    "Product with the name: " + name + " already exists"
            );
        }
    }

    private Product save(Product product) {
        try {
            return this.productRepository.save(product);
        } catch (Exception ex) {
            throw new EntityNotSavedException(ExceptionErrorCodeType.PRODUCT_NOT_SAVED, "Product not saved.");
        }
    }

    public ProductResponseDTO createProduct(UserPrincipal principal, CreateProductRequestDTO requestDTO) {
        WebShopSeller seller = this.webShopSellerService.findOneByUuid(principal.getUuid());

        this.productWithSellerUuidAndNameDoesNotExistAndThrowIfItDoes(principal.getUuid(), requestDTO.getName());

        Product product = new Product();

        product.setAvailable(requestDTO.getIsAvailable());
        product.setDescription(requestDTO.getDescription());
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setSeller(seller);

        return this.modelMapper.map(this.save(product), ProductResponseDTO.class);
    }
}
