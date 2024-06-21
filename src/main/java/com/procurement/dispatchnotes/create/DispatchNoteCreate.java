package com.procurement.dispatchnotes.create;
import com.interfaces.DispatchNoteCreateInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;

import java.util.Properties;


public class DispatchNoteCreate implements DispatchNoteCreateInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private DispatchNoteCreate() {
    }

//TODO Constructor
    public DispatchNoteCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public void DNCreate() {
        try {
            loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
            page.waitForSelector("//*[contains(text(), 'Purchase Orders')]").click();
            String title = properties.getProperty("Title");
            page.locator("//*[contains(text(), '" + title + "')]").first().click();
            page.waitForSelector("#btnCreateDispatchNote").click();
            page.waitForSelector("#select2-sourceCountryId-container").click();
            String sourceCountry = properties.getProperty("SourceCountry");
            page.waitForSelector(".select2-search__field").fill(sourceCountry);
            page.waitForSelector("//li[contains(text(), '" + sourceCountry + "')]").click();
            page.waitForSelector("#select2-destinationCountryId-container").click();
            String destinationCountry = properties.getProperty("DestinationCountry");
            page.waitForSelector(".select2-search__field").fill(destinationCountry);
            page.waitForSelector("//li[contains(text(), '" + destinationCountry + "')]").click();
            page.waitForSelector("#addDispatchNotePackages").click();
            page.waitForSelector("#select2-packagetypeId-container").click();
            String packageType = properties.getProperty("PackageType");
            page.waitForSelector(".select2-search__field").fill(packageType);
            page.waitForSelector("//li[contains(text(), '" + packageType + "')]").click();
            page.waitForSelector("#grossWeight").fill(String.valueOf(properties.getProperty("GrossWeight")));
            page.waitForSelector("#netWeight").fill(String.valueOf(properties.getProperty("NetWeight")));
            page.waitForSelector("#volume").fill(String.valueOf(properties.getProperty("Volume")));
            page.waitForSelector("#quantity").fill(String.valueOf(properties.getProperty("Quantity")));
            page.waitForSelector("#saveDispatchNotePackages").click();
            page.waitForSelector("#btnCreate").click();
            page.waitForSelector(".bootbox-accept").click();
            logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}