package com.poc.classes.dispatchnotes.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.dispatchnotes.IDnCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.Properties;
import static com.constants.disptchnotes.LDnCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class DnCreate implements IDnCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private DnCreate() {
    }

//TODO Constructor
    public DnCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iLogin = iLogin;
    }

    public void create() {
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
        waitForLocator(poNavigationBarLocator);
        poNavigationBarLocator.click();

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator dnCreateButtonLocator = page.locator(DN_CREATE_BUTTON);
        waitForLocator(dnCreateButtonLocator);
        dnCreateButtonLocator.click();

        Locator sourceCountryLocator = page.locator(SOURCE_COUNTRY_CODE);
        waitForLocator(sourceCountryLocator);
        sourceCountryLocator.click();

        String sourceCountry = properties.getProperty("SourceCountry");
        Locator sourceCountrySearchField = page.locator(SEARCH_FIELD);
        waitForLocator(sourceCountrySearchField);
        sourceCountrySearchField.fill(sourceCountry);

        Locator getSourceCountryLocator = page.locator(getSourceCountry(sourceCountry));
        waitForLocator(getSourceCountryLocator);
        getSourceCountryLocator.click();

        Locator sourceCodeCountryLocator = page.locator(DESTINATION_COUNTRY_CODE);
        waitForLocator(sourceCodeCountryLocator);
        sourceCodeCountryLocator.click();

        String destinationCountry = properties.getProperty("DestinationCountry");
        Locator destinationCountrySearchField = page.locator(SEARCH_FIELD);
        waitForLocator(destinationCountrySearchField);
        destinationCountrySearchField.fill(destinationCountry);

        Locator getDestinationCountryLocator = page.locator(getDestinationCountry(destinationCountry));
        waitForLocator(getDestinationCountryLocator);
        getDestinationCountryLocator.click();

        Locator addDnPackagesButtonLocator = page.locator(ADD_DISPATCH_NOTE_PACKAGES_BUTTON);
        waitForLocator(addDnPackagesButtonLocator);
        addDnPackagesButtonLocator.click();

        Locator packageTypeLocator = page.locator(PACKAGE_TYPE);
        waitForLocator(packageTypeLocator);
        packageTypeLocator.click();

        String packageType = properties.getProperty("PackageType");
        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
        waitForLocator(searchFieldLocator);
        searchFieldLocator.fill(packageType);

        Locator getPackageLocator = page.locator(getPackageType(packageType));
        waitForLocator(getPackageLocator);
        getPackageLocator.click();

        String grossWeight = String.valueOf(properties.getProperty("GrossWeight"));
        Locator grossWeightLocator = page.locator(GROSS_WEIGHT);
        waitForLocator(grossWeightLocator);
        grossWeightLocator.fill(grossWeight);

        String netWeight = String.valueOf(properties.getProperty("NetWeight"));
        Locator netWeightLocator = page.locator(NET_WEIGHT);
        waitForLocator(netWeightLocator);
        netWeightLocator.fill(netWeight);

        String volume = String.valueOf(properties.getProperty("Volume"));
        Locator volumeLocator = page.locator(VOLUME);
        waitForLocator(volumeLocator);
        volumeLocator.fill(volume);

        String quantity = String.valueOf(properties.getProperty("Quantity"));
        Locator quantityLocator = page.locator(QUANTITY);
        waitForLocator(quantityLocator);
        quantityLocator.fill(quantity);

        Locator saveDnPackagesButtonLocator = page.locator(SAVE_DISPATCH_NOTE_PACKAGES_BUTTON);
        waitForLocator(saveDnPackagesButtonLocator);
        saveDnPackagesButtonLocator.click();

        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}