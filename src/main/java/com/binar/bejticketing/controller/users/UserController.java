package com.binar.bejticketing.controller.users;

import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.postUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<String> addRoletoUser(@RequestBody RoleToUserForm form) {
        userService.addRoletoUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User users){
        return userService.updaterUser(id,users);
    }
    @Data
    class RoleToUserForm{
        private String username;
        private String roleName;
    }
}
