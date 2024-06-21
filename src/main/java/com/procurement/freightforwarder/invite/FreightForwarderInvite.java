package com.procurement.freightforwarder.invite;
import com.interfaces.FFRInvite;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class FreightForwarderInvite implements FFRInvite {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private FreightForwarderInvite(){
    }

//TODO Constructor
    public FreightForwarderInvite(LoginPageInterface loginPageInterface, Properties properties, Page page,LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void InviteMethod() {
        try {
            loginPageInterface.LoginMethod(properties.getProperty("LogisticsManager"));
            page.waitForSelector("//*[contains(text(), 'Dispatch Notes')]").click();
            String poReferenceId = properties.getProperty("PoReferenceId");
            List<String> containerList = page.locator("#listContainer tr td").allTextContents();
            for (String tr : containerList) {
                if (tr.contains(poReferenceId)) {
                    page.locator(".btn-link").first().click();
                }
            }
            page.waitForSelector("#addFrightForwordVendors").click();
            page.waitForSelector("#select2-ffvendorId-container").click();
            String freightVendor = properties.getProperty("Vendor");
            page.waitForSelector(".select2-search__field").fill(freightVendor);
            page.waitForSelector("//li[contains(text(), 'IND Vendor')]").click();
            page.waitForSelector("#saveFrightForworderVendor").click();
            page.waitForSelector("#vendorSendMailBtnId").click();
            logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}