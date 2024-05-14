package com.pacto.internalrecruitment.model.dtos.user;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String token;

    public LoginResponseDto(String token){
        this.token = token;
    }
}
