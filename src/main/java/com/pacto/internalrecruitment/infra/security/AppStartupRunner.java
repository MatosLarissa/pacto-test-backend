package com.pacto.internalrecruitment.infra.security;

import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pacto.internalrecruitment.repository.RoleRepository;
import com.pacto.internalrecruitment.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppStartupRunner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Iniciando a aplicação e configurando os papéis e o administrador.");
        initializeRolesAndAdmin();
    }

    private void initializeRolesAndAdmin() {
        logger.info("Verificando se o papel de ADMIN já existe.");
        if (roleRepository.findByRoleType("ADMIN").isPresent()) {
            logger.info("Papel de ADMIN já existe. Nenhuma ação necessária.");
            return;
        }

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
        admin.setEmail("admin@pacto.com");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(roles);

        logger.info("Criando a conta do administrador.");
        userRepository.save(admin);
        logger.info("Conta do administrador criada com sucesso.");
    }
}
