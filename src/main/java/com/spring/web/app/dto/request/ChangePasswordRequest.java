package com.spring.web.app.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordRequest {
    @NotNull
    @NotBlank(message = "Username can not empty!")
    private String userName;

    @NotNull
    @NotBlank(message = "Current password can not empty!")
    private String currentPassword;

    @NotNull
    @NotBlank(message = "New password can not empty!")
    private String newPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
