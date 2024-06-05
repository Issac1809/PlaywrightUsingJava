package com.yokogawa.dispatchnotes.create;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;

import java.util.Properties;


public class DispatchNoteCreate implements DispatchNoteCreateInterface {

    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private DispatchNoteCreate(){
    }

//TODO Test Constructor
    public DispatchNoteCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public DispatchNoteCreate(VariablesForNonCatalog variablesForNonCatalog, Page page, LoginPageInterface loginPageInterface, LogoutPageInterface logoutPageInterface){
        this.variablesForNonCatalog = variablesForNonCatalog;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public void DNCreate(){
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnCreateDispatchNote").click();
        page.locator("#select2-sourceCountryId-container").click();
        String sourceCountry = properties.getProperty("SourceCountry");
        page.locator(".select2-search__field").fill(sourceCountry);
        page.locator("//li[contains(text(), '"+ sourceCountry +"')]").click();
        page.locator("#select2-destinationCountryId-container").click();
        String destinationCountry = properties.getProperty("DestinationCountry");
        page.locator(".select2-search__field").fill(destinationCountry);
        page.locator("//li[contains(text(), '"+ destinationCountry +"')]").click();
        page.locator("#addDispatchNotePackages").click();
        page.locator("#select2-packagetypeId-container").click();
        String packageType = properties.getProperty("PackageType");
        page.locator(".select2-search__field").fill(packageType);
        page.locator("//li[contains(text(), '"+ packageType +"')]").click();
        page.locator("#grossWeight").fill(String.valueOf(properties.getProperty("GrossWeight")));
        page.locator("#netWeight").fill(String.valueOf(properties.getProperty("NetWeight")));
        page.locator("#volume").fill(String.valueOf(properties.getProperty("Volume")));
        page.locator("#quantity").fill(String.valueOf(properties.getProperty("Quantity")));
        page.locator("#saveDispatchNotePackages").click();
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        }
}