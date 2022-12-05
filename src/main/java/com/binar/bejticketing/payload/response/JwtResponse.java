package com.binar.bejticketing.payload.response;

import com.binar.bejticketing.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String displayName;
    private String firstname;
    private String lastname;
    private Gender gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;
    private String email;
    private List<String> role;
    private String pictureUrl;


    public JwtResponse(String token, Long id, String displayName, String firstname, String lastname, Date birthday , Gender gender, String email, List<String> role, String pictureUrl) {
        this.token = token;
        this.id = id;
        this.displayName = displayName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
        this.pictureUrl = pictureUrl;
    }
}
