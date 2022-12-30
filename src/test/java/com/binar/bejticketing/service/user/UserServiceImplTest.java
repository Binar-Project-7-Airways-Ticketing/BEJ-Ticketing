package com.binar.bejticketing.service.user;


import com.binar.bejticketing.dto.UserUpdateDto;
import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.repository.UserRepository;
import com.binar.bejticketing.utils.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

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

        ArgumentCaptor<User> userEntityArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userEntityArgumentCaptor.capture());

        User captureUser = userEntityArgumentCaptor.getValue();
        assertThat(captureUser).isEqualTo(userUpdate);
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

    void addRoletoUser(){

    }
    void saveRole(){

    }
}
