package com.company.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthorizationDTO {

    @NotBlank
    private String login;

    @Size(min = 3,max = 15)
    private String password;
}
