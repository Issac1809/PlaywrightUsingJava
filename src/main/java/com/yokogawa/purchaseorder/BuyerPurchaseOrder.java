package com.yokogawa.purchaseorder;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.Properties;

public class BuyerPurchaseOrder implements PurchaseOrderInterface{

    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private BuyerPurchaseOrder(){
    }

//TODO Test Constructor
    public BuyerPurchaseOrder(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public BuyerPurchaseOrder(VariablesForNonCatalog variablesForNonCatalog, Page page, LoginPageInterface loginPageInterface, LogoutPageInterface logoutPageInterface){
        this.variablesForNonCatalog = variablesForNonCatalog;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void SendForVendor(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnSendPO").click();
        page.locator("#vendorSendMailBtnId").click();
        logoutPageInterface.LogoutMethod();
    }
}