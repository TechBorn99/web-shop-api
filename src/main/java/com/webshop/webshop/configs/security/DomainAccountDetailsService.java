package com.webshop.webshop.configs.security;

import com.webshop.webshop.configs.security.models.UserPrincipal;
import com.webshop.webshop.domain.user.account.Account;
import com.webshop.webshop.services.user.account.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainAccountDetailsService implements UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findOneByEmailOrElseThrowNotFound(email);
        return createSpringSecurityUser(account);
    }

    private UserPrincipal createSpringSecurityUser(Account account) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));

        UserPrincipal userPrincipal = this.modelMapper.map(account, UserPrincipal.class);
        userPrincipal.setPassword(account.getPassword());
        userPrincipal.setRoles(grantedAuthorities);
        return userPrincipal;
    }
}
