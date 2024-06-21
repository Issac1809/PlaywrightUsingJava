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

    public void InviteMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("LogisticsManager"));
        page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#addFrightForwordVendors").click();
        page.locator("#select2-ffvendorId-container").click();
        String freightVendor = properties.getProperty("Vendor");
        page.locator(".select2-search__field").fill(freightVendor);
        page.locator("//li[contains(text(), 'IND Vendor')]").click();
        page.locator("#saveFrightForworderVendor").click();
        page.locator("#vendorSendMailBtnId").click();
        logoutPageInterface.LogoutMethod();
    }
}