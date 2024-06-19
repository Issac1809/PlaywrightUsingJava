package com.procurement.requestforquotations.quotationregret;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.QuotationRegret;
import com.interfaces.QuotationSubmit;
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
        quotationSubmit.InviteRegisteredVendor();
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnRegret").click();
        page.locator(".bootbox-input").fill("Regret");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}