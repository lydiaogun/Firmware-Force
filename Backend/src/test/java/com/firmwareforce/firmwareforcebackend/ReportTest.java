package com.firmwareforce.firmwareforcebackend;

import com.firmwareforce.firmwareforcebackend.manager.NotificationTableManager;
import com.firmwareforce.firmwareforcebackend.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.firmwareforce.firmwareforcebackend.manager.ReportTableManager;
import com.firmwareforce.firmwareforcebackend.repository.ReportRepository;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReportTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationTableManager notificationTableManager;

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportTableManager reportTableManager;

    @Test
    public void testUpVoteIncreasesCount() {
        // this test sets the votes to 6, snd tests if it upvotes it to 7 votes - 6 7
        report testReport = new report();
        testReport.setVotes(6);
        testReport.upVote();
        assertEquals(7, testReport.getVotes(), "Upvoting should increase the count from 6 to 7");
    }

    @Test
    public void testDownVoteDecreasesCount() {
        // this test sets the votes to 5, snd tests if it downvotes it to 4 votes
        report testReport = new report();
        testReport.setVotes(5);
        testReport.downVote();
        assertEquals(4, testReport.getVotes(), "Downvoting should decrease the count from 5 to 4");
    }

    @Test
    public void testReportStatusUpdate() {
        Long reportId = 1L;
        Status newStatus = Status.IN_PROGRESS;
        
        report tempReport = new report();
        tempReport.setStatus(Status.NOT_STARTED);
        when(reportRepository.findById(reportId)).thenReturn(Optional.of(tempReport));
        when(reportRepository.save(any(report.class))).thenAnswer(invocation -> invocation.getArgument(0));
        report updatedReport = reportTableManager.updateReportStatus(reportId, newStatus);
        assertEquals(Status.IN_PROGRESS, updatedReport.getStatus());
        verify(reportRepository).save(updatedReport);
    }
    @Test
    void updateReportStatusNull() {
        Long reportId = 2L;
        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());
        report result = reportTableManager.updateReportStatus(reportId,Status.FIXED);
        assertNull(result);
        verify(reportRepository).findById(reportId);
        verify(reportRepository, never()).save(any(report.class));   
    }

    @Test
    public void testCreateReport() {
        report newReport = new report();
        newReport.setVotes(0);
        newReport.setIssue(issueType.POTHOLE); // Weight: 2
        newReport.setCategory(categoryType.ROAD); // Weight: 4
        when(reportRepository.save(any(report.class))).thenReturn(newReport);
        report savedReport = reportTableManager.createReport(newReport);
        assertEquals(issueType.POTHOLE, savedReport.getIssue());
        assertEquals(6, savedReport.getPriority(), "Priority should be calculated to 6");
        assertEquals(Status.NOT_STARTED, savedReport.getStatus(), "Status should default to NOT_STARTED");
        assertEquals(0, savedReport.getVotes(), "Votes should default to 0");
        verify(reportRepository, times(1)).save(newReport);
    }

    @Test
    public void testGetAllReportsSortedByPriority() {
        report lowPriority = new report();
        lowPriority.setPriority(2);
        lowPriority.setCritical(1);
        lowPriority.setStatus(Status.NOT_STARTED);
        report highPriority = new report();
        highPriority.setPriority(9);
        highPriority.setCritical(1);
        highPriority.setStatus(Status.NOT_STARTED);
        List<report> unsortedList = Arrays.asList(lowPriority, highPriority);
        when(reportRepository.findAll()).thenReturn(unsortedList);
        List<report> results = reportTableManager.getAllReportsSortedByPriority();
        assertEquals(2, results.size(), "Should return 2 reports because they are marked as critical");
        assertEquals(9, results.get(0).getPriority(), "High priority (9) should be first");
        assertEquals(2, results.get(1).getPriority(), "Low priority (2) should be last");
    }

    @Test
    public void testGetAllReportsSortedByVotes() {
        report lowVotes = new report();
        lowVotes.setVotes(5);
        lowVotes.setCritical(0);

        report highVotes = new report();
        highVotes.setVotes(100);
        highVotes.setCritical(0);

        List<report> unsortedList = Arrays.asList(lowVotes, highVotes);
        when(reportRepository.findAll()).thenReturn(unsortedList);
        List<report> results = reportTableManager.getAllReportsNonCriticalSortedByVotes();

        assertEquals(2, results.size());

        assertEquals(100, results.get(0).getVotes(), "High votes should be moved to the front (index 0)");
        assertEquals(5, results.get(1).getVotes(), "Low votes should be pushed to the back (index 1)");
    }
}