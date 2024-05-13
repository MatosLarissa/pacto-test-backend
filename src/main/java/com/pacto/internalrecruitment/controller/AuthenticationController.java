package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.controller.util.HttpResponseCreator;
import com.pacto.internalrecruitment.model.dtos.user.AuthenticationRequestDto;
import com.pacto.internalrecruitment.model.dtos.user.LoginResponseDto;
import com.pacto.internalrecruitment.model.dtos.user.SignInRequestDto;
import com.pacto.internalrecruitment.model.dtos.user.SignInResponseDto;
import com.pacto.internalrecruitment.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthenticationController extends HttpResponseCreator {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequestDto data) {
        LoginResponseDto response = authenticationService.authenticationUser(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid SignInRequestDto data) {
        SignInResponseDto response = authenticationService.registerUser(data);
        return ResponseEntity.ok(response);
    }
}