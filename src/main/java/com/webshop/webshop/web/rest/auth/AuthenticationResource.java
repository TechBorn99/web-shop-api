package com.webshop.webshop.web.rest.auth;

import com.webshop.webshop.services.auth.AuthenticationService;
import com.webshop.webshop.utils.ReturnResponse;
import com.webshop.webshop.web.rest.auth.payload.request.ForgotPasswordRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignInRequestDto;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import com.webshop.webshop.web.rest.auth.payload.response.AccountResponseDto;
import com.webshop.webshop.web.rest.auth.payload.response.SignInResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    @ApiOperation("Endpoint for user sign up.")
    public ResponseEntity<AccountResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return ReturnResponse.entityCreated(authenticationService.signUp(signUpRequestDto));
    }

    @PostMapping("/sign-in")
    @ApiOperation("Endpoint for user sign in.")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        return ReturnResponse.entityGet(authenticationService.signIn(requestDto));
    }

    @PostMapping("/forgot-password")
    @ApiOperation("Endpoint for the forgot password functionality.")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto requestDto) {
        return ReturnResponse.entityGet(authenticationService.forgotPassword(requestDto));
    }
}
