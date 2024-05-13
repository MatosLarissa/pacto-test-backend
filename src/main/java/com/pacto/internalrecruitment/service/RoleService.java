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
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDto createRole(RoleRequestDto data) {
        Optional<Role> existingRole = roleRepository.findByRoleType(data.getRoleType().toUpperCase());

        if (existingRole.isPresent()) {
            throw new AlreadyExistsException("Role already exists");
        }

        Role newRole = RoleFactory.createRole(data);
        roleRepository.save(newRole);
        return new RoleResponseDto(newRole.getId(), newRole.getRoleType());
    }


    public List<Role> findAllRoles() {
        logger.info("Finding all roles");
        return roleRepository.findAll();
    }
}
