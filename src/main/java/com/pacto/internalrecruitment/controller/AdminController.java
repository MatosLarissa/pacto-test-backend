package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.admin.AdminRequestDto;
import com.pacto.internalrecruitment.model.dtos.admin.AdminResponseDto;
import com.pacto.internalrecruitment.service.AdminService;
import com.pacto.internalrecruitment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Operation(description = "Busca todoso os users ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os usuários")
    })
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @Operation(description = "Cria um usuário ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os usuários")
    })

    @GetMapping("/create")
    @PostMapping
    public ResponseEntity<AdminResponseDto> registerAdmin(@RequestBody AdminRequestDto data) {
        AdminResponseDto createdUser = adminService.registerAdmin(data);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
