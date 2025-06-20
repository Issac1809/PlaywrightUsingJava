package com.source.classes.requisitions.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.nio.file.Paths;
import java.util.*;
import static com.constants.requisitions.LPrCreate.*;

public class Create implements IPrCreate {

    Logger logger;
    PlaywrightFactory playwrightFactory;
    ObjectMapper objectMapper;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    double randomNumber;
    String appUrl;

//TODO Constructor
    public Create(PlaywrightFactory playwrightFactory, ObjectMapper objectMapper, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.logger = LoggerUtil.getLogger(Create.class);
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public void requesterLoginPRCreate() {
        try {
            String emailId = jsonNode.get("mailIds").get("requesterEmail").asText();
            iLogin.performLogin(emailId);
        } catch (Exception exception) {
            logger.error("Exception in Requester Login Function: {}", exception.getMessage());
        }
    }

    public void createButton() {
        try {
            Locator createButton = page.locator(CREATE_BUTTON);
            createButton.click();
        } catch (Exception exception) {
            logger.error("Exception in Create Button Function: {}", exception.getMessage());
        }
    }

    public void purchaseType(String type, String purchaseType) {
        try {
            String prTypeLocator;
            if(type.equalsIgnoreCase("sales")){
                prTypeLocator = getSalesPrType(purchaseType);
            } else if(type.equalsIgnoreCase("ps")){
                prTypeLocator = getPsType(purchaseType);
            } else {
                prTypeLocator = getSdPrType(purchaseType);
            }
            Locator prType = page.locator(prTypeLocator);
            prType.click();
        } catch (Exception exception) {
            logger.error("Exception in Purchase Type Function: {}", exception.getMessage());
        }
    }

    public synchronized void title(String type, String purchaseType) {
        try {
            String getTitle = jsonNode.get("requisition").get("orderTitle").asText();
            randomNumber = Math.round(Math.random() * 1000);
            String title = type.toUpperCase() + getTitle + "-" + purchaseType.toUpperCase() + "-" + randomNumber;
            Locator titleLocator = page.locator(TITLE);
            titleLocator.fill(title);

            String getType = "";

            if(type.equalsIgnoreCase("PS")) {
                getType = purchaseType.equalsIgnoreCase("catalog") ? "psCatalogTitle" : "psNonCatalogTitle";
            } else {
                getType = purchaseType.equalsIgnoreCase("catalog") ? "salesCatalogTitle" : "salesNonCatalogTitle";
            }

            playwrightFactory.savePropertiesIntoJsonFile("requisition", getType, title);
        } catch (Exception exception) {
            logger.error("Exception in Title Function: {}", exception.getMessage());
        }
    }

    public void shipToYokogawa() {
        try {
            String value = jsonNode.get("requisition").get("shipToYokogawa").asText().toLowerCase();
            if (value.equals("no")) {
                Locator shipToYokogawa = page.locator(SHIP_TO_YOKOGAWA);
                shipToYokogawa.click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Ship To Yokogawa Function: {}", exception.getMessage());
        }
    }

    public List<String> project() {
        List<String> wbsValues = new ArrayList<>();
        try {
            String projectCodeValue = jsonNode.get("requisition").get("projectCode").asText();

            Locator project = page.locator(PROJECT);
            project.click();

            Locator projectValue = page.locator(PROJECT_SEARCH);
            projectValue.fill(projectCodeValue);

            String projectSelectLocator = getLocator(projectCodeValue);
            Locator projectSelect = page.locator(projectSelectLocator);
            projectSelect.click();

            APIResponse projectResponse = page.request().fetch(appUrl + "/api/Projects/searchByUserId?keyword=" + projectCodeValue, RequestOptions.create());
            JsonNode projectCodeJson = objectMapper.readTree(projectResponse.body());
            JsonNode firstProjectObject = projectCodeJson.get(0);
            String projectId = firstProjectObject.get("id").asText();

            APIResponse wbsResponse = page.request().fetch(appUrl + "/api/workBreakdownStructures/search?projectid=" + projectId, RequestOptions.create());
            JsonNode wbsCodeJson = objectMapper.readTree(wbsResponse.body());

            for(JsonNode wbs : wbsCodeJson){
                if(wbs.has("text")){
                    wbsValues.add(wbs.get("text").asText());
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Project Function: {}", exception.getMessage());
        }
        return wbsValues;
    }

    public List<String> salesOrder() {
        List<String> serviceOrders = new ArrayList<>();
        try {
            String salesOrderValue = jsonNode.get("requisition").get("salesOrder").asText();

            Locator salesOrder = page.locator(SALES_ORDER);
            salesOrder.click();

            Locator salesOrderLocator = page.locator(SALES_ORDER_SEARCH);
            salesOrderLocator.fill(salesOrderValue);

            String salesOrderSelectLocator = getLocator(salesOrderValue);
            Locator salesOrderSelect = page.locator(salesOrderSelectLocator);
            salesOrderSelect.click();

            APIResponse salesOrderResponse = page.request().fetch(appUrl + "/api/SalesOrders/search?keyword=" + salesOrderValue, RequestOptions.create());
            JsonNode salesOrderJson = objectMapper.readTree(salesOrderResponse.body());
            JsonNode firstSalesOrderObject = salesOrderJson.get(0);
            String salesOrderId = firstSalesOrderObject.get("id").asText();
            String companyId = firstSalesOrderObject.get("companyId").asText();

            APIResponse departmentPicResponse = page.request().fetch(appUrl + "/api/GetDepartmentPICsByCompanyId?companyId=" + companyId, RequestOptions.create());
            JsonNode departmentPicJson = objectMapper.readTree(departmentPicResponse.body());

            for(JsonNode departmentPic : departmentPicJson){
                if(departmentPic.has("picName")){
                    String departmentPicMail = departmentPic.get("picName").asText();
                    playwrightFactory.savePropertiesIntoJsonFile("requisition", "sdDepartmentPic", departmentPicMail);
                }
            }

            APIResponse serviceOrderResponse = page.request().fetch(appUrl + "/api/ServiceOrders/search?salesOrderId=" + salesOrderId, RequestOptions.create());
            JsonNode serviceOrderJson = objectMapper.readTree(serviceOrderResponse.body());

            for(JsonNode serviceOrder : serviceOrderJson){
                if(serviceOrder.has("text")){
                    serviceOrders.add(serviceOrder.get("text").asText());
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Sales Order Function: {}", exception.getMessage());
        }
        return serviceOrders;
    }

    public void serviceOrder(List<String> serviceOrders) {
        try {
            String serviceOrdersFromProperties = jsonNode.get("requisition").get("serviceOrder").asText();

            for(String getServiceOrders : serviceOrders) {
                if (getServiceOrders.equals(serviceOrdersFromProperties)) {
                    Locator serviceOrderLocator = page.locator(SERVICE_ORDER);
                    serviceOrderLocator.click();

                    Locator serviceOrderSearch = page.locator(SERVICE_ORDER_SEARCH);
                    serviceOrderSearch.fill(serviceOrdersFromProperties);

                    String serviceOrderSelectLocator = getLocator(serviceOrdersFromProperties);
                    Locator serviceOrderSelect = page.locator(serviceOrderSelectLocator);
                    serviceOrderSelect.click();
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Service Order Function: {}", exception.getMessage());
        }
    }

    public void departmentPic(){
        try {
            String departmentPic = jsonNode.get("requisition").get("departmentPic").asText();

            Locator departmentPicLocator = page.locator(DEPT_PIC);
            departmentPicLocator.click();

            Locator departmentPicSearch = page.locator(DEPT_PIC_SEARCH);
            departmentPicSearch.fill(departmentPic);

            String departmentPicSelectLocator = getLocator(departmentPic);
            Locator departmentPicSelect = page.locator(departmentPicSelectLocator);
            departmentPicSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Department PIC Function: {}", exception.getMessage());
        }
    }

    public void wbs(List<String> wbs) {
        try {
            String wbsFromProperties = jsonNode.get("requisition").get("wbsCode").asText();

            for(String getWbs : wbs) {
                if (getWbs.equals(wbsFromProperties)) {
                    Locator wbsLocator = page.locator(WBS);
                    wbsLocator.click();

                    Locator wbsSearch = page.locator(WBS_SEARCH);
                    wbsSearch.fill(wbsFromProperties);

                    String wbsSelectLocator = getLocator(wbsFromProperties);
                    Locator wbsSelect = page.locator(wbsSelectLocator);
                    wbsSelect.click();
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in WBS Function: {}", exception.getMessage());
        }
    }

    public void company() {
        try {
            String company = jsonNode.get("requisition").get("companyName").asText();

            Locator companyLocator = page.locator(COMPANY);
            companyLocator.click();

            Locator companySearch = page.locator(COMPANY_SEARCH);
            companySearch.fill(company);

            String companySelectLocator = getLocator(company);
            Locator companySelect = page.locator(companySelectLocator);
            companySelect.click();
            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Exception in Company Function: {}", exception.getMessage());
        }
    }

    public void billableToCustomer() {
        try {
            String billableTocustomer = jsonNode.get("requisition").get("billableTocustomer").asText();

            Locator billableTocustomerLocator = page.locator(BILLABLE_TO_CUSTOMER);
            billableTocustomerLocator.click();

            Locator billableTocustomerSearch = page.locator(BILLABLE_TO_CUSTOMER_SEARCH);
            billableTocustomerSearch.fill(billableTocustomer);

            String billableTocustomerSelectLocator = getLocator(billableTocustomer);
            Locator billableTocustomerSelect = page.locator(billableTocustomerSelectLocator);
            billableTocustomerSelect.click();
            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Exception in Billable To Customer Function: {}", exception.getMessage());
        }
    }

    public void caseMarking() {
        try {
            String caseMarking = jsonNode.get("requisition").get("caseMarking").asText();

            Locator caseMarkingLocator = page.locator(CASE_MARKING);
            caseMarkingLocator.click();
            caseMarkingLocator.fill(caseMarking);
            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Exception in Case Marking Function: {}", exception.getMessage());
        }
    }

    public void messageToSourcing() {
        try {
            String messageToSourcing = jsonNode.get("requisition").get("messageToSourcing").asText();

            Locator messageToSourcingLocator = page.locator(MESSAGE_TO_SOURCING);
            messageToSourcingLocator.click();
            messageToSourcingLocator.fill(messageToSourcing);
            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Exception in Message To Sourcing Function: {}", exception.getMessage());
        }
    }

    public void businessUnit() {
        try {
            String businessUnit = jsonNode.get("requisition").get("businessUnit").asText();

            Locator businessUnitLocator = page.locator(BUSINESS_UNIT);
            businessUnitLocator.click();

            Locator businessUnitSearch = page.locator(BUSINESS_UNIT_SEARCH);
            businessUnitSearch.fill(businessUnit);

            String businessUnitSelectLocator = getLocator(businessUnit);
            Locator businessUnitSelect = page.locator(businessUnitSelectLocator);
            businessUnitSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Business Unit Function: {}", exception.getMessage());
        }
    }

    public void salesReferenceId() {
        try {
            String salesReferenceId = jsonNode.get("requisition").get("salesReferenceId").asText();

            Locator salesReferenceIdLocator = page.locator(SALES_REFERENCE_ID);
            salesReferenceIdLocator.fill(salesReferenceId);
        } catch (Exception exception) {
            logger.error("Exception in Sales Reference Id Function: {}", exception.getMessage());
        }
    }

    public void incoterm() {
        try {
            Locator incotermLocator = page.locator(INCOTERM);
            incotermLocator.click();

            String incotermValue = jsonNode.get("requisition").get("incoterm").asText();
            Locator incotermSearch = page.locator(INCOTERM_SEARCH);
            incotermSearch.fill(incotermValue);

            String incotermOptionLocator = getLocator(incotermValue);
            Locator incotermSelect = page.locator(incotermOptionLocator);
            incotermSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Incoterm Function: {}", exception.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
            String liquidatedDamages = jsonNode.get("requisition").get("liquidatedDamages").asText();

            if (liquidatedDamages.equalsIgnoreCase("special")) {
                Locator liquidatedDamagesLocator = page.locator(LIQUIDATED_DAMAGES_SELECT);
                liquidatedDamagesLocator.click();
                Locator liquidatedDamagesRemarksLocator = page.locator(LIQUIDATED_DAMAGES);
                liquidatedDamagesRemarksLocator.fill("Special Liquidated Damages");
            }
        } catch (Exception exception) {
            logger.error("Exception in Liquidated Damages Function: {}", exception.getMessage());
        }
    }

    public void warrantyRequirements(String type){
        try {
            String warrantyRequirement = jsonNode.get("requisition").get("warrantyRequirement").asText();

            if(type.equalsIgnoreCase("PS") || type.equalsIgnoreCase("SD")) {
                Locator warrantyRequirements = page.locator(WARRANTY_REQUIREMENTS);
                warrantyRequirements.click();
            } else {
                Locator salesWarrantyRequirements = page.locator(SALES_WARRANTY_REQUIREMENTS);
                salesWarrantyRequirements.click();
            }
            Locator warrantyRequirementsSearch = page.locator(WARRANTY_REQUIREMENTS_SEARCH);
            warrantyRequirementsSearch.fill(warrantyRequirement);

            String warrantyRequirementSelector = getLocator(warrantyRequirement);
            Locator warrantyRequirementSelect = page.locator(warrantyRequirementSelector);
            warrantyRequirementSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Warranty Requirements Function: {}", exception.getMessage());
        }
    }

    public void priceValidity(String type){
        try {
            String priceValidity = jsonNode.get("requisition").get("priceValidity").asText();

            if(type.equalsIgnoreCase("PS") || type.equalsIgnoreCase("SD")) {
                Locator priceValidityLocator = page.locator(PRICE_VALIDITY);
                priceValidityLocator.click();
            } else {
                Locator salesPriceValidity = page.locator(SALES_PRICE_VALIDITY);
                salesPriceValidity.click();
            }

                Locator priceValiditySearch = page.locator(PRICE_VALIDITY_SEARCH);
                priceValiditySearch.fill(priceValidity);

                String priceValiditySelector = getLocator(priceValidity);
                Locator priceValiditySelect = page.locator(priceValiditySelector);
                priceValiditySelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Price Validity Function: {}", exception.getMessage());
        }
    }

    public void shippingAddress() {
        try {
            String shipToYokogawa = jsonNode.get("requisition").get("shipToYokogawa").asText();
            String shippingAddressValue = jsonNode.get("requisition").get("shippingAddress").asText();
            String shippingAddressEnduser = jsonNode.get("requisition").get("shippingAddressEnduser").asText();

            if(shipToYokogawa.equalsIgnoreCase("yes")){
                Locator shippingAddressLocator = page.locator(SHIPPING_ADDRESS);
                shippingAddressLocator.click();

                String shippingAddressOptionLocator = getLocator(shippingAddressValue);

                Locator shippingAddressSelect = page.locator(shippingAddressOptionLocator);
                shippingAddressSelect.last().click();
            } else {
                Locator shippingAddressEnduserLocator = page.locator(SHIPPING_ADDRESS_ENDUSERS_OR_OTHERS);
                shippingAddressEnduserLocator.click();

                Locator shipingAddressEndUserSearchLocator = page.locator(SHIPPING_ADDRESS_ENDUSERS_SEARCH);
                shipingAddressEndUserSearchLocator.fill(shippingAddressEnduser);

                String shippingAddressEnduserOptionLocator = getLocator(shippingAddressEnduser);
                Locator shippingAddressEnduserSelect = page.locator(shippingAddressEnduserOptionLocator);
                shippingAddressEnduserSelect.click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Shipping Address Function: {}", exception.getMessage());
        }
    }

    public void shippingMode(String purchaseType) {
        try {
            String getShippingMode = jsonNode.get("requisition").get("shippingMode").asText();

            Locator shippingModeLocator = page.locator(SHIPPING_MODE);
            shippingModeLocator.click();

            Locator shippingModeSearchLocator = page.locator(SHIPPING_MODE_SEARCH);
            shippingModeSearchLocator.fill(getShippingMode);

            String finalShippingMode = getLocator(getShippingMode);
            Locator shippingMode = page.locator(finalShippingMode);
            shippingMode.click();
        } catch (Exception exception) {
            logger.error("Exception in Shipping Mode Function: {}", exception.getMessage());
        }
    }

    public void quotationRequiredBy() {
        try {
            Locator quotationRequiredBy = page.locator(QUOTATION_REQUIRED_BY);
            quotationRequiredBy.click();

            Locator daysOfMonth = page.locator(DAYS_OF_MONTH);
            daysOfMonth.last().click();

            Locator expectedPoIssueLabelLocator = page.locator(EXPECTED_PO_ISSUE_LABEL);
            expectedPoIssueLabelLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Quotation Required By Function: {}", exception.getMessage());
        }
    }

    public void expectedPOIssue(String purchaseType) {
        try {
            Locator expectedPoIssueField;
            Locator todayOption;
            if(purchaseType.equalsIgnoreCase("catalog")){
                expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE_CATALOG);
            } else {
                expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE_NON_CATALOG);
            }
            todayOption = page.locator(DAYS_OF_NEXT_MONTH).first();
            expectedPoIssueField.click();
            todayOption.click();
        } catch (Exception exception) {
            logger.error("Exception in Expected PO Function: {}", exception.getMessage());
        }
    }

    public void expectedDelivery(String purchaseType) {
        try {
            Locator expectedDeliveryField;
            Locator todayOption;
            if(purchaseType.equalsIgnoreCase("catalog")){
                expectedDeliveryField = page.locator(EXPECTED_DELIVERY_CATALOG);
            } else {
                expectedDeliveryField = page.locator(EXPECTED_DELIVERY_NON_CATALOG);
            }
            todayOption = page.locator(DAYS_OF_MONTH).last();
            expectedDeliveryField.click();
            todayOption.click();
        } catch (Exception exception) {
            logger.error("Exception in Expected Delivery Function: {}", exception.getMessage());
        }
    }

    public void rohsCompliance(){
        try {
            String compliance = jsonNode.get("requisition").get("rohsCompliance").asText();

            if (compliance.equalsIgnoreCase("no")) {
                Locator rohsComplianceLocator = page.locator(ROHS_COMPLIANCE);
                rohsComplianceLocator.click();
            }
        } catch (Exception exception) {
            logger.error("Exception in RoHS Compliance Function: {}", exception.getMessage());
        }
    }

    public void inspectionRequired(String type, String purchaseType) {
        try {
            Locator inspectionRequiredLocator;
            String isInspectionRequired = jsonNode.get("requisition").get("inspectionRequired").asText();

            if (isInspectionRequired.equalsIgnoreCase("yes")) {
                if (purchaseType.equalsIgnoreCase("catalog")){
                    inspectionRequiredLocator = page.locator(CATALOG_INSPECTION_REQUIRED);
                } else if (type.equals("PS") || type.equals("SD")) {
                    inspectionRequiredLocator = page.locator(NON_CATALOG_INSPECTION_REQUIRED);
                } else {
                    inspectionRequiredLocator = page.locator(SALES_NON_CATALOG_INSPECTION_REQUIRED);
                }
                inspectionRequiredLocator.click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Inspection Required Function: {}", exception.getMessage());
        }
    }

    public void oiAndTpCurrency(){
        try {
            String currency = jsonNode.get("requisition").get("oiAndTpCurrency").asText();

            Locator oiAndTpCurrencyLocator = page.locator(OI_AND_TP_CURRENCY);
            oiAndTpCurrencyLocator.click();

            Locator oiAndTpCurrencySearchLocator = page.locator(OI_AND_TP_CURRENCY_SEARCH);
            oiAndTpCurrencySearchLocator.fill(currency);

            String currencySelect = getLocator(currency);
            Locator curremcySelectLocator = page.locator(currencySelect);
            curremcySelectLocator.click();

            Locator oiAndTpCurrencyLabelLocator = page.locator(OI_AND_TP_CURRENCY_LABEL);
            oiAndTpCurrencyLabelLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in OI/TP Currency Function: {}", exception.getMessage());
        }
    }

    public void orderIntake(String type){
        try {
            String orderIntake = jsonNode.get("requisition").get("orderIntake").asText();
            if(type.equalsIgnoreCase("PS") || type.equalsIgnoreCase("SD")) {
                Locator orderIntakeLocator = page.locator(ORDER_INTAKE);
                orderIntakeLocator.fill(orderIntake);
            } else {
                Locator salesOrderIntakeLocator = page.locator(SALES_ORDER_INTAKE);
                salesOrderIntakeLocator.fill(orderIntake);
            }
        } catch (Exception exception) {
            logger.error("Exception in Order Intake Function: {}", exception.getMessage());
        }
    }

    public void targetPrice(String type, String purchaseType){
        try {
            if (purchaseType.equalsIgnoreCase("NonCatalog")) {
                String targetPrice = jsonNode.get("requisition").get("targetPrice").asText();
                if(type.equalsIgnoreCase("PS") || type.equalsIgnoreCase("SD")) {
                    Locator targetPriceLocator = page.locator(TARGET_PRICE);
                    targetPriceLocator.fill(targetPrice);
                } else {
                    Locator salesTargetPriceLocator = page.locator(SALES_TARGET_PRICE);
                    salesTargetPriceLocator.fill(targetPrice);
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Target Price Function: {}", exception.getMessage());
        }
    }

    public void addLineRequisitionItemsNonCatalog() {
        try {
            boolean itemImport = jsonNode.get("requisition").get("itemImport").asBoolean();
            if(itemImport) {
                Locator importPopUpButton = page.locator(ITEM_IMPORT_POPUP);
                importPopUpButton.click();

                Locator itemFile = page.locator(CHOOSE_FILE);
                String filePath = jsonNode.get("configSettings").get("nonCatalogItemImportFilePath").asText();
                itemFile.setInputFiles(Paths.get(filePath));

                Locator uploadButton = page.locator(UPLOAD_BUTTON);
                uploadButton.click();
            } else {
                String idValue;
                List<String> inputTypes = new ArrayList<>();
                String[] itemNames = jsonNode.get("requisition").get("items").asText().split(",");
                String[] quantities = jsonNode.get("requisition").get("quantityList").asText().split(",");

                Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
                addLineItemButton.click();

                for (int i = 0; i < itemNames.length; i++) {
                    Locator nonCatalogItemsDropdownLocator = page.locator(NON_CATALOG_ITEMS_DROPDOWN);
                    nonCatalogItemsDropdownLocator.click();

                    Locator itemSearchLocator = page.locator(ITEM_SEARCH);
                    itemSearchLocator.fill(itemNames[i]);

                    String itemName = itemNames[i];
                    String encodedName = itemName.replace(" ", "%20");

                    APIResponse itemSpecificationResponse = page.request().fetch(appUrl + "/api/Itemcategory/search?keyword=" + encodedName + "&purchaseMethod=NonCatalog");
                    JsonNode itemSpecificationsObject = objectMapper.readTree(itemSpecificationResponse.body());
                    idValue = itemSpecificationsObject.get(0).get("id").asText();

                    Locator itemNameLocator = page.locator(getLocator(itemNames[i]));
                    itemNameLocator.first().click();

                    APIResponse getItemSpecifications = page.request().fetch(appUrl + "/api/Items/Spefications?itemId=" + idValue);
                    JsonNode getItemSpecificationsJson = objectMapper.readTree(getItemSpecifications.body());

                    if(!getItemSpecificationsJson.isNull()){
                        for(int j = 0; j < getItemSpecificationsJson.size(); j++){
                            JsonNode item = getItemSpecificationsJson.get(j);
                            if(item.has("inputType")) {
                                inputTypes.add(item.get("inputType").asText());
                            }
                        }

                        for(String inputType : inputTypes){
                            switch (inputType) {
                                case "Text" -> {
                                    List<Locator> textFields = page.locator(ITEM_SPECIFICATIONS_TEXT_FIELD_LOCATORS).all();
                                    for (Locator textField : textFields) {
                                        String idLocator = textField.getAttribute("id");
                                        Locator textFieldLocator = page.locator("#" + idLocator);
                                        if (textFieldLocator.isEnabled()) {
                                            textFieldLocator.fill("2000");
                                        }
                                    }
                                }
                                case "Selection" -> {
                                    List<Locator> selectionFields = page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_LOCATORS).all();
                                    for (Locator selectionField : selectionFields) {
                                        String idLocator = selectionField.getAttribute("id");
                                        Locator selectionFieldLocator = page.locator("#" + idLocator);
                                        if (selectionFieldLocator.isEnabled()) {
                                            selectionFieldLocator.click();
                                            Locator itemSpecificationLocator = page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_RESULT_LOCATOR);
                                            itemSpecificationLocator.click();
                                        }
                                    }
                                }
                                case "CheckBox" -> {
                                    List<Locator> checkBoxFields = page.locator(ITEM_SPECIFICATIONS_CHECKBOX_FIELD_LOCATORS).all();
                                    for (Locator checkBoxField : checkBoxFields) {
                                        String idLocator = checkBoxField.getAttribute("id");
                                        Locator checkBoxFieldLocator = page.locator("#" + idLocator);
                                        if (checkBoxFieldLocator.isEnabled()) {
                                            checkBoxFieldLocator.click();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Locator quantity = page.locator(QUANTITY);
                    quantity.fill(quantities[i]);

                    String shippingPoint = jsonNode.get("requisition").get("shippingPoint").asText();
                    Locator shippingPointLocator = page.locator(SHIPPING_POINT_LOCATOR);
                    shippingPointLocator.click();

                    Locator shippingPointSearchField = page.locator(SHIPPING_POINT_SEARCH_FIELD);
                    shippingPointSearchField.fill(shippingPoint);

                    String shippingPointOptionLocator = getLocator(shippingPoint);
                    Locator shippingPointOptionSelect = page.locator(shippingPointOptionLocator);
                    shippingPointOptionSelect.last().click();

                    Locator addItemButtonLocator = page.locator(ADD_ITEM_BUTTON);
                    addItemButtonLocator.click();

                    if (i < itemNames.length - 1) {
                        addLineItemButton.click();
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Non-Catalog Requisition Items IMPORT Function: {}", exception.getMessage());
        }
    }

    public Map<String, String> vendor() {
        Map<String, String> rateContractArray = new HashMap<>();
        try {
            String vendorNameValue = jsonNode.get("requisition").get("vendorName").asText();
            Locator vendorLocator = page.locator(VENDOR);
            vendorLocator.click();

            APIResponse vendorApiResponse = page.request().fetch(appUrl + "/api/Vendors/GetAllVendorsByKeyword/1?keyword=" + vendorNameValue, RequestOptions.create());
            JsonNode vendorJson = objectMapper.readTree(vendorApiResponse.body());
            JsonNode vendorIdJson = vendorJson.get(0);
            int vendorId = vendorIdJson.get("id").asInt();

            Locator vendorSearchLocator = page.locator(VENDOR_SEARCH);
            vendorSearchLocator.fill(vendorNameValue);

            String vendorOptionLocator = getLocator(vendorNameValue);
            Locator vendorOptionSelectLocator = page.locator(vendorOptionLocator);
            vendorOptionSelectLocator.click();

            APIResponse rateContractApiResponse = page.request().fetch(appUrl + "/api/RateContractsByVendorIdandCompany?vendorId=" + vendorId, RequestOptions.create());
            JsonNode rateContractJson = objectMapper.readTree(rateContractApiResponse.body());

            for(JsonNode rateContract : rateContractJson){
                if(rateContract.has("id") && rateContract.has("text")){
                    String rateContractId = rateContract.get("id").asText();
                    String rateContractText = rateContract.get("text").asText();
                    rateContractArray.put(rateContractId, rateContractText);
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Vendor Function: {}", exception.getMessage());
        }
        return rateContractArray;
    }

    public List<String> rateContract(Map<String, String> rateContractArray) {
        List<String> rateContractItems = new ArrayList<>();
        try {
            String rateContractValue = jsonNode.get("requisition").get("rateContract").asText();

            Locator rateContractLocator = page.locator(RATE_CONTRACT);
            rateContractLocator.click();

            for(Map.Entry<String, String> rateContract : rateContractArray.entrySet()){
                if(rateContract.getValue().equals(rateContractValue)){
                    String rateContractId = rateContract.getKey();
                    Locator rateContractSearchLocator = page.locator(RATE_CONTRACT_SEARCH);
                    rateContractSearchLocator.fill(rateContractValue);

                    String rateContractOptionLocator = getLocator(rateContractValue);
                    Locator rateContractOptionSelectLocator = page.locator(rateContractOptionLocator);
                    rateContractOptionSelectLocator.click();

                    APIResponse rateContractResponse = page.request().fetch(appUrl + "/api/RateContracts/ratecontract?rateContractId=" + rateContractId, RequestOptions.create());
                    JsonNode rateContractJsonResponse = objectMapper.readTree(rateContractResponse.body());
                    JsonNode itemsArray = rateContractJsonResponse.get("items");
                    for(JsonNode item : itemsArray){
                        if(item.has("name")){
                            rateContractItems.add(item.get("name").asText());
                        }
                    }
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Rate Contract Function: {}", exception.getMessage());
        }
        return rateContractItems;
    }

    public void buyerManager(){
        try {
            String buyerManagerName = jsonNode.get("mailIds").get("buyerManagerEmail").asText();
            Locator buyerManager = page.locator(BUYER_MANAGER);
            buyerManager.click();

            Locator buyerManagerSearch = page.locator(BUYER_MANAGER_SEARCH);
            buyerManagerSearch.fill(buyerManagerName);

            String buyerManagerLocator = getLocator(buyerManagerName);
            Locator buyerManagerSelectLocator = page.locator(buyerManagerLocator);
            buyerManagerSelectLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Buyer Manager Function: {}", exception.getMessage());
        }
    }

    public void projectManager(){
        try {
            String projectManagerName = jsonNode.get("mailIds").get("projectManagerEmail").asText();
            Locator projectManager = page.locator(PROJECT_MANAGER);
            projectManager.click();

            Locator projectManagerSearch = page.locator(PROJECT_MANAGER_SEARCH);
            projectManagerSearch.fill(projectManagerName);

            String projectManagerLocator = getLocator(projectManagerName);
            Locator projectManagerSelectLocator = page.locator(projectManagerLocator);
            projectManagerSelectLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Project Manager Function: {}", exception.getMessage());
        }
    }

    public void tcasCompliance(){
        String tcasCompliance = jsonNode.get("requisition").get("tcasComplianceApplicable").asText();
        String[] tcasQuestionNumbers = jsonNode.get("requisition").get("tcasQuestionNumber").asText().split(",");
        try {
            if(tcasCompliance.equalsIgnoreCase("yes")){
                Locator tcasComplianceApplicable = page.locator(TCAS_COMPLIANCE_APPLICABLE);
                tcasComplianceApplicable.click();

                for(String tcasQuestionNumber : tcasQuestionNumbers){
                    Locator tcasQuestionNumberLocator = page.locator(TCAS_QUESTION_NUMBER + tcasQuestionNumber);
                    tcasQuestionNumberLocator.click();
                }

                Locator tcasAddButtonLocator = page.locator(TCAS_ADD_BUTTON);
                tcasAddButtonLocator.click();

                String tcasFilePath = jsonNode.get("configSettings").get("tcasFilePath").asText().trim();
                Locator tcasFileUploadButtonLocator = page.locator(TCAS_FILE_UPLOAD_BUTTON);
                tcasFileUploadButtonLocator.setInputFiles(Paths.get(tcasFilePath));
            }
        } catch (Exception exception) {
            logger.error("Exception in TCAS Function: {}", exception.getMessage());
        }
    }

    public void addLineRequisitionItemsCatalog(List<String> rateContractItems) {
        try {
            boolean itemImport = jsonNode.get("requisition").get("itemImport").asBoolean();
            if(itemImport){
                Locator importPopUpButton = page.locator(ITEM_IMPORT_POPUP);
                importPopUpButton.click();

                Locator itemFile = page.locator(CHOOSE_FILE);
                String filePath = jsonNode.get("configSettings").get("catalogItemImportFilePath").asText();
                itemFile.setInputFiles(Paths.get(filePath));

                Locator uploadButton = page.locator(UPLOAD_BUTTON);
                uploadButton.click();
            } else {
                Locator addLineItemButton;
                Locator addItemButton;

                addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
                addLineItemButton.click();

                for (int i = 0; i < rateContractItems.size(); i++) {
                    Locator itemDropDown = page.locator(CATALOG_ITEMS_DROPDOWN);
                    itemDropDown.click();

                    Locator itemSearch = page.locator(ITEM_SEARCH);
                    itemSearch.fill(rateContractItems.get(i));

                    String itemDropDownOptionSelect = getLocator(rateContractItems.get(i));
                    Locator itemSelect = page.locator(itemDropDownOptionSelect);
                    itemSelect.click();

                    Locator quantity = page.locator(QUANTITY);
                    quantity.fill(String.valueOf(i + 10000));

                    String shippingPoint = jsonNode.get("requisition").get("shippingPoint").asText();
                    Locator shippingPointLocator = page.locator(SHIPPING_POINT_LOCATOR);
                    shippingPointLocator.click();

                    Locator shippingPointSearch = page.locator(SHIPPING_POINT_SEARCH_FIELD);
                    shippingPointSearch.fill(shippingPoint);

                    String shippingPointOptionLocator = getLocator(shippingPoint);
                    Locator shippingPointOptionSelect = page.locator(shippingPointOptionLocator);
                    shippingPointOptionSelect.last().click();

                    addItemButton = page.locator(ADD_ITEM_BUTTON);
                    addItemButton.click();

                    if (i < rateContractItems.size() - 1) {
                        addLineItemButton.click();
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Catalog Requisition Items Function: {}", exception.getMessage());
        }
    }

    public void notes() {
        try {
            String notesText = jsonNode.get("requisition").get("requisitionNotes").asText();
            Locator notes = page.locator(NOTES);
            notes.fill(notesText);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Notes Function: {}", exception.getMessage());
        }
    }

    public void attachments(){
        try {
            String[] requisitionAttachmentsTypes = jsonNode.get("requisition").get("requisitionAttachmentsTypes").asText().split(",");
            String internalFilePath = jsonNode.get("configSettings").get("internalAttachmentFilePath").asText();
            String externalFilePath = jsonNode.get("configSettings").get("externalAttachmentFilePath").asText();

            Locator attachmentsButton = page.locator(ATTACHMENTS);
            attachmentsButton.click();

            for (String requisitionAttachmentsType : requisitionAttachmentsTypes) {
                if (requisitionAttachmentsType.equalsIgnoreCase("internal")) {
                    Locator fileUpload = page.locator(FILE_UPLOAD);
                    fileUpload.setInputFiles(Paths.get(internalFilePath));

                    Locator attachFileButton = page.locator(ATTACH_FILE_BUTTON);
                    attachFileButton.click();
                } else if (requisitionAttachmentsType.equalsIgnoreCase("external")) {
                    Locator fileUpload = page.locator(FILE_UPLOAD);
                    fileUpload.setInputFiles(Paths.get(externalFilePath));

                    Locator externalRadioButton = page.locator(EXTERNAL_RADIO_BUTTON);
                    externalRadioButton.click();

                    Locator attachFileButton = page.locator(ATTACH_FILE_BUTTON);
                    attachFileButton.click();
                }
            }
            Locator continueButton = page.locator(CONTINUE_BUTTON);
            continueButton.click();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Attachments Function: {}", exception.getMessage());
        }
    }

    public int prCreate(String type, String purchaseType) {
        int status = 0;
        try {
            Locator createDraftButton = page.locator(CREATE_DRAFT_BUTTON);
            createDraftButton.click();

            Locator yesButton = page.locator(YES);

            String reqType;
            if(type.equalsIgnoreCase("sales")){
                reqType = "/api/RequisitionsSales/";
            } else if(type.equalsIgnoreCase("ps")){
                reqType = "/api/Requisitions/";
            } else {
                reqType = "/api/RequisitionsNonPoc/";
            }

            Response statusResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                    yesButton::click
            );

            JsonNode response = objectMapper.readTree(statusResponse.body());

            String requisitionStatus = "";
            if(response.has("status")){
                requisitionStatus = response.get("status").asText();
            }

            playwrightFactory.savePropertiesIntoJsonFile("requisition", "requisitionStatus", requisitionStatus);

            status = statusResponse.status();

            String url = page.url();
            String[] urlArray = url.split("=");
            String getUid = urlArray[1];
            playwrightFactory.savePropertiesIntoJsonFile("requisition", "requisitionUid", getUid);
            playwrightFactory.savePropertiesIntoJsonFile("requisition", "purchaseType", type);

            //Save Transaction number
            if (type.equalsIgnoreCase("sales"))
            {
                APIResponse apiResponse = page.request().fetch(appUrl + "/api/RequisitionsSales/" + getUid, RequestOptions.create());
                JsonNode jsonNode1 = objectMapper.readTree(apiResponse.body());
                String requisitionId = jsonNode1.get("requisitionId").asText();
                String transactionId = jsonNode1.get("transactionId").asText();
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "requisitionId", requisitionId);
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "salesTransactionNumber", transactionId);
            } else if(type.equalsIgnoreCase("PS")) {
                APIResponse apiResponse = page.request().fetch(appUrl + "/api/Requisitions/" + getUid, RequestOptions.create());
                JsonNode jsonNode1 = objectMapper.readTree(apiResponse.body());
                String requisitionId = jsonNode1.get("requisitionId").asText();
                String transactionId = jsonNode1.get("transactionId").asText();
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "requisitionId", requisitionId);
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "psTransactionNumber", transactionId);
            } else {
                APIResponse apiResponse = page.request().fetch(appUrl + "/api/RequisitionsNonPoc/" + getUid, RequestOptions.create());
                JsonNode jsonNode1 = objectMapper.readTree(apiResponse.body());
                String requisitionId = jsonNode1.get("requisitionId").asText();
                String transactionId = jsonNode1.get("transactionId").asText();
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "requisitionId", requisitionId);
                playwrightFactory.savePropertiesIntoJsonFile("requisition", "sdTransactionNumber", transactionId);
            }

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Requisition Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Create Function: {}", exception.getMessage());
        }
        return status;
    }
}