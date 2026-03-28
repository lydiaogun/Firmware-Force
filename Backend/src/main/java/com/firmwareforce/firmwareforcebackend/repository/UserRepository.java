package com.firmwareforce.firmwareforcebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firmwareforce.firmwareforcebackend.user;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    
    Optional<user> findByUsername(String username);
    Optional<user> findByEmailAddress(String emailAddress);
}