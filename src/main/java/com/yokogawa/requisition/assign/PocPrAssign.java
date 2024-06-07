package com.yokogawa.requisition.assign;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import java.util.Properties;

public class PocPrAssign implements PrAssign {

    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;

    private PocPrAssign(){
    }

//TODO Constructor
    public PocPrAssign(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void BuyerManagerLogin() {
        loginPageInterface.LoginMethod(properties.getProperty("BuyerManager"));
    }

    public void BuyerManagerAssign() {
        String title = properties.getProperty("Title");
        String buyerMailId = properties.getProperty("Buyer");
        page.locator("//*[contains(text(), '"+ title +"')]").first().click();
//TODO Assign Buyer
        page.locator("#btnAssignUser").click();
        page.locator("#select2-bgUser-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(buyerMailId);
        page.locator("//li[contains(text(),'"+ buyerMailId +"')]").first().click();
        page.locator("#saveBuyerUser").click();
        logoutPageInterface.LogoutMethod();
    }
}