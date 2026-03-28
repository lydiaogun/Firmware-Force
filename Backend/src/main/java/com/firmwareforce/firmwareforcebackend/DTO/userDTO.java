package com.firmwareforce.firmwareforcebackend.DTO;

import com.firmwareforce.firmwareforcebackend.UserRole;

public class userDTO 
{
    private String username;
    private UserRole userRole;
    private String passwordHash;
    
    public userDTO(){}

    public userDTO(String username, UserRole userRole, String passwordHash)
    {
        this.username = username;
        this.userRole = userRole;
        this.passwordHash = passwordHash;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
}
