package com.pacto.internalrecruitment.model.dto.user;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String email;
    private String password;

    public AuthenticationRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
