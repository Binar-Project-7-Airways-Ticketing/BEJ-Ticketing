package com.binar.bejticketing.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String birthday;
    private String address;
    private String email;
    private String noHp;
    private List<String> role;
    private String pictureUrl;


    public JwtResponse(String token,Long id, String username, String firstname, String lastname,String birthday , String address, String email, String noHp,  List<String> role,String pictureUrl) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.noHp = noHp;
        this.role = role;
        this.pictureUrl = pictureUrl;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRole() {
        return role;
    }
}
