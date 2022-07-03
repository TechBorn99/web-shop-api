package com.webshop.webshop.services.product;

import com.webshop.webshop.configs.security.models.UserPrincipal;
import com.webshop.webshop.domain.product.Product;
import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.repositories.ProductRepository;
import com.webshop.webshop.services.user.WebShopSellerService;
import com.webshop.webshop.utils.enums.ProductAttributesSortBy;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityAlreadyExistsException;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import com.webshop.webshop.utils.exceptions.types.InvalidProductSortAttributeException;
import com.webshop.webshop.web.rest.product.payload.request.CreateProductRequestDTO;
import com.webshop.webshop.web.rest.product.payload.request.get.GetProductPageWithFiltersRequestDTO;
import com.webshop.webshop.web.rest.product.payload.request.get.ProductFilterRequestDTO;
import com.webshop.webshop.web.rest.product.payload.request.get.ProductSortersRequestDTO;
import com.webshop.webshop.web.rest.product.payload.response.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebShopSellerService webShopSellerService;

    @Autowired
    private ModelMapper modelMapper;

    private ArrayList<Product> findProducts() {
        return new ArrayList<>(this.productRepository.findAll());
    }

    private boolean doesProductContainsString(Product product, String filterString) {
        return product.getName().toLowerCase().contains(filterString.toLowerCase())
                || product.getDescription().toLowerCase().contains(filterString.toLowerCase());
    }

    private boolean isProductPriceWithinRange(Product product, Double priceFrom, Double priceTo) {
        return product.getPrice() >= priceFrom && product.getPrice() <= priceTo;
    }

    private boolean isProductWithinDateRange(Product product, LocalDateTime createdAtFrom, LocalDateTime createdAtTo) {
        return product.getCreatedAt().isAfter(createdAtFrom) && product.getCreatedAt().isBefore(createdAtTo);
    }

    private ArrayList<Product> filterProducts(ArrayList<Product> products, ProductFilterRequestDTO requestDTO) {
        return products.stream().filter(product -> {
            boolean isContainedWithinSearchQueryFilter = true;
            boolean isContainedWithinDateFilter = true;
            boolean isContainedWithinPriceFilter = true;

            if (requestDTO.getContainsChars() != null) {
                isContainedWithinSearchQueryFilter = this.doesProductContainsString(
                        product,
                        requestDTO.getContainsChars()
                );
            }

            if (requestDTO.getPriceRange() != null) {
                isContainedWithinPriceFilter = this.isProductPriceWithinRange(
                        product,
                        requestDTO.getPriceRange().getPriceFrom() != null ?
                                requestDTO.getPriceRange().getPriceFrom()
                                : 0.0,
                        requestDTO.getPriceRange().getPriceTo() != null ?
                                requestDTO.getPriceRange().getPriceTo()
                                : Double.MAX_VALUE
                );
            }

            if (requestDTO.getDateRange() != null) {
                isContainedWithinDateFilter = this.isProductWithinDateRange(
                        product,
                        requestDTO.getDateRange().getCreatedAtFrom() != null ?
                                requestDTO.getDateRange().getCreatedAtFrom()
                                : LocalDateTime.MIN,
                        requestDTO.getDateRange().getCreatedAtTo() != null ?
                                requestDTO.getDateRange().getCreatedAtTo()
                                : LocalDateTime.MAX
                );
            }

            return isContainedWithinSearchQueryFilter
                    && isContainedWithinDateFilter
                    && isContainedWithinPriceFilter;
        }).collect(toCollection(ArrayList::new));
    }

    private ArrayList<Product> sortProducts(ArrayList<Product> products, ProductSortersRequestDTO requestDTO) {
        if (products.size() <= 1) {
            return products;
        }

        switch (requestDTO.getAttribute()) {
            case ProductAttributesSortBy.DATE_OF_CREATION:
                products.sort(Comparator.comparing(Product::getCreatedAt));
                break;
            case ProductAttributesSortBy.PRICE:
                products.sort(Comparator.comparing(Product::getPrice));
                break;
            case ProductAttributesSortBy.NAME:
                products.sort(Comparator.comparing(Product::getName));
                break;
            default:
                throw new InvalidProductSortAttributeException(
                        ExceptionErrorCodeType.PRODUCT_SORT_BY_INVALID,
                        "Product sorting attribute with name: " + requestDTO.getAttribute() + " not found."
                );
        }

        if (!requestDTO.isAscending()) Collections.reverse(products);

        return products;
    }

    private Page<Product> pageProducts(ArrayList<Product> products, long start, long end, Pageable pageable) {
        return new PageImpl<>(products.subList((int) start, (int) end), pageable, products.size());
    }

    public Page<ProductResponseDTO> getProductsWithFiltersAndSorters(GetProductPageWithFiltersRequestDTO requestDTO) {
        ArrayList<Product> products = this.findProducts();

        if (products.size() == 0) return Page.empty();

        ArrayList<Product> filteredProducts = null;

        if (requestDTO.getFilters() != null) {
            filteredProducts = this.filterProducts(products, requestDTO.getFilters());

            if (filteredProducts.size() == 0) return Page.empty();
        }

        ArrayList<Product> sortedProducts = null;

        if (requestDTO.getSorters() != null) {
            sortedProducts = this.sortProducts(
                    filteredProducts == null ? products : filteredProducts,
                    requestDTO.getSorters()
            );
        }

        int offset, pageSize, pageNumber;

        if (requestDTO.getPageable() == null) {
            offset = 0;
            pageSize = 10;
            pageNumber = 1;
        } else {
            offset = requestDTO.getPageable().getOffset();
            pageSize = requestDTO.getPageable().getPageSize();
            pageNumber = requestDTO.getPageable().getPageNumber();
        }

        Page<Product> pagedProducts = this.pageProducts(
                sortedProducts != null ?
                        sortedProducts
                        : (filteredProducts != null ? filteredProducts : products),
                offset,
                offset + pageSize,
                PageRequest.of(pageNumber, pageSize)
        );

        return pagedProducts.map(product -> this.modelMapper.map(product, ProductResponseDTO.class));
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
