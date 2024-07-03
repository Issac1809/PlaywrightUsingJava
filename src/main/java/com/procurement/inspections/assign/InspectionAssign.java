package com.procurement.inspections.assign;
import com.interfaces.InspectionAssignInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class InspectionAssign implements InspectionAssignInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private InspectionAssign() {
    }

    //TODO Constructor
    public InspectionAssign(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void RequesterInspectionAssign() {
        try {
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
        page.locator("#btnCreateInspection").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}