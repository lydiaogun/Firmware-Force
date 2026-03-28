package com.firmwareforce.firmwareforcebackend.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firmwareforce.firmwareforcebackend.repository.NotificationRepository;
import com.firmwareforce.firmwareforcebackend.manager.NotificationTableManager;
import java.util.List;
import java.util.stream.Collectors;
import com.firmwareforce.firmwareforcebackend.notification;

@Service
public class NotificationTableManager {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<notification> getAllNotifications() 
    {
        return notificationRepository.findAll();
    }

    public List<notification> getNotificationByUserId(Long userId) 
    {
        return notificationRepository.findAll().stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public notification createNotification(Long reportId, Long userId, String notificationText)
    {
        return new notification(reportId, userId, notificationText);
    }
    
    public void deleteNotifcationById(Long notificationId)
    {
        notification NotifToDelete = notificationRepository.findById(notificationId).orElse(null);
        notificationRepository.delete(NotifToDelete);
    }
}
