package com.webshop.webshop.domain.user.account;

import com.webshop.webshop.domain.base.BaseEntity;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Boolean isActive = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @NotNull
    @Column(unique = true)
    private String hash;

    public Account(SignUpRequestDto requestDto) {
        this.setFirstName(requestDto.getFirstName());
        this.setLastName(requestDto.getLastName());
        this.setEmail(requestDto.getEmail());
    }
}
