package com.ps.classes.requisition.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrCreate;
import java.nio.file.Paths;
import java.util.*;
import static com.constants.requisitions.LPrCreate.*;

public class Create implements IPrCreate {

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

        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.playwright = playwright;
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void requesterLoginPRCreate(String emailId) {
        try {
            iLogin.performLogin(emailId);
        } catch (Exception exception) {
            System.out.println("Error in Requester Login Function: " + exception.getMessage());
        }
    }

    public void createButton() {
        try {
            page.locator(CREATE_BUTTON).click();
        } catch (Exception exception) {
            System.out.println("Error in Create Button Function: " + exception.getMessage());
        }
    }

    public void purchaseType(String purchaseType) {
        try {
            String prTypeLocator = getPrType(purchaseType);
            page.locator(prTypeLocator).click();
        } catch (Exception exception) {
            System.out.println("Error in Purchase Type Function: " + exception.getMessage());
        }
    }

    public void title(String purchaseType) {
        try {
            String title = properties.getProperty("orderTitle");
            randomNumber = Math.round(Math.random() * 1000);

            if(purchaseType.toLowerCase().equals("catalog")){
                String catalogTitle = title + "-" + purchaseType + "-" + randomNumber;
                page.locator(TITLE).fill(catalogTitle);
                playwrightFactory.saveToPropertiesFile("catalogTitle", catalogTitle);
            } else {
                String nonCatalogTitle = title + "-" + purchaseType + "-" + randomNumber;
                page.locator(TITLE).fill(nonCatalogTitle);
                playwrightFactory.saveToPropertiesFile("nonCatalogTitle", nonCatalogTitle);
            }
        } catch (Exception exception) {
            System.out.println("Error in Title Function: " + exception.getMessage());
        }
    }

    public void shipToYokogawa() {
        try {
            String val = properties.getProperty("shipToYokogawa").toLowerCase();
            if (val.equals("no")) {
                Locator shipToYokogawa = page.locator(SHIP_TO_YOKOGAWA);
                shipToYokogawa.click();
            }
        } catch (Exception exception) {
            System.out.println("Error in Ship To Yokogawa Function: " + exception.getMessage());
        }
    }

    public List<String> project() {
        List<String> wbsValues = new ArrayList<>();
        try {
            String projectCodeValue = properties.getProperty("projectCode");

            page.locator(PROJECT).click();

            Locator projectSearchLocator = page.locator(PROJECT_SEARCH);
            projectSearchLocator.fill(projectCodeValue);

            String projectSelectLocator = getProject(projectCodeValue);
            Locator projectSelectElement = page.locator(projectSelectLocator);
            projectSelectElement.click();

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
                    Locator wbsLocator = page.locator(WBS);
                    wbsLocator.click();

                    Locator wbsSearchLocator = page.locator(WBS_SEARCH);
                    wbsSearchLocator.fill(wbsFromProperties);

                    String wbsSelectLocator = getWBSForCandNC(wbsFromProperties);
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
            String vendorNameValue = properties.getProperty("vendorName");
            Locator vendorLocator = page.locator(VENDOR);
            vendorLocator.click();

            APIResponse vendorApiResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Vendors/GetAllVendorsByKeyword/1882?keyword=" + vendorNameValue, RequestOptions.create());
            JsonNode vendorJson = objectMapper.readTree(vendorApiResponse.body());
            JsonNode vendorIdJson = vendorJson.get(0);
            int vendorId = vendorIdJson.get("id").asInt();

            Locator vendorSearchLocator = page.locator(VENDOR_SEARCH);
            vendorSearchLocator.fill(vendorNameValue);

            String vendorOptionLocator = getVendor(vendorNameValue);
            Locator vendorOption = page.locator(vendorOptionLocator);
            vendorOption.click();

            APIResponse rateContractApiResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/RateContractsByVendorIdandCompany?vendorId=" + vendorId, RequestOptions.create());
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

            Locator rateContractLocator = page.locator(RATE_CONTRACT);
            rateContractLocator.click();

            for(Map.Entry<String, String> rateContract : rateContractArray.entrySet()){
                if(rateContract.getValue().equals(rateContractValue)){
                    String rateContractId = rateContract.getKey();
                    Locator rateContractSearchLocator = page.locator(RATE_CONTRACT_SEARCH);
                    rateContractSearchLocator.fill(rateContractValue);

                    String rateContractOptionLocator = getRateContract(rateContractValue);
                    Locator rateContractOption = page.locator(rateContractOptionLocator);
                    rateContractOption.click();

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
        } catch (Exception error) {
            System.out.println("Error in Rate Contract Function: " + error.getMessage());
        }
        return rateContractItems;
    }

    public void incoterm() {
        try {
            Locator incotermLocator = page.locator(INCOTERM);
            incotermLocator.click();

            String incotermValue = properties.getProperty("incoterm");
            Locator incotermSearchLocator = page.locator(INCOTERM_SEARCH);
            incotermSearchLocator.fill(incotermValue);

            String incotermOptionLocator = getIncoterm(incotermValue);
            Locator incotermOption = page.locator(incotermOptionLocator);
            incotermOption.click();
        } catch (Exception error) {
            System.out.println("Error in Incoterm Function: " + error.getMessage());
        }
    }

    public void shippingAddress() {
        try {
            String shipToYokogawa = properties.getProperty("shipToYokogawa");
            if(shipToYokogawa.toLowerCase().equals("yes")){
                Locator shippingAddressLocator = page.locator(SHIPPING_ADDRESS);
                shippingAddressLocator.click();

                String shippingAddressValue = properties.getProperty("shippingAddress");
                String shippingAddressOptionLocator = getShippingAddress(shippingAddressValue);

                Locator shippingAddressOption = page.locator(shippingAddressOptionLocator);
                shippingAddressOption.last().click();
            } else {
                Locator shippingAddressEndUsersLocator = page.locator(SHIPPING_ADDRESS_ENDUSERS_OR_OTHERS);
                shippingAddressEndUsersLocator.click();

                String shippingAddressEnduser = properties.getProperty("shippingAddressEnduser");
                Locator shippingAddressEndUserSearchLocator = page.locator(SHIPPING_ADDRESS_ENDUSERS_SEARCH);
                shippingAddressEndUserSearchLocator.fill(shippingAddressEnduser);

                String shippingAddressEnduserOptionLocator = getShippingAddressEnduser(shippingAddressEnduser);
                Locator shippingAddressEnduserOption = page.locator(shippingAddressEnduserOptionLocator);
                shippingAddressEnduserOption.click();
            }
        } catch (Exception error) {
            System.out.println("Error in Shipping Address Function: " + error.getMessage());
        }
    }

    public void shippingMode(String purchaseType) {
        try {
            String shippingMode = purchaseType.toLowerCase().equals("catalog") ? CATALOG_SHIPPING_MODE : NON_CATALOG_MH_SHIPPING_MODE;

            Locator shippingModeLocator = page.locator(shippingMode);
            shippingModeLocator.click();

            String getShippingMode = properties.getProperty("shippingMode");

            Locator shippingModeSearch = page.locator(SHIPPING_MODE_SEARCH);
            shippingModeSearch.fill(getShippingMode);

            String finalShippingMode = getShippingMode(getShippingMode);

            Locator finalShippingModeLocator = page.locator(finalShippingMode);
            finalShippingModeLocator.click();
        } catch (Exception error) {
            System.out.println("Error in Shipping Mode Function: " + error.getMessage());
        }
    }

    public void quotationRequiredBy() {
        try {
            Locator quotationRequiredByField = page.locator(QUOTATION_REQUIRED_BY);
            quotationRequiredByField.click();

            Locator todayOption = page.locator(TODAY);
            todayOption.first().click();
        } catch (Exception error) {
            System.out.println("Error in Quotation Required By Function: " + error.getMessage());
        }
    }

    public void expectedPOIssue(String purchaseType) {
        Locator expectedPoIssueField;
        try {
            if(purchaseType.toLowerCase().equals("catalog")){
                expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE_CATALOG);
            } else {
                expectedPoIssueField = page.locator(EXPECTED_PO_ISSUE_NON_CATALOG);
            }
            expectedPoIssueField.click();

            Locator todayOption = page.locator(TODAY);
            todayOption.first().click();
        } catch (Exception error) {
            System.out.println("Error in Expected PO Function: " + error.getMessage());
        }
    }

    public void expectedDelivery(String purchaseType) {
        Locator expectedDeliveryField;
        try {
            if(purchaseType.toLowerCase().equals("catalog")){
                expectedDeliveryField = page.locator(EXPECTED_DELIVERY_CATALOG);
            } else {
                expectedDeliveryField = page.locator(EXPECTED_DELIVERY_NON_CATALOG);
            }
            expectedDeliveryField.click();

            Locator todayOption = page.locator(TODAY);
            todayOption.first().click();
        } catch (Exception error) {
            System.out.println("Error in Expected Delivery Function: " + error.getMessage());
        }
    }

    public void buyerManager(){
        try {
            Locator buyerManagerDropdown = page.locator(BUYER_MANAGER);
            buyerManagerDropdown.click();

            String buyerManagerName = properties.getProperty("buyerManagerEmail");

            Locator buyerManagerSearch = page.locator(BUYER_MANAGER_SEARCH);
            buyerManagerSearch.fill(buyerManagerName);

            String buyerManagerLocator = getBuyerManager(buyerManagerName);
            Locator finalBuyerManagerSelect = page.locator(buyerManagerLocator);
            finalBuyerManagerSelect.click();
        } catch (Exception error) {
            System.out.println("Error in Buyer Manager Function: " + error.getMessage());
        }
    }

    public void projectManager(){
        try {
            Locator projectManagerDropdown = page.locator(PROJECT_MANAGER);
            projectManagerDropdown.click();

            String projectManagerName = properties.getProperty("projectManagerEmail");

            Locator projectManagerSearch = page.locator(PROJECT_MANAGER_SEARCH);
            projectManagerSearch.fill(projectManagerName);

            String projectManagerLocator = getProjectManager(projectManagerName);
            Locator finalProjectManager = page.locator(projectManagerLocator);
            finalProjectManager.click();
        } catch (Exception error) {
            System.out.println("Error in Project Manager Function: " + error.getMessage());
        }
    }

    public void rohsCompliance(){
        try {
            String compliance = properties.getProperty("rohsCompliance").toLowerCase().trim();

            if (compliance.equals("no")) {
                Locator rohsComplianceLocator = page.locator(ROHS_COMPLIANCE);
                rohsComplianceLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error in RoHS Compliance Function: " + error.getMessage());
        }
    }

    public void oiAndTpCurrency(){
        try {
            Locator oiAndTpCurrencyLocator = page.locator(OI_AND_TP_CURRENCY);
            oiAndTpCurrencyLocator.click();

            String currency = properties.getProperty("oiAndTpCurrency");

            Locator oiAndTpCurrencySearchLocator = page.locator(OI_AND_TP_CURRENCY_SEARCH);
            oiAndTpCurrencySearchLocator.fill(currency);

            String currencySelect = getOiAndTpCurrency(currency);
            Locator currencySelectLocator = page.locator(currencySelect);
            currencySelectLocator.click();
        } catch (Exception error) {
            System.out.println("Error in OI/TP Currency Function: " + error.getMessage());
        }
    }

    public void orderIntake(){
        try {
            String orderIntake = properties.getProperty("orderIntake");
            Locator orderIntakeLocator = page.locator(ORDER_INTAKE);
            orderIntakeLocator.fill(orderIntake);
        } catch (Exception error) {
            System.out.println("Error in Order Intake Function: " + error.getMessage());
        }
    }

    public void targetPrice(String purchaseType){
        try {
            if (purchaseType.equals("NonCatalog")) {
                String targetPrice = properties.getProperty("targetPrice");
                Locator targetPriceLocator = page.locator(TARGET_PRICE);
                targetPriceLocator.fill(targetPrice);
            }
        } catch (Exception error) {
            System.out.println("Error in Target Price Function: " + error.getMessage());
        }
    }

    public void warrantyRequirements(){
        try {
            Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS);
            warrantyRequirementsLocator.click();

            String warrantyRequirement = properties.getProperty("warrantyRequirement");

            Locator warrantyRequirementsSearchLocator = page.locator(WARRANTY_REQUIREMENTS_SEARCH);
            warrantyRequirementsSearchLocator.fill(warrantyRequirement);

            String warrantyRequirementSelector = getWarrantyRequirements(warrantyRequirement);
            Locator warrantyRequirementOptionLocator = page.locator(warrantyRequirementSelector);
            warrantyRequirementOptionLocator.click();
        } catch (Exception error) {
            System.out.println("Error in Warranty Requirements Function: " + error.getMessage());
        }
    }

    public void priceValidity(){
        try {
            Locator priceValidityLocator = page.locator(PRICE_VALIDITY);
            priceValidityLocator.click();

            String warrantyRequirement = properties.getProperty("priceValidity");

            Locator priceValiditySearchLocator = page.locator(PRICE_VALIDITY_SEARCH);
            priceValiditySearchLocator.fill(warrantyRequirement);

            String priceValiditySelector = getPriceValidity(warrantyRequirement);
            Locator priceValidityOptionLocator = page.locator(priceValiditySelector);
            priceValidityOptionLocator.click();
        } catch (Exception error) {
            System.out.println("Error in Price Validity Function: " + error.getMessage());
        }
    }

    public void inspectionRequired(String purchaseType) {
        Locator inspectionRequiredLocator;
        try {
            String isInspectionRequired = properties.getProperty("inspectionRequired");

            if (isInspectionRequired.toLowerCase().equals("yes")) {
                if (purchaseType.toLowerCase().equals("catalog")){
                    inspectionRequiredLocator = page.locator(CATALOG_INSPECTION_REQUIRED);
                } else {
                    inspectionRequiredLocator = page.locator(NON_CATALOG_INSPECTION_REQUIRED);
                }
                inspectionRequiredLocator.click();
            }
        } catch (Exception error) {
            System.out.println("Error in Inspection Required Function: " + error.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
            String liquidatedDamages = properties.getProperty("liquidatedDamages").toLowerCase().trim();

            if (liquidatedDamages.equals("special")) {
                Locator liquidatedDamagesSelector = page.locator(LIQUIDATED_DAMAGES_SELECT);
                liquidatedDamagesSelector.click();

                Locator liquidatedDamagesInput = page.locator(LIQUIDATED_DAMAGES);
                liquidatedDamagesInput.fill("Special Liquidated Damages");
            }
        } catch (Exception error) {
            System.out.println("Error in Liquidated Damages Function: " + error.getMessage());
        }
    }

    public void tcasCompliance(){
        String tcasCompliance = properties.getProperty("tcasComplianceApplicable");
        String[] tcasQuestionNumbers = properties.getProperty("tcasQuestionNumber").split(",");
        try {
            if(tcasCompliance.toLowerCase().equals("yes")){
                Locator tcasLocator = page.locator(TCAS_COMPLIANCE_APPLICABLE);
                tcasLocator.click();

                for(String tcasQuestionNumber : tcasQuestionNumbers){
                    Locator tcasQuestionNumberLocator = page.locator(TCAS_QUESTION_NUMBER + tcasQuestionNumber);
                    tcasQuestionNumberLocator.click();
                }

                Locator tcasAddButtonLocator = page.locator(TCAS_ADD_BUTTON);
                tcasAddButtonLocator.click();

                String tcasFilePath = properties.getProperty("tcasFilePath").trim();
                Locator tcasCertificateUploadButtonLocator = page.locator(TCAS_FILE_UPLOAD_BUTTON);
                tcasCertificateUploadButtonLocator.setInputFiles(Paths.get(tcasFilePath));
            }
        } catch (Exception error) {
            System.out.println("Error in TCAS Function: " + error.getMessage());
        }
    }

    public void addLineRequisitionItemsCatalog(List<String> rateContractItems) {
        Locator itemDropdown;
        Locator addItemButton;
        try {
            Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
            addLineItemButton.click();

                for(int i = 0; i < rateContractItems.size(); i++){
                    itemDropdown = page.locator(CATALOG_ITEMS_DROPDOWN);
                    itemDropdown.click();

                    Locator itemSearchBox = page.locator(ITEM_SEARCH);
                    itemSearchBox.fill(rateContractItems.get(i));

                    String itemDropDownOptionSelect = getItem(rateContractItems.get(i));
                    Locator itemOptionSelect = page.locator(itemDropDownOptionSelect);
                    itemOptionSelect.click();

                    Locator quantityField = page.locator(QUANTITY);
                    quantityField.fill(String.valueOf(i + 10));

                    String shippingPoint = properties.getProperty("shippingPoint");
                    Locator shippingPointField = page.locator(SHIPPING_POINT_LOCATOR);
                    shippingPointField.click();

                    Locator shippingPointSearchFieldLocator = page.locator(SHIPPING_POINT_SEARCH_FIELD);
                    shippingPointSearchFieldLocator.fill(shippingPoint);

                    String shippingPointOptionLocator = getShippingPoint(shippingPoint);
                    Locator shippingPointOption = page.locator(shippingPointOptionLocator);
                    shippingPointOption.last().click();

                    addItemButton = page.locator(ADD_ITEM_BUTTON);
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

            Locator addLineItemButton = page.locator(ADD_LINE_ITEM_BUTTON);
            addLineItemButton.click();

             for (int i = 0; i < itemNames.length; i++) {
                 itemDropdown = page.locator(NON_CATALOG_ITEMS_DROPDOWN);
                 itemDropdown.click();

                 Locator itemSearchBox = page.locator(ITEM_SEARCH);
                 itemSearchBox.fill(itemNames[i]);

                 String itemName = itemNames[i];
                 String encodedName = itemName.replace(" ", "%20");

                 APIResponse itemSpecificationResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Itemcategory/search?keyword=" + encodedName + "&purchaseMethod=NonCatalog");
                 JsonNode itemSpecificationsObject = objectMapper.readTree(itemSpecificationResponse.body());
                 idValue = itemSpecificationsObject.get(0).get("id").asText();

                 Locator itemOption = page.locator(getItem(itemNames[i]));
                 itemOption.first().click();

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

                 Locator quantityField = page.locator(QUANTITY);
                 quantityField.fill(quantities[i]);

                 String shippingPoint = properties.getProperty("shippingPoint");
                 Locator shippingPointField = page.locator(SHIPPING_POINT_LOCATOR);
                 shippingPointField.click();

                 Locator shippingPointSearchFieldLocator = page.locator(SHIPPING_POINT_SEARCH_FIELD);
                 shippingPointSearchFieldLocator.fill(shippingPoint);

                 String shippingPointOptionLocator = getShippingPoint(shippingPoint);
                 Locator shippingPointOption = page.locator(shippingPointOptionLocator);
                 shippingPointOption.last().click();

                 addItemButton = page.locator(ADD_ITEM_BUTTON);
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

    public void notes() {
        try {
            String notesText = properties.getProperty("requisitionNotes");
            Locator notesField = page.locator(NOTES);
            notesField.fill(notesText);
        } catch (Exception error) {
            System.out.println("Error in Requisition Notes Function: " + error.getMessage());
        }
    }

    public void attachments(){
        try {
            Locator attachmentsButton = page.locator(ATTACHMENTS);
            attachmentsButton.click();

            String[] requisitionAttachmentsTypes = properties.getProperty("requisitionAttachmentsTypes").split(",");
            for(int i = 0; i < requisitionAttachmentsTypes.length; i++){
                if(requisitionAttachmentsTypes[i].toLowerCase().equals("internal")){
                    String internalFilePath = properties.getProperty("internalFilePath");

                    Locator internalFileUpload = page.locator(FILE_UPLOAD);
                    internalFileUpload.setInputFiles(Paths.get(internalFilePath));

                    Locator attachFileButton = page.locator(ATTACH_FILE_BUTTON);
                    attachFileButton.click();
                } else if(requisitionAttachmentsTypes[i].toLowerCase().equals("external")) {
                    String externalFilePath = properties.getProperty("externalFilePath");

                    Locator externalFileUpload = page.locator(FILE_UPLOAD);
                    externalFileUpload.setInputFiles(Paths.get(externalFilePath));

                    Locator externalRadioButton = page.locator(EXTERNAL_RADIO_BUTTON);
                    externalRadioButton.click();

                    Locator attachFileButton = page.locator(ATTACH_FILE_BUTTON);
                    attachFileButton.click();
                }
            }

            Locator continueButton = page.locator(CONTINUE_BUTTON);
            continueButton.click();
        } catch (Exception error) {
            System.out.println("Error in Requisition Attachments Function: " + error.getMessage());
        }
    }

    public int prCreate(String purchaseType) {
        int status = 0;
        try {
            Locator createDraftButtonLocator = page.locator(CREATE_DRAFT_BUTTON);
            createDraftButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES);
            yesButtonLocator.click();

            APIResponse statusResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions");
            status = statusResponse.status();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("Error in Requisition Create Function: " + error.getMessage());
        }
        return status;
    }
}