package com.procurement.orderschedule.approve;
import com.interfaces.OrderScheduleApproveInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class OrderScheduleApprove implements OrderScheduleApproveInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private OrderScheduleApprove(){
    }

//TODO Constructor
    public OrderScheduleApprove(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void OSApprove(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector("//*[contains(text(), 'Purchase Orders')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.waitForSelector("#BtnToApproveOS").click();
        page.waitForSelector("#btnApprove").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}