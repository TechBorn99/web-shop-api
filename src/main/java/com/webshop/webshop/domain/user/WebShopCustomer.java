package com.webshop.webshop.domain.user;

import com.webshop.webshop.domain.base.BaseEntity;
import com.webshop.webshop.domain.user.account.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "webshop_customer")
@EqualsAndHashCode(callSuper = false)
public class WebShopCustomer extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public WebShopCustomer(Account account) {
        this.setUuid(account.getUuid());
        this.account = account;
    }

}
