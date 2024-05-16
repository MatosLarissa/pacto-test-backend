package com.pacto.internalrecruitment.model.dtos.admin;

import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import com.pacto.internalrecruitment.model.User;

@Data
public class AdminResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String roles;

    private StatusAccount status;

    private YearsExperience yearsExperience;

    private LocalDateTime lastActivity;

    private LocalDateTime creationDate;

    public AdminResponseDto(User adminResponseDto){
      this.firstName = adminResponseDto.getFirstName();
      this.lastName = adminResponseDto.getLastName();
      this.email = adminResponseDto.getEmail();
      this.password = adminResponseDto.getPassword();
      this.roles = adminResponseDto.getRoles().toString();
      this.status = adminResponseDto.getStatus();
      this.lastActivity = adminResponseDto.getLastActivity();
      this.creationDate = adminResponseDto.getCreationDate();
    }

}
