package com.webshop.webshop.domain.user;

import com.webshop.webshop.domain.base.BaseEntity;
import com.webshop.webshop.domain.user.account.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "webshop_admin")
@EqualsAndHashCode(callSuper = false)
public class WebShopAdmin extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public WebShopAdmin(Account account) {
        this.setUuid(account.getUuid());
        this.account = account;
    }

}
