package com.spring.web.app.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResetPasswordRequest {
    @NotNull
    @NotBlank(message = "Token reset can not empty!")
    private String token;

    @NotNull
    @NotBlank(message = "New password can not empty!")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
