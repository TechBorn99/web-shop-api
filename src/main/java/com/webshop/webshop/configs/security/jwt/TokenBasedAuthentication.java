package com.webshop.webshop.configs.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;
    private final UserDetails principle;

    public TokenBasedAuthentication(String token, UserDetails principle) {
        super(principle.getAuthorities());
        this.token = token;
        this.principle = principle;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
