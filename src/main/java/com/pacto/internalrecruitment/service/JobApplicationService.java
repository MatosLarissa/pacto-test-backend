package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.AlreadyExistsException;
import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.JobApplication;
import com.pacto.internalrecruitment.model.User;

import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationRequestDto;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationResponseDto;
import com.pacto.internalrecruitment.repository.JobApplicationRepository;
import com.pacto.internalrecruitment.repository.JobRepository;
import com.pacto.internalrecruitment.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(JobApplicationService.class);

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Autowired
    public JobApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository, JobRepository jobRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public List<JobApplication> findAllApplications() {
        logger.info("Procurando todas as candidaturas");
        return jobApplicationRepository.findAll();
    }

    public List<JobApplication> getApplicationByUserId(Integer id) {
        logger.info("Procurando candidatura por usuário com id: {}", id);
        List<JobApplication> applications = jobApplicationRepository.findByUserId(id);
        if(applications.isEmpty()) {
            logger.info("Nenhuma candidatura encontrada para o usuário com id: {}", id);
            return Collections.emptyList();
        }
        return applications;
    }

    public JobApplication getApplicationById(Integer id) {
        logger.info("Procurando candidatura por id: {}", id);
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidatura com id " + id + " não encontrada"));
    }


    public List<JobApplication> getJobByStatus(String status) {
        logger.info("Procurando aplicação por status: {}", status);
        try {
            return jobApplicationRepository.findByStatusContaining(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Status inválido: {}", status);
            return Collections.emptyList();
        }
    }

    public JobApplicationResponseDto applyToJob(JobApplicationRequestDto data) {
        User userFound = userRepository.findById(data.getUserId()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Job jobFound = jobRepository.findById(data.getJobId())
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));

        if (jobApplicationRepository.existsByUserIdAndJobId(data.getUserId(), data.getJobId())) {
            throw new AlreadyExistsException("Já esta cadastrado nesta vaga.");
        }else{
            try {
                JobApplication createJobApplication = new JobApplication();

                createJobApplication.setJobId(jobFound.getJobId());
                createJobApplication.setUserId(userFound.getUserId());
                jobApplicationRepository.save(createJobApplication);
                logger.info("O usuário com id: {} se candidatou à vaga com id: {} com sucesso.", data.getUserId(), data.getJobId() );

                return new JobApplicationResponseDto(
                        createJobApplication.getJobApplicationId(),
                        createJobApplication.getStatus(),
                        createJobApplication.getUserId(),
                        createJobApplication.getJobId(),
                        createJobApplication.getApplicationDate()
                );

            }catch (Exception e){
                String message = "Erro ao canditar o usuário de id: " + data.getUserId() + " a vaga de id: " + data.getJobId();
                logger.error(message, e);
                throw new ExistingException(message);
            }
        }
    }

    public JobApplicationResponseDto updateJobApplication(Integer id, JobApplication data) {
        logger.info("Atualizando candidatura com id: {}", id);
        try {
            JobApplication jobApplicationFound = getApplicationById(id);
            jobApplicationFound.setStatus(data.getStatus());
            JobApplication applicationUpdated = jobApplicationRepository.save(jobApplicationFound);
            logger.info("Candidatura de id: {} foi atualizada para o status: {} com sucesso",id, applicationUpdated.getStatus());
            return new JobApplicationResponseDto(
                    applicationUpdated.getJobApplicationId(),
                    applicationUpdated.getStatus(),
                    applicationUpdated.getUserId(),
                    applicationUpdated.getJobId(),
                    applicationUpdated.getApplicationDate()
            );
        } catch (Exception e) {
            String message = "Erro ao atualizar candidatura de id: "+ id +" para o status: " + data.getStatus();
            logger.error(message, e);
            throw new ExistingException(message);
        }
    }

    public void updateFeedbackStatus(Integer id, String newStatus) {
        logger.info("Atualizando status do feedback com id: {}", id);
        try {
            JobApplication feedbackFound = jobApplicationRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Feedback não encontrado."));

            feedbackFound.setStatus(newStatus);
            jobApplicationRepository.save(feedbackFound);
            logger.info("Feedback de id: {} foi atualizado para o status: {} com sucesso", id, newStatus);
        } catch (Exception e) {
            String message = "Erro ao atualizar status do feedback de id: " + id;
            logger.error(message, e);
            throw new ExistingException(message);
        }
    }


    public void deleteJob(Integer id) {
        logger.info("Excluindo candidatura com id: {}", id);
        JobApplication jobApplication = getApplicationById(id);
        jobApplicationRepository.delete(jobApplication);
    }

}
