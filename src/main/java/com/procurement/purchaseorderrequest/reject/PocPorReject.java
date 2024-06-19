package com.procurement.purchaseorderrequest.reject;
import com.interfaces.*;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class PocPorReject implements PorReject{

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;
    PorEdit porEdit;
    PorApproval porApproval;

    private PocPorReject(){
    }

//TODO Constructor
    public PocPorReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PorEdit porEdit, PorApproval porApproval){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.porEdit = porEdit;
        this.porApproval = porApproval;
    }

    public void PorReject() throws InterruptedException {
        loginPageInterface.LoginMethod(properties.getProperty("ProjectManager"));
        page.waitForSelector("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.waitForSelector("#btnReject").click();
        page.waitForSelector(".bootbox-input").fill("Updated");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        porEdit.PorEditMethod();
        porApproval.SendForApproval();
    }
}
