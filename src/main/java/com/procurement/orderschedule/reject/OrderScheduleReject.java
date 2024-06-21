package com.procurement.orderschedule.reject;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.OSReject;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class OrderScheduleReject implements OSReject {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private OrderScheduleReject(){
    }

    //TODO Constructor
    public OrderScheduleReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void OSRejectMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector("//*[contains(text(), 'Purchase Orders')]").click();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#BtnToApproveOS").click();
        page.waitForSelector("#btnReject").click();
        page.waitForSelector(".bootbox-input").fill("Rejected");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}