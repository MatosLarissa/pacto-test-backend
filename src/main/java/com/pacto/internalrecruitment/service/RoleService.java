package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.dtos.role.RoleRequestDto;
import com.pacto.internalrecruitment.model.dtos.role.RoleResponseDto;
import com.pacto.internalrecruitment.model.factory.RoleFactory;
import com.pacto.internalrecruitment.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDto createRole(RoleRequestDto data) {
        logger.info("Criando função: {}", data.getRoleType());
        Optional<Role> existingRole = roleRepository.findByRoleType(data.getRoleType().toUpperCase());

        if (existingRole.isPresent()) {
            logger.error("A função já existe: {}", data.getRoleType());
            throw new AlreadyExistsException("Role already exists");
        }

        Role newRole = RoleFactory.createRole(data);
        roleRepository.save(newRole);
        logger.info("Função criada com sucesso: {}", newRole.getRoleType());
        return new RoleResponseDto(newRole.getRoleId(), newRole.getRoleType());
    }

    public List<Role> findAllRoles() {
        logger.info("Buscando todas as funções");
        return roleRepository.findAll();
    }
}
