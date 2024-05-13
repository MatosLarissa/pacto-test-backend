package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    UserDetails findUserDetailsByEmail(String email);

}
