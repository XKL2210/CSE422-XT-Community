package com.example.xtcommunity;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String mobile;

    public User(String username, String password, String name, LocalDate dateOfBirth, String email, String mobile) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobile = mobile;
        this.id = UUID.randomUUID().toString();
    }

    private boolean checkPassword(String password) {
        return this.password.equalsIgnoreCase(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
