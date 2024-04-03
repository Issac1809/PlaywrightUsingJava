package com.yokogawa.requisition.sendforapproval;
import com.microsoft.playwright.Page;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
public class PocPrSendForApproval implements PrSendForApproval {
    Logout logout = new LogoutPage();
    public void PrSendForApproval(Page page){
        page.locator("#btnSendApproval").click();
        page.locator("//button[contains(text(), 'Yes')]").click();
        logout.Logout(page);
    }
}
