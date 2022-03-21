package com.company.dto.profile;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String login;
    @Size(min = 3, max = 5)
    private String password;
    @Email
    private String email;

    private LocalDateTime createdDate;
    private ProfileRole role;
    private ProfileStatus status;

    private String jwt;

}
