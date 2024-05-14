package com.pacto.internalrecruitment.model.dtos.user;

import lombok.Data;

@Data
public class SignInRequestDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String yearsExperience;

    public SignInRequestDto(String firstName, String lastName, String email, String password, String yearsExperience ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.yearsExperience = yearsExperience;
    }
}
