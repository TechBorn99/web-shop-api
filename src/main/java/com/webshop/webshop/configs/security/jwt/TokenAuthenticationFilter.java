package com.webshop.webshop.configs.security.jwt;

import com.webshop.webshop.utils.TokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;
    private TokenProvider tokenProvider;

    public TokenAuthenticationFilter(
            UserDetailsService userDetailsService,
            TokenProvider tokenProvider
    ) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            String username = tokenProvider.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                TokenBasedAuthentication tokenBasedAuthentication = new TokenBasedAuthentication(token, userDetails);
                SecurityContextHolder.getContext().setAuthentication(tokenBasedAuthentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
