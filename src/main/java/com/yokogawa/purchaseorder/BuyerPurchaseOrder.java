package com.yokogawa.purchaseorder;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;

public class BuyerPurchaseOrder implements PurchaseOrderInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void SendForVendor(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        page.locator("//*[contains(text(), '"+ NonCatalogTitle +"')]").first().click();
        page.locator("#btnSendPO").click();
        page.locator("#vendorSendMailBtnId").click();
        logout.Logout(page);
    }
}
