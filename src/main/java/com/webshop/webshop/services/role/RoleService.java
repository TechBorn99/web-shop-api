package com.webshop.webshop.services.role;

import com.webshop.webshop.domain.user.account.Role;
import com.webshop.webshop.repositories.RoleRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findOneByNameOrElseThrowNotFound(String name) {
        return roleRepository
                .findOneByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                ExceptionErrorCodeType.ROLE_NOT_FOUND,
                                "Role with name " + name + " not found."
                        )
                );
    }
}
