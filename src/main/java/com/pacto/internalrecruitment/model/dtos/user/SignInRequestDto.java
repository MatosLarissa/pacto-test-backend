package com.pacto.internalrecruitment.model.dto.user;

import com.pacto.internalrecruitment.model.enums.user.UserRole;
import com.pacto.internalrecruitment.shared.enums.Experience;
import lombok.Data;

@Data
public class SignInRequestDto {
    private String name;

    private String email;

    private String password;

    private String experienceId;

    public SignInRequestDto(String name, String email, String password, String experienceId ){
        this.name = name;
        this.email = email;
        this.password = password;
        this.experienceId = experienceId;
    }
}
