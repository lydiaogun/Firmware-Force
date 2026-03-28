package com.firmwareforce.firmwareforcebackend;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class criticalAssignTest {

    @Test
    public void testCriticalAssignSetsFlagCorrectly() {
        HashMap<categoryType, Boolean> testCatCrit = new HashMap<>();
        testCatCrit.put(categoryType.ROAD, true);
        testCatCrit.put(categoryType.PAVEMENT, false);
        HashMap<issueType, Boolean> testIssueCrit = new HashMap<>();
        testIssueCrit.put(issueType.CRASH, true);
        testIssueCrit.put(issueType.POTHOLE, false);
        criticalAssign myCriticalAssign = new criticalAssign(testCatCrit, testIssueCrit);
        // 2 reports are created - one is critical and one is not
        report criticalReport = new report();
        criticalReport.setCategory(categoryType.ROAD);
        criticalReport.setIssue(issueType.CRASH);
        report normalReport = new report();
        normalReport.setCategory(categoryType.ROAD);
        normalReport.setIssue(issueType.POTHOLE);
        myCriticalAssign.assign(criticalReport);
        myCriticalAssign.assign(normalReport);
        assertEquals(1, criticalReport.getCritical(), "ROAD(True) + CRASH(True) should equal 1");
        assertEquals(0, normalReport.getCritical(), "ROAD(True) + POTHOLE(False) should equal 0");
    }
}