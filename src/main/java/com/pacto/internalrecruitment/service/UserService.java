package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.*;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final JobApplicationService jobApplicationService;
    private final JobService jobService;
    private final FeedbackService feedbackService;

    @Autowired
    public UserService(UserRepository userRepository, JobApplicationService jobApplicationService, JobService jobService, FeedbackService feedbackService) {
        this.userRepository = userRepository;
        this.jobApplicationService = jobApplicationService;
        this.jobService = jobService;
        this.feedbackService = feedbackService;
    }

    public List<User> findAllUsers() {
        logger.info("Buscando todos os usuários");
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        logger.info("Buscando usuário por ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }


    public UserJobAndFeedback findUserByIdAndReturnJobAndFeedback(Integer id) {
        logger.info("Buscando vagas para o usuário de ID: {}", id);

        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            logger.info("Nenhuma usuário encontrado para o id: {}", id);
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = existingUser.get();
        List<JobApplication> jobApplicationList = jobApplicationService.getApplicationByUserId(id);
        List<Feedback> feedbackList = feedbackService.getFeedbackByUserCandidateId(id);

        UserJobAndFeedback userJobAndFeedback = new UserJobAndFeedback();
        userJobAndFeedback.setUserId(id);
        userJobAndFeedback.setFirstName(user.getFirstName());
        userJobAndFeedback.setLastName(user.getLastName());
        userJobAndFeedback.setEmail(user.getEmail());
        userJobAndFeedback.setYearsExperience(user.getYearsExperience());
        userJobAndFeedback.setLastActivity(user.getLastActivity());

        Set<JobAndApplicationAndFeedback> jobAndApplicationsSet = new HashSet<>();
        for (JobApplication application : jobApplicationList) {
            JobAndApplicationAndFeedback jobAndApplication = new JobAndApplicationAndFeedback();
            jobAndApplication.setJobApplication(application);
            Job job = jobService.getJobById(application.getJobId());
            jobAndApplication.setJob(job);

            Feedback feedback = feedbackList.stream()
                    .filter(f -> f.getJobApplicationId().equals(application.getJobApplicationId()))
                    .findFirst()
                    .orElse(null);

            jobAndApplication.setFeedback(feedback);
            jobAndApplicationsSet.add(jobAndApplication);
        }

        userJobAndFeedback.setJobApplicationsAndFeedback(jobAndApplicationsSet);

        return userJobAndFeedback;
    }


    public void deleteUserById(Integer id) {
        logger.info("Excluindo usuário por ID: {}", id);
        userRepository.deleteById(id);
    }
}
