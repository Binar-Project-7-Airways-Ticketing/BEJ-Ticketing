package com.binar.bejticketing.service.user;

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
    public User postUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getRoleStatus());
        return roleRepository.save(role);
    }

    @Override
    public void addRoletoUser(String username, String roleName) {
    log.info("adding role {} to user {}",roleName,username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByroleStatus(roleName);
        user.getRoles().add(role);
    }
    @Override
    public User findById(Long id) {
        Optional<User> search = userRepository.findById(id);
        if(search.isPresent()) {
            log.info("updated!");
            return search.get();
        }
        else{
            log.error("No such movie existed");
            return null;
        }
    }
    @Override
    public User updaterUser(Long id,User user) {
        User users = findById(id);
        if (user != null) {
            user.setUsername(user.getUsername());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setAddress(user.getAddress());
            user.setBirthday(user.getBirthday());
            user.setEmail(user.getEmail());
            user.setPassword(user.getPassword());
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
