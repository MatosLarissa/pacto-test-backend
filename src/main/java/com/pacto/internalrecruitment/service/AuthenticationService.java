package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AccessDeniedException;
import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.user.AuthenticationRequestDto;
import com.pacto.internalrecruitment.model.dtos.user.LoginResponseDto;
import com.pacto.internalrecruitment.model.dtos.user.SignInRequestDto;
import com.pacto.internalrecruitment.model.dtos.user.SignInResponseDto;
import com.pacto.internalrecruitment.model.factory.UserFactory;
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

    public SignInResponseDto registerUser(SignInRequestDto data) {
        Optional<User> existingUser = userRepository.findByEmail(data.getEmail());

        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("O email: " + data.getEmail() + " já está cadastrado");
        } else {
            try {
                User newUser = UserFactory.createUser(data);
                String token = tokenService.generateToken(newUser.getEmail());
                userRepository.save(newUser);
                return new SignInResponseDto(token);
            } catch (Exception e) {
                logger.error("Erro ao salvar o usuário: " + e.getMessage());
                throw new ExistingException("Erro ao cadastrar usuário");
            }
        }
    }
}
