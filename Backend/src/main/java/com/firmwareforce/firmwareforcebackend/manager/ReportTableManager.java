package com.firmwareforce.firmwareforcebackend.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firmwareforce.firmwareforcebackend.Status;
import com.firmwareforce.firmwareforcebackend.categoryType;
import com.firmwareforce.firmwareforcebackend.issueType;
import com.firmwareforce.firmwareforcebackend.notification;
import com.firmwareforce.firmwareforcebackend.prioritiser;
import com.firmwareforce.firmwareforcebackend.report;
import com.firmwareforce.firmwareforcebackend.repository.NotificationRepository;
import com.firmwareforce.firmwareforcebackend.repository.ReportRepository;
import com.firmwareforce.firmwareforcebackend.criticalAssign;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;
@Service
public class ReportTableManager {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationTableManager notificationTableManager;

    public List<report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<report> getReportById(Long reportId) {
        return reportRepository.findById(reportId);
    }

    public List<report> getReportsByUserId(Long userId) {
        return reportRepository.findAll().stream()
                .filter(r -> r.getUserId() != null && r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    
    public report createReport(report newReport) {
        HashMap<categoryType, Integer> catWeights = new HashMap<>();
        for (categoryType cat : categoryType.values()) {
            int catWeight = switch (cat) {
                case ROAD, SAFETY -> 4;
                case PAVEMENT, WASTE -> 3;
                default -> 0;
            };
            catWeights.put(cat, catWeight);
        }
        HashMap<issueType, Integer> issueWeights = new HashMap<>();
        for (issueType issue : issueType.values()) {
            int weight = switch (issue) {
                case CRASH -> 5;
                case ILLEGAL_ACTIVITY -> 4;
                case OBSTRUCTION, SEWAGE_SPILL, ANTISOCIAL_BEHAVIOUR -> 3;
                case POTHOLE, FLY_TIPPING, LOOSE_TILE, HAZARD, LARGE_GROUP_GATHERING -> 2;
                case OVERFLOWING_BIN, CRACKS -> 1;
                default -> 0;
            };
            issueWeights.put(issue, weight);
        }

        HashMap<categoryType, Boolean> catCrit = new HashMap<>();
        for (categoryType cat : categoryType.values()) {
            Boolean catBool = switch (cat) {
                case ROAD, SAFETY -> true;
                case PAVEMENT, WASTE -> false;
                default -> false;
            };
            catCrit.put(cat, catBool);
        }
        HashMap<issueType, Boolean> issueCrit = new HashMap<>();
        for (issueType issue : issueType.values()) {
            boolean issBool = switch (issue) {
                case ILLEGAL_ACTIVITY, SEWAGE_SPILL, CRASH, FLY_TIPPING -> true;
                case OBSTRUCTION, ANTISOCIAL_BEHAVIOUR, POTHOLE, CRACKS, LARGE_GROUP_GATHERING, HAZARD, LOOSE_TILE -> false;
                default -> false;
            };
            issueCrit.put(issue, issBool);
        }

        criticalAssign reportCriticalAssign = new criticalAssign(catCrit, issueCrit);
        reportCriticalAssign.assign(newReport);
        prioritiser reportPrioritiser = new prioritiser(catWeights, issueWeights);
        reportPrioritiser.prioritise(newReport);
        newReport.setStatus(Status.NOT_STARTED);
        
        return reportRepository.save(newReport);
    }

    public report updateReportStatus(Long reportId, Status newStatus) {
        Optional<report> reportOpt = reportRepository.findById(reportId);
        if (reportOpt.isPresent()) {
            report r = reportOpt.get();
            r.setStatus(newStatus);
            String notificationText = "Report " + r.getUserId() + " status updated to: " + newStatus;
            notification newNotification = notificationTableManager.createNotification(r.getUserId(), reportId, notificationText);
            notificationRepository.save(newNotification);

            if (r.getStatus() == Status.FIXED)
            {
                reportRepository.delete(r);
                return null;
            }

            return reportRepository.save(r);
        }
        return null;
    }

    public report upvoteReport(Long reportId) {
        return reportRepository.findById(reportId).map(r -> {
            r.upVote();
            return reportRepository.save(r);
        }).orElse(null);
    }

    public List<report> getReportsByStatus(Status status) {
        return reportRepository.findAll().stream()
                .filter(r -> r.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<report> getAllReportsSortedByPriority() {
        return reportRepository.findAll().stream()
                .filter(r -> r.getCritical() == 1)
                .filter(r -> r.getStatus() != Status.FIXED)
                .sorted(Comparator.comparing(report::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public List<report> getAllReportsNonCriticalSortedByVotes() {
        return reportRepository.findAll().stream()
                .filter(r -> r.getCritical() == 0)
                .filter(r -> r.getStatus() != Status.FIXED)
                .sorted(Comparator.comparing(report::getVotes, Comparator.nullsFirst(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());
    }

    public report downvoteReport(Long reportId) {
        return reportRepository.findById(reportId).map(r -> {
            r.downVote(); // Uses the method you wrote in report.java
            return reportRepository.save(r);
        }).orElse(null);
    }
}

