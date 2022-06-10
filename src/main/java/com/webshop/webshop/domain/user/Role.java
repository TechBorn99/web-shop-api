package com.webshop.webshop.domain.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column
    private String name;
}
