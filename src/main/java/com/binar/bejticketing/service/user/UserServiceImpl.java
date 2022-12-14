package com.binar.bejticketing.service.user;

import com.binar.bejticketing.dto.UserUpdateDto;
import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.exception.EntityNotFoundException;
import com.binar.bejticketing.repository.RoleRepository;
import com.binar.bejticketing.repository.UserRepository;
import com.binar.bejticketing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> id1 = userRepository.findById(id);
        if (id1.isEmpty()){
            throw new EntityNotFoundException(id, User.class);
        }
        userRepository.deleteUserById(id);
        return true;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getRoleStatus());
        return roleRepository.save(role);
    }

    @Override
    public void addRoletoUser(String displayName, String roleName) {
    log.info("adding role {} to user {}",roleName,displayName);
        User user = userRepository.findByDisplayName(displayName);
        Role role = roleRepository.findByRoleStatus(roleName);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void uploadImage(String url, Long id) {
        userRepository.uploadImage(url, id);
    }

    @Override
    public User findById(Long id) {
        Optional<User> search = userRepository.findById(id);
        if(search.isPresent()) {
            return search.get();
        }
        else{
            return null;
        }
    }

    @Override
    public void changePassword(String password, Long id) {
        userRepository.changePassword(password,id);
    }

    @Override
    public User updaterUser(Long id, UserUpdateDto user) {
        User users = findById(id);
        if (user != null) {
            users.setDisplayName(user.getDisplayName());
            users.setFirstName(user.getFirstName());
            users.setLastName(user.getLastName());
            users.setGender(user.getGender());
            users.setBirthday(user.getBirthday());
            users.setEmail(user.getEmail());
            userRepository.saveAndFlush(users);
        }
        return users;
    }


}
