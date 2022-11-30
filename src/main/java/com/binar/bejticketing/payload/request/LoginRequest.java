package com.binar.bejticketing.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
//    @NotBlank
    private String username;

    @NotBlank
    private String password;

//    @NotBlank
    @Email
    private String email;

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
