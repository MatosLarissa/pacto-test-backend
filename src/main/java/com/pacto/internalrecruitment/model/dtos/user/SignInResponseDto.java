package com.pacto.internalrecruitment.model.dtos.user;

import lombok.Data;

@Data
public class SignInResponseDto {

    private String token;

    public SignInResponseDto(String token){
        this.token = token;
    }
}
