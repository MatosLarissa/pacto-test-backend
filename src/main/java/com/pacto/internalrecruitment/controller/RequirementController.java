package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementResponseDto;
import com.pacto.internalrecruitment.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/requirement")
@CrossOrigin("*")
public class RequirementController {

    private final RequirementService requirementService;

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public ResponseEntity<List<Requirement>> getAllRequirements() {
        List<Requirement> response = requirementService.findAllRequirements();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Requirement> createRequirement(@RequestBody Requirement data) {
        Requirement response = requirementService.createRequirement(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Integer id) {
        Requirement response = requirementService.getRequirementById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getName/{requirementName}")
    public ResponseEntity<List<Requirement>> getRequirementsByName(@PathVariable String requirementName) {
        List<Requirement> response = requirementService.getRequirementsByNameContaining(requirementName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RequirementResponseDto> updateRequirement(@PathVariable Integer id, @RequestBody Requirement data) {
        RequirementResponseDto response = requirementService.updateRequirement(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Integer id) {
        requirementService.deleteRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
