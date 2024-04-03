package com.yokogawa.purchaseorderrequest.approval;
import com.microsoft.playwright.Page;
import java.util.List;
public interface PorApproval {
    void SendForApproval(String cfo, Page page) throws InterruptedException;
    List<String> GetPorApprovers(Page page);
    void ApproverLogin(List<String> matchingApprovers, String projectManager, String departmentManager, String businessUnitManager, Page page) throws InterruptedException;
}
