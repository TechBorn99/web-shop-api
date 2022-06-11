package com.webshop.webshop.web.rest.auth;

import com.webshop.webshop.services.auth.AuthenticationService;
import com.webshop.webshop.web.rest.auth.payload.request.SignUpRequestDto;
import com.webshop.webshop.web.rest.auth.payload.response.SignUpResponseDto;
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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return null;
    }
}
