package com.webshop.webshop.domain.product;

import com.webshop.webshop.domain.base.BaseEntity;
import com.webshop.webshop.domain.user.WebShopSeller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @NotNull
    private String name;

    private String description;

    @ManyToOne(optional = false)
    private WebShopSeller seller;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    @Min(value = 0)
    private Double price;

    // relationship
    // private Supplier supplier;

    // nullable
    // private String imageUrl;
}
