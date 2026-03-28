package com.firmwareforce.firmwareforcebackend;

import com.firmwareforce.firmwareforcebackend.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.firmwareforce.firmwareforcebackend.service.AuthService;
import com.firmwareforce.firmwareforcebackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private LoginService loginService;

    @Test
    public void testAuthenticateValid() {
        // tests for correct password
        user mockUser = new user();
        mockUser.setUserId(1L);
        mockUser.setUsername("testUser");
        mockUser.setPasswordHash("hashedSecret");
        mockUser.setUserRole(UserRole.ADMIN);

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(authService.checkPassword("hashedSecret", "rawPassword123")).thenReturn(true);

        HashMap<String, String> result = loginService.authenticate("testUser", "rawPassword123");
        assertNotNull(result, "The result should not be null for a valid login");
        assertEquals(mockUser.getUserRole().toString(), result.get("userRole"), "The role in the response should match the user's role");
        assertNotNull(result.get("token"), "The response should contain a JWT token");
        assertTrue(result.get("token").startsWith("ey"), "The token should follow the JWT format (starting with 'ey')");

    }

    @Test
    public void testAuthenticateInvalid() {
        // test for wrong password
        user mockUser = new user();
        mockUser.setUsername("testUser");
        mockUser.setPasswordHash("hashedSecret");
        mockUser.setUserRole(UserRole.ADMIN);

        String secret = "12ehewhfshdgpishipghewipheiph4wip5803y08hihoihougduoaguuHPHOPHIPHPI";
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
        .setSubject(mockUser.getUsername())
        .claim("role", mockUser.getUserRole())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(key)
        .compact();

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(authService.checkPassword("hashedSecret", "wrongPassword")).thenReturn(false);

        HashMap<String, String> testData = new HashMap<String, String>();
        testData.put("token", token);
        testData.put("userRole", mockUser.getUserRole().toString());

        boolean result = (loginService.authenticate("testUser", "wrongPassword") == testData);
        assertFalse(result, "Should return false for invalid password");
    }
    @Test // test for user not existing
    public void testUserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
        HashMap<String, String> result = loginService.authenticate("nonExistentUser", "password123");
        assertNull(result, "The service should return null if the user is not found in the database");
    }
    @Test // test for null input
    public void testNullInput() {
        HashMap<String, String> result = loginService.authenticate(null, "password123");
        assertNull(result, "The service should handle null inputs gracefully by returning null.");
    }
}
