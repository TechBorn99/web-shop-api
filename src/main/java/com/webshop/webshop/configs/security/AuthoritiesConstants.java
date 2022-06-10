package com.webshop.webshop.configs.security;

public class AuthoritiesConstants {

    public static final String WEBSHOP_CUSTOMER = "WEBSHOP_CUSTOMER";
    public static final String WEBSHOP_SELLER = "WEBSHOP_SELLER";

    public static final String AUTH_WEBSHOP_CUSTOMER = "hasAuthority('WEBSHOP_CUSTOMER')";
    public static final String AUTH_WEBSHOP_SELLER = "hasAuthority('WEBSHOP_SELLER')";
    public static final String AUTH_ALL = "hasAuthority('WEBSHOP_CUSTOMER', 'WEBSHOP_SELLER')";

    public AuthoritiesConstants() { }
}
