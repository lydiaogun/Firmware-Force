package com.firmwareforce.firmwareforcebackend.service;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import com.firmwareforce.firmwareforcebackend.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.firmwareforce.firmwareforcebackend.user;
import org.springframework.beans.factory.annotation.Value;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public HashMap<String, String> authenticate(String username, String rawPassword) 
    {
        user profile = userRepository.findByUsername(username).orElse(null);

        if (profile != null) 
        {
            Boolean passwordMatch = authService.checkPassword(profile.getPasswordHash(), rawPassword);

            if (passwordMatch)
            {
                SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

                String token = Jwts.builder()
                .setSubject(username)
                .claim("role", profile.getUserRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();

                HashMap<String, String> authData = new HashMap<String, String>();
                authData.put("token", token);
                authData.put("userRole", profile.getUserRole().toString());
                authData.put("userId", profile.getUserId().toString());

                return authData;
            }
            else
            {
                return null;
            }
        }

        return null;
    }
}
