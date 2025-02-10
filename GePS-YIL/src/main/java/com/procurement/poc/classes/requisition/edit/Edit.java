package com.procurement.poc.classes.requisition.edit;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrApprove;
import com.procurement.poc.interfaces.requisitions.IPrAssign;
import com.procurement.poc.interfaces.requisitions.IPrEdit;
import com.procurement.poc.interfaces.requisitions.IPrSendForApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrEdit.*;

public class Edit implements IPrEdit {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrSendForApproval iPrSendForApproval;
    private IPrApprove iPrApprove;
    private IPrAssign iPrAssign;
    private String url;

    private Edit(){
    }

//TODO Constructor
    public Edit(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrSendForApproval iPrSendForApproval, IPrApprove iPrApprove, IPrAssign iPrAssign){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrSendForApproval = iPrSendForApproval;
        this.iPrApprove = iPrApprove;
        this.iPrAssign = iPrAssign;
        this.url = properties.getProperty("appUrl");
    }

    public void edit() {
        try {

        iLogin.performLogin(properties.getProperty("requesterEmail"));
        String title = properties.getProperty("currentTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle).first();
        waitForLocator(titleLocator);
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith(url + "/api/Requisitions/") && resp.status() == 200,
                titleLocator::click
        );

        Locator editButtonLocator = page.locator(EDIT_BUTTON.getLocator()).first();
        waitForLocator(editButtonLocator);
        editButtonLocator.click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON.getLocator());
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator submitButtonLocator = page.locator(ACCEPT.getLocator());
        waitForLocator(submitButtonLocator);

        statusAssertion(page, submitButtonLocator::click, "requisition", "Draft");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rejectEdit()  {
        try {
        edit();
        iPrSendForApproval.sendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void buyerSuspendEdit()  {
        try {
        edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerLogin();
        iPrAssign.buyerManagerAssign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}