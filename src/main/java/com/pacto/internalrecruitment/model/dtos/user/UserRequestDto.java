package com.pacto.internalrecruitment.model.dtos.user;


import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

@Data
public class UserRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String roles;

    private YearsExperience yearsExperience;

    public UserRequestDto(String firstName, String lastName, String email, String password, String roles, YearsExperience yearsExperience ){
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.roles = roles;
      this.yearsExperience = yearsExperience;
    }
}
