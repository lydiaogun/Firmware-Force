package com.firmwareforce.firmwareforcebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.firmwareforce.firmwareforcebackend.DTO.SignupRequest;
import com.firmwareforce.firmwareforcebackend.user;
import com.firmwareforce.firmwareforcebackend.manager.UserTableManager;
import com.firmwareforce.firmwareforcebackend.service.SignupService;
import com.firmwareforce.firmwareforcebackend.service.LoginService;
import com.firmwareforce.firmwareforcebackend.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserTableManager tableManager;

    @Autowired
    private SignupService signupService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<user> getAllUsers() {
        return tableManager.getAllUsers();
    }


    @GetMapping("/{username}")
    public ResponseEntity<user> getUserByUsername(@PathVariable String username) {
        return tableManager.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        try {
            user newUser = new user();
            newUser.setUsername(signupRequest.getUsername());
            newUser.setEmailAddress(signupRequest.getEmailAddress());
            newUser.setPhoneNumber(signupRequest.getPhoneNumber());
            newUser.setDateOfBirth(signupRequest.getDateOfBirth());
            newUser.setAddress(signupRequest.getAddress());
            newUser.setUserRole(signupRequest.getUserRole());

            String hashedPassword = authService.hashPassword(signupRequest.getPassword());
            newUser.setPasswordHash(hashedPassword);
            user createdUser = signupService.registerNewUser(newUser);
            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin/{username}/{rawPassword}")
    public ResponseEntity<?> signin(@PathVariable String username, @PathVariable String rawPassword) {
        try {

            HashMap<String, String> authData = loginService.authenticate(username, rawPassword);

            if (authData != null)
            {
                return (ResponseEntity.ok(authData));
            }
            else
            {
                return (ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sign in failed"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // used to create new user
    @PostMapping
    public user createUser(@RequestBody user newUser) {
        return tableManager.createUser(newUser);
    }
}
