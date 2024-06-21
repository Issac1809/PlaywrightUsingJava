package com.procurement.requisition.approve;
import com.interfaces.PrApprove;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class PocPrApprove implements PrApprove {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;

    private PocPrApprove(){
    }

//TODO Constructor
    public PocPrApprove(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void Approve() {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("ProjectManager"));
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#btnApprove").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
