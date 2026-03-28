package com.firmwareforce.firmwareforcebackend.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService 
{
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hashPassword(String password)
    {
        return encoder.encode(password);
    }

    public boolean checkPassword(String userHashedPassword, String rawPassword)
    {
        return encoder.matches(rawPassword, userHashedPassword);
    }

}