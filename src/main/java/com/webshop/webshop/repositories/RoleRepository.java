package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.user.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findOneByName(String name);

}
