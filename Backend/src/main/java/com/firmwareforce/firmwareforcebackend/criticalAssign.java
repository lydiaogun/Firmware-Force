package com.firmwareforce.firmwareforcebackend;

import java.util.HashMap;

public class criticalAssign 
{
    private HashMap<categoryType, Boolean> critC;
    private HashMap<issueType, Boolean>  critI;

    public criticalAssign(HashMap<categoryType, Boolean> critC, HashMap<issueType, Boolean> critI)
    {
        this.critC = critC;
        this.critI = critI;
    }

    public void setCrit(HashMap<categoryType, Boolean> critC, HashMap<issueType, Boolean> critI)
    {
        this.critC = critC;
        this.critI = critI;
    }

    public void assign(report Report)
    {
        categoryType Category = Report.getCategory();
        issueType Issue = Report.getIssue();

        Boolean cValue = critC.get(Category);
        Boolean iValue = critI.get(Issue);
        Boolean finalBool = (cValue && iValue);
        int finalScore;
        if (finalBool)
        {
            finalScore = 1;
        }
        else
        {
            finalScore = 0;
        }
        Report.setCritical(finalScore);
    }
}
