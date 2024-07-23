package com.TeleApps.spring_boot3_jwt_loginPage.response;

import org.springframework.http.HttpStatus;

import com.TeleApps.spring_boot3_jwt_loginPage.entity.UserInformation;

public class UserResponse {

    private String message;
    private HttpStatus status;
    private UserInformation userInfo;

    public UserResponse(String message, HttpStatus status, UserInformation userInfo) {
        this.message = message;
        this.status = status;
        this.userInfo = userInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public UserInformation getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInformation userInfo) {
        this.userInfo = userInfo;
    }

}
