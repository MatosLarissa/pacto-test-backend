package com.pacto.internalrecruitment.model.dto.user;

import com.pacto.internalrecruitment.model.enums.user.UserRole;
import com.pacto.internalrecruitment.shared.enums.Experience;
import com.pacto.internalrecruitment.shared.enums.Status;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequestDto {

    private String name;

    private String email;

    private String password;

    private UserRole role;

    private Status status;

    private Date lastActivity;

    private Date creationDate;

    private Experience experienceId;

    public UserRequestDto(String name, String email, String password, UserRole role, Status status, Date lastActivity, Date creationDate, Experience experienceId ){
      this.name = name;
      this.email = email;
      this.password = password;
      this.role = role;
      this.status = status;
      this.lastActivity = lastActivity;
      this.creationDate = creationDate;
      this.experienceId = experienceId;
    }
}
