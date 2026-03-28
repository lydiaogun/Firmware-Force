package com.firmwareforce.firmwareforcebackend;

import jakarta.persistence.*;

@Entity
@Table(name = "votes")

public class vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voteid")
    private Long voteId;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "reportid")
    private Long reportId;

    @Enumerated(EnumType.STRING)
    @Column(name = "votetype")
    private voteType VoteType;

    public vote(){}

    public vote(Long userId, Long reportId, voteType VoteType)
    {
        this.userId = userId;
        this.reportId = reportId;
        this.VoteType = VoteType;
    }

    public void setVoteId(Long voteId)
    {
        this.voteId = voteId;
    }

    public Long getVoteId()
    {
        return voteId;
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

    public void setVoteType(voteType VoteType)
    {
        this.VoteType = VoteType;
    }

    public voteType getVoteType()
    {
        return VoteType;
    }
    
}
