package com.procurement.nonPoc.classes.requisition.approve;
import com.factory.PlaywrightFactory;
import com.procurement.nonPoc.interfaces.logout.ILogout;
import com.procurement.nonPoc.interfaces.requisitions.IPrApprove;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.nonPoc.interfaces.login.ILogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.nonPoc.constants.requisitions.LPrApprove.*;

public class Approve implements IPrApprove {

    PlaywrightFactory playwrightFactory;
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;

    private Approve(){
    }

//TODO Constructor
    public Approve(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void approve() {
//        try {
//        String projectManager = properties.getProperty("projectManagerEmail");
//        iLogin.performLogin(projectManager);
//        String title = properties.getProperty("orderTitle");
//        String approveButtonLocator = getApproveButton(title);
//        Locator projectManagerOption = page.locator(approveButtonLocator);
//        waitForLocator(projectManagerOption);
//        projectManagerOption.first().click();
//
//        Locator approveButton = page.locator(APPROVE);
//        waitForLocator(approveButton);
//        approveButton.click();
//
//        Locator yesButtonLocator = page.locator(YES);
//        waitForLocator(yesButtonLocator);
//        yesButtonLocator.click();
//        iLogout.performLogout();
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
        try {
            int x = Integer.parseInt(properties.getProperty("ApproverCount"));
            List<String> approvers = new ArrayList<>();
            for(int i=1;i<=x;i++){
                approvers.add(properties.getProperty("Approver"+i));
            }
            String PRReferenceNumber = properties.getProperty("PRReferenceNumber");
            for (String approverMailId : approvers) {
                ApproveMethod(approverMailId , PRReferenceNumber);
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public void ApproveMethod(String ApproverMailId , String PRReferenceNumber) throws InterruptedException {
        iLogin.performLogin(ApproverMailId);
        page.locator("#ni-my-approvals").click();
        String title = "Requisition "+ PRReferenceNumber;
        page.locator(String.format("//*[contains(text(), '%s')]", title)).first().click();
        String poReferenceId = page.waitForSelector("#referenceId").textContent();
        PlaywrightFactory.saveToPropertiesFile("POReferenceId",poReferenceId);
        page.locator("#btnApprove").click();
        Thread.sleep(1000);
        page.locator(".bootbox-accept").click();
        Thread.sleep(2000);
        iLogout.performLogout();
    }


}