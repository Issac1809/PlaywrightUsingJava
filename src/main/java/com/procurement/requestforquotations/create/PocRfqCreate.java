package com.procurement.requestforquotations.create;
import com.interfaces.RfqCreate;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class PocRfqCreate implements RfqCreate {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocRfqCreate(){
    }

//TODO Constructor
    public PocRfqCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void BuyerLogin() {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void BuyerRfqCreate() {
        try {
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(),'"+ title +"')]").first().click();
        page.waitForSelector("#btnCreateRFQ").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RfQNotes() {
        try {
        page.waitForSelector("#notes").fill(properties.getProperty("RfQNotes"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RFQCreate() throws InterruptedException {
        try {
        Thread.sleep(1000);
        page.waitForSelector("#btnCreate").click();
        page.waitForSelector("//button[contains(text(),'Yes')]").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}