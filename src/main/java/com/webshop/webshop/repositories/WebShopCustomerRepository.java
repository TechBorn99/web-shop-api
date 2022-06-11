package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.user.WebShopCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebShopCustomerRepository extends JpaRepository<WebShopCustomer, Long> {
}
