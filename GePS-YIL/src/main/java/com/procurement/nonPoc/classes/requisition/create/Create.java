package com.procurement.nonPoc.classes.requisition.create;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Download;
import com.procurement.nonPoc.interfaces.login.ILogin;
import com.procurement.nonPoc.interfaces.logout.ILogout;
import com.procurement.nonPoc.interfaces.requisitions.IPrCreate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.nonPoc.constants.requisitions.LPrCreate.*;

public class Create implements IPrCreate {

    private Page page;
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private String prType;

    private Create(){
    }

//TODO Constructor
    public Create(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void requesterLoginPRCreate() {
        try {
            String requesterEmail = properties.getProperty("requesterEmail");
            iLogin.performLogin(requesterEmail);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void createButton() {
        try {
            Locator createButton = page.locator(CREATE_BUTTON.getLocator());
            waitForLocator(createButton);
            createButton.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void purchaseType() {
        try {
            String purchaseType = properties.getProperty("purchaseType");
            String prTypeLocator = getPrType(purchaseType);
            page.locator(prTypeLocator).click();
        } catch (Exception error) {
            System.out.println("What is the Error: " + error);
        }
    }

    public void title() {
        try {
            prType=properties.getProperty("purchaseType");
            switch (prType) {
                case "Catalog":
                    Locator catalogTitleLocator = page.locator(TITLE.getLocator());
                    waitForLocator(catalogTitleLocator);
                    String catalogTitle = properties.getProperty("orderTitle");
                    catalogTitleLocator.fill(catalogTitle + "-" + prType);
                    break;
                case "NonCatalog":
                    Locator nonCatalogTitleLocator = page.locator(TITLE.getLocator());
                    waitForLocator(nonCatalogTitleLocator);
                    String nonCatalogTitle = properties.getProperty("orderTitle");
                    nonCatalogTitleLocator.fill(nonCatalogTitle + "-" + prType);
                    break;
                case "MH":
                    Locator mhTitleLocator = page.locator(TITLE.getLocator());
                    waitForLocator(mhTitleLocator);
                    String mhTitle = properties.getProperty("orderTitle");
                    mhTitleLocator.fill(mhTitle + "-" + prType);
                    break;
                default:
                    System.out.println("--Enter Proper PR Type--");
                    break;
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void shipToYokogawa() {
        try {
            String shipToYokogawaValue = properties.getProperty("shipToYokogawa").toLowerCase().trim();
            if (shipToYokogawaValue.equals("no")) {
                page.locator(SHIP_TO_YOKOGAWA.getLocator()).check();
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void salesOrder(){
        Locator salesOrderLocator = page.locator(SALES_ORDER.getLocator());
        waitForLocator(salesOrderLocator);
        salesOrderLocator.click();

        String salesOrderValue = properties.getProperty("salesOrder");
        Locator salesOrderSearchLocator = page.locator(SEARCH.getLocator());
        waitForLocator(salesOrderSearchLocator);
        salesOrderSearchLocator.fill(salesOrderValue);

        String salesOrderSelectLocator = getString(salesOrderValue);
        Locator salesOrderSelectElement = page.locator(salesOrderSelectLocator);
        waitForLocator(salesOrderSelectElement);
        salesOrderSelectElement.click();
    }

    public void departmentPIC(){
        Locator depPIC = page.locator(DEPARTMENT_PIC.getLocator());
        waitForLocator(depPIC);
        depPIC.click();

        String departmentPIC = properties.getProperty("departmentPIC");
        Locator depPICSearch = page.locator(SEARCH.getLocator());
        waitForLocator(depPICSearch);
        depPICSearch.fill(departmentPIC);

        String depPICSelectLocator = getString(departmentPIC);
        Locator depPICSelectElement = page.locator(depPICSelectLocator);
        waitForLocator(depPICSelectElement);
        depPICSelectElement.click();
    }

//    public void project() {
//        try {
//            Locator projectLocator = page.locator(PROJECT);
//            waitForLocator(projectLocator);
//            projectLocator.click();
//
//            String projectCodeValue = properties.getProperty("projectCode");
//            Locator projectSearchLocator = page.locator(PROJECT_SEARCH);
//            waitForLocator(projectSearchLocator);
//            projectSearchLocator.fill(projectCodeValue);
//
//            String projectSelectLocator = getProject(projectCodeValue);
//            Locator projectSelectElement = page.locator(projectSelectLocator);
//            waitForLocator(projectSelectElement);
//            projectSelectElement.click();
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }

//    public void wbs() {
//        try {
//            if (prType.toUpperCase().equals("MH")) {
//                Locator wbsLocator = page.locator(WBS);
//                waitForLocator(wbsLocator);
//                wbsLocator.click();
//
//                List<String> wbsResultsList = page.locator(WBS_LIST).allTextContents();
//                for (int i = 0; i < wbsResultsList.size(); i++) {
//                    String wbsResult = wbsResultsList.get(i);
//                    if (wbsResult.endsWith("E")) {
//                        String wbsForMh = getWBSForMh(wbsResult);
//                        Locator finalWbsLocator = page.locator(wbsForMh);
//                        waitForLocator(finalWbsLocator);
//                        finalWbsLocator.first().click();
//                        break;
//                    }
//                }
//            } else {
//                Locator wbsLocator = page.locator(WBS);
//                waitForLocator(wbsLocator);
//                wbsLocator.click();
//
//                String wbsCodeValue = properties.getProperty("wbsCode");
//                Locator wbsSearchLocator = page.locator(WBS_SEARCH);
//                waitForLocator(wbsSearchLocator);
//                wbsSearchLocator.fill(wbsCodeValue);
//
//                String wbsSelectLocator = getWBSForCandNC(wbsCodeValue);
//                Locator finalWbsLocator = page.locator(wbsSelectLocator);
//                waitForLocator(finalWbsLocator);
//                finalWbsLocator.click();
//            }
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }

    public void vendor() {
        try {
            Locator vendorLocator = page.locator(VENDOR.getLocator());
            waitForLocator(vendorLocator);
            vendorLocator.click();

            String vendorNameValue = properties.getProperty("vendorName");
            Locator vendorSearchLocator = page.locator(SEARCH.getLocator());
            waitForLocator(vendorSearchLocator);
            vendorSearchLocator.fill(vendorNameValue);

            String vendorOptionLocator = getString(vendorNameValue);
            Locator vendorOption = page.locator(vendorOptionLocator);
            waitForLocator(vendorOption);
            vendorOption.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rateContract() {
        try {
            Locator rateContractLocator = page.locator(RATE_CONTRACT.getLocator());
            waitForLocator(rateContractLocator);
            rateContractLocator.click();

            String rateContractValue = properties.getProperty("rateContract");
            Locator rateContractSearchLocator = page.locator(SEARCH.getLocator());
            waitForLocator(rateContractSearchLocator);
            rateContractSearchLocator.fill(rateContractValue);

            String rateContractOptionLocator = getString(rateContractValue);
            Locator rateContractOption = page.locator(rateContractOptionLocator);
            waitForLocator(rateContractOption);
            rateContractOption.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void incoterm() {
        try {
            Locator incotermLocator = page.locator(INCOTERM.getLocator());
            waitForLocator(incotermLocator);
            incotermLocator.click();

            String incotermValue = properties.getProperty("incoterm");
            Locator incotermSearchLocator = page.locator(SEARCH.getLocator());
            incotermSearchLocator.fill(incotermValue);

            String incotermOptionLocator = getString(incotermValue);
            Locator incotermOption = page.locator(incotermOptionLocator);
            waitForLocator(incotermOption);
            incotermOption.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void shippingAddress() {
        try {
            Locator shippingAddressLocator = page.locator(SHIPPING_ADDRESS.getLocator());
            waitForLocator(shippingAddressLocator);
            shippingAddressLocator.click();

            String shippingAddressValue = properties.getProperty("shippingAddress");
            String shippingAddressOptionLocator = getString(shippingAddressValue);

            Locator shippingAddressOption = page.locator(shippingAddressOptionLocator);
            waitForLocator(shippingAddressOption);
            shippingAddressOption.last().click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void billingType() {
        try {
            Locator billingTypeLocator = page.locator(BILLING_TYPE.getLocator());
            waitForLocator(billingTypeLocator);
            billingTypeLocator.click();

            String selectedBillingType = properties.getProperty("billingType");
            String billingTypeOptionLocator = getString(selectedBillingType);

            Locator billingTypeOption = page.locator(billingTypeOptionLocator);
            waitForLocator(billingTypeOption);
            billingTypeOption.last().click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void shippingMode() {
        try {
            String shippingMode = prType.equals("Catalog") ? CATALOG_SHIPPING_MODE.getLocator() : NON_CATALOG_MH_SHIPPING_MODE.getLocator();

            Locator shippingModeLocator = page.locator(shippingMode);
            waitForLocator(shippingModeLocator);
            shippingModeLocator.click();

            String getShippingMode = properties.getProperty("shippingMode");

            Locator shippingModeSearch = page.locator(SEARCH.getLocator());
            waitForLocator(shippingModeSearch);
            shippingModeSearch.fill(getShippingMode);

            String finalShippingMode = getString(getShippingMode);

            Locator finalShippingModeLocator = page.locator(finalShippingMode);
            waitForLocator(finalShippingModeLocator);
            finalShippingModeLocator.click();
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void billableToCustomer(){
        Locator billableLocator = page.locator(BILLABLE_TO_CUSTOMER.getLocator());
        waitForLocator(billableLocator);
        billableLocator.click();

        String billableValue = properties.getProperty("billableToCustomer");
        String billableOptionsLocator = getString(billableValue);

        Locator billableOptionElement = page.locator(billableOptionsLocator);
        waitForLocator(billableOptionElement);
        billableOptionElement.click();
    }

    public void quotationRequiredBy() {
        try {
            Locator quotationRequiredByField = page.locator(QUOTATION_REQUIRED_BY.getLocator());
            waitForLocator(quotationRequiredByField);
            quotationRequiredByField.click();

            Locator todayOption = page.locator(TODAY.getLocator());
            waitForLocator(todayOption);
            todayOption.first().click();
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void expectedPOIssue() {
        try {
            Locator expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE.getLocator());
            waitForLocator(expectedPoIssueField);
            expectedPoIssueField.click();

            Locator todayOption = page.locator(TODAY.getLocator());
            for (int i = 0; i < todayOption.count(); i++) {
                if (todayOption.nth(i).isVisible()) {
                    todayOption.nth(i).click(); // Click the visible element
                    break; // Stop the loop once the visible element is found and clicked
                }
            }

    //this is because after selecting the expected PO date it requires another click outside to click on Expected Delivery
            page.locator("//label[@for='expectedDelivery']").click();

//            waitForLocator(todayOption);
//            todayOption.last().click();
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void expectedDelivery() {
        try {
            Locator expectedDeliveryField = page.locator(EXPECTED_DELIVERY.getLocator());
            waitForLocator(expectedDeliveryField);
            expectedDeliveryField.click();

            Locator todayOption = page.locator(TODAY.getLocator());
            for (int i = 0; i < todayOption.count(); i++) {
                if (todayOption.nth(i).isVisible()) {
                    todayOption.nth(i).click(); // Click the visible element
                    break; // Stop the loop once the visible element is found and clicked
                }
            }
//            waitForLocator(todayOption);
//            todayOption.last().click();
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

//TODO There is no Buyer Manager in YCA
//    public void buyerManager(){
//        try {
//            Locator buyerManagerDropdown = page.locator(BUYER_MANAGER);
//            waitForLocator(buyerManagerDropdown);
//            buyerManagerDropdown.click();
//
//            String buyerManagerName = properties.getProperty("buyerManagerEmail");
//
//            Locator buyerManagerSearch = page.locator(BUYER_MANAGER_SEARCH);
//            waitForLocator(buyerManagerSearch);
//            buyerManagerSearch.fill(buyerManagerName);
//
//            String buyerManagerLocator = getBuyerManager(buyerManagerName);
//            Locator finalBuyerManagerSelect = page.locator(buyerManagerLocator);
//            waitForLocator(finalBuyerManagerSelect);
//            finalBuyerManagerSelect.click();
//        } catch (Exception error) {
//            System.out.println("Error encountered: " + error.getMessage());
//        }
//    }

//TODO There is no Project Manager in YCA
//    public void projectManager(){
//        try {
//            Locator projectManagerDropdown = page.locator(PROJECT_MANAGER);
//            waitForLocator(projectManagerDropdown);
//            projectManagerDropdown.click();
//
//            String projectManagerName = properties.getProperty("projectManagerEmail");
//
//            Locator projectManagerSearch = page.locator(PROJECT_MANAGER_SEARCH);
//            waitForLocator(projectManagerSearch);
//            projectManagerSearch.fill(projectManagerName);
//
//            String projectManagerLocator = getProjectManager(projectManagerName);
//            Locator finalProjectManager = page.locator(projectManagerLocator);
//            waitForLocator(finalProjectManager);
//            finalProjectManager.click();
//        } catch (Exception error) {
//            System.out.println("Error encountered: " + error.getMessage());
//        }
//    }

    public void buyerGroup(){
        Locator buyerGroupDropdown = page.locator(BUYER_GROUP.getLocator());
        waitForLocator(buyerGroupDropdown);
        buyerGroupDropdown.click();

        String buyerGroupName = properties.getProperty("buyerGroupName");

        Locator buyerGroupSearch = page.locator(SEARCH.getLocator());
        waitForLocator(buyerGroupSearch);
        buyerGroupSearch.fill(buyerGroupName);

        String buyerGroupElement = getString(buyerGroupName);
        Locator buyerGroupSelect = page.locator(buyerGroupElement);
        waitForLocator(buyerGroupSelect);
        buyerGroupSelect.click();
    }

    public void checker(){
        Locator checkerDropdown = page.locator(CHECKER.getLocator());
        waitForLocator(checkerDropdown);
        checkerDropdown.click();

        String checkerName = properties.getProperty("checker");

        Locator checkerSearch = page.locator(SEARCH.getLocator());
        waitForLocator(checkerSearch);
        checkerSearch.fill(checkerName);

        String checkerElement = getString(checkerName);
        Locator checkerElementLocator = page.locator(checkerElement);
        waitForLocator(checkerElementLocator);
        checkerElementLocator.click();
    }


    public void rohsCompliance(){
        try {
            String compliance = properties.getProperty("rohsCompliance").toLowerCase().trim();

            if (compliance.equals("no")) {
                Locator rohsComplianceLocator = page.locator(ROHS_COMPLIANCE.getLocator());
                waitForLocator(rohsComplianceLocator);
                rohsComplianceLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void oiAndTpCurrency(){
        try {
            if (prType.equals("NonCatalog")) {

                Locator oiAndTpCurrencyLocator = page.locator(OI_AND_TP_CURRENCY.getLocator());
                waitForLocator(oiAndTpCurrencyLocator);
                oiAndTpCurrencyLocator.click();

                String currency = properties.getProperty("oiAndTpCurrency").toLowerCase().trim();

                Locator oiAndTpCurrencySearchLocator = page.locator(SEARCH.getLocator());
                waitForLocator(oiAndTpCurrencySearchLocator);
                oiAndTpCurrencySearchLocator.fill(currency);

                String currencySelect = getString(currency);
                Locator currencySelectLocator = page.locator(currencySelect);
                waitForLocator(currencySelectLocator);
                currencySelectLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void orderIntake(){
        try {
            String orderIntake = properties.getProperty("orderIntake");
            Locator orderIntakeLocator = page.locator(ORDER_INTAKE.getLocator());
            waitForLocator(orderIntakeLocator);
            orderIntakeLocator.fill(orderIntake);
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void targetPrice(){
        try {
//            if (prType.equals("NonCatalog")) {
                String targetPrice = properties.getProperty("targetPrice");
                Locator targetPriceLocator = page.locator(TARGET_PRICE.getLocator());
                waitForLocator(targetPriceLocator);
                targetPriceLocator.fill(targetPrice);
//            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void caseMarking(){
        try {
            String caseMarkingDetails = properties.getProperty("caseMarking");
            Locator caseMarking = page.locator(CASE_MARKING.getLocator());
            waitForLocator(caseMarking);
            caseMarking.fill(caseMarkingDetails);
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void messageToSourcing(){
        try {
            String messageDetails = properties.getProperty("messageToSourcing");
            Locator messageToSourcing = page.locator(MESSAGE_TO_SOURCING.getLocator());
            waitForLocator(messageToSourcing);
            messageToSourcing.fill(messageDetails);
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void warrantyRequirements(){
        try {
            if (prType.equals("NonCatalog")) {
                Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS.getLocator());
                waitForLocator(warrantyRequirementsLocator);
                warrantyRequirementsLocator.click();

                String warrantyRequirement = properties.getProperty("warrantyRequirement");

                Locator warrantyRequirementsSearchLocator = page.locator(SEARCH.getLocator());
                waitForLocator(warrantyRequirementsSearchLocator);
                warrantyRequirementsSearchLocator.fill(warrantyRequirement);

                String warrantyRequirementSelector = getString(warrantyRequirement);
                Locator warrantyRequirementOptionLocator = page.locator(warrantyRequirementSelector);
                waitForLocator(warrantyRequirementOptionLocator);
                warrantyRequirementOptionLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void priceValidity(){
        try {
            Locator priceValidityLocator = page.locator(PRICE_VALIDITY.getLocator());
            waitForLocator(priceValidityLocator);
            priceValidityLocator.click();

            String warrantyRequirement = properties.getProperty("priceValidity");

            Locator priceValiditySearchLocator = page.locator(SEARCH.getLocator());
            waitForLocator(priceValiditySearchLocator);
            priceValiditySearchLocator.fill(warrantyRequirement);

            String priceValiditySelector = getString(warrantyRequirement);
            Locator priceValidityOptionLocator = page.locator(priceValiditySelector);
            waitForLocator(priceValidityOptionLocator);
            priceValidityOptionLocator.click();
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void inspectionRequired() {
        try {
            boolean isInspectionRequired = Boolean.parseBoolean(properties.getProperty("inspectionRequired"));

            if (isInspectionRequired) {
                Locator inspectionRequiredLocator = page.locator(INSPECTION_REQUIRED.getLocator());
                waitForLocator(inspectionRequiredLocator);
                inspectionRequiredLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
            String liquidatedDamages = properties.getProperty("liquidatedDamages").toLowerCase().trim();

            if (liquidatedDamages.equals("special")) {
                Locator liquidatedDamagesSelector = page.locator(LIQUIDATED_DAMAGES_SELECT.getLocator());
                waitForLocator(liquidatedDamagesSelector);
                liquidatedDamagesSelector.click();

                Locator liquidatedDamagesInput = page.locator(LIQUIDATED_DAMAGES.getLocator());
                waitForLocator(liquidatedDamagesInput);
                liquidatedDamagesInput.fill("Special Liquidated Damages");
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void addLineRequisitionItems() {
        try {
            Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON.getLocator());
            waitForLocator(addLineItemButton);
            addLineItemButton.click();

            Locator itemsDropdown = page.locator(ITEMS.getLocator());
            waitForLocator(itemsDropdown);
            itemsDropdown.click();

            page.locator("(//ul[@id='select2-item-results']/li)[1]").click();
            page.locator("#soItemNumber").fill("1");
            page.locator("#quantity").fill("10");
            page.locator("#remarks").fill("Remarks 1");
            page.locator("#description").fill("Desc 1");
            Locator addItemButton = page.locator(ADD_ITEM_BUTTON.getLocator());
            waitForLocator(addItemButton);
            addItemButton.click();

//            if (prType.equals("Catalog")) {
//                List<String> itemList = page.locator(ITEMS_LIST).allTextContents();
//                for (int i = 1; i <= itemList.size(); i++) {
//                    String itemName = itemList.get(i);
//                    itemName.split(" - ");
//
//                    if (i > 1) {
//                        waitForLocator(addItemButton);
//                        addLineItemButton.click();
//                        waitForLocator(itemsDropdown);
//                        itemsDropdown.click();
//                    }
//
//                    Locator itemSearchBox = page.locator(ITEM_SEARCH);
//                    waitForLocator(itemSearchBox);
//                    itemSearchBox.fill(itemName);
//
//                    Locator itemOption = page.locator(getItem(itemName));
//                    waitForLocator(itemOption);
//                    itemOption.first().click();
//
//                    Locator quantityField = page.locator(QUANTITY);
//                    waitForLocator(quantityField);
//                    quantityField.fill(String.valueOf(i));
//
//                    addItemButton = page.locator(ADD_ITEM_BUTTON);
//                    waitForLocator(addItemButton);
//                    addItemButton.click();
//                }
//            } else if (prType.equals("NonCatalog")) {
//                String[] itemNames = properties.getProperty("items").split(",");
//                String[] quantities = properties.getProperty("quantityList").split(",");
//
//                for (int i = 0; i < itemNames.length; i++) {
//                    waitForLocator(addItemButton);
//                    addLineItemButton.click();
//
//                    waitForLocator(itemsDropdown);
//                    itemsDropdown.click();
//
//                    Locator itemSearchBox = page.locator(ITEM_SEARCH);
//                    waitForLocator(itemSearchBox);
//                    itemSearchBox.fill(itemNames[i]);
//
//                    Locator itemOption = page.locator(getItem(itemNames[i]));
//                    waitForLocator(itemOption);
//                    itemOption.first().click();
//
//                    Locator quantityField = page.locator(QUANTITY);
//                    waitForLocator(quantityField);
//                    quantityField.fill(quantities[i]);
//
//                    waitForLocator(addItemButton);
//                    addItemButton.click();
//                }
//            } else if (prType.equalsIgnoreCase("MH")) {
//                String mhItemName = properties.getProperty("mhItem");
//
//                Locator itemSearchBox = page.locator(ITEM_SEARCH);
//                waitForLocator(itemSearchBox);
//                itemSearchBox.fill(mhItemName);
//
//                Locator itemOption = page.locator(getItem(mhItemName));
//                waitForLocator(itemOption);
//                itemOption.first().click();
//
//                Locator quantityField = page.locator(QUANTITY);
//                waitForLocator(quantityField);
//                quantityField.fill("20");
//
//                waitForLocator(addItemButton);
//                addItemButton.click();
//            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }

    }

    public void createItemsFile() {
        page.locator("#itemImport").click();

        // Wait for the new tab (or page) to be created
        Page newTab = page.waitForPopup(() -> {
            page.click("#importLink");  // Clicking on a link that opens a new tab
        });
        page.click("//button[(@aria-label='Close') and (contains(text(),'Cancel'))]");

        // Now you can interact with the new tab
        newTab.waitForLoadState();
        newTab.locator(".form-control-sm").fill(properties.getProperty("rateContract"));
        Download download = newTab.waitForDownload(() -> {
            newTab.click("//a[(@class='export-link') and (contains(text(),'" + properties.getProperty("rateContract") + "'))]");
        });
        // Wait for the download to complete and save it to a specific location
        download.saveAs(Paths.get("Downloads/" + download.suggestedFilename()));
        newTab.close();

        String filePath = "Downloads/ExportItems.xlsx"; // Path to your Excel file

        try {
            // Step 1: Load the Excel file
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet (index 0)


            // Step 2: Delete the second row (row index 1)
            if (sheet.getLastRowNum() > 2) {
                Row rowToDelete = sheet.getRow(1); // Get the second row (index 1)

                if (rowToDelete != null) {
                    sheet.removeRow(rowToDelete);  // Delete the row

                    // Step 3: Shift the rows up (if necessary) to fill the gap left by the deleted row
                    if (1 < sheet.getLastRowNum()) {
                        sheet.shiftRows(2, sheet.getLastRowNum(), -1);  // Shift rows starting from the third row up
                    }
                }
            }

            // Step 2: Start the loop from the second row (index 1)
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // rowIndex 1 for second row
                Row row = sheet.getRow(rowIndex);

                Cell Quantity = row.getCell(11);
                if(Quantity == null)
                    Quantity = row.createCell(14);
                Quantity.setCellValue(rowIndex * 2 + 1);

                Cell Description = row.getCell(12);
                if(Description == null)
                    Description = row.createCell(14);
                Description.setCellValue("Desc " + (rowIndex+1));

                Cell Remarks = row.getCell(13);
                if(Remarks == null)
                    Remarks = row.createCell(14);
                Remarks.setCellValue("Remarks " + (rowIndex+1));

                Cell SOItemNumber = row.getCell(14);
                if(SOItemNumber == null)
                    SOItemNumber = row.createCell(14);
                SOItemNumber.setCellValue(rowIndex + 1);
            }

            // Step 4: Write the updated data back to the file
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);

            // Step 5: Close the streams
            fileInputStream.close();
            fileOutputStream.close();
            workbook.close();
            System.out.println("Excel file updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void importItems(){
        page.locator("#itemImport").click();
        Locator itemFile = page.locator("#formFile");
        itemFile.setInputFiles(Paths.get("Downloads/ExportItems.xlsx"));
        page.locator("#btnUpload").click();
    }

    public void notes() {
        try {
            String notesText = properties.getProperty("requisitionNotes");
            Locator notesField = page.locator(NOTES.getLocator());
            waitForLocator(notesField);
            notesField.fill(notesText);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void attachments(){
        try {
//TODO Internal Attachment
            Locator attachmentsButton = page.locator(ATTACHMENTS.getLocator());
            waitForLocator(attachmentsButton);
            attachmentsButton.click();

            Locator internalFileUpload = page.locator(FILE_UPLOAD.getLocator());
            waitForLocator(internalFileUpload);
            internalFileUpload.setInputFiles(Paths.get(properties.getProperty("internalFilePath")));

            Locator attachInternalFileButton = page.locator(ATTACH_FILE_BUTTON.getLocator());
            waitForLocator(attachInternalFileButton);
            attachInternalFileButton.click();

            Locator continueButton = page.locator(CONTINUE_BUTTON.getLocator());
            waitForLocator(continueButton);
            continueButton.click();

//TODO External Attachment
            Locator externalAttachmentsButton = page.locator(ATTACHMENTS.getLocator());
            waitForLocator(externalAttachmentsButton);
            externalAttachmentsButton.click();

            Locator externalFileUpload = page.locator(FILE_UPLOAD.getLocator());
            waitForLocator(externalFileUpload);
            externalFileUpload.setInputFiles(Paths.get(properties.getProperty("externalFilePath")));

            Locator externalRadioButton = page.locator(EXTERNAL_RADIO_BUTTON.getLocator());
            waitForLocator(externalRadioButton);
            externalRadioButton.click();

            Locator attachExternalFileButton = page.locator(ATTACH_FILE_BUTTON.getLocator());
            waitForLocator(attachExternalFileButton);
            attachExternalFileButton.click();

//            Locator continueButton = page.locator(CONTINUE_BUTTON);
            waitForLocator(continueButton);
            continueButton.click();

        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void prCreate() {
        try {
            Locator createDraftButtonLocator = page.locator(CREATE_DRAFT_BUTTON.getLocator());
            waitForLocator(createDraftButtonLocator);
            createDraftButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES.getLocator());
            waitForLocator(yesButtonLocator);
            yesButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}