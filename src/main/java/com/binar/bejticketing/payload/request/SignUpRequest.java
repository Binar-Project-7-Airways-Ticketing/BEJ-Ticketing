package com.binar.bejticketing.payload.request;

import com.binar.bejticketing.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
public class SignUpRequest {
    @NotBlank
    @Size(min = 3)
    private String displayName;

    @NotBlank
    @Email
    private String email;

    private String firstname;

    private String lastname;

    private String role;

    private Gender gender;

    @NotBlank
    @Size(min = 2)
    private String password;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

//    public String getDisplayName() {
//        return displayname;
//    }
//
//    public void setDisplayName(String displayName) {
//        this.displayname = displayName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
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
//
//    public Object getBirthday() {
//        return birthday;
//    }
}

