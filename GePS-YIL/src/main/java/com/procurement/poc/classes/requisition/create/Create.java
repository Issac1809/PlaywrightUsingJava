package com.procurement.poc.classes.requisition.create;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrCreate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static com.factory.PlaywrightFactory.*;
import static com.procurement.poc.constants.requisitions.LPrCreate.*;

public class Create implements IPrCreate {

    private Page page;
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private String prType;
    private ObjectMapper objectMapper;
    private String type;
    private String url;

    private Create(){
    }

    public Create(ILogin iLogin, Properties properties, Page page, ILogout iLogout, ObjectMapper objectMapper, String type){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
        this.type = type;
        this.url = properties.getProperty("appUrl");
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
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void purchaseType() {
        try {
//            String purchaseType = properties.getProperty("purchaseType");
            String prTypeLocator = getPrType(type);
            page.locator(prTypeLocator).click();

            String catalogType = properties.getProperty("rateContractType");
            if(catalogType.trim().contains("bop")){
                Locator bopLocator = page.locator(BOP_RADIO_LOCATOR.getLocator());
                bopLocator.click();
            }
        } catch (Exception error) {
            System.out.println("What is the Error: " + error);
        }
    }

    public void title() {
        try {
            switch (type) {
                case "catalog":
                    Locator catalogTitleLocator = page.locator(TITLE.getLocator());
                    waitForLocator(catalogTitleLocator);
                    Integer catCount = Integer.parseInt(properties.getProperty("catCount"));
                    String catalogTitle = properties.getProperty("orderTitle") + "PS-Catalog-" + catCount;
                    catalogTitleLocator.fill(catalogTitle);
                    catCount++;
                    saveToPropertiesFile("catCount",catCount.toString());
                    saveToPropertiesFile("currentTitle",catalogTitle);
                    break;
                case "noncatalog":
                    Locator nonCatalogTitleLocator = page.locator(TITLE.getLocator());
                    waitForLocator(nonCatalogTitleLocator);
                    Integer ncCount = Integer.parseInt(properties.getProperty("nonCatalogCount"));
                    String nonCatalogTitle = properties.getProperty("orderTitle")+ "PS-Non Catalog-" + ncCount;
                    nonCatalogTitleLocator.fill(nonCatalogTitle);
                    ncCount++;
                    saveToPropertiesFile("nonCatalogCount",ncCount.toString());
                    saveToPropertiesFile("currentTitle",nonCatalogTitle);
                    break;
//                case "MH":
//                    Locator mhTitleLocator = page.locator(TITLE.getLocator());
//                    waitForLocator(mhTitleLocator);
//                    String mhTitle = properties.getProperty("orderTitle");
//                    mhTitleLocator.fill(mhTitle + "-" + prType);
//                    break;
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
//            Locator projectLocator = page.locator(PROJECT.getLocator());
//            waitForLocator(projectLocator);
//            projectLocator.click();
//
//            String projectCodeValue = properties.getProperty("projectCode");
//            Locator projectSearchLocator = page.locator(SEARCH.getLocator());
//            waitForLocator(projectSearchLocator);
//            projectSearchLocator.fill(projectCodeValue);
//
//            String projectSelectLocator = getString(projectCodeValue);
//            Locator projectSelectElement = page.locator(projectSelectLocator);
//            waitForLocator(projectSelectElement);
//
//            Response response = page.waitForResponse(
//                    resp -> resp.url().startsWith(GET_WBS_API.getAPI()) && resp.status() == 200,
//                    projectSelectElement::click
//            );
////            projectSelectElement.click();
////            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }
//
//    public void wbs() {
//        try {
//                Locator wbsLocator = page.locator(WBS.getLocator());
//                waitForLocator(wbsLocator);
//                wbsLocator.click();
//
//                String wbsCodeValue = properties.getProperty("wbsCode");
//                Locator wbsSearchLocator = page.locator(SEARCH.getLocator());
//                waitForLocator(wbsSearchLocator);
//                wbsSearchLocator.fill(wbsCodeValue);
//
//                String wbsSelectLocator = getString(wbsCodeValue);
//                Locator finalWbsLocator = page.locator(wbsSelectLocator);
//                waitForLocator(finalWbsLocator);
//                finalWbsLocator.click();
//
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }
//

//
//    public void wbs(List<String> wbs) {
//        String wbsFromProperties = properties.getProperty("wbsCode");
//        try {
//            for(String getWbs : wbs) {
//                if (getWbs.equals(wbsFromProperties)) {
//                    Locator wbsLocator = page.locator(WBS.getLocator());
//                    wbsLocator.click();
//                    Locator wbsSearchLocator = page.locator(SEARCH.getLocator());
//                    wbsSearchLocator.fill(wbsFromProperties);
//                    String wbsSelectLocator = getString(wbsFromProperties);
//                    Locator finalWbsLocator = page.locator(wbsSelectLocator);
//                    finalWbsLocator.click();
//                    break;
//                }
//            }
//        } catch (Exception error) {
//            System.out.println("Error in WBS Function: " + error.getMessage());
//        }
//    }
//
//
//    public void vendor() {
//        try {
//            Locator vendorLocator = page.locator(VENDOR.getLocator());
//            waitForLocator(vendorLocator);
//            vendorLocator.click();
//
//            String vendorNameValue = properties.getProperty("vendorName");
//            Locator vendorSearchLocator = page.locator(SEARCH.getLocator());
//            waitForLocator(vendorSearchLocator);
//            vendorSearchLocator.fill(vendorNameValue);
//
//            String vendorOptionLocator = getString(vendorNameValue);
//            Locator vendorOption = page.locator(vendorOptionLocator);
//            waitForLocator(vendorOption);
//
//            Response response = page.waitForResponse(
//                    resp -> resp.url().startsWith(GET_RATE_CONTRACTS_API.getAPI()) && resp.status() == 200,
//                    vendorOption::click
//            );
//
//
//
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }

//    public void rateContract() {
//        try {
//            Locator rateContractLocator = page.locator(RATE_CONTRACT.getLocator());
//            waitForLocator(rateContractLocator);
//            rateContractLocator.click();
//
//            String rateContractValue = properties.getProperty("rateContract");
//            Locator rateContractSearchLocator = page.locator(SEARCH.getLocator());
//            waitForLocator(rateContractSearchLocator);
//            rateContractSearchLocator.fill(rateContractValue);
//
//            String rateContractOptionLocator = getString(rateContractValue);
//            Locator rateContractOption = page.locator(rateContractOptionLocator);
//            waitForLocator(rateContractOption);
//            rateContractOption.click();
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }

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
            String shippingMode = type.equals("catalog") ? CATALOG_SHIPPING_MODE.getLocator() : NON_CATALOG_MH_SHIPPING_MODE.getLocator();

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

    public void plantCode() {
        try {
            String plantCode = PLANT_CODE.getLocator();
            Locator plantCodeLocator = page.locator(plantCode);
            waitForLocator(plantCodeLocator);
            plantCodeLocator.click();

            String getPlantCode = properties.getProperty("plantCode");

            Locator plantCodeSearch = page.locator(SEARCH.getLocator());
            waitForLocator(plantCodeSearch);
            plantCodeSearch.fill(getPlantCode);

            String finalPlantCode = getString(getPlantCode);

            Locator finalPlantCodeLocator = page.locator(finalPlantCode);
            waitForLocator(finalPlantCodeLocator);
            finalPlantCodeLocator.click();
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

            Locator todayOption = page.locator(DAYS_OF_MONTH.getLocator()).last();
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

            Locator todayOption = page.locator(DAYS_OF_NEXT_MONTH.getLocator()).first();
            for (int i = 0; i < todayOption.count(); i++) {
                if (todayOption.nth(i).isVisible()) {
                    todayOption.nth(i).click(); // Click the visible element
                    break; // Stop the loop once the visible element is found and clicked
                }
            }

    //this is because after selecting the expected PO date it requires another click outside to click on Expected Delivery
            page.locator(EXPECTED_PO_ISSUE_LABEL.getLocator()).click();

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

            Locator todayOption = page.locator(DAYS_OF_NEXT_MONTH.getLocator()).last();
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
            if (!(prType.equals("catalog"))) {

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
            Locator orderIntakeLocator = page.locator(ORDER_INTAKE.getLocator()).first();
            waitForLocator(orderIntakeLocator);
            orderIntakeLocator.first().fill(orderIntake);
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void targetPrice(){
        try {
                String targetPrice = properties.getProperty("targetPrice");
                Locator targetPriceLocator = page.locator(TARGET_PRICE.getLocator());
                waitForLocator(targetPriceLocator);
                targetPriceLocator.fill(targetPrice);
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }

    public void typeOfPurchase(){
        Locator typeOfPurchase = page.locator(TYPE_OF_PURCHASE.getLocator());
        waitForLocator(typeOfPurchase);
        typeOfPurchase.click();

        String typeOfPurchaseValue = properties.getProperty("typeOfPurchase");

        Locator typeOfPurchaseSearchLocator = page.locator(SEARCH.getLocator());
        waitForLocator(typeOfPurchaseSearchLocator);
        typeOfPurchaseSearchLocator.fill(typeOfPurchaseValue);

        String typeOfPurchaseSelector = getString(typeOfPurchaseValue);
        Locator typeOfPurchaseOptionLocator = page.locator(typeOfPurchaseSelector);
        waitForLocator(typeOfPurchaseOptionLocator);
        typeOfPurchaseOptionLocator.click();
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
            ////div[label[contains(text(),'Item')]]//span[contains(text(),'Item')]
            Locator firstItem = page.locator(FIRST_ITEM.getLocator());
            waitForLocator(firstItem);
            firstItem.click();
//            page.locator("#soItemNumber").fill("1");
            page.locator(QUANTITY.getLocator()).fill("10");
//            page.locator("#remarks").fill("Remarks 1");
            page.locator(DESCRIPTION.getLocator()).fill("Desc 1");
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
        page.locator(IMPORT_ITEM.getLocator()).click();

        // Wait for the new tab (or page) to be created
        Page newTab = page.waitForPopup(() -> {
            page.click(DOWNLOAD_RATE_CONTRACT.getLocator());  // Clicking on a link that opens a new tab
        });
        page.click(CANCEL_ITEM_IMPORT.getLocator());

        // Now you can interact with the new tab
        newTab.waitForLoadState();
        newTab.locator(SEARCH_RC_FOR_DOWNLOAD.getLocator()).fill(properties.getProperty("rateContract"));
        Download download = newTab.waitForDownload(() -> {
            newTab.click(SELECT_RATE_CONTRACT_DOWNLOAD.getLocator());
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
                    Quantity = row.createCell(11);
                Quantity.setCellValue(rowIndex * 2 + 1);

                Cell Description = row.getCell(12);
                if(Description == null)
                    Description = row.createCell(12);
                Description.setCellValue("Desc " + (rowIndex+1));

                Cell Remarks = row.getCell(13);
                if(Remarks == null)
                    Remarks = row.createCell(13);
                Remarks.setCellValue("Remarks " + (rowIndex+1));

//                Cell SOItemNumber = row.getCell(14);
//                if(SOItemNumber == null)
//                    SOItemNumber = row.createCell(14);
//                SOItemNumber.setCellValue(rowIndex + 1);
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
            System.out.println("What is the error: " + e.getMessage());
        }
    }

    public void importItems(){
        if(properties.getProperty("purchaseType").equals("catalog")) {
            createItemsFile();
            page.locator(IMPORT_ITEM.getLocator()).click();
            Locator itemFile = page.locator(CHOOSE_FILE.getLocator());
            itemFile.setInputFiles(Paths.get("Downloads/ExportItems.xlsx"));
        }
        else{
            page.locator(IMPORT_ITEM.getLocator()).click();
            Locator itemFile = page.locator(CHOOSE_FILE.getLocator());
            itemFile.setInputFiles(Paths.get("Downloads/NonCatalogPRItemsImport.xlsx"));
        }
        page.locator(UPLOAD.getLocator()).click();
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

            statusAssertion(page, yesButtonLocator::click,"requisition","Draft");

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }



    //API Methods

    public List<String> project() {
        List<String> wbsValues = new ArrayList<>();
        try {
            String projectCodeValue = properties.getProperty("projectCode");
            page.locator(PROJECT.getLocator()).click();
            Locator projectSearchLocator = page.locator(SEARCH.getLocator());
            projectSearchLocator.fill(projectCodeValue);
            String projectSelectLocator = getString(projectCodeValue);
            Locator projectSelectElement = page.locator(projectSelectLocator);
            projectSelectElement.click();
//            Response response = page.waitForResponse(response1 -> response1.url().equals(url + "/api/Projects/search?keyword=" + projectCodeValue),projectSelectElement::click);
//            JsonNode abc = objectMapper.readTree(response.body());
            APIResponse projectResponse = page.request().fetch(url + "/api/Projects/search?keyword=" + projectCodeValue, RequestOptions.create());
            JsonNode projectCodeJson = objectMapper.readTree(projectResponse.body());
            JsonNode firstProjectObject = projectCodeJson.get(0);
            String projectId = firstProjectObject.get("id").asText();
            APIResponse wbsResponse = page.request().fetch(url + "/api/workBreakdownStructures/search?projectid=" + projectId, RequestOptions.create());
            JsonNode wbsCodeJson = objectMapper.readTree(wbsResponse.body());
            for(JsonNode wbs : wbsCodeJson){
                if(wbs.has("text")){
                    wbsValues.add(wbs.get("text").asText());
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Project Function: " + error.getMessage());
        }
        return wbsValues;
    }

    public void wbs(List<String> wbs) {
        String wbsFromProperties = properties.getProperty("wbsCode");
        try {
            for(String getWbs : wbs) {
                if (getWbs.equals(wbsFromProperties)) {
                    Locator wbsLocator = page.locator(WBS.getLocator());
                    wbsLocator.click();

                    Locator wbsSearchLocator = page.locator(SEARCH.getLocator());
                    wbsSearchLocator.fill(wbsFromProperties);

                    String wbsSelectLocator = getString(wbsFromProperties);
                    Locator finalWbsLocator = page.locator(wbsSelectLocator);
                    finalWbsLocator.click();
                    break;
                }
            }
        } catch (Exception error) {
            System.out.println("Error in WBS Function: " + error.getMessage());
        }
    }

    public Map<String, String> vendor() {
        Map<String, String> rateContractArray = new HashMap<>();
        try {
            String rateContractType = properties.getProperty("rateContractType");
            String vendorNameValue = properties.getProperty("vendorName");
            Locator vendorLocator = page.locator(VENDOR.getLocator());
            vendorLocator.click();

            APIResponse vendorApiResponse = page.request().fetch(url+"/api/Vendors/ApprovedVendorsByKeyword?keyword=" + vendorNameValue, RequestOptions.create());
            JsonNode vendorJson = objectMapper.readTree(vendorApiResponse.body());
            JsonNode vendorIdJson = vendorJson.get(0);
            int vendorId = vendorIdJson.get("id").asInt();

            Locator vendorSearchLocator = page.locator(SEARCH.getLocator());
            vendorSearchLocator.fill(vendorNameValue);

            String vendorOptionLocator = getString(vendorNameValue);
            Locator vendorOption = page.locator(vendorOptionLocator);
            vendorOption.click();

            APIResponse rateContractApiResponse;
            if(rateContractType.toLowerCase().equals("standard")){
                rateContractApiResponse = page.request().fetch(url + "/api/RateContractsByVendorId?vendorId=" + vendorId + "&&itemType=" + 1, RequestOptions.create());
            }
            else{
                rateContractApiResponse = page.request().fetch(url + "/api/RateContractsByVendorId?vendorId=" + vendorId + "&&itemType=" + 0, RequestOptions.create());

            }
            JsonNode rateContractJson = objectMapper.readTree(rateContractApiResponse.body());

            for(JsonNode rateContract : rateContractJson){
                if(rateContract.has("id") && rateContract.has("text")){
                    String rateContractId = rateContract.get("id").asText();
                    String rateContractText = rateContract.get("text").asText();
                    rateContractArray.put(rateContractId, rateContractText);
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Vendor Function: " + error.getMessage());
        }
        return rateContractArray;
    }

    public List<String> rateContract(Map<String, String> rateContractArray) {
        List<String> rateContractItems = new ArrayList<>();
        try {
            String rateContractValue = properties.getProperty("rateContract");

            Locator rateContractLocator = page.locator(RATE_CONTRACT.getLocator());
            rateContractLocator.click();

            for(Map.Entry<String, String> rateContract : rateContractArray.entrySet()){
                if(rateContract.getValue().trim().contains(rateContractValue)){
                    String rateContractId = rateContract.getKey();
                    Locator rateContractSearchLocator = page.locator(SEARCH.getLocator());
                    rateContractSearchLocator.fill(rateContractValue);

                    String rateContractOptionLocator = getString(rateContractValue);
                    Locator rateContractOption = page.locator(rateContractOptionLocator);
                    rateContractOption.click();

                    String rateContractType = properties.getProperty("rateContractType").trim().toLowerCase();
                    APIResponse rateContractResponse;

                    if(rateContractType.contains("bop")){
                        rateContractResponse = page.request().fetch(url + "/api/RateContracts/bopRCItemSearch?bopRcId=" + rateContractId, RequestOptions.create());
                        JsonNode rateContractJsonResponse = objectMapper.readTree(rateContractResponse.body());
                        for(JsonNode Response : rateContractJsonResponse) {
                            rateContractItems.add(Response.get("bopCode").asText());
                        }
                    }
                    else{
                         rateContractResponse = page.request().fetch(url + "/api/RateContracts/ratecontract?rateContractId=" + rateContractId, RequestOptions.create());
                        JsonNode rateContractJsonResponse = objectMapper.readTree(rateContractResponse.body());
                        JsonNode itemsArray = rateContractJsonResponse.get("items");
                        for(JsonNode item : itemsArray){
                            if(item.has("name")){
                                rateContractItems.add(item.get("name").asText());
                            }
                        }
                    }
                    break;

//                    JsonNode rateContractJsonResponse = objectMapper.readTree(rateContractResponse.body());
//                    JsonNode itemsArray = rateContractJsonResponse.get("items");
//                    for(JsonNode item : itemsArray){
//                        if(item.has("name")){
//                            rateContractItems.add(item.get("name").asText());
//                        }
//                    }
//                    break;
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Rate Contract Function: " + error.getMessage());
        }
        return rateContractItems;
    }

    public void addLineRequisitionItemsCatalog(List<String> rateContractItems) {
        Locator itemDropdown;
        Locator addItemButton;
        try {
            Locator addLineItemButton;
            Locator quantityField;
            String catalogType = properties.getProperty("rateContractType");
            if(catalogType.trim().contains("bop")){
                addLineItemButton = page.locator(ADD_BOP2_LINE_ITEM_BUTTON.getLocator());
                itemDropdown = page.locator(BOP_RC_ITEMS_DROPDOWN.getLocator());
                addItemButton = page.locator(ADD_BOP_ITEM_BUTTON.getLocator());
                quantityField = page.locator(BOP_QUANTITY.getLocator());
            }
            else {
                addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON.getLocator());
                itemDropdown = page.locator(CATALOG_ITEMS_DROPDOWN.getLocator());
                addItemButton = page.locator(ADD_ITEM_BUTTON.getLocator());
                quantityField = page.locator(QUANTITY.getLocator());
            }
            addLineItemButton.click();

            for(int i = 0; i < rateContractItems.size(); i++){
                itemDropdown.click();

                Locator itemSearchBox = page.locator(SEARCH.getLocator());
                itemSearchBox.fill(rateContractItems.get(i));

                String itemDropDownOptionSelect = getString(rateContractItems.get(i));
                Locator itemOptionSelect = page.locator(itemDropDownOptionSelect);
                itemOptionSelect.click();

                quantityField.fill(String.valueOf(i + 10));

                addItemButton.click();

                if(i < rateContractItems.size() - 1) {
                        addLineItemButton.click();
                } else {
                    break;
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Catalog Requisition Items Function: " + error.getMessage());
        }
    }

    public void addLineRequisitionItemsNonCatalog() {
        Locator itemDropdown;
        Locator addItemButton;
        String idValue;
        List<String> inputTypes = new ArrayList<>();
        try {
            String[] itemNames = properties.getProperty("items").split(",");
            String[] quantities = properties.getProperty("quantityList").split(",");

            Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON.getLocator());
            addLineItemButton.click();

            for (int i = 0; i < itemNames.length; i++) {
                itemDropdown = page.locator(NON_CATALOG_ITEMS_DROPDOWN.getLocator());
                itemDropdown.click();

                Locator itemSearchBox = page.locator(SEARCH.getLocator());
                itemSearchBox.fill(itemNames[i]);

                String itemName = itemNames[i].trim();
                String encodedName = itemName.replace(" ", "%20");

                APIResponse itemSpecificationResponse = page.request().fetch(url + "/api/ItemandCategory/search?keyword=" + encodedName + "&purchaseMethod=NonCatalog");
                JsonNode itemSpecificationsObject = objectMapper.readTree(itemSpecificationResponse.body());
                idValue = itemSpecificationsObject.get(0).get("id").asText();

                Locator itemOption = page.locator(getString(itemNames[i]));
                itemOption.first().click();

                APIResponse getItemSpecifications = page.request().fetch(url + "/api/Items/Spefications?itemId=" + idValue);
                JsonNode getItemSpecificationsJson = objectMapper.readTree(getItemSpecifications.body());

                if(!getItemSpecificationsJson.isNull()){
                    for(int j = 0; j < getItemSpecificationsJson.size(); j++){JsonNode item = getItemSpecificationsJson.get(j);
                        if(item.has("inputType")) {
                            inputTypes.add(item.get("inputType").asText());
                        }
                    }

                    for(String inputType : inputTypes){
                        if(inputType.equals("Text")){
                            List<Locator> textFields = page.locator(ITEM_SPECIFICATIONS_TEXT_FIELD_LOCATORS.getLocator()).all();
                            for(Locator textField : textFields){
                                String idLocator = textField.getAttribute("id");
                                Locator textFieldLocator = page.locator("#" + idLocator);
                                if(textFieldLocator.isEnabled()){
                                    textFieldLocator.fill("2000");
                                }
                            }
                        } else if(inputType.equals("Selection")){
                            List<Locator> selectionFields = page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_LOCATORS.getLocator()).all();
                            for(Locator selectionField : selectionFields){
                                String idLocator = selectionField.getAttribute("id");
                                Locator selectionFieldLocator = page.locator("#" + idLocator);
                                if(selectionFieldLocator.isEnabled()){
                                    selectionFieldLocator.click();
                                    page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_RESULT_LOCATOR.getLocator()).click();
                                }
                            }
                        } else if(inputType.equals("CheckBox")){
                            List<Locator> checkBoxFields = page.locator(ITEM_SPECIFICATIONS_CHECKBOX_FIELD_LOCATORS.getLocator()).all();
                            for(Locator checkBoxField : checkBoxFields){
                                String idLocator = checkBoxField.getAttribute("id");
                                Locator checkBoxFieldLocator = page.locator("#" + idLocator);
                                if(checkBoxFieldLocator.isEnabled()){
                                    checkBoxFieldLocator.click();
                                }
                            }
                        }
                    }
                }

                Locator quantityField = page.locator(QUANTITY.getLocator());
                quantityField.fill(quantities[i]);

                addItemButton = page.locator(ADD_ITEM_BUTTON.getLocator());
                addItemButton.click();

                if(i < itemNames.length - 1) {
                    addLineItemButton.click();
                } else {
                    break;
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Non-Catalog Requisition Items Function: " + error.getMessage());
        }
    }
}