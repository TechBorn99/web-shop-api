package com.webshop.webshop.web.rest.user;

import com.webshop.webshop.configs.security.annotations.AuthenticatedUser;
import com.webshop.webshop.configs.security.models.UserPrincipal;
import com.webshop.webshop.services.auth.AuthenticationService;
import com.webshop.webshop.utils.ReturnResponse;
import com.webshop.webshop.utils.exceptions.BaseException;
import com.webshop.webshop.web.rest.auth.payload.response.AccountResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/user-info")
    @ApiOperation("Endpoint for getting info about currently logged in user.")
    public ResponseEntity<AccountResponseDto> loggedInUserInfo(@AuthenticatedUser UserPrincipal principal) {
        try {
            return ReturnResponse.entityGet(authenticationService.getLoggedInUser(principal));
        } catch (BaseException ex) {
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage());
        }
    }
}
