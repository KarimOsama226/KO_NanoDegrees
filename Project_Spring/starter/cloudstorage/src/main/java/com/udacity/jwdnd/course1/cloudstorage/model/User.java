package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;

    public User(Integer userId, String userName, String encodedSalt, String hashedPassword, String firstName, String lastName) {
        this.userName= userName;
        this.salt = encodedSalt;
        this.password = hashedPassword;
        this.firstName=firstName;
        this.lastName=lastName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
