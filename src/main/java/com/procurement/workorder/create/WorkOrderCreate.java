package com.procurement.workorder.create;
import com.interfaces.WorkOrderCreateInterface;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class WorkOrderCreate implements WorkOrderCreateInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WorkOrderCreate(){
    }

//TODO Constructor
    public WorkOrderCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public void WOCreate() {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("LogisticsManager"));
        page.waitForSelector("//*[contains(text(), 'Dispatch Notes')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.waitForSelector("#dnActionDropdown").click();
        page.waitForSelector("#btnToCreateWorkOrder").click();
        page.locator("#select2-freightForwarderId-container").first().click();
        String vendorId = properties.getProperty("Vendor");
        page.waitForSelector(".select2-search__field").fill(vendorId);
        page.locator("//li[contains(text(), '" + vendorId + "')]").first().click();
        page.waitForSelector("#select2-priority-container").click();
        page.waitForSelector("//li[contains(text(), 'High')]").click();
        page.getByPlaceholder("Select Date").last().click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.waitForSelector("#destinationTermLocationId").fill("India");
        page.waitForSelector("//*[contains(text(), 'Proceed')]").click();
        page.waitForSelector("#vendorSendMailBtnId").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}