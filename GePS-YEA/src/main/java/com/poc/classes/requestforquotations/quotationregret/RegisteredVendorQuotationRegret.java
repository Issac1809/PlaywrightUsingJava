package com.procurement.requestforquotations.quotationregret;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.rfq.QuotationRegret;
import com.interfaces.rfq.QuotationSubmit;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class RegisteredVendorQuotationRegret implements QuotationRegret {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    QuotationSubmit quotationSubmit;
    Properties properties;
    Page page;

    private RegisteredVendorQuotationRegret(){
    }

//TODO Constructor
    public RegisteredVendorQuotationRegret(QuotationSubmit quotationSubmit, LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.quotationSubmit = quotationSubmit;
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void Regret(){
        try {
        quotationSubmit.InviteRegisteredVendor();
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnRegret").click();
        page.locator(".bootbox-input").fill("Regret");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}