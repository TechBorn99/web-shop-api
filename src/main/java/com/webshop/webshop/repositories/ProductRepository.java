package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByDeletedAtIsNull();

    List<Product> findAllBySellerUuidAndName(String uuid, String name);

    Optional<Product> findOneByUuid(String uuid);

}
