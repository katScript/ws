package com.spring.web.app.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    @NotBlank(message = "Username can not empty!")
    private String username;

    @NotNull
    @NotBlank(message = "Password can not empty!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
