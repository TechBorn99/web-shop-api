package com.webshop.webshop.services.user.account;

import com.webshop.webshop.domain.user.account.Account;
import com.webshop.webshop.domain.user.account.Role;
import com.webshop.webshop.repositories.AccountRepository;
import com.webshop.webshop.services.mail.MailService;
import com.webshop.webshop.services.role.RoleService;
import com.webshop.webshop.utils.HashValueProvider;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityAlreadyExistsException;
import com.webshop.webshop.utils.exceptions.types.EntityNotFoundException;
import com.webshop.webshop.utils.exceptions.types.EntityNotSavedException;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailService mailService;

    public Account findOneByEmailOrElseThrowNotFound(String email) {
        return accountRepository.findOneByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(ExceptionErrorCodeType.EMAIL_NOT_FOUND,
                        "Account with email " + email + " not found"));
    }

    public void findOneByEmailAndThrowIfExists (String email) {
        if (accountRepository.findOneByEmail(email).isPresent()) {
            throw new EntityAlreadyExistsException(ExceptionErrorCodeType.EMAIL_ALREADY_EXISTS,
                    "Account with email " + email + " already exists");
        }
    }

    public Account findOneByHashOrElseThrowNotFound (String hash) {
        return accountRepository.findOneByHash(hash).orElseThrow(
                () -> new EntityNotFoundException(ExceptionErrorCodeType.ACCOUNT_NOT_FOUND_BY_HASH,
                        "Account with hash " + hash + " not found"));
    }

    public Account save(Account account) {
        try {
            return accountRepository.save(account);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new EntityNotSavedException(ExceptionErrorCodeType.ACCOUNT_NOT_SAVED,
                    "Account not saved in the database");
        }
    }

    public Account createAndGeneratePassword(SignUpRequestDto requestDto) {
        this.findOneByEmailAndThrowIfExists(requestDto.getEmail());

        Account account = new Account(requestDto);
        Role role = roleService.findOneByNameOrElseThrowNotFound(requestDto.getRole());
        String password = RandomString.make(16);

        String signInUrl = System.getenv("CLIENT_APP_URL") + "/auth/sign-in";

        account.setRole(role);
        account.setIsActive(true);
        account.setPassword(this.bCryptPasswordEncoder.encode(password));
        account.setHash(HashValueProvider.generateHash());
        account.setPhoneNumber(requestDto.getPhoneNumber());

        this.mailService.composeWelcomeMail(requestDto.getEmail(), password, signInUrl);

        return this.save(account);
    }
}
