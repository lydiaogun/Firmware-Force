package com.firmwareforce.firmwareforcebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firmwareforce.firmwareforcebackend.notification;

@Repository
public interface NotificationRepository extends JpaRepository<notification, Long> {
    
}