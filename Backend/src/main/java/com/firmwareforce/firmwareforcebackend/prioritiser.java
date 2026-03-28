package com.firmwareforce.firmwareforcebackend;

import java.util.HashMap;

public class prioritiser 
{
    private HashMap<categoryType, Integer> prioritiesC;
    private HashMap<issueType, Integer>  prioritiesI;

    public prioritiser(HashMap<categoryType, Integer> prioritiesC, HashMap<issueType, Integer> prioritiesI)
    {
        this.prioritiesC = prioritiesC;
        this.prioritiesI = prioritiesI;
    }

    public void setPriorities(HashMap<categoryType, Integer> prioritiesC, HashMap<issueType, Integer> prioritiesI)
    {
        this.prioritiesC = prioritiesC;
        this.prioritiesI = prioritiesI;
    }

    public void prioritise(report Report)
    {
        categoryType Category = Report.getCategory();
        issueType Issue = Report.getIssue();

        Integer cValue = prioritiesC.get(Category);
        Integer iValue = prioritiesI.get(Issue);
        int finalScore = (cValue != null ? cValue : 0) + (iValue != null ? iValue : 0);
        Report.setPriority(finalScore);
    }
}
