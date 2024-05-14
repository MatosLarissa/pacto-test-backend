package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.job.JobResponseDto;
import com.pacto.internalrecruitment.model.enums.StatusJob;
import com.pacto.internalrecruitment.model.factory.JobFactory;
import com.pacto.internalrecruitment.repository.JobRepository;
import com.pacto.internalrecruitment.repository.RequirementRepository;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    private final JobRepository jobRepository;
    private final RequirementRepository requirementRepository;
    private final RequirementService requirementService;
    private final UserRepository userRepository;

    @Autowired
    public JobService(JobRepository jobRepository, RequirementRepository requirementRepository, RequirementService requirementService, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.requirementRepository = requirementRepository;
        this.requirementService = requirementService;
        this.userRepository = userRepository;
    }

    public List<Job> findAllJobs() {
        logger.info("Procurando todas as vagas");
        return jobRepository.findAll();
    }

    public Job getJobById(Integer id) {
        logger.info("Procurando vaga por id: {}", id);
        return jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Vaga com id " + id + " não encontrada"));
    }

    public List<Job> getJobByTitleOrDescription(String jobText) {
        logger.info("Procurando vaga por título ou descrição: {}", jobText);
        return jobRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(jobText, jobText);
    }

    public List<Job> getJobByStatus(String status) {
        logger.info("Procurando vaga por status: {}", status);
        try {
            return jobRepository.findByStatusContaining(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Status inválido: {}", status);
            return Collections.emptyList();
        }
    }

    public List<Job> getJobByRequirement(String requirement) {
        logger.info("Procurando vaga por requisito: {}", requirement);
        return jobRepository.findByRequirementsContaining(requirement);
    }

    public JobResponseDto createJob(Job data) {
        logger.info("Criando vaga: {}", data.getTitle());
        Optional<User> userExist = userRepository.findById(data.getCreatorId());
        if (!userExist.isPresent()) {
            throw new NotFoundException("Usuário com id " + data.getCreatorId() + " não encontrado");
        }

        try {
            Set<Requirement> updatedRequirements = new HashSet<>();
            data.getRequirements().forEach(requirement -> {
                List<Requirement> existingRequirements = requirementRepository.findByRequirementName(requirement.getRequirementName().toUpperCase());
                if (existingRequirements.isEmpty()) {
                    Requirement newRequirement = requirementService.createRequirement(requirement);
                    updatedRequirements.add(newRequirement);
                } else {
                    updatedRequirements.addAll(existingRequirements);
                }
            });
            data.setRequirements(updatedRequirements);

            Job newJob = JobFactory.createJob(data);
            jobRepository.save(newJob);
            logger.info("Vaga criada com sucesso: {}", newJob.getTitle());
            return new JobResponseDto(newJob.getJobId(), newJob.getTitle(), newJob.getDescription(), newJob.getCreatorId(), newJob.getStatus(), newJob.getLastUpdate(), newJob.getRequirements());
        } catch (Exception e) {
            String message = "Erro ao salvar a vaga: " + data.getTitle() + " " + data.getDescription();
            logger.error(message, e);
            throw new ExistingException(message);
        }
    }

    public JobResponseDto updateJob(Integer id, Job data) {
        logger.info("Atualizando vaga com id: {}", id);
        Job job = getJobById(id);
        try {
            Job updatedJob = JobFactory.updateJob(job, data);
            jobRepository.save(updatedJob);
            logger.info("Vaga atualizada com sucesso: {}", updatedJob.getTitle());
            return new JobResponseDto(updatedJob.getJobId(), updatedJob.getTitle(), updatedJob.getDescription(), updatedJob.getCreatorId(), updatedJob.getStatus(), updatedJob.getLastUpdate(), updatedJob.getRequirements());
        } catch (Exception e) {
            String message = "Erro ao salvar a vaga: " + data.getTitle() + data.getDescription();
            logger.error(message, e);
            throw new ExistingException(message);
        }
    }

    public void deleteJob(Integer id) {
        logger.info("Excluindo vaga com id: {}", id);
        Job job = getJobById(id);
        jobRepository.delete(job);
    }
}
