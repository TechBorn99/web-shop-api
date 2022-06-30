package com.webshop.webshop.services.auth;

import com.webshop.webshop.domain.user.WebShopAdmin;
import com.webshop.webshop.domain.user.WebShopCustomer;
import com.webshop.webshop.domain.user.WebShopSeller;
import com.webshop.webshop.domain.user.account.Account;
import com.webshop.webshop.services.mail.MailService;
import com.webshop.webshop.services.role.RoleService;
import com.webshop.webshop.services.user.WebShopAdminService;
import com.webshop.webshop.services.user.WebShopCustomerService;
import com.webshop.webshop.services.user.WebShopSellerService;
import com.webshop.webshop.services.user.account.AccountService;
import com.webshop.webshop.configs.security.AuthoritiesConstants;
import com.webshop.webshop.utils.HashValueProvider;
import com.webshop.webshop.utils.TokenProvider;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.UserUnauthorizedException;
import com.webshop.webshop.web.rest.auth.payload.request.ForgotPasswordRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.ResetPasswordRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignInRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import com.webshop.webshop.web.rest.auth.payload.response.AccountResponseDto;
import com.webshop.webshop.web.rest.auth.payload.response.SignInResponseDto;
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

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

    public AccountResponseDto signUp(SignUpRequestDto requestDto) {
        accountService.findOneByEmailAndThrowIfExists(requestDto.getEmail());

        String roleName = requestDto.getRole().getName();

        Account account = this.accountService.createAndGeneratePassword(requestDto);

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

        return modelMapper.map(account, AccountResponseDto.class);
    }

    public SignInResponseDto signIn(SignInRequestDto requestDto) {
        Account account = accountService.findOneByEmailOrElseThrowNotFound(requestDto.getEmail());

        if (!bCryptPasswordEncoder.matches(requestDto.getPassword(), account.getPassword())) {
            throw new UserUnauthorizedException(
                    ExceptionErrorCodeType.SIGNIN_WRONG_PASSWORD,
                    "Password is invalid for user with the email " + account.getEmail()
            );
        }

        String token = tokenProvider.generateToken(account);

        return new SignInResponseDto(
                token,
                this.modelMapper.map(account, AccountResponseDto.class)
        );
    }

    public String forgotPassword(ForgotPasswordRequestDto requestDto) {
        Account account = this.accountService.findOneByEmailOrElseThrowNotFound(requestDto.getEmail());

        mailService.composeForgotPasswordMail(
                new String[] { requestDto.getEmail() },
                System.getenv("CLIENT_APP_URL")
                        + "/auth/reset-password?token="
                        + account.getHash()
        );

        return account.getHash();
    }

    public Void resetPassword(ResetPasswordRequestDto requestDto) {
        Account account = accountService.findOneByHashOrElseThrowNotFound(requestDto.getHash());

        account.setPassword(requestDto.getPassword());
        account.setHash(HashValueProvider.generateHash());

        accountService.save(account);

        return null;
    }
}
