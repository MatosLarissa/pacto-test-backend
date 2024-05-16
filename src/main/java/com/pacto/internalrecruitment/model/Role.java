package com.pacto.internalrecruitment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "role")
@Entity(name = "role")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_type")
    private String roleType;

    public Role(Integer roleId, String roleType) {
        this.roleId = roleId;
        this.roleType = roleType;
    }

    public Role(String roleType) {
        this.roleType = roleType;
    }

}
