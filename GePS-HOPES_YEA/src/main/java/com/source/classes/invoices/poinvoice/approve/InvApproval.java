package com.source.classes.invoices.poinvoice.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.poinvoices.IInvApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.invoices.poinvoice.LInvApproval.*;
import static com.constants.orderschedules.LOsEdit.getTitle;

public class InvApproval implements IInvApproval {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private InvApproval(){
    }

//TODO Constructor
    public InvApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InvApproval.class);
    }

    public int approval(String referenceId, String transactionId, String uid){
        int status = 0;
        try {
            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            String financeChecker = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(financeChecker);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            Locator invoiceTitle = page.locator(getTitle(referenceId));
            invoiceTitle.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator updateFinanceFields = page.locator(UPDATE_FINANCE_FIELDS);
            updateFinanceFields.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator bankAccountDropdownLocator = page.locator(BANK_ACCOUNT);
            if(bankAccountDropdownLocator.count() > 0 && bankAccountDropdownLocator.isVisible()){
                bankAccountDropdownLocator.click();
            }
            Locator bankAccountDataLocator = page.locator(BANK_ACCOUNT_DATA);
            if(bankAccountDataLocator.count() > 0 && bankAccountDataLocator.isVisible()){
                bankAccountDataLocator.click();
            }

            Locator accountTypeDropdownLocator = page.locator(ACCOUNT_TYPE);
            if(accountTypeDropdownLocator.count() > 0 && accountTypeDropdownLocator.isVisible()){
                accountTypeDropdownLocator.click();
            }
            Locator accountTypeDataLocator = page.locator(ACCOUNT_TYPE_DATA);
            if(accountTypeDataLocator.count() > 0 && accountTypeDataLocator.isVisible()){
                accountTypeDataLocator.click();
            }

            Locator documentTypeDropdownLocator =page.locator(DOCUMENT_TYPE);
            if(documentTypeDropdownLocator.count() > 0 && documentTypeDropdownLocator.isVisible()){
                documentTypeDropdownLocator.click();
            }
            Locator documentTypeDataLocator = page.locator(DOCUMENT_TYPE_DATA);
            if(documentTypeDataLocator.count() > 0 && documentTypeDataLocator.isVisible()){
                documentTypeDataLocator.click();
            }

            Locator generalLedgerDropdownLocator =page.locator(GENERAL_LEDGER);
            if(generalLedgerDropdownLocator.count() > 0 && generalLedgerDropdownLocator.isVisible()){
                generalLedgerDropdownLocator.click();
            }
            Locator generalLedgerDataLocator = page.locator(GENERAL_LEDGER_DATA);
            if(generalLedgerDataLocator.count() > 0 && generalLedgerDataLocator.isVisible()){
                generalLedgerDataLocator.click();
            }

            Locator textLocator = page.locator(TEXT);
            if(textLocator.count() > 0 && textLocator.isVisible()){
                textLocator.fill("Finance Field");
            }

            Locator taxCodeDropdownLocator = page.locator(TAX_CODE);
            if(taxCodeDropdownLocator.count() > 0 && taxCodeDropdownLocator.isVisible()){
                taxCodeDropdownLocator.click();
            }
            Locator taxCodeDataLocator = page.locator(TAX_CODE_DATA);
            if(taxCodeDataLocator.count() > 0 && taxCodeDataLocator.isVisible()){
                taxCodeDataLocator.click();
            }

            Locator saveFinanceFieldsLocator = page.locator(SAVE_FINANCE_FIELDS);
            saveFinanceFieldsLocator.click();

            Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
            approveButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);

            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/") && response.status() == 200,
                    acceptLocator::click);

            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Approve", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Approval function: {}", exception.getMessage());
        }
        return status;
    }
}