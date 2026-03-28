package com.firmwareforce.firmwareforcebackend.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firmwareforce.firmwareforcebackend.user;
import com.firmwareforce.firmwareforcebackend.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserTableManager {

    @Autowired
    private UserRepository userRepository;

    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<user> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public user createUser(user newUser) {
        return userRepository.save(newUser);
    }
}