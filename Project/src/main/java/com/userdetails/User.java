package com.userdetails;

public class User
{
    private String userFullName;
    private String emailId;
    private String userId;
    private String password;

    public String getUserFullName() {
        return userFullName;}
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getEmailId() {return emailId;}

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passward) {
        this.password = passward;
    }

    public User(String userFullName, String emailId, String userId, String password) {
        this.userFullName = userFullName;
        this.emailId = emailId;
        this.userId = userId;
        this.password = password;
    }

}
