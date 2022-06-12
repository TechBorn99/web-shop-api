package com.webshop.webshop.repositories;

import com.webshop.webshop.domain.user.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByEmail(String email);
    Optional<Account> findOneByHash(String hash);

}
