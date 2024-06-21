package com.procurement.requestforquotations.quotationrequote;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.QuotationRequote;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class RegisteredVendorQuotationRequote implements QuotationRequote {

    LoginPageInterface loginPageInterface;
    Properties properties;
    Page page;
    LogoutPageInterface logoutPageInterface;

    private RegisteredVendorQuotationRequote(){
    }

//TODO Constructor
    public RegisteredVendorQuotationRequote(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void Requote() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.waitForSelector("//a[contains(text(), ' Requote')]").click();
        page.waitForSelector(".bootbox-accept").click();
        page.waitForSelector("#vendorSendMailBtnId").click();
        logoutPageInterface.LogoutMethod();
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//span[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#btnEditQuote").click();
        Thread.sleep(2000);
        page.waitForSelector("#btnUpdate").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
