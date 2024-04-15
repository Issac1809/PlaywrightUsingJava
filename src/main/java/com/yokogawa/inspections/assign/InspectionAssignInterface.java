package com.yokogawa.inspections.assign;
import com.microsoft.playwright.Page;
public interface InspectionAssignInterface {
    void RequesterInspectionAssign(String mailId, String inspectorMailId, String poReferenceId, Page page);
}
