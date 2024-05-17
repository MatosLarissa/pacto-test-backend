package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.admin.AdminRequestDto;
import com.pacto.internalrecruitment.model.dtos.admin.AdminResponseDto;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import com.pacto.internalrecruitment.repository.RoleRepository;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminResponseDto registerAdmin(AdminRequestDto data) {
        Optional<User> existingUser = userRepository.findByEmail(data.getEmail());
        String inputExperience = String.valueOf(data.getYearsExperience());

        if (!YearsExperience.contains(inputExperience)) {
            throw new ExistingException("Erro ao cadastrar o tempo de experiência, só é permitido um desses valores (ZERO_TO_ONE_YEARS, ONE_TO_THREE_YEARS, THREE_TO_FIVE_YEARS, FIVE_PLUS_YEARS)");
        }

        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("O email: " + data.getEmail() + " já está cadastrado");
        } else {
            try {
                logger.info("Criando o papel de ADMIN.");
                Role adminRole = roleRepository.save(new Role("ADMIN"));
                logger.info("Papel de ADMIN criado com sucesso.");

                logger.info("Criando o papel de USER.");
                roleRepository.save(new Role("USER"));
                logger.info("Papel de USER criado com sucesso.");

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                User admin = new User();

                admin.setFirstName("Administrador");
                admin.setLastName(data.getLastName());
                admin.setEmail(data.getEmail());
                admin.setPassword(passwordEncoder.encode(data.getPassword()));
                admin.setRoles(roles);
                logger.info("Criando a conta do administrador.");
                userRepository.save(admin);
                logger.info("Conta do administrador criada com sucesso.");
                return new AdminResponseDto(admin);
            } catch (Exception e) {
                logger.error("Erro ao salvar o usuário: " + e.getMessage());
                throw new ExistingException("Erro ao cadastrar usuário");
            }
        }
    }
}
