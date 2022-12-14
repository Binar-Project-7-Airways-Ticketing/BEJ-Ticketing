package com.binar.bejticketing.service;

import com.binar.bejticketing.dto.UserUpdateDto;
import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> getAllUsers();
    boolean deleteUser(Long id);
    User updaterUser(Long id, UserUpdateDto user);
    User getUserById(Long id);
    User findById(Long id);
    void changePassword(String password , Long id);
    Role saveRole(Role role);
    void addRoletoUser(String displayName, String roleName);
    void uploadImage(String url, Long id);
}
