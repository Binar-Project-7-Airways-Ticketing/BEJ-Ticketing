package com.binar.bejticketing.service.user;


import com.binar.bejticketing.dto.UserUpdateDto;
import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.repository.RoleRepository;
import com.binar.bejticketing.repository.UserRepository;
import com.binar.bejticketing.utils.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void updaterUser(){
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserUpdateDto userUpdate = new UserUpdateDto();
        userUpdate.setDisplayName("Rehan1");
        userUpdate.setFirstName("Rehan");
        userUpdate.setLastName("Ritonga");
        userUpdate.setGender(Gender.PRIA);
        userUpdate.setEmail("Raihan123@gmail.com");


        userService.updaterUser(1L,userUpdate);
        Optional<User> userUpdates = userRepository.findById(1L);
        ArgumentCaptor<User> userEntityArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).saveAndFlush(userEntityArgumentCaptor.capture());

        Optional<User> captureUser = Optional.ofNullable(userEntityArgumentCaptor.getValue());
        assertThat(captureUser).isEqualTo(userUpdates);
    }

    @Test
    void deleteUser(){
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUser(1L);
        verify(userRepository).deleteUserById(1L);
    }

    @Test
    void getAllUsers(){
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void getUserById(){
        userService.getUserById(1L);
        verify(userRepository).getUserById(1L);
    }

    @Test
    void uploadImage(){
        userService.uploadImage("https//image.com",1L);
        verify(userRepository).uploadImage("https//image.com",1L);
    }

    @Test
    void findById(){
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.findById(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void changePassword(){
        userService.changePassword("Rehan170882",1L);
        verify(userRepository).changePassword("Rehan170882",1L);
    }

    @Test
    void addRoletoUser(){
        User user = new User();
        user.setDisplayName("Tes");
        Role role = new Role();
        role.setRoleStatus("USER");
        when(userRepository.findByDisplayName(user.getDisplayName())).thenReturn(user);
        when(roleRepository.findByRoleStatus(role.getRoleStatus())).thenReturn(role);

        userService.addRoletoUser("Tes","USER");
        verify(userRepository).save(user);
    }
    @Test
    void saveRole(){
        Role role = new Role();
        role.setRoleStatus("ADMIN");

        userService.saveRole(role);
        verify(roleRepository).save(role);
    }
}
