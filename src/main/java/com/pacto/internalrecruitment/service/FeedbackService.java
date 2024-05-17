package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Feedback;
import com.pacto.internalrecruitment.model.JobApplication;
import com.pacto.internalrecruitment.model.dtos.feedback.FeedbackRequestDto;
import com.pacto.internalrecruitment.repository.FeedbackRepository;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    private final FeedbackRepository feedbackRepository;
    private final JobApplicationService jobApplicationService;
    private final UserRepository userRepository;
    ;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, JobApplicationService jobApplicationService, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.jobApplicationService = jobApplicationService;
        this.userRepository = userRepository;
    }

    public List<Feedback> findAllFeedback() {
        logger.info("Buscando todos os feedbacks");
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Integer id) {
        logger.info("Procurando feedback por id: {}", id);
        return feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException("Candidatura não encontrada"));
    }

    public List<Feedback> getFeedbackByUserCandidateId(Integer id) {
        logger.info("Buscando feedbacks por usuário candidato com id: {}", id);
        List<Feedback> feedbacks = feedbackRepository.findByUserCandidateId(id);
        return feedbacks.isEmpty() ? Collections.emptyList() : feedbacks;
    }

    public List<Feedback> getFeedbackByUserResponseId(Integer id) {
        logger.info("Buscando feedbacks por usuário responsável com id: {}", id);
        List<Feedback> feedbacks = feedbackRepository.findByUserResponseId(id);
        return feedbacks.isEmpty() ? Collections.emptyList() : feedbacks;
    }

    public Feedback getFeedbackByJobApplicationId(Integer id) {
        logger.info("Buscando feedback por id da aplicação: {}", id);
        return feedbackRepository.findByJobApplicationId(id)
                .orElseThrow(() -> new NotFoundException("Feedback não encontrado"));
    }

    public Feedback createFeedback(FeedbackRequestDto data) {
        logger.info("Iniciando criação de feedback");

        try {
            logger.info("Chamando getApplicationById com id: {}", data.getJobApplicationId());

            jobApplicationService.getApplicationById(data.getJobApplicationId());
            userRepository.findById(data.getUserCandidateId()).orElseThrow(() -> new NotFoundException("Não foi possivel encontrar o candidato"));
            jobApplicationService.updateFeedbackStatus(data.getJobApplicationId(), data.getStatusApplicant().toUpperCase());

            Feedback feedback = buildFeedbackFromDto(data);
            feedbackRepository.save(feedback);
            logger.info("Feedback para a aplicação com id {} criado com sucesso", data.getJobApplicationId());
            return feedback;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            String message = "Erro ao criar feedback para a aplicação com id: " + data.getJobApplicationId();
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }


    public Feedback updateFeedback(Integer id, FeedbackRequestDto data) {
        getFeedbackById(data.getJobApplicationId());
        Feedback existingJobApplicationId= getFeedbackByJobApplicationId(data.getJobApplicationId());
        try {
            Feedback updatedFeedback = buildFeedbackFromDto(data);
            updatedFeedback.setFeedbackId(existingJobApplicationId.getFeedbackId());
            jobApplicationService.updateFeedbackStatus(data.getJobApplicationId(), data.getStatusApplicant().toUpperCase());
            feedbackRepository.save(updatedFeedback);
            logger.info("Feedback para a aplicação com id {} atualizado com sucesso", data.getJobApplicationId());
            return updatedFeedback;
        } catch (Exception e) {
            String message = "Erro ao atualizar feedback para a aplicação com id: " + data.getJobApplicationId();
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    public void deleteFeedback(Integer id) {
        logger.info("Excluindo feedback com id: {}", id);
        feedbackRepository.deleteById(id);
    }

    private Feedback buildFeedbackFromDto(FeedbackRequestDto data) {
        Feedback feedback = new Feedback();
        feedback.setTitle(data.getTitle());
        feedback.setContent(data.getContent());
        feedback.setUserResponseId(data.getUserResponseId());
        feedback.setUserCandidateId(data.getUserCandidateId());
        feedback.setJobApplicationId(data.getJobApplicationId());
        return feedback;
    }
}
