package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementResponseDto;
import com.pacto.internalrecruitment.model.factory.RequirementFactory;
import com.pacto.internalrecruitment.repository.RequirementRepository;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {
    private static final Logger logger = LoggerFactory.getLogger(RequirementService.class);

    private final RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public List<Requirement> findAllRequirements() {
        logger.info("Buscando todos os requisitos");
        return requirementRepository.findAll();
    }

    public Requirement getRequirementById(Integer id) {
        logger.info("Buscando requisito por ID: {}", id);
        return requirementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requisito com id " + id + " não encontrado"));
    }

    public List<Requirement> getRequirementsByNameContaining(String requirementName) {
        logger.info("Buscando requisitos que contenham o nome: {}", requirementName);
        List<Requirement> requirements = requirementRepository.findByRequirementNameContaining(requirementName.toUpperCase());
        if (requirements.isEmpty()) {
            throw new NotFoundException("Requisito com nome que contenha " + requirementName + " não encontrado");
        }
        return requirements;
    }

    public Requirement createRequirement(Requirement data) {
        logger.info("Criando requisito: {}", data.getRequirementName());
        validateYearsExperience(data.getYearsExperience());
        checkForExistingRequirement(data.getRequirementName());
        try {
            Requirement newRequirement = RequirementFactory.createRequirement(data);
            return requirementRepository.save(newRequirement);
        } catch (Exception e) {
            String message = "Erro ao salvar o requisito: " + data.getRequirementName() + " " + data.getYearsExperience();
            logger.error("{} {}", message, e.getMessage());
            throw new AlreadyExistsException(message);
        }
    }

    private void validateYearsExperience(String yearsExperience) {
        if (!YearsExperience.contains(yearsExperience)) {
            throw new AlreadyExistsException("Erro ao cadastrar o tempo de experiência, apenas os seguintes valores são permitidos: (ZERO_TO_ONE_YEARS, ONE_TO_THREE_YEARS, THREE_TO_FIVE_YEARS, FIVE_PLUS_YEARS)");
        }
    }

    private void checkForExistingRequirement(String requirementName) {
        List<Requirement> existingRequirements = requirementRepository.findByRequirementName(requirementName.toUpperCase());
        if (!existingRequirements.isEmpty()) {
            logger.error("O requisito já existe: {}", requirementName);
            throw new AlreadyExistsException("O requisito já existe: " + requirementName);
        }
    }

    public RequirementResponseDto updateRequirement(Integer id, Requirement data) {
        logger.info("Atualizando requisito com ID: {}", id);
        validateYearsExperience(data.getYearsExperience());
        Requirement requirement = getRequirementById(id);
        try {
            Requirement updatedRequirement = RequirementFactory.updateRequirement(requirement, data);
            requirementRepository.save(updatedRequirement);
            return new RequirementResponseDto(updatedRequirement.getRequirementId(), updatedRequirement.getRequirementName(), updatedRequirement.getYearsExperience());
        } catch (Exception e) {
            String message = "Erro ao salvar o requisito: " + data.getRequirementName() + " " + data.getYearsExperience();
            logger.error("{} {}", message, e.getMessage());
            throw new AlreadyExistsException(message);
        }
    }

    public void deleteRequirement(Integer id) {
        logger.info("Excluindo requisito com ID: {}", id);
        Requirement requirement = getRequirementById(id);
        requirementRepository.delete(requirement);
    }
}
