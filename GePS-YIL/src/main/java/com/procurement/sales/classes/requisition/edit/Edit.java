package com.procurement.sales.classes.requisition.edit;

import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.procurement.sales.interfaces.requisitions.IPrEdit;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.sales.constants.requisitions.LPrEdit.*;

public class Edit implements IPrEdit {
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private String url;

    private Edit(){
    }

    //TODO Constructor
    public Edit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
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
                    resp -> resp.url().startsWith(url + "/api/RequisitionsOthers/") && resp.status() == 200,
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

            statusAssertion(page, submitButtonLocator::click, "salesRequisition", "Approved");

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
