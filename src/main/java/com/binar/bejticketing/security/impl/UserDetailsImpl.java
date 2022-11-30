package com.binar.bejticketing.security.impl;

import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String birthday;
    private final String address;
    private final String email;
    private final String noHp;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(long id, String username, String firstname, String lastname, String birthday, String address, String email, String password, String noHp, List<SimpleGrantedAuthority> authorities) {
        this.id=id;
        this.username=username;
        this.firstname=firstname;
        this.lastname=lastname;
        this.birthday=birthday;
        this.address=address;
        this.email=email;
        this.password=password;
        this.noHp =noHp;
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
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword(),
                user.getNoHp(),
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
    public String getAddress() {
        return address;
    }
    public String getNoHp(){
        return noHp;
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
        return username;
    }
    public String getEmail(){
        return email;
    }

    public String getBirthday(){
        return birthday;
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