package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        logger.info("Buscando todos os usuários");
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        logger.info("Buscando usuário por ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        logger.info("Salvando usuário: {}", user);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        logger.info("Atualizando usuário: {}", user);
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        logger.info("Excluindo usuário por ID: {}", id);
        userRepository.deleteById(id);
    }
}
