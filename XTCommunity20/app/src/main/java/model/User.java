package model;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private Gender gender;
    private String dateOfBirth;
    private String email;
    private String phone;

    public User(String username, String password
            , String fullName, Gender gender
            ,String dateOfBirth, String email
            ,String phone) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.fullName = "";
        this.gender = null;
        this.dateOfBirth= "";
        this.email = "";
        this.phone = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
