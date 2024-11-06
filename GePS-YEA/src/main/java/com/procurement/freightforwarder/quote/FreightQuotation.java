package com.procurement.freightforwarder.quote;
import com.interfaces.ffr.FFRQuotation;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class FreightQuotation implements FFRQuotation {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private FreightQuotation(){
    }

    //TODO Constructor
    public FreightQuotation(LoginPageInterface loginPageInterface, Properties properties, Page page,LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void QuoteMethod() {
        try {
            loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
            page.locator("//*[contains(text(), 'Freight Forwarder Requests')]").click();
            String dnReferenceId = properties.getProperty("DispatchNoteReferenceId");
            List<String> containerList = page.locator("#listContainer tr td").allTextContents();
            for (String tr : containerList) {
                if (tr.contains(dnReferenceId)) {
                    page.locator(".btn-link").first().click();
                }
            }
            page.locator("#btnffrSendQuote").click();
            String totalChargeableWeight = properties.getProperty("Total Chargeable Weight");
            page.locator("#totalChargableWeight").fill(totalChargeableWeight);
            String unitRate = properties.getProperty("Unit Rate");
            page.locator("#rate").fill(unitRate);
            page.locator("#submitQuotation").click();
            logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}