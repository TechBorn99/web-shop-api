package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.user.WebShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebShopAdminRepository extends JpaRepository<WebShopAdmin, Long> {
}
