package com.example.memsource.entity;

import com.example.memsource.dto.UserCredentialsDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCredentials {

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    public UserCredentials() {
    }

    public UserCredentials(UserCredentialsDto userCredentialsDto) {
        this.userId = userCredentialsDto.getUserId();
        this.userName = userCredentialsDto.getUserName();
        this.password = userCredentialsDto.getPassword();
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
