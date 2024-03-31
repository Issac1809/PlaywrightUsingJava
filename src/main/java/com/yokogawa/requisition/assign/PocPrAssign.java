package org.requisition.assign;
import com.microsoft.playwright.Page;

public class PocPrAssign implements PrAssign {
    public void BuyerManagerLogin(String mailId, String password, Page page) {
//TODO Login Page
        page.locator("Input_Email").fill(mailId);
        page.locator("Input_Password").fill(password);
        page.locator("login-submit").click();
    }
    public void BuyerManagerAssign(String title, String buyerManager, String buyer, Page page) {
        page.locator("//*[contains(text(),'"+ title +"')]").click();
//TODO Assign Buyer
        page.locator("btnAssignUser").click();
        page.locator("select2-bgUser-container").click();
        page.locator("select2-search__field").fill(buyerManager);
        page.locator("//li[contains(text(),'"+ buyer +"')]").click();
        page.locator("saveBuyerUser").click();

//TODO Login Avatar Button
        page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
        page.locator("//a[@onclick='user_logout()']").click();
    }
}
