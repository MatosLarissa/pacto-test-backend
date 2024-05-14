package com.pacto.internalrecruitment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "requirement")
@Entity(name = "requirement")
@NoArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="requirement_id")
    private Integer requirementId;

    @Column(name = "requirement_name", unique = true)
    private String requirementName;

    @Column(name = "years_experience")
    private String yearsExperience;

}
