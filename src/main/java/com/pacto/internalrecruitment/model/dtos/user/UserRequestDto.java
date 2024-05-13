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

    private UserRole role;

    private YearsExperience yearsExperience;

    public UserRequestDto(String firstName, String lastName, String email, String password, UserRole role, YearsExperience yearsExperience ){
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.role = role;
      this.yearsExperience = yearsExperience;
    }
}
