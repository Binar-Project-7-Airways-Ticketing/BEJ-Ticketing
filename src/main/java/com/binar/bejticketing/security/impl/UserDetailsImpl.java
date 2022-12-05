package com.binar.bejticketing.security.impl;

import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String displayname;
    private final String firstname;
    private final String lastname;
    private final Gender gender;
    private final Date birthday;
    private final String email;
    private  final  String pictureUrl;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(long id, String displayname, String firstname, String lastname, Gender gender, Date birthday, String email, String password, String pictureUrl, List<SimpleGrantedAuthority> authorities) {
        this.id=id;
        this.displayname=displayname;
        this.firstname=firstname;
        this.lastname=lastname;
        this.gender=gender;
        this.birthday=birthday;
        this.email=email;
        this.password=password;
        this.pictureUrl=pictureUrl;
        this.authorities=authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());
        List<SimpleGrantedAuthority> authorities =roles
                .stream().map(role -> new SimpleGrantedAuthority(role.getRoleStatus()))
                .toList();

        return new UserDetailsImpl(
                user.getId(),
                user.getDisplayName(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getBirthday(),
                user.getEmail(),
                user.getPassword(),
                user.getPictureUrl(),
                authorities);

    }


    public Long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return displayname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}