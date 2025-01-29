package com.constants.requisitions;
import java.util.Properties;

public class LPrCreate {

    public Properties properties;

    public static final String CREATE_BUTTON = "//button[@data-bs-toggle='modal']";
    public static final String TITLE = "#title";
    public static final String SHIP_TO_YOKOGAWA = "#shipToYokogawa";
    public static final String PROJECT = "#select2-projectId-container";
    public static final String PROJECT_SEARCH = ".select2-search__field";
    public static final String WBS = "#select2-wbsId-container";
    public static final String WBS_SEARCH = ".select2-search__field";
    public static final String VENDOR = "#select2-vendorId-container";
    public static final String VENDOR_SEARCH = ".select2-search__field";
    public static final String RATE_CONTRACT = "#select2-rateContractId-container";
    public static final String RATE_CONTRACT_SEARCH = ".select2-search__field";
    public static final String INCOTERM = "#select2-incoterm-container";
    public static final String INCOTERM_SEARCH = ".select2-search__field";
    public static final String SHIPPING_ADDRESS = "#select2-shippingaddressId-container";
    public static final String SHIPPING_ADDRESS_ENDUSERS_OR_OTHERS = "#select2-endusersId-container";
    public static final String SHIPPING_ADDRESS_ENDUSERS_SEARCH = ".select2-search__field";
    public static final String CATALOG_SHIPPING_MODE = "#select2-shippingModeId-container";
    public static final String NON_CATALOG_MH_SHIPPING_MODE = "#select2-shippingmodeid-container";
    public static final String SHIPPING_MODE_SEARCH = ".select2-search__field";
    public static final String QUOTATION_REQUIRED_BY = "//*[@id='dates']/div[1]/input[2]";
    public static final String EXPECTED_PO_ISSUE_CATALOG = "//*[@id='dates']/div[1]/input[2]";
    public static final String EXPECTED_PO_ISSUE_NON_CATALOG = "//*[@id='dates']/div[2]/input[2]";
    public static final String EXPECTED_DELIVERY_CATALOG = "//*[@id='dates']/div[2]/input[2]";
    public static final String EXPECTED_DELIVERY_NON_CATALOG = "//*[@id='dates']/div[3]/input[2]";
    public static final String TODAY = "//span[@class='flatpickr-day today']";
    public static final String BUYER_MANAGER = "#select2-buyerManagerId-container";
    public static final String BUYER_MANAGER_SEARCH = ".select2-search__field";
    public static final String PROJECT_MANAGER = "#select2-projectManagerId-container";
    public static final String PROJECT_MANAGER_SEARCH = ".select2-search__field";
    public static final String ROHS_COMPLIANCE = "#rohsnotcomplianceid";
    public static final String OI_AND_TP_CURRENCY = "#select2-oiTpCurrencyId-container";
    public static final String OI_AND_TP_CURRENCY_SEARCH = ".select2-search__field";
    public static final String ORDER_INTAKE = "#orderintakeid";
    public static final String TARGET_PRICE = "#targetpriceid";
    public static final String WARRANTY_REQUIREMENTS = "#select2-warrantyrequirementsid-container";
    public static final String WARRANTY_REQUIREMENTS_SEARCH = ".select2-search__field";
    public static final String PRICE_VALIDITY = "#select2-pricevalidityid-container";
    public static final String PRICE_VALIDITY_SEARCH = ".select2-search__field";
    public static final String CATALOG_INSPECTION_REQUIRED = "#inspectRequired";
    public static final String NON_CATALOG_INSPECTION_REQUIRED = "#inspectrequired";
    public static final String LIQUIDATED_DAMAGES_SELECT = "#isLDStandardNoId";
    public static final String LIQUIDATED_DAMAGES = "#liquidatedamageTextId";
    public static final String TCAS_COMPLIANCE_APPLICABLE = "#tcasApplicable";
    public static final String TCAS_QUESTION_NUMBER = "#q";
    public static final String TCAS_ADD_BUTTON = "#tcasSubmit";
    public static final String TCAS_FILE_UPLOAD_BUTTON = "#tcasFilePreupload";
    public static final String ADD_LINE_ITEM_BUTTON = "#addLineRequisitionItems";
    public static final String CATALOG_ITEMS_DROPDOWN = "#select2-itemId-container";
    public static final String NON_CATALOG_ITEMS_DROPDOWN = "#select2-itemid-container";
    public static final String ITEM_SEARCH = ".select2-search__field";
    public static final String QUANTITY = "#quantity";
    public static final String SHIPPING_POINT_LOCATOR = "#select2-itemShippingPointId-container";
    public static final String SHIPPING_POINT_SEARCH_FIELD = ".select2-search__field";
    public static final String ADD_ITEM_BUTTON = "#saveRequisitionItem";
    public static final String ITEM_SPECIFICATIONS_TEXT_FIELD_LOCATORS = "//div[@id=\"itemspec-container\"]//input[@type=\"text\"]";
    public static final String ITEM_SPECIFICATIONS_SELECTION_FIELD_LOCATORS = "//div[@id=\"itemspec-container\"]//span[@class=\"select2-selection__rendered\"]";
    public static final String ITEM_SPECIFICATIONS_SELECTION_FIELD_RESULT_LOCATOR = "//span[@class=\"select2-results\"]//li[@class=\"select2-results__option select2-results__option--highlighted\"]";
    public static final String ITEM_SPECIFICATIONS_CHECKBOX_FIELD_LOCATORS = "//div[@id=\"itemspec-container\"]//input[@type=\"checkbox\"]";
    public static final String NOTES = "#notes";
    public static final String ATTACHMENTS = "#attachDocs";
    public static final String FILE_UPLOAD = "#formFilePreupload";
    public static final String EXTERNAL_RADIO_BUTTON = "#radioInActive";
    public static final String ATTACH_FILE_BUTTON = "#saveAttachments1";
    public static final String CONTINUE_BUTTON = "#submitAttachmentsId";
    public static final String CREATE_DRAFT_BUTTON = "//*[contains(text(), 'Create Draft')]";
    public static final String YES = ".bootbox-accept";

//TODO Constructor
    private LPrCreate() {
    }

//TODO Methods to get dynamic locators
    public static String getPrType(String type) {
        return "//a[@href='/Procurement/Requisitions/POC_" + type + "_Create']";
    }

    public static String getProject(String project) {
        return "//li[contains(text(),'" + project + "')]";
    }

    public static String getShippingPoint(String shippingPoint) {
        return "//li[contains(text(), " + shippingPoint + ")]";
    }

    public static String getWBSForCandNC(String wbs) {
        return "//li[contains(text(),'" + wbs + "')]";
    }

    public static String getVendor(String vendor) {
        return "//li[contains(text(),'" + vendor + "')]";
    }

    public static String getRateContract(String rateContract) {
        return "//li[contains(text(), \"" + rateContract + "\")]";
    }

    public static String getIncoterm(String incoterm) {
        return "//li[contains(text(),'" + incoterm + "')]";
    }

    public static String getShippingAddress(String shippingAddress) {
        return "//*[contains(text(),'" + shippingAddress + "')]";
    }

    public static String getShippingAddressEnduser(String shippingAddressEnduser){
        return "//li[contains(text(), '" + shippingAddressEnduser + "')]";
    }

    public static String getShippingMode(String shippingMode) {
        return "//li[contains(text(),'" + shippingMode + "')]";
    }

    public static String getBuyerManager(String buyerManager) {
        return "//li[contains(text(),'" + buyerManager + "')]";
    }

    public static String getProjectManager(String projectManager) {
        return "//li[contains(text(),'" + projectManager + "')]";
    }

    public static String getOiAndTpCurrency(String currency) {
        return "//li[contains(text(),'" + currency + "')]";
    }

    public static String getWarrantyRequirements(String warrantyRequirements) {
        return "//li[contains(text(),'" + warrantyRequirements + "')]";
    }

    public static String getPriceValidity(String priceValidity) {
        return "//li[contains(text(),'" + priceValidity + "')]";
    }

    public static String getItem(String item) {
        return "//li[contains(text(),'" + item + "')]";
    }
}