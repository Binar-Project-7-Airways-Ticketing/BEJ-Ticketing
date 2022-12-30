package com.binar.bejticketing.controller.users;

import com.binar.bejticketing.dto.NotificationDto;
import com.binar.bejticketing.dto.NotificationUpdateDto;
import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.service.NotificationService;
import com.binar.bejticketing.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Authorize")
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto){
        Notification notification = modelMapper.map(notificationDto,Notification.class) ;
        User user = userService.getUserById(notificationDto.getUser());
        notification.setUser(user);


        return new ResponseEntity<>(notificationService.createNotification(notification), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Notification> updateNotification(@RequestBody NotificationUpdateDto notificationDto){
        Notification notification = modelMapper.map(notificationDto,Notification.class) ;
        User user = userService.getUserById(notificationDto.getUser());
        notification.setUser(user);


        return new ResponseEntity<>(notificationService.updateNotification(notification), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataNotFoundException> deleteNotification(@PathVariable("id")Long id){


        return new ResponseEntity<>(notificationService.deleteNotification(id),HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Notification>> getAllNotificationByUser(@PathVariable("idUser")Long id){
        return new ResponseEntity<>(notificationService.getAllNotificationByUser(id), HttpStatus.OK);
    }

    @GetMapping("/get/{idNotification}")
    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable("idNotification")Long id){
        return new ResponseEntity<>(notificationService.getNotificationyId(id), HttpStatus.OK);
    }

    @PutMapping("/read/{idNotification}")
    public ResponseEntity isRead(@PathVariable("idNotification")Long id){
        notificationService.isRead(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
