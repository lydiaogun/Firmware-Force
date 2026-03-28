package com.firmwareforce.firmwareforcebackend;
import jakarta.persistence.*;

@Entity
@Table(name = "app_users")

public class user 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    private String username;

    @Column(name = "emailaddress")
    private String emailAddress;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "dateofbirth")
    private String dateOfBirth;

    private String address;

    @Column(name = "userrole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "passwordhash")
    private String passwordHash;

    public user(){}

    public user(String username, String emailAddress, String phoneNumber, String dateOfBirth, String address, UserRole userRole)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userRole = userRole;
        this.passwordHash = null;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    } 

    public String getUsername()
    {
        return username;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    } 

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    } 

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setAddress(String address)
    {
        this.address = address;
    } 

    public String getAddress()
    {
        return address;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    } 

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }
}