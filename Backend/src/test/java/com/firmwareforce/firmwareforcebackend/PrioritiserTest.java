package com.firmwareforce.firmwareforcebackend;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrioritiserTest {

    @Test
    public void testPrioritiserAcceptsHashMaps() {
        HashMap<categoryType, Integer> testCatWeights = new HashMap<>();
        testCatWeights.put(categoryType.ROAD, 4);
        testCatWeights.put(categoryType.PAVEMENT, 3);

        HashMap<issueType, Integer> testIssueWeights = new HashMap<>();
        testIssueWeights.put(issueType.CRASH, 5);
        testIssueWeights.put(issueType.POTHOLE, 2);
        prioritiser myPrioritiser = new prioritiser(testCatWeights, testIssueWeights);
        report testReport = new report();
        testReport.setCategory(categoryType.ROAD);
        testReport.setIssue(issueType.POTHOLE);
        myPrioritiser.prioritise(testReport);
        assertEquals(6, testReport.getPriority(), "ROAD(4) + POTHOLE(2) should result in a priority of 6");
    }

    @Test
    public void testPrioritiserMissingValues() {
        HashMap<categoryType, Integer> emptyCatWeights = new HashMap<>();
        HashMap<issueType, Integer> emptyIssueWeights = new HashMap<>();

        prioritiser myPrioritiser = new prioritiser(emptyCatWeights, emptyIssueWeights);

        report testReport = new report();
        testReport.setCategory(categoryType.SAFETY);
        testReport.setIssue(issueType.HAZARD);
        myPrioritiser.prioritise(testReport);
        assertEquals(0, testReport.getPriority(), "Missing map values should default to a priority of 0");
    }
}