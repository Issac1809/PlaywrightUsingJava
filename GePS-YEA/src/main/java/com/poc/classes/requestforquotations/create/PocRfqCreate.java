package com.poc.classes.requestforquotations.create;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.requestforquotation.IRfqCreate;
import java.util.Properties;

public class Create implements IRfqCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private Create(){
    }

//TODO Constructor
    public Create(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void buyerLogin() {
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void buyerRfqCreate() {
        try {
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(),'"+ title +"')]").first().click();
        page.locator("#btnCreateRFQ").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RfQNotes() {
        try {
        page.locator("#notes").fill(properties.getProperty("RfQNotes"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RFQCreate() throws InterruptedException {
        try {
        Thread.sleep(1000);
        page.locator("#btnCreate").click();
        page.locator("//button[contains(text(),'Yes')]").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}