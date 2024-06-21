package com.procurement.requisition.assign;
import com.interfaces.PrAssign;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
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
        try {
        loginPageInterface.LoginMethod(properties.getProperty("BuyerManager"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void BuyerManagerAssign() {
        try {
        String title = properties.getProperty("Title");
        String buyerMailId = properties.getProperty("Buyer");
        page.locator("//*[contains(text(), '"+ title +"')]").first().click();
//TODO Assign Buyer
        page.waitForSelector("#btnAssignUser").click();
        page.waitForSelector("#select2-bgUser-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(buyerMailId);
        page.locator("//li[contains(text(),'"+ buyerMailId +"')]").first().click();
        page.waitForSelector("#saveBuyerUser").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}