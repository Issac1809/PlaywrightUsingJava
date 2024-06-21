package com.procurement.dispatchnotes.cancel;
import com.interfaces.*;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class PocDnCancel implements DnCancel {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    DispatchNoteCreateInterface dispatchNoteCreateInterface;

    private PocDnCancel(){
    }

    //TODO Constructor
    public PocDnCancel(LoginPageInterface loginPageInterface, Properties properties, Page page,LogoutPageInterface logoutPageInterface, DispatchNoteCreateInterface dispatchNoteCreateInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.dispatchNoteCreateInterface = dispatchNoteCreateInterface;
    }

    public void PocDnCancelMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("LogisticsManager"));
        page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#dropdownMenuButton").click();
        page.locator("#btnToCancel").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        dispatchNoteCreateInterface.DNCreate();
    }
}