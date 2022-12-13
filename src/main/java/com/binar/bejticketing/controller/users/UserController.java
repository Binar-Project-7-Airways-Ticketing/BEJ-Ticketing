package com.binar.bejticketing.controller.users;

import com.binar.bejticketing.dto.PasswordDto;
import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.dto.UserUpdateDto;
import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.payload.response.MessageResponse;
import com.binar.bejticketing.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    @Value("${CLOUDINARY_URL}")
    Cloudinary cloudinary;
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<String> addRoletoUser(@RequestBody RoleToUserForm form) {
        userService.addRoletoUser(form.getDisplayname(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto){

        return userService.updaterUser(id,userUpdateDto);
    }
    @Data
    class RoleToUserForm{
        private String displayname;
        private String roleName;
    }
    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ResponseData> upload(@PathVariable("id") Long id , @RequestParam("image") MultipartFile file)throws IOException {
        byte[] bit = file.getBytes();
        Files.write(Path.of("src/main/java/com/binar/bejticketing/media/profile.jpg"),bit);

        String filename = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(new File("src/main/java/com/binar/bejticketing/media/profile.jpg"),
                ObjectUtils.asMap("public_id", filename));

        String url = cloudinary.url().imageTag(filename);
        url = url.replaceAll("<img src='","");
        url = url.replaceAll("'\\/>","");

        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(url);

        userService.uploadImage(url, id);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update/password/{id}")
    public MessageResponse changePassword(@PathVariable Long id, @RequestBody PasswordDto password){
        String pass = bCryptPasswordEncoder.encode(password.getPass());
        userService.changePassword(pass , id);
        return new MessageResponse("Password Change Successfully");
    }


}
