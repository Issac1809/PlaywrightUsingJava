package com.procurement.dispatchnotes.edit;
import com.interfaces.DnEdit;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class PocDnEdit implements DnEdit {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocDnEdit(){
    }

    //TODO Constructor
    public PocDnEdit(LoginPageInterface loginPageInterface, Properties properties, Page page,LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void PocDnEditMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#btnEdit").click();
        page.locator("#btnUpdate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}