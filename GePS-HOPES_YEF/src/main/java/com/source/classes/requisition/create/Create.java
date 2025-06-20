package com.source.classes.requisition.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
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
    Playwright playwright;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    double randomNumber;

    private Create(){
    }

//TODO Constructor
    public Create(PlaywrightFactory playwrightFactory, ObjectMapper objectMapper, Playwright playwright, ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.logger = LoggerUtil.getLogger(Create.class);
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.playwright = playwright;
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void requesterLoginPRCreate() {
        try {
            String emailId = properties.getProperty("requesterEmail");
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
            } else {
                prTypeLocator = getPsType(purchaseType);
            }
            Locator prType = page.locator(prTypeLocator);
            prType.click();
        } catch (Exception exception) {
            logger.error("Exception in Purchase Type Function: {}", exception.getMessage());
        }
    }

    public void title(String type, String purchaseType) {
        try {
            String getTitle = properties.getProperty("orderTitle");
            randomNumber = Math.round(Math.random() * 1000);
            String title = type.toUpperCase() + getTitle + "-" + purchaseType.toUpperCase() + "-" + randomNumber;
            Locator titleLocator = page.locator(TITLE);
            titleLocator.fill(title);
            playwrightFactory.saveToPropertiesFile("title", title);
        } catch (Exception exception) {
            logger.error("Exception in Title Function: {}", exception.getMessage());
        }
    }

    public void shipToYokogawa() {
        try {
            String value = properties.getProperty("shipToYokogawa").toLowerCase();
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
            String projectCodeValue = properties.getProperty("projectCode");

            Locator project = page.locator(PROJECT);
            project.click();

            Locator projectValue = page.locator(PROJECT_SEARCH);
            projectValue.fill(projectCodeValue);

            String projectSelectLocator = getProject(projectCodeValue);
            Locator projectSelect = page.locator(projectSelectLocator);
            projectSelect.click();

            APIResponse projectResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Projects/searchByUserId?keyword=" + projectCodeValue, RequestOptions.create());
            JsonNode projectCodeJson = objectMapper.readTree(projectResponse.body());
            JsonNode firstProjectObject = projectCodeJson.get(0);
            String projectId = firstProjectObject.get("id").asText();

            APIResponse wbsResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/workBreakdownStructures/search?projectid=" + projectId, RequestOptions.create());
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

    public void wbs(List<String> wbs) {
        try {
            String wbsFromProperties = properties.getProperty("wbsCode");

            for(String getWbs : wbs) {
                if (getWbs.equals(wbsFromProperties)) {
                    Locator wbsLocator = page.locator(WBS);
                    wbsLocator.click();

                    Locator wbsSearch = page.locator(WBS_SEARCH);
                    wbsSearch.fill(wbsFromProperties);

                    String wbsSelectLocator = getWBS(wbsFromProperties);
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
            String company = properties.getProperty("companyName");

            Locator companyLocator = page.locator(COMPANY);
            companyLocator.click();

            Locator companySearch = page.locator(COMPANY_SEARCH);
            companySearch.fill(company);

            String companySelectLocator = getCompany(company);
            Locator companySelect = page.locator(companySelectLocator);
            companySelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Company Function: {}", exception.getMessage());
        }
    }

    public void businessUnit() {
        try {
            String businessUnit = properties.getProperty("businessUnit");

            Locator businessUnitLocator = page.locator(BUSINESS_UNIT);
            businessUnitLocator.click();

            Locator businessUnitSearch = page.locator(BUSINESS_UNIT_SEARCH);
            businessUnitSearch.fill(businessUnit);

            String businessUnitSelectLocator = getbusinessUnit(businessUnit);
            Locator businessUnitSelect = page.locator(businessUnitSelectLocator);
            businessUnitSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Business Unit Function: {}", exception.getMessage());
        }
    }

    public void salesReferenceId() {
        try {
            String salesReferenceId = properties.getProperty("salesReferenceId");

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

            String incotermValue = properties.getProperty("incoterm");
            Locator incotermSearch = page.locator(INCOTERM_SEARCH);
            incotermSearch.fill(incotermValue);

            String incotermOptionLocator = getIncoterm(incotermValue);
            Locator incotermSelect = page.locator(incotermOptionLocator);
            incotermSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Incoterm Function: {}", exception.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
            String liquidatedDamages = properties.getProperty("liquidatedDamages");

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

    public void warrantyRequirements(){
        try {
            String warrantyRequirement = properties.getProperty("warrantyRequirement");

            Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS);
            warrantyRequirementsLocator.click();

            Locator warrantyRequirementsSearch = page.locator(WARRANTY_REQUIREMENTS_SEARCH);
            warrantyRequirementsSearch.fill(warrantyRequirement);

            String warrantyRequirementSelector = getWarrantyRequirements(warrantyRequirement);
            Locator warrantyRequirementSelect = page.locator(warrantyRequirementSelector);
            warrantyRequirementSelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Warranty Requirements Function: {}", exception.getMessage());
        }
    }

    public void priceValidity(){
        try {
            String priceValidity = properties.getProperty("priceValidity");

            Locator priceValidityLocator = page.locator(PRICE_VALIDITY);
            priceValidityLocator.click();

            Locator priceValiditySearch = page.locator(PRICE_VALIDITY_SEARCH);
            priceValiditySearch.fill(priceValidity);

            String priceValiditySelector = getPriceValidity(priceValidity);
            Locator priceValiditySelect = page.locator(priceValiditySelector);
            priceValiditySelect.click();
        } catch (Exception exception) {
            logger.error("Exception in Price Validity Function: {}", exception.getMessage());
        }
    }

    public void shippingAddress() {
        try {
            String shipToYokogawa = properties.getProperty("shipToYokogawa");
            String shippingAddressValue = properties.getProperty("shippingAddress");
            String shippingAddressEnduser = properties.getProperty("shippingAddressEnduser");

            if(shipToYokogawa.equalsIgnoreCase("yes")){
                page.locator(SHIPPING_ADDRESS).click();
                String shippingAddressOptionLocator = getShippingAddress(shippingAddressValue);
                page.locator(shippingAddressOptionLocator).last().click();
            } else {
                page.locator(SHIPPING_ADDRESS_ENDUSERS_OR_OTHERS).click();
                page.locator(SHIPPING_ADDRESS_ENDUSERS_SEARCH).fill(shippingAddressEnduser);
                String shippingAddressEnduserOptionLocator = getShippingAddressEnduser(shippingAddressEnduser);
                page.locator(shippingAddressEnduserOptionLocator).click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Shipping Address Function: {}", exception.getMessage());
        }
    }

    public void shippingMode(String purchaseType) {
        try {
            String getShippingMode = properties.getProperty("shippingMode");

            page.locator(SHIPPING_MODE).click();
            page.locator(SHIPPING_MODE_SEARCH).fill(getShippingMode);
            String finalShippingMode = getShippingMode(getShippingMode);
            page.locator(finalShippingMode).click();
        } catch (Exception exception) {
            logger.error("Exception in Shipping Mode Function: {}", exception.getMessage());
        }
    }

    public void quotationRequiredBy() {
        try {
            page.locator(QUOTATION_REQUIRED_BY).click();
            page.locator(TODAY + "[1]").first().click();
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
                todayOption = page.locator(TODAY + "[1]");
            } else {
                expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE_NON_CATALOG);
                todayOption = page.locator(TODAY + "[2]");
            }
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
                todayOption = page.locator(TODAY + "[2]");
            } else {
                expectedDeliveryField = page.locator(EXPECTED_DELIVERY_NON_CATALOG);
                todayOption = page.locator(TODAY + "[3]");
            }
            expectedDeliveryField.click();
            todayOption.click();
        } catch (Exception exception) {
            logger.error("Exception in Expected Delivery Function: {}", exception.getMessage());
        }
    }

    public void rohsCompliance(){
        try {
            String compliance = properties.getProperty("rohsCompliance");

            if (compliance.equalsIgnoreCase("no")) {
                page.locator(ROHS_COMPLIANCE).click();
            }
        } catch (Exception exception) {
            logger.error("Exception in RoHS Compliance Function: {}", exception.getMessage());
        }
    }

    public void inspectionRequired(String purchaseType) {
        try {
            Locator inspectionRequiredLocator;
            String isInspectionRequired = properties.getProperty("inspectionRequired");

            if (isInspectionRequired.equalsIgnoreCase("yes")) {
                if (purchaseType.equalsIgnoreCase("catalog")){
                    inspectionRequiredLocator = page.locator(CATALOG_INSPECTION_REQUIRED);
                } else {
                    inspectionRequiredLocator = page.locator(NON_CATALOG_INSPECTION_REQUIRED);
                }
                inspectionRequiredLocator.click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Inspection Required Function: {}", exception.getMessage());
        }
    }

    public void oiAndTpCurrency(){
        try {
            String currency = properties.getProperty("oiAndTpCurrency");

            page.locator(OI_AND_TP_CURRENCY).click();
            page.locator(OI_AND_TP_CURRENCY_SEARCH).fill(currency);

            String currencySelect = getOiAndTpCurrency(currency);
            page.locator(currencySelect).click();
        } catch (Exception exception) {
            logger.error("Exception in OI/TP Currency Function: {}", exception.getMessage());
        }
    }

    public void orderIntake(){
        try {
            String orderIntake = properties.getProperty("orderIntake");
            page.locator(ORDER_INTAKE).fill(orderIntake);
        } catch (Exception exception) {
            logger.error("Exception in Order Intake Function: {}", exception.getMessage());
        }
    }

    public void targetPrice(String purchaseType){
        try {
            if (purchaseType.equalsIgnoreCase("NonCatalog")) {
                String targetPrice = properties.getProperty("targetPrice");
                page.locator(TARGET_PRICE).fill(targetPrice);
            }
        } catch (Exception exception) {
            logger.error("Exception in Target Price Function: {}", exception.getMessage());
        }
    }

    public void addLineRequisitionItemsNonCatalog() {
        try {
            String idValue;
            List<String> inputTypes = new ArrayList<>();
            String[] itemNames = properties.getProperty("items").split(",");
            String[] quantities = properties.getProperty("quantityList").split(",");

            Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
            addLineItemButton.click();

            for (int i = 0; i < itemNames.length; i++) {
                page.locator(NON_CATALOG_ITEMS_DROPDOWN).click();
                page.locator(ITEM_SEARCH).fill(itemNames[i]);

                String itemName = itemNames[i];
                String encodedName = itemName.replace(" ", "%20");

                APIResponse itemSpecificationResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Itemcategory/search?keyword=" + encodedName + "&purchaseMethod=NonCatalog");
                JsonNode itemSpecificationsObject = objectMapper.readTree(itemSpecificationResponse.body());
                idValue = itemSpecificationsObject.get(0).get("id").asText();

                page.locator(getItem(itemNames[i])).first().click();

                APIResponse getItemSpecifications = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Items/Spefications?itemId=" + idValue);
                JsonNode getItemSpecificationsJson = objectMapper.readTree(getItemSpecifications.body());

                if(!getItemSpecificationsJson.isNull()){
                    for(int j = 0; j < getItemSpecificationsJson.size(); j++){
                        JsonNode item = getItemSpecificationsJson.get(j);
                        if(item.has("inputType")) {
                            inputTypes.add(item.get("inputType").asText());
                        }
                    }

                    for(String inputType : inputTypes){
                        if(inputType.equals("Text")){
                            List<Locator> textFields = page.locator(ITEM_SPECIFICATIONS_TEXT_FIELD_LOCATORS).all();
                            for(Locator textField : textFields){
                                String idLocator = textField.getAttribute("id");
                                Locator textFieldLocator = page.locator("#" + idLocator);
                                if(textFieldLocator.isEnabled()){
                                    textFieldLocator.fill("2000");
                                }
                            }
                        } else if(inputType.equals("Selection")){
                            List<Locator> selectionFields = page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_LOCATORS).all();
                            for(Locator selectionField : selectionFields){
                                String idLocator = selectionField.getAttribute("id");
                                Locator selectionFieldLocator = page.locator("#" + idLocator);
                                if(selectionFieldLocator.isEnabled()){
                                    selectionFieldLocator.click();
                                    page.locator(ITEM_SPECIFICATIONS_SELECTION_FIELD_RESULT_LOCATOR).click();
                                }
                            }
                        } else if(inputType.equals("CheckBox")){
                            List<Locator> checkBoxFields = page.locator(ITEM_SPECIFICATIONS_CHECKBOX_FIELD_LOCATORS).all();
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

                page.locator(QUANTITY).fill(quantities[i]);

                String shippingPoint = properties.getProperty("shippingPoint");
                page.locator(SHIPPING_POINT_LOCATOR).click();

                page.locator(SHIPPING_POINT_SEARCH_FIELD).fill(shippingPoint);

                String shippingPointOptionLocator = getShippingPoint(shippingPoint);
                page.locator(shippingPointOptionLocator).last().click();

                page.locator(ADD_ITEM_BUTTON).click();

                if(i < itemNames.length - 1) {
                    addLineItemButton.click();
                } else {
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Non-Catalog Requisition Items Function: {}", exception.getMessage());
        }
    }

    public Map<String, String> vendor() {
        Map<String, String> rateContractArray = new HashMap<>();
        try {
            String vendorNameValue = properties.getProperty("vendorName");
            page.locator(VENDOR).click();

            APIResponse vendorApiResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Vendors/GetAllVendorsByKeyword/1882?keyword=" + vendorNameValue, RequestOptions.create());
            JsonNode vendorJson = objectMapper.readTree(vendorApiResponse.body());
            JsonNode vendorIdJson = vendorJson.get(0);
            int vendorId = vendorIdJson.get("id").asInt();

            page.locator(VENDOR_SEARCH).fill(vendorNameValue);

            String vendorOptionLocator = getVendor(vendorNameValue);
            page.locator(vendorOptionLocator).click();

            APIResponse rateContractApiResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/RateContractsByVendorIdandCompany?vendorId=" + vendorId, RequestOptions.create());
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
            String rateContractValue = properties.getProperty("rateContract");

            page.locator(RATE_CONTRACT).click();

            for(Map.Entry<String, String> rateContract : rateContractArray.entrySet()){
                if(rateContract.getValue().equals(rateContractValue)){
                    String rateContractId = rateContract.getKey();
                    page.locator(RATE_CONTRACT_SEARCH).fill(rateContractValue);

                    String rateContractOptionLocator = getRateContract(rateContractValue);
                    page.locator(rateContractOptionLocator).click();

                    APIResponse rateContractResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/RateContracts/ratecontract?rateContractId=" + rateContractId, RequestOptions.create());
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
            String buyerManagerName = properties.getProperty("buyerManagerEmail");
            page.locator(BUYER_MANAGER).click();
            page.locator(BUYER_MANAGER_SEARCH).fill(buyerManagerName);
            String buyerManagerLocator = getBuyerManager(buyerManagerName);
            page.locator(buyerManagerLocator).click();
        } catch (Exception exception) {
            logger.error("Exception in Buyer Manager Function: {}", exception.getMessage());
        }
    }

    public void projectManager(){
        try {
            String projectManagerName = properties.getProperty("projectManagerEmail");
            page.locator(PROJECT_MANAGER).click();
            page.locator(PROJECT_MANAGER_SEARCH).fill(projectManagerName);

            String projectManagerLocator = getProjectManager(projectManagerName);
            page.locator(projectManagerLocator).click();
        } catch (Exception exception) {
            logger.error("Exception in Project Manager Function: {}", exception.getMessage());
        }
    }

    public void tcasCompliance(){
        String tcasCompliance = properties.getProperty("tcasComplianceApplicable");
        String[] tcasQuestionNumbers = properties.getProperty("tcasQuestionNumber").split(",");
        try {
            if(tcasCompliance.equalsIgnoreCase("yes")){
                page.locator(TCAS_COMPLIANCE_APPLICABLE).click();

                for(String tcasQuestionNumber : tcasQuestionNumbers){
                    page.locator(TCAS_QUESTION_NUMBER + tcasQuestionNumber).click();
                }

                page.locator(TCAS_ADD_BUTTON).click();

                String tcasFilePath = properties.getProperty("tcasFilePath").trim();
                page.locator(TCAS_FILE_UPLOAD_BUTTON).setInputFiles(Paths.get(tcasFilePath));
            }
        } catch (Exception exception) {
            logger.error("Exception in TCAS Function: {}", exception.getMessage());
        }
    }

    public void addLineRequisitionItemsCatalog(List<String> rateContractItems) {
        Locator addLineItemButton;
        Locator addItemButton;
        try {
            addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
            addLineItemButton.click();

            for(int i = 0; i < rateContractItems.size(); i++){
                page.locator(CATALOG_ITEMS_DROPDOWN).click();

                page.locator(ITEM_SEARCH).fill(rateContractItems.get(i));

                String itemDropDownOptionSelect = getItem(rateContractItems.get(i));
                page.locator(itemDropDownOptionSelect).click();

                page.locator(QUANTITY).fill(String.valueOf(i + 10));

                String shippingPoint = properties.getProperty("shippingPoint");
                page.locator(SHIPPING_POINT_LOCATOR).click();

                page.locator(SHIPPING_POINT_SEARCH_FIELD).fill(shippingPoint);

                String shippingPointOptionLocator = getShippingPoint(shippingPoint);
                page.locator(shippingPointOptionLocator).last().click();

                addItemButton = page.locator(ADD_ITEM_BUTTON);
                addItemButton.click();

                if(i < rateContractItems.size() - 1) {
                    addLineItemButton.click();
                } else {
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Catalog Requisition Items Function: {}", exception.getMessage());
        }
    }

    public void notes() {
        try {
            String notesText = properties.getProperty("requisitionNotes");
            page.locator(NOTES).fill(notesText);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Notes Function: {}", exception.getMessage());
        }
    }

    public void attachments(){
        try {
            page.locator(ATTACHMENTS).click();

            String[] requisitionAttachmentsTypes = properties.getProperty("requisitionAttachmentsTypes").split(",");
            for(int i = 0; i < requisitionAttachmentsTypes.length; i++){
                if(requisitionAttachmentsTypes[i].equalsIgnoreCase("internal")){
                    String internalFilePath = properties.getProperty("internalFilePath");
                    page.locator(FILE_UPLOAD).setInputFiles(Paths.get(internalFilePath));
                    page.locator(ATTACH_FILE_BUTTON).click();
                } else if(requisitionAttachmentsTypes[i].equalsIgnoreCase("external")) {
                    String externalFilePath = properties.getProperty("externalFilePath");
                    page.locator(FILE_UPLOAD).setInputFiles(Paths.get(externalFilePath));
                    page.locator(EXTERNAL_RADIO_BUTTON).click();
                    page.locator(ATTACH_FILE_BUTTON).click();
                }
            }
            page.locator(CONTINUE_BUTTON).click();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Attachments Function: {}", exception.getMessage());
        }
    }

    public int prCreate(String purchaseType) {
        int status = 0;
        try {
            page.locator(CREATE_DRAFT_BUTTON).click();
            page.locator(YES).click();

            page.waitForURL("**/POC_Details?uid=*", new Page.WaitForURLOptions().setTimeout(10000));

            String url = page.url();
            String[] uid = url.split("=");
            String finalUid = uid[1];

            APIResponse statusResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions/" + finalUid, RequestOptions.create());
            JsonNode response = objectMapper.readTree(statusResponse.body());

            String requisitionStatus = "";
            if(response.has("status")){
                requisitionStatus = response.get("status").asText();
            }

            playwrightFactory.saveToPropertiesFile("requisitionStatus", requisitionStatus);

            status = statusResponse.status();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Create Function: {}", exception.getMessage());
        }
        return status;
    }
}