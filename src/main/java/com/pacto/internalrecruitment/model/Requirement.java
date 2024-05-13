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
    private Integer id;

    @Column(name = "requirement_name", unique = true)
    private String requirementName;

    @Column(name = "years_experience")
    private String yearsExperience;

    @ManyToMany(mappedBy = "requirements")
    private Set<Job> jobs = new HashSet<>();

    @ManyToMany(mappedBy = "requirements")
    private Set<User> user = new HashSet<>();

}
