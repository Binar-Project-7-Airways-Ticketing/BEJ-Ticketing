package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> getAllUsers();
    boolean deleteUser(Long id);
    User getUserById(Long id);
    User postUser(User user);
}
