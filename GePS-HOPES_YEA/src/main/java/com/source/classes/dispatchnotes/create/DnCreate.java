package com.source.classes.dispatchnotes.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.dispatchnotes.IDnCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import static com.constants.dispatchnotes.LDnCreate.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class DnCreate implements IDnCreate {

    Logger logger;
    JsonNode jsonNode;
    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private DnCreate() {
    }

//TODO Constructor
    public DnCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout) {
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iLogin = iLogin;
        this.logger = LoggerUtil.getLogger(DnCreate.class);
    }

    public void create(String type, String purchaseType) {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
            poNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator dnCreateButtonLocator = page.locator(DN_CREATE_BUTTON);
            dnCreateButtonLocator.click();

            Locator sourceCountryLocator = page.locator(SOURCE_COUNTRY_CODE);
            sourceCountryLocator.click();

            String sourceCountry = jsonNode.get("dispatchNotes").get("sourceCountry").asText();
            Locator sourceCountrySearchField = page.locator(SEARCH_FIELD);
            sourceCountrySearchField.fill(sourceCountry);

            Locator getSourceCountryLocator = page.locator(getSourceCountry(sourceCountry));
            getSourceCountryLocator.click();

            Locator sourceCodeCountryLocator = page.locator(DESTINATION_COUNTRY_CODE);
            sourceCodeCountryLocator.click();

            String destinationCountry = jsonNode.get("dispatchNotes").get("destinationCountry").asText();
            Locator destinationCountrySearchField = page.locator(SEARCH_FIELD);
            destinationCountrySearchField.fill(destinationCountry);

            Locator getDestinationCountryLocator = page.locator(getDestinationCountry(destinationCountry));
            getDestinationCountryLocator.click();

            Locator addDnPackagesButtonLocator = page.locator(ADD_DISPATCH_NOTE_PACKAGES_BUTTON);
            addDnPackagesButtonLocator.click();

            Locator packageTypeLocator = page.locator(PACKAGE_TYPE);
            packageTypeLocator.click();

            String packageType = jsonNode.get("dispatchNotes").get("packageType").asText();
            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            searchFieldLocator.fill(packageType);

            Locator getPackageLocator = page.locator(getPackageType(packageType));
            getPackageLocator.click();

            String grossWeight = jsonNode.get("dispatchNotes").get("grossWeightKg").asText();
            Locator grossWeightLocator = page.locator(GROSS_WEIGHT);
            grossWeightLocator.fill(grossWeight);

            String netWeight = jsonNode.get("dispatchNotes").get("netWeightKg").asText();
            Locator netWeightLocator = page.locator(NET_WEIGHT);
            netWeightLocator.fill(netWeight);

            String volume = jsonNode.get("dispatchNotes").get("volumeCm3").asText();
            Locator volumeLocator = page.locator(VOLUME);
            volumeLocator.fill(volume);

            String quantity = jsonNode.get("dispatchNotes").get("totalChargeableWeightKg").asText();
            Locator quantityLocator = page.locator(QUANTITY);
            quantityLocator.fill(quantity);

            Locator saveDnPackagesButtonLocator = page.locator(SAVE_DISPATCH_NOTE_PACKAGES_BUTTON);
            saveDnPackagesButtonLocator.click();

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Create function: {}", exception.getMessage());
        }
    }
}