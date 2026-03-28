package com.firmwareforce.firmwareforcebackend;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "reports")

public class report 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportid")
    private Long reportId;

    @CreationTimestamp
    @Column(name = "datereported")
    private LocalDateTime dateReported;

    @Enumerated(EnumType.STRING)
    private categoryType category;

    @Enumerated(EnumType.STRING)
    private issueType issue;

    private Integer priority;

    @Column(name = "reportlocation")
    private String location;
    
    @Column(name = "reportstatus")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "userid")
    private Long userId;

    private Integer critical;

    @Column(name = "votes")
    private Integer voteCount;

    public report(){}

    public report(String location, categoryType category, issueType issue, LocalDateTime dateReported, Long userId, Integer critical, Integer voteCount)
    {
        this.location = location;
        this.category = category;
        this.issue = issue;
        this.dateReported = dateReported;
        this.userId = userId;
        this.voteCount = voteCount;
    }

    public Integer getVotes()
    {
        return voteCount;
    }

    public void setVotes(Integer voteCount)
    {
        this.voteCount = voteCount;
    }

    public void upVote()
    {
        voteCount++;
    }

    public void downVote()
    {
        voteCount--;
    }

    public void setCritical(Integer critical)
    {
        this.critical = critical;
    }

    public Integer getCritical()
    {
        return critical;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public LocalDateTime getDateReported()
    {
        return dateReported;
    }

    public void setDateReported(LocalDateTime dateReported)
    {
        this.dateReported = dateReported;
    }

    public void setCategory(categoryType category)
    {
        this.category = category;
    }

    public categoryType getCategory()
    {
        return category;
    }

    public void setIssue(issueType issue)
    {
        this.issue = issue;
    }

    public issueType getIssue()
    {
        return issue;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
}