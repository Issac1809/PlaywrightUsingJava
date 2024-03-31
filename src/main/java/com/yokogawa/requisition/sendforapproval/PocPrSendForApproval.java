package org.requisition.sendforapproval;
import com.microsoft.playwright.Page;
public class PocPrSendForApproval implements PrSendForApproval {
    public void PrSendForApproval(Page page){
        page.locator("#btnSendApproval").click();
        page.locator("//button[contains(text(), 'Yes')]").click();
    }
}
