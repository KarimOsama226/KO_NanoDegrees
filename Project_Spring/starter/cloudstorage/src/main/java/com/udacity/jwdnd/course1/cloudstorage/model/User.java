package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;

    public User(Integer userId, String username, String encodedSalt, String hashedPassword, String firstName, String lastName) {
        this.username= username;
        this.salt = encodedSalt;
        this.password = hashedPassword;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public User() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
