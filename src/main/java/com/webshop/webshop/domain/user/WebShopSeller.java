package com.webshop.webshop.domain.user;

import com.webshop.webshop.domain.base.BaseEntity;
import com.webshop.webshop.domain.user.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "webshop_seller")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WebShopSeller extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public WebShopSeller(Account account) {
        this.setUuid(account.getUuid());
        this.setAccount(account);
    }
}
