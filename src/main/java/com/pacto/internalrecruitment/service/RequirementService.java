package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementRequestDto;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementResponseDto;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import com.pacto.internalrecruitment.model.factory.RequirementFactory;
import com.pacto.internalrecruitment.repository.RequirementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequirementService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public List<Requirement> findAllRequirements() {
        logger.info("Finding all roles");
        return requirementRepository.findAll();
    }

    public Requirement getRequirementById(int id) {
        Requirement requirement = requirementRepository.findById(id).orElse(null);
        if (requirement == null) {
            throw new NotFoundException("Requisito com id " + id + " não encontrado");
        }
        return requirement;
    }

    public Requirement getRequirementByName(String requirementName) {
        Requirement requirement = requirementRepository.findByRequirementName(requirementName).orElse(null);
        if (requirement == null) {
            throw new NotFoundException("Requisito com requirementName " + requirementName + " não encontrado");
        }
        return requirement;
    }

    public RequirementResponseDto createRequirements(RequirementRequestDto data) {
        String inputExperience = data.getYearsExperience();
        if (!YearsExperience.contains(inputExperience)) {
            throw new ExistingException("Erro ao cadastra o tempo de experiencia, só é permitido um desses valores(ZERO_TO_ONE_YEARS, ONE_TO_THREE_YEARS, THREE_TO_FIVE_YEARS, FIVE_PLUS_YEARS)");
        }

        Optional<Requirement> existingRequirement = requirementRepository.findByRequirementName(data.getRequirementName().toUpperCase());
        if (existingRequirement.isPresent()) {
            throw new AlreadyExistsException("O requisito: " + data.getRequirementName() + " já existe");
        }else{
            try{
                Requirement newRequirement = RequirementFactory.createRequirement(data);
                requirementRepository.save(newRequirement);
                return new RequirementResponseDto(newRequirement.getId(), newRequirement.getRequirementName(), newRequirement.getYearsExperience());
            }catch (Exception e) {
                String message = "Erro ao salvar o Requisito: " + data.getRequirementName() + data.getYearsExperience();
                logger.error("{}{}", message, e.getMessage());
                throw new ExistingException(message);
            }
        }
    }

    public RequirementResponseDto updateRequirement(int id, RequirementRequestDto data) {

        String inputExperience = data.getYearsExperience();
        if (!YearsExperience.contains(inputExperience)) {
            throw new ExistingException("Erro ao atualizar o tempo de experiencia, só é permitido um desses valores(ZERO_TO_ONE_YEARS, ONE_TO_THREE_YEARS, THREE_TO_FIVE_YEARS, FIVE_PLUS_YEARS)");
        }
        Requirement requirement = requirementRepository.findById(id).orElse(null);

        if(requirement == null){
            throw new NotFoundException("Requisito com id " + id + " não encontrado");
        }else {
            try {
                Requirement updatedRequirement = RequirementFactory.updateRequirement(requirement, data);
                requirementRepository.save(updatedRequirement);
                return new RequirementResponseDto(updatedRequirement.getId(), updatedRequirement.getRequirementName(), updatedRequirement.getYearsExperience());

            } catch (Exception e) {
                String message = "Erro ao salvar o Requisito: " + data.getRequirementName() + data.getYearsExperience();
                logger.error("{}{}", message, e.getMessage());
                throw new ExistingException(message);
            }
        }
    }

    public void deleteRequirement(int id) {
        Requirement requirement = getRequirementById(id);
        if (requirement != null) {
            requirementRepository.delete(requirement);
        } else {
            throw new ExistingException("Requisito com id: " + id + " não existe");
        }
    }
}
