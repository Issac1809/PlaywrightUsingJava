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
    public DispatchNotesAssign(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PlayWrightFactory playWrightFactory){
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
        this.playWrightFactory = playWrightFactory;
    }

    public void DNAssign() {
        try {
            String logisticsManager = properties.getProperty("LogisticsManager");
            loginPageInterface.LoginMethod(logisticsManager);
            page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
            String poReferenceId = properties.getProperty("PoReferenceId");
            List<String> containerList = page.locator("#listContainer tr td").allTextContents();
            for (String tr : containerList) {
                if (tr.contains(poReferenceId)) {
                    page.locator(".btn-link").first().click();
                }
            }
            String dnReferenceId = page.locator("#dispatchNote").innerText();
            playWrightFactory.savePropertiesToFile3(dnReferenceId);
            page.locator("#dnActionDropdown").click();
            page.locator("#btnToAssign").click();
            page.locator("#select2-assignerId-container").click();
            page.locator(".select2-search__field").fill(logisticsManager);
            page.locator("//li[contains(text(), '" + logisticsManager + "')]").click();
            page.locator("#saveAssine").click();
            logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}