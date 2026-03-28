package com.firmwareforce.firmwareforcebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.firmwareforce.firmwareforcebackend.notification;
import com.firmwareforce.firmwareforcebackend.manager.NotificationTableManager;
import com.firmwareforce.firmwareforcebackend.repository.NotificationRepository;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationTableManager tableManager;

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<notification> getAllNotifications() {
        return tableManager.getAllNotifications();
    }

    @GetMapping("/{userId}")
    public List<notification> getNotificationsByUserId(@PathVariable Long userId) {
        return tableManager.getNotificationByUserId(userId);
    }

    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<?> deleteNotifcationById(@PathVariable Long notificationId) {
        tableManager.deleteNotifcationById(notificationId);
        notification notif = notificationRepository.findById(notificationId).orElse(null);
        return (notif == null) ? ResponseEntity.ok("notification deleted") : ResponseEntity.notFound().build();
    }
}
