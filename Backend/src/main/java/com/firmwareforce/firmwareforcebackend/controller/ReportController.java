package com.firmwareforce.firmwareforcebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.firmwareforce.firmwareforcebackend.Status;
import com.firmwareforce.firmwareforcebackend.report;
import com.firmwareforce.firmwareforcebackend.vote;
import com.firmwareforce.firmwareforcebackend.voteType;
import com.firmwareforce.firmwareforcebackend.manager.ReportTableManager;
import com.firmwareforce.firmwareforcebackend.manager.VoteTableManager;

import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
    
    @Autowired
    private ReportTableManager tableManager;

    @Autowired
    private VoteTableManager voteTableManager;

    @GetMapping
    public List<report> getAllReports() {
        return tableManager.getAllReports();
    }

// gets report using table manager using Id
    @GetMapping("/{id}")
    public ResponseEntity<report> getReportById(@PathVariable Long id) {
        return tableManager.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

// used to create new report
    @PostMapping("/createReport")
    public report createReport(@RequestBody report newReport) {
        return tableManager.createReport(newReport);
    }

    // downvote
    @PutMapping("/downvote/{reportId}/{userId}")
    public ResponseEntity<report> upvoteReport(@PathVariable Long reportId, @PathVariable Long userId) {
        vote Vote = voteTableManager.getVoteByUserIdReportId(userId, reportId);
        if (Vote != null)
        {
            voteType VoteType = Vote.getVoteType();

            if (VoteType == voteType.UP)
            {
                voteTableManager.deleteVoteByUserIdReportId(userId, reportId);
                report updated = tableManager.downvoteReport(reportId);
                return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
            }
            else
            {
                voteTableManager.deleteVoteByUserIdReportId(userId, reportId);
                report updated = tableManager.upvoteReport(reportId);
                return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
            }
        }
        else
        {
            voteTableManager.createVote(userId, reportId, voteType.DOWN);
            report updated = tableManager.downvoteReport(reportId);
            return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        }
    }

    // upvotes the report
    @PutMapping("/upvote/{reportId}/{userId}")
    public ResponseEntity<report> downvoteReport(@PathVariable Long reportId, @PathVariable Long userId) {
        vote Vote = voteTableManager.getVoteByUserIdReportId(userId, reportId);
        if (Vote != null)
        {
            voteType VoteType = Vote.getVoteType();

            if (VoteType == voteType.UP)
            {
                voteTableManager.deleteVoteByUserIdReportId(userId, reportId);
                report updated = tableManager.downvoteReport(reportId);
                return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
            }
            else
            {
                voteTableManager.deleteVoteByUserIdReportId(userId, reportId);
                report updated = tableManager.upvoteReport(reportId);
                return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
            }
        }
        else
        {
            voteTableManager.createVote(userId, reportId, voteType.UP);
            report updated = tableManager.upvoteReport(reportId);
            return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        }
    }

// changes status
    @PutMapping("/{status}/{id}/status")
    public ResponseEntity<report> updateStatus(@PathVariable Long id, @PathVariable Status status) {
        report updated = tableManager.updateReportStatus(id, status);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.ok(null);
    }

// gts the reports from table mnanager and filters
    @GetMapping("/status/{status}")
    public List<report> getByStatus(@PathVariable Status status) {
        return tableManager.getReportsByStatus(status);
    }
// user reports
    @GetMapping("/user/{userId}")
    public List<report> getByUserId(@PathVariable Long userId) {
        return tableManager.getReportsByUserId(userId);
    }
// gets and filters via priority in descending order
    @GetMapping("/priority")
    public List<report> getReportsByPriority() {
        return tableManager.getAllReportsSortedByPriority();
    }
// gets and filters reports via votes in descending order
    @GetMapping("/votes")
    public List<report> getReportsByVotes() {
        return tableManager.getAllReportsNonCriticalSortedByVotes();
    }
}