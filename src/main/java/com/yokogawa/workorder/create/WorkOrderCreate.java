package com.yokogawa.workorder.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.List;
import java.util.Properties;

public class WorkOrderCreate implements WorkOrderCreateInterface {

    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WorkOrderCreate(){
    }

//TODO Test Constructor
    public WorkOrderCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public WorkOrderCreate(VariablesForNonCatalog variablesForNonCatalog, Page page, LoginPageInterface loginPageInterface, LogoutPageInterface logoutPageInterface){
        this.variablesForNonCatalog = variablesForNonCatalog;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public void WOCreate() {
        loginPageInterface.LoginMethod(properties.getProperty("LogisticsManager"));
        page.pause();
        page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#dnActionDropdown").click();
        page.locator("#btnToCreateWorkOrder").click();
        page.locator("#select2-freightForwarderId-container").first().click();
        String vendorId = properties.getProperty("Vendor");
        page.locator(".select2-search__field").fill(vendorId);
        page.locator("//li[contains(text(), '" + vendorId + "')]").first().click();
        page.locator("#select2-priority-container").click();
        page.locator("//li[contains(text(), 'High')]").click();
        page.getByPlaceholder("Select Date").last().click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#destinationTermLocationId").fill("India");
        page.locator("//*[contains(text(), 'Proceed')]").click();
        page.locator("#vendorSendMailBtnId").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}