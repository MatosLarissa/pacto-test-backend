package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.controller.util.HttpResponseCreator;
import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.dtos.role.RoleRequestDto;
import com.pacto.internalrecruitment.model.dtos.role.RoleResponseDto;
import com.pacto.internalrecruitment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/role")
@CrossOrigin("*")
public class RoleController extends HttpResponseCreator {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleRequestDto data) {
            System.out.println("ROLEEEE" + data.getRoleType());
            RoleResponseDto response = roleService.createRole(data);
            return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> response = roleService.findAllRoles();
        return ResponseEntity.ok(response);
    }

}