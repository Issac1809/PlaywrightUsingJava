package com.yokogawa.purchaseorderrequest.approval;
import com.microsoft.playwright.Page;
import java.util.List;
public interface PorApproval {
    List<String> SendForApproval(String cfo, String president, Page page) throws InterruptedException;
    void ApproverLogin(List<String> matchingApprovers, String projectManager, String departmentManager, String businessUnitManager, Page page);
}
