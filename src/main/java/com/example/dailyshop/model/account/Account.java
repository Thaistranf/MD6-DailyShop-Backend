package com.example.dailyshop.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "accountTable")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
        private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false, unique = true)
    @Email(message = "Email không hợp lệ")
    private String email;

    private LocalDateTime registrationTime;
    private boolean enabled = true;

    private boolean checkProfile = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public Account(String account, String password, String confirmPassword, String email, LocalDateTime registrationTime, boolean enabled, boolean checkProfile, Set<Role> roles) {
        this.account = account;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.registrationTime = registrationTime;
        this.enabled = enabled;
        this.checkProfile = checkProfile;
        this.roles = roles;
    }

    public Account() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCheckProfile() {
        return checkProfile;
    }

    public void setCheckProfile(boolean checkProfile) {
        this.checkProfile = checkProfile;
    }
}
