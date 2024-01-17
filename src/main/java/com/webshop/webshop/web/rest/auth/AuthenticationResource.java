package com.webshop.webshop.web.rest.auth;

import com.webshop.webshop.services.auth.AuthenticationService;
import com.webshop.webshop.utils.ReturnResponse;
import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.utils.exceptions.types.EmailNotSentException;
import com.webshop.webshop.utils.exceptions.types.EntityAlreadyExistsException;
import com.webshop.webshop.utils.exceptions.types.EntityNotFoundException;
import com.webshop.webshop.utils.exceptions.types.InvalidPhoneNumberException;
import com.webshop.webshop.web.rest.auth.payload.request.ForgotPasswordRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.ResetPasswordRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignInRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import com.webshop.webshop.web.rest.auth.payload.response.AccountResponseDto;
import com.webshop.webshop.web.rest.auth.payload.response.SignInResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    @ApiOperation("Endpoint for user sign up.")
    public ResponseEntity<AccountResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        try {
            return ReturnResponse.entityCreated(authenticationService.signUp(signUpRequestDto));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }

    @PostMapping("/sign-in")
    @ApiOperation("Endpoint for user sign in.")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        try {
            return ReturnResponse.entityGet(authenticationService.signIn(requestDto));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    @ApiOperation("Endpoint for the forgot password functionality.")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto requestDto) {
        try {
            return ReturnResponse.entityGet(authenticationService.forgotPassword(requestDto));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }

    @PutMapping("/reset-password")
    @ApiOperation("Endpoint for resetting password.")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequestDto requestDto) {
        try {
            return ReturnResponse.entityUpdated(authenticationService.resetPassword(requestDto));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }
}
