package com.webshop.webshop.configs.security;

public class AuthoritiesConstants {

    public static final String WEBSHOP_CUSTOMER = "WEBSHOP_CUSTOMER";
    public static final String WEBSHOP_SELLER = "WEBSHOP_SELLER";
    public static final String WEBSHOP_ADMIN = "WEBSHOP_ADMIN";

    public static final String AUTH_WEBSHOP_CUSTOMER = "hasAuthority('WEBSHOP_CUSTOMER')";
    public static final String AUTH_WEBSHOP_SELLER = "hasAuthority('WEBSHOP_SELLER')";
    public static final String AUTH_WEBSHOP_ADMIN = "hasAuthority('WEBSHOP_ADMIN')";
    public static final String AUTH_ANY = "hasAnyAuthority('WEBSHOP_CUSTOMER', 'WEBSHOP_SELLER', 'WEBSHOP_ADMIN')";

    public AuthoritiesConstants() { }
}
