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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Table(name = "users")
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "status")
    private StatusAccount status = StatusAccount.PENDING;

    @Column(name = "years_experience")
    private YearsExperience yearsExperience;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;


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
        if(this.role == UserRole.ADMIN) return Stream.of("ROLE_ADMIN").map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        else return Stream.of("ROLE_USER").map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
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
