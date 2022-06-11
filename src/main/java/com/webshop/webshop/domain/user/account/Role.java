package com.webshop.webshop.domain.user.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
public class Role {

    @Id
    @Column
    private String name;
}
