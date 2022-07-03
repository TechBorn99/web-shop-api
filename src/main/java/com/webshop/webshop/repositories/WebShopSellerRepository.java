package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.user.WebShopSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebShopSellerRepository extends JpaRepository<WebShopSeller, Long> {

    Optional<WebShopSeller> findOneByUuid(String uuid);

}
