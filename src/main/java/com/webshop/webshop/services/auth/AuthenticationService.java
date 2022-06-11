package com.webshop.webshop.services.auth;

import com.webshop.webshop.domain.user.WebShopAdmin;
import com.webshop.webshop.domain.user.WebShopCustomer;
import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.domain.user.account.Account;
import com.webshop.webshop.services.user.WebShopAdminService;
import com.webshop.webshop.services.user.WebShopCustomerService;
import com.webshop.webshop.services.user.WebShopSellerService;
import com.webshop.webshop.services.user.account.AccountService;
import com.webshop.webshop.configs.security.AuthoritiesConstants;
import com.webshop.webshop.utils.HashValueProvider;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import com.webshop.webshop.web.rest.auth.payload.response.SignUpResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebShopSellerService webShopSellerService;

    @Autowired
    private WebShopCustomerService webShopCustomerService;

    @Autowired
    private WebShopAdminService webShopAdminService;

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        accountService.findOneByEmailAndThrowIfExists(requestDto.getEmail());

        String hash = HashValueProvider.generateHash();
        String roleName = requestDto.getRole().getName();
        String password = bCryptPasswordEncoder.encode(requestDto.getPassword());

        Account account = new Account(requestDto, password, roleName, hash);

        switch (roleName) {
            case AuthoritiesConstants.WEBSHOP_CUSTOMER:
                this.webShopCustomerService.save(new WebShopCustomer(account));
                break;
            case AuthoritiesConstants.WEBSHOP_SELLER:
                this.webShopSellerService.save(new WebShopSeller(account));
                break;
            case AuthoritiesConstants.WEBSHOP_ADMIN:
                this.webShopAdminService.save(new WebShopAdmin(account));
                break;
        }

        return modelMapper.map(account, SignUpResponseDto.class);
    }

}
