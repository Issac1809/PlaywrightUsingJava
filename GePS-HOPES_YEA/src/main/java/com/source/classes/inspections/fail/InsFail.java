package com.source.classes.inspections.fail;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.inspections.IInsFail;
import com.source.interfaces.inspections.IInsReadyForInspection;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import org.apache.logging.log4j.Logger;

import java.util.List;
import static com.constants.inspections.LInsFail.*;

public class InsFail implements IInsFail {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IInsReadyForInspection iInsReadyForInspection;

    private InsFail() {
    }

//TODO Constructor
    public InsFail(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IInsReadyForInspection iInsReadyForInspection) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iInsReadyForInspection = iInsReadyForInspection;
    }

    public void fail() {
        try {
            String mailId = jsonNode.get("mailIds").get("requesterEmail").asText();
            iLogin.performLogin(mailId);

            Locator osNavigationBarLocator = page.locator(OS_NAVIGATION_BAR);
            osNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            Locator assignButtonLocator = page.locator(ASSIGN_INSPECTOR_BUTTON);
            assignButtonLocator.click();

            Locator dropDownLocator = page.locator(SELECT_INSPECTOR_DROP_DOWN);
            dropDownLocator.click();

            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            searchFieldLocator.fill(mailId);

            Locator requesterMailIdLocator = page.locator(getRequestorId(mailId));
            requesterMailIdLocator.first().click();

            Locator saveInspectorButtonLocator = page.locator(SAVE_INSPECTOR);
            saveInspectorButtonLocator.click();

            Locator createButtonLocator = page.locator(CREATE_INSPECTION_BUTTON);
            createButtonLocator.click();

            Locator radioButton = page.locator(PHYSICAL_INSPECTION_NOT_REQUIRED);
            radioButton.click();

            Locator failButtonLocator = page.locator(INSPECTION_FAIL_BUTTON);
            failButtonLocator.click();

            Locator createInspectionButtonLocator = page.locator(CREATE_BUTTON);
            createInspectionButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS_INPUT);
            remarksLocator.fill("Failed");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            iLogout.performLogout();

            iInsReadyForInspection.readyForInspection();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Fail function: {}", exception.getMessage());
        }
    }
}