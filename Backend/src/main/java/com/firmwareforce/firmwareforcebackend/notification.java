package com.firmwareforce.firmwareforcebackend;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")

public class notification 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationid")
    private Long notificationId;

    @Column(name = "reportid")
    private Long reportId;
    
    @Column(name = "userid")
    private Long userId;

    @Column(name = "notificationtext")
    private String notificationText;

    public notification(){}

    public notification(Long reportId, Long userId, String notificationText)
    {
        this.reportId = reportId;
        this.userId = userId;
        this.notificationText = notificationText;
    }

    public Long getNotificationId()
    {
        return notificationId;
    }

    public void setNotificationId(Long notificationId)
    {
        this.notificationId = notificationId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getNotificationText()
    {
        return notificationText;
    }

    public void setNotificationText(String notificationText)
    {
        this.notificationText = notificationText;
    }
}
