package com.company.entity;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "email",unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
}
