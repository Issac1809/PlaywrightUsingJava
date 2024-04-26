package com.yokogawa.orderschedule.approve;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

import java.util.List;

import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;

public class OrderScheduleApprove implements OrderScheduleApproveInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void OSApprove(String mailId, String poReferenceId, Page page){
        login.Login(mailId, page);
        System.out.println(poReferenceId);
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        System.out.println(containerList);
        for(String tr : containerList){
            System.out.println(tr);
            if(tr.equals(poReferenceId)){
                System.out.println(poReferenceId);
                Locator detailsButton = page.locator(".bi-info-square-fill").first();
                detailsButton.click();
            }
        }
        page.locator("#BtnToApproveOS").click();
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
