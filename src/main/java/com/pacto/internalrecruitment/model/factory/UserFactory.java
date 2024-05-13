package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.user.SignInRequestDto;
import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserFactory {

    public static User createUser(SignInRequestDto requestDto) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDto.getPassword());
        User newUser = new User();

        newUser.setFirstName(requestDto.getFirstName());
        newUser.setLastName(requestDto.getLastName());
        newUser.setEmail(requestDto.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.USER);
        newUser.setStatus(StatusAccount.PENDING);
        newUser.setYearsExperience(requestDto.getYearsExperience());

        return newUser;
    }
}
