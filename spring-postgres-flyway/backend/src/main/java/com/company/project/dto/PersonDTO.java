package com.company.project.dto;

import com.company.project.entities.Person;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class PersonDTO {

    private Long UserId;
    private String username;
    private String password;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private Instant createdOn;
    private ZonedDateTime lastLogin;
    Set<RoleDTO> roles = new HashSet<>();

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        UserId = p.getUserId();
        this.username = p.getUsername();
        this.password = p.getPassword();
        this.email = p.getEmail();
        this.birthdate = p.getBirthdate();
        this.createdOn = p.getCreatedOn();
        this.lastLogin = p.getLastLogin();
        p.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(ZonedDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDTO personDTO = (PersonDTO) o;

        if (UserId != null ? !UserId.equals(personDTO.UserId) : personDTO.UserId != null) return false;
        if (username != null ? !username.equals(personDTO.username) : personDTO.username != null) return false;
        if (password != null ? !password.equals(personDTO.password) : personDTO.password != null) return false;
        if (email != null ? !email.equals(personDTO.email) : personDTO.email != null) return false;
        if (birthdate != null ? !birthdate.equals(personDTO.birthdate) : personDTO.birthdate != null) return false;
        if (createdOn != null ? !createdOn.equals(personDTO.createdOn) : personDTO.createdOn != null) return false;
        return lastLogin != null ? lastLogin.equals(personDTO.lastLogin) : personDTO.lastLogin == null;
    }

    @Override
    public int hashCode() {
        int result = UserId != null ? UserId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }
}
