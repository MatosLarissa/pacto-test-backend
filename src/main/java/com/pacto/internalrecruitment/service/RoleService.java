package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles() {
        logger.info("Finding all roles");
        return roleRepository.findAll();
    }

    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
    public Role findByRoleType(String roleType) {
        return roleRepository.findByRoleType(roleType).orElse(null);
    }

    public Role saveRole(Role role) {
        Optional<Role> roleExist = roleRepository.findByRoleType(role.getRoleType());
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

}