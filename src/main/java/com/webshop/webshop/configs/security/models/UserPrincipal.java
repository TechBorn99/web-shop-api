package com.webshop.webshop.configs.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.webshop.domain.user.account.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;
    private String uuid;
    private String email;
    @JsonIgnore
    private String password;
    private Role role;
    private boolean isActive;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal() { }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }
}
