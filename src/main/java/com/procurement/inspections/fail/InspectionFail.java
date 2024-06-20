package com.procurement.inspections.fail;
import com.interfaces.InsFail;
import com.interfaces.InspectionCreateInterface;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.microsoft.playwright.Page;
import com.procurement.inspections.create.InspectionCreate;

import java.util.List;
import java.util.Properties;

public class InspectionFail implements InsFail {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    InspectionCreateInterface inspectionCreateInterface;

    private InspectionFail() {
    }

    //TODO Constructor
    public InspectionFail(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, InspectionCreateInterface inspectionCreateInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.inspectionCreateInterface = inspectionCreateInterface;
    }

    public void RequesterInspectionFail() {
        String mailId = properties.getProperty("EmailID");
        loginPageInterface.LoginMethod(mailId);
        page.locator("//*[contains(text(), 'Order Schedules')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for (String tr : containerList) {
            if (tr.contains(poReferenceId)) {
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#btnAssignInspector").click();
        page.locator("#select2-InspectionId-container").click();
        page.locator(".select2-search__field").fill(mailId);
        page.locator("//li[contains(text(), '" + mailId + "')]").first().click();
        page.locator("#saveInspector").click();
        page.locator("#btnForCreateInspection").click();
        page.locator("#physicalInspectionNotReq").click();
        page.locator("#inspectFail").click();
        page.locator("#btnCreateInspection").click();
        page.locator(".bootbox-input").fill("Failed");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        inspectionCreateInterface.VendorInspectionCreate();
    }
}