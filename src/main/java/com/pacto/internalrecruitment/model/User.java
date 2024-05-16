package com.pacto.internalrecruitment.model;

import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Table(name = "users")
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusAccount status = StatusAccount.PENDING;

    @Column(name = "years_experience")
    @Enumerated(EnumType.STRING)
    private YearsExperience yearsExperience;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToMany
    @JoinTable(
            name = "user_requirement",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id", referencedColumnName = "requirement_id")
    )
    private Set<Requirement> requirements = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.lastActivity = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }
    public void setUsername(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
