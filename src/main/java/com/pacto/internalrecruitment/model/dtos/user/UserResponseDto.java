package com.pacto.internalrecruitment.model.dtos.user;

import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserRole role;

    private StatusAccount status;

    private YearsExperience yearsExperience;

    private LocalDateTime lastActivity;

    private LocalDateTime creationDate;

    public UserResponseDto(String firstName, String lastName, String email, String password, UserRole role, StatusAccount status, YearsExperience yearsExperience, LocalDateTime lastActivity, LocalDateTime creationDate){
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.role = role;
      this.status = status;
      this.lastActivity = lastActivity;
      this.creationDate = creationDate;
    }
}
