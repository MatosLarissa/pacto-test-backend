package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AccessDeniedException;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.user.AuthenticationRequestDto;
import com.pacto.internalrecruitment.model.dtos.user.LoginResponseDto;
import com.pacto.internalrecruitment.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@Transactional
public class AuthenticationService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final JwtTokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService( UserRepository userRepository, JwtTokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto authenticationUser(AuthenticationRequestDto data) {
            Optional<User> user = userRepository.findByEmail(data.getEmail());
            if (!user.isPresent()) {
                throw new AccessDeniedException("O email: " + data.getEmail() + " não está cadastrado");
            }

            UserDetails userDetails = userRepository.findUserDetailsByEmail(data.getEmail());
            if (userDetails != null && passwordEncoder.matches(data.getPassword(), userDetails.getPassword())) {
                String token = tokenService.generateToken(data.getEmail());
                return new LoginResponseDto(token);
            } else {
                throw new AccessDeniedException("usuário não autorizado");
            }
    }

}
