package com.pacto.internalrecruitment.model.dtos.user;

import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

@Data
public class SignInRequestDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private YearsExperience yearsExperience;

    public SignInRequestDto(String firstName, String lastName, String email, String password, YearsExperience yearsExperience ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.yearsExperience = yearsExperience;
    }
}
