package com.yokogawa.msa;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
public class PorInspectPO implements PorInspectPoInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void InspectCreatePO(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        page.locator("//*[contains(text(),'" + NonCatalogTitle + "')]").first().click();
        Locator createPOButton = page.locator("#createPOContainer");
        createPOButton.evaluate("(element) => { element.style.display = 'block'; }");
        createPOButton.click();
        page.waitForSelector(".bootbox-accept").click();
        logout.Logout(page);
    }
}
