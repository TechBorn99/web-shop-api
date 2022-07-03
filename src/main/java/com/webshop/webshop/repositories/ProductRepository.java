package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellerUuidAndName(String uuid, String name);

}
