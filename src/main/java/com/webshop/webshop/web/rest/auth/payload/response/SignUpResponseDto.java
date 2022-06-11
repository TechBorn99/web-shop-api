package com.webshop.webshop.web.rest.auth.payload.response;

import com.webshop.webshop.web.rest._base.BaseWithTimestampsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SignUpResponseDto extends BaseWithTimestampsDto {

    private String firstName;
    private String lastName;
    private String email;
    private RoleResponseDto role;

}