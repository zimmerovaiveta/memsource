package com.example.memsource.dto;

import com.example.memsource.entity.UserCredentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@JsonIgnoreProperties
public class UserCredentialsDto {
    long userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    @Size(min = 8)
    private String password;

    public UserCredentialsDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserCredentialsDto(UserCredentials userCredentials) {
        this.userId = userCredentials.getUserId();
        this.userName = userCredentials.getUserName();
        this.password = userCredentials.getPassword();
    }

    public UserCredentialsDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
