package com.procurement.poc.classes.purchaseorderrequest.reject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorEdit;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorReject;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorSendForApproval;

import java.util.List;
import java.util.Properties;

import static com.procurement.poc.constants.purchaseorderrequests.LPorReject.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorReject implements IPorReject {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;
    IPorEdit iPorEdit;
    IPorSendForApproval iPorSendForApproval;

    private PorReject(){
    }

//TODO Constructor
    public PorReject(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPorEdit iPorEdit, IPorSendForApproval iPorSendForApproval){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPorEdit = iPorEdit;
        this.iPorSendForApproval = iPorSendForApproval;
    }

    public void porReject() {
        try {
//        List<String> matchingApprovers = iPorSendForApproval.getApprovers();
//        for(String approver : matchingApprovers){
//            iLogin.performLogin(approver);
//            break;
//        }
        iLogin.performLogin(properties.getProperty("Approver1"));

        Locator porNavigationBar = page.locator(POR_NAVIGATION_BAR.getLocator());
        waitForLocator(porNavigationBar);
        porNavigationBar.click();

        String title = properties.getProperty("orderTitle");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON.getLocator());
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT.getLocator());
        waitForLocator(rejectButtonLocator);
        remarksInputLocator.fill("Updated");

        Locator acceptLocator = page.locator(YES.getLocator());
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        iPorEdit.porEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}