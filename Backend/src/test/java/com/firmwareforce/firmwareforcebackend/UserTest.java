package com.firmwareforce.firmwareforcebackend;

import com.firmwareforce.firmwareforcebackend.service.SignupService;
import com.firmwareforce.firmwareforcebackend.repository.UserRepository;
import com.firmwareforce.firmwareforcebackend.manager.UserTableManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SignupService signupService;

    @InjectMocks
    private UserTableManager userTableManager;

    @Test
    public void testRegisterNewUser() {
        user newUser = new user();
        newUser.setUsername("NewGuy");
        newUser.setEmailAddress("newguy@test.com");
        when(userRepository.findByUsername("NewGuy")).thenReturn(Optional.empty());
        when(userRepository.findByEmailAddress("newguy@test.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(user.class))).thenReturn(newUser);
        user createdUser = signupService.registerNewUser(newUser);
        assertNotNull(createdUser);
        assertEquals(UserRole.COMMUNITY, createdUser.getUserRole());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void testGetByUsername() {
        // 1. ARRANGE
        user newUser2 = new user("mock user2", "mockUser1@gmail.com", "07345 678910", "01/04/2005", "University of Birmingham", UserRole.ADMIN);

        // Stub the repository: "When someone asks for 'mock user2', return this user"
        when(userRepository.findByUsername("mock user2")).thenReturn(Optional.of(newUser2));

        // 2. ACT
        // This will now successfully find the user because the mock is "programmed" to respond
        user gottenUser = userTableManager.getUserByUsername("mock user2").orElse(null);

        // 3. ASSERT
        assertNotNull(gottenUser, "The manager should have found the user");
        assertEquals(newUser2, gottenUser, "The returned user should match our mock data");

        // Verify that the manager actually talked to the repository
        verify(userRepository).findByUsername("mock user2");
    }
}
