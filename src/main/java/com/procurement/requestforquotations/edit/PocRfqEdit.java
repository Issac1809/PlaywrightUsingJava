package com.procurement.requestforquotations.edit;
import com.interfaces.RfqEdit;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class PocRfqEdit implements RfqEdit {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;

    private PocRfqEdit(){
    }

//TODO Constructor
    public PocRfqEdit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void RfqEditMethod() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.waitForSelector("#btnEditRfq").click();
        Thread.sleep(2000);
        page.waitForSelector("#btnUpdate").click();
        page.waitForSelector(".bootbox-input").fill("Updated");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
