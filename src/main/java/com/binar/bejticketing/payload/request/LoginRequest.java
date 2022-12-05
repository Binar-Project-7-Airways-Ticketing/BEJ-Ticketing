package com.binar.bejticketing.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginRequest {
//    @NotBlank
    private String displayName;

    @NotBlank
    private String password;

//    @NotBlank
    @Email
    private String email;

//    public String getDisplayname() {
//        return displayname;
//    }
//    public String getEmail() {
//        return email;
//    }
//
//    public void setDisplayname(String displayname) {
//        this.displayname = displayname;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
