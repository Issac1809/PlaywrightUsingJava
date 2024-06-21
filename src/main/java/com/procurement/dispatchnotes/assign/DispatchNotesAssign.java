package com.procurement.dispatchnotes.assign;
import com.factory.PlayWrightFactory;
import com.interfaces.DispatchNotesAssignInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.microsoft.playwright.Playwright;

import java.util.List;
import java.util.Properties;

public class DispatchNotesAssign implements DispatchNotesAssignInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    PlayWrightFactory playWrightFactory;

    private DispatchNotesAssign(){
    }

//TODO Constructor
    public DispatchNotesAssign(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void DNAssign() {
        try {
            String logisticsManager = properties.getProperty("LogisticsManager");
            loginPageInterface.LoginMethod(logisticsManager);
            page.waitForSelector("//*[contains(text(), 'Dispatch Notes')]").click();
            String poReferenceId = properties.getProperty("PoReferenceId");
            List<String> containerList = page.locator("#listContainer tr td").allTextContents();
            for (String tr : containerList) {
                if (tr.contains(poReferenceId)) {
                    page.locator(".btn-link").first().click();
                }
            }
            String dnReferenceId = page.waitForSelector("#dispatchNote").innerText();
            playWrightFactory.savePropertiesToFile3(dnReferenceId);
            page.waitForSelector("#dnActionDropdown").click();
            page.waitForSelector("#btnToAssign").click();
            page.waitForSelector("#select2-assignerId-container").click();
            page.waitForSelector(".select2-search__field").fill(logisticsManager);
            page.waitForSelector("//li[contains(text(), '" + logisticsManager + "')]").click();
            page.waitForSelector("#saveAssine").click();
            logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}