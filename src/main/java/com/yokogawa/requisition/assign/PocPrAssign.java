package com.yokogawa.requisition.assign;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

public class PocPrAssign implements PrAssign {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void BuyerManagerLogin(String emailId, Page page) {
        login.Login(emailId, page);
    }
    public void BuyerManagerAssign(String title, String buyer, Page page) {
        page.locator("//*[contains(text(),'"+ title +"')]").first().click();
//TODO Assign Buyer
        page.locator("#btnAssignUser").click();
        page.locator("#select2-bgUser-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(buyer);
        page.locator("//li[contains(text(),'"+ buyer +"')]").first().click();
        page.locator("#saveBuyerUser").click();
        logout.Logout(page);
    }
}
