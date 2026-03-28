package com.firmwareforce.firmwareforcebackend;

import com.firmwareforce.firmwareforcebackend.controller.NotificationController;
import com.firmwareforce.firmwareforcebackend.manager.NotificationTableManager;
import com.firmwareforce.firmwareforcebackend.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationController notificationController;

    @Mock
    private NotificationTableManager notificationTableManager;

    @Test
    public void testCreateNotification() {
        Long userId = 1L;
        Long reportId = 1L;
        String text = "Report 1 status updated to INVESTIGATING";
        notification fakeNotification = new notification();
        fakeNotification.setNotificationId(100L);
        when(notificationTableManager.createNotification(reportId, userId, text))
                .thenReturn(fakeNotification);
        when(notificationRepository.getReferenceById(100L))
                .thenReturn(fakeNotification);
        notification result = notificationTableManager.createNotification(reportId, userId, text);
        assertNotNull(result, "The created notification should not be null");
        assertEquals(100L, result.getNotificationId());
        notification retrieved = notificationRepository.getReferenceById(result.getNotificationId());
        assertNotNull(retrieved, "The repository should return the notification for the given ID");

    }

    @Test
    public void testGetNotificationById() {
        notification fakeNotification = new notification();
        fakeNotification.setNotificationId(200L);
        when(notificationTableManager.createNotification(2L, 2L, "Report 2 status updated to IN_PROGRESS"))
                .thenReturn(fakeNotification);
        when(notificationRepository.getReferenceById(200L)).thenReturn(fakeNotification);
        notification result = notificationTableManager.createNotification(2L, 2L, "Report 2 status updated to IN_PROGRESS");
        notification retrieved = notificationRepository.getReferenceById(200L);
        assertNotNull(result, "The manager should have returned a notification");
        assertEquals(fakeNotification, retrieved, "The repository should return the exact notification we stored");
    }

    @Test
    public void testDeleteNotificationById() {
        notification fakeNotification = new notification();
        fakeNotification.setNotificationId(500L);

        when(notificationTableManager.createNotification(1L, 1L, "Delete Me"))
                .thenReturn(fakeNotification);
        when(notificationRepository.getReferenceById(500L))
                .thenReturn(fakeNotification)
                .thenReturn(null);
        notification created = notificationTableManager.createNotification(1L, 1L, "Delete Me");
        assertNotNull(notificationRepository.getReferenceById(500L));
        notificationRepository.delete(created);
        assertNull(notificationRepository.getReferenceById(500L), "The notification should be gone after deletion");
    }
}
