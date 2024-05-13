package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.controller.util.HttpResponseCreator;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementRequestDto;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementResponseDto;
import com.pacto.internalrecruitment.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/requirement")
@CrossOrigin("*")
public class RequirementController  extends HttpResponseCreator {

    private final RequirementService requirementService;

    @Autowired
    public RequirementController(final RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public ResponseEntity<List<Requirement>> getAllRequirements() {
        List<Requirement> response = requirementService.findAllRequirements();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> crateRequirement(@RequestBody RequirementRequestDto data) {
        RequirementResponseDto response = requirementService.createRequirements(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Requirement> getRequirementsById(@PathVariable Integer id){
        Requirement response = requirementService.getRequirementById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getName/{requirementName}")
    public ResponseEntity<Requirement> getRequirementByName(@PathVariable String requirementName){
        Requirement response = requirementService.getRequirementByName(requirementName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateRequirement(@PathVariable Integer id, @RequestBody RequirementRequestDto data) {
        RequirementResponseDto response = requirementService.updateRequirement(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Integer id) {
        requirementService.deleteRequirement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
