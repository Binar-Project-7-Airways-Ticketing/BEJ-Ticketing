package com.binar.bejticketing.controller.users;

import com.binar.bejticketing.entity.ERoles;
import com.binar.bejticketing.entity.Role;
import com.binar.bejticketing.entity.User;
import com.binar.bejticketing.payload.request.LoginRequest;
import com.binar.bejticketing.payload.request.SignUpRequest;
import com.binar.bejticketing.payload.response.JwtResponse;
import com.binar.bejticketing.payload.response.MessageResponse;
import com.binar.bejticketing.repository.RoleRepository;
import com.binar.bejticketing.repository.UserRepository;
import com.binar.bejticketing.security.JwtUtils;
import com.binar.bejticketing.security.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = null;
        if (loginRequest.getEmail() != null){
            User user = userRepository.findByEmail(loginRequest.getEmail());
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getDisplayName()
                            , loginRequest.getPassword()));
        }else {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getDisplayName()
                            , loginRequest.getPassword()));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getDisplayname(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getBirthday(),
                userDetails.getGender(),
                userDetails.getEmail(),
                roles,
                userDetails.getPictureUrl()));
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByDisplayName(signUpRequest.getDisplayName()).equals(true)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail()).equals(true)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new account
        User user = new User();
        user.setDisplayName(signUpRequest.getDisplayName());
        user.setFirstName(signUpRequest.getFirstname());
        user.setLastName(signUpRequest.getLastname());
        user.setGender(signUpRequest.getGender());
        user.setBirthday((Date) signUpRequest.getBirthday());
        user.setEmail(signUpRequest.getEmail());

        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();

        if (strRole == null) {
            Role userRole = roleRepository.findByRoleStatus(String.valueOf(ERoles.USER_ROLE));
            user.setRole(userRole);
        }
        else{
            {
                if ("admin".equals(strRole)) {
                    Role adminRole = roleRepository.findByRoleStatus(String.valueOf(ERoles.ADMIN_ROLE));
                    user.setRole(adminRole);
                } else {
                    Role userRole = roleRepository.findByRoleStatus(String.valueOf(ERoles.USER_ROLE));
                    user.setRole(userRole);
                }
            }
        }
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully! "));
    }
}
