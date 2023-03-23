package com.company.project.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate; // Data = 2023-03-21
    private Instant createdOn; // Data + Hora + GMT = 2023-03-21T17:18:29.989953066Z
    private ZonedDateTime LastLogin; // Data + Hora + Fuso Horário

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Person() {
    }

    public Person(Long userId,
                  String username,
                  String password,
                  String email,
                  LocalDate birthdate,
                  Instant createdOn,
                  ZonedDateTime lastLogin) {
        UserId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.createdOn = createdOn;
        LastLogin = lastLogin;
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
        return LastLogin;
    }

    public void setLastLogin(ZonedDateTime lastLogin) {
        LastLogin = lastLogin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (UserId != null ? !UserId.equals(person.UserId) : person.UserId != null) return false;
        if (username != null ? !username.equals(person.username) : person.username != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (birthdate != null ? !birthdate.equals(person.birthdate) : person.birthdate != null) return false;
        if (createdOn != null ? !createdOn.equals(person.createdOn) : person.createdOn != null) return false;
        return LastLogin != null ? LastLogin.equals(person.LastLogin) : person.LastLogin == null;
    }

    @Override
    public int hashCode() {
        int result = UserId != null ? UserId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (LastLogin != null ? LastLogin.hashCode() : 0);
        return result;
    }
}