package com.firmwareforce.firmwareforcebackend.service;

import com.firmwareforce.firmwareforcebackend.user;
import com.firmwareforce.firmwareforcebackend.UserRole;
import com.firmwareforce.firmwareforcebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private UserRepository userRepository;
    
    public user registerNewUser(user newUser) {
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.findByEmailAddress(newUser.getEmailAddress()).isPresent()) {
            throw new RuntimeException("An account with this email already exists!");
        }

        if (newUser.getUserRole() == null) {
            newUser.setUserRole(UserRole.COMMUNITY);
        }
        
        return userRepository.save(newUser);
    }
}

