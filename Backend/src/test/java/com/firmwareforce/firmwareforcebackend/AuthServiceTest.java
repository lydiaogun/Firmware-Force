package com.firmwareforce.firmwareforcebackend;

import com.firmwareforce.firmwareforcebackend.service.AuthService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    
    private final AuthService authService = new AuthService();

    @Test
    public void testPasswordHashed(){
        String rawPassword = "mySecretPassword123";
        String hashedPassword = authService.hashPassword(rawPassword);
        assertNotNull(hashedPassword);
        assertNotEquals(rawPassword, hashedPassword, "The hashed password should not look like the raw password!");
    }

    @Test
    public void testCheckPasswordMatches() {
        String rawPassword = "mySecretPassword123";
        String hashedPassword = authService.hashPassword(rawPassword);
        assertTrue(authService.checkPassword(hashedPassword, rawPassword), "Should return true for the correct password");
        assertFalse(authService.checkPassword(hashedPassword, "wrongPassword"), "Should return false for a bad password");
    }

    @Test
    void checkCorrectPassword() {
        String rawPassword = "Password123";
        String hashedPassword = authService.hashPassword(rawPassword);
        boolean result = authService.checkPassword(hashedPassword, rawPassword);
        assertEquals(true, result);
    }
    @Test
    void checkIncorrectPassword() {
        String rawPassword = "Password123";
        String hashedPassword = authService.hashPassword(rawPassword);
        boolean result =authService.checkPassword(hashedPassword, "wrongPassword");
        assertEquals(false, result);
    }
}