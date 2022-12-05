package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> getAllUsers();
    boolean deleteUser(Long id);
    User updaterUser(Long id,User user);
    User getUserById(Long id);
    User postUser(User user);
    User findById(Long id);
    Role saveRole(Role role);
    void addRoletoUser(String displayName, String roleName);
    void uploadImage(String url, Long id);
}
