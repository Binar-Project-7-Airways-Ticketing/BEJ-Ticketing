package com.binar.bejticketing.service.user;

import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.exception.EntityNotFoundException;
import com.binar.bejticketing.repository.UserRepository;
import com.binar.bejticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
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

    static User unwrapUser(Optional<User> entity, Long id){
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
