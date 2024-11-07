package com.procurement.nonPoc.constants.requisitions;
import java.util.Properties;

public class LPrCreate {

    public Properties properties;

    public static final String CREATE_BUTTON = "//button[@data-bs-toggle='modal']";
    public static final String TITLE = "#title";
    public static final String SHIP_TO_YOKOGAWA = "#shipToYokogawa";
    public static final String SALES_ORDER = "#select2-salesOrderId-container";
    public static final String PROJECT = "#select2-projectId-container";
    public static final String PROJECT_SEARCH = ".select2-search__field";
    public static final String SALESORDER_SEARCH = ".select2-search__field";
    public static final String DEPARTMENT_PIC = "#select2-searchDepartmentPic-container";
    public static final String DEPARTMENT_PIC_SEARCH = ".select2-search__field";
    public static final String WBS = "#select2-wbsId-container";
    public static final String WBS_SEARCH = ".select2-search__field";
    public static final String WBS_LIST = "#select2-wbsId-results";
    public static final String VENDOR = "#select2-vendor-container";
    public static final String VENDOR_SEARCH = ".select2-search__field";
    public static final String RATE_CONTRACT = "#select2-rateContract-container";
    public static final String RATE_CONTRACT_SEARCH = ".select2-search__field";
    public static final String INCOTERM = "#select2-incoterm-container";
    public static final String INCOTERM_SEARCH = ".select2-search__field";
    public static final String SHIPPING_ADDRESS = "#select2-endusersId-container";
    public static final String CATALOG_SHIPPING_MODE = "#select2-shippingMode-container";
    public static final String NON_CATALOG_MH_SHIPPING_MODE = "#select2-shippingmodeid-container";
    public static final String SHIPPING_MODE_SEARCH = ".select2-search__field";
    public static final String QUOTATION_REQUIRED_BY = "//*[@id='dates']/div[1]/input[2]";
    public static final String EXPECTED_PO_ISSUE = "//*[@id='dates']/div[1]/input[2]";
    public static final String EXPECTED_DELIVERY = "//*[@id='dates']/div[2]/input[2]";
    public static final String TODAY = "//span[@class='flatpickr-day today']";
    public static final String BUYER_MANAGER = "#select2-buyerManagerId-container";
    public static final String BUYER_MANAGER_SEARCH = ".select2-search__field";
    public static final String PROJECT_MANAGER = "#select2-projectManagerId-container";
    public static final String BUYER_GROUP = "#select2-buyerGroup-container";
    public static final String BUYER_GROUP_SEARCH = ".select2-search__field";
    public static final String CHECKER = "#select2-checker-container";
    public static final String CHECKER_SEARCH = ".select2-search__field";
    public static final String PROJECT_MANAGER_SEARCH = ".select2-search__field";
    public static final String ROHS_COMPLIANCE = "#rohsnotcomplianceid";
    public static final String OI_AND_TP_CURRENCY = "#select2-oiTpCurrencyId-container";
    public static final String OI_AND_TP_CURRENCY_SEARCH = ".select2-search__field";
    public static final String ORDER_INTAKE = "#orderIntake";
    public static final String TARGET_PRICE = "#targetPrice";
    public static final String CASE_MARKING = "#caseMarking";
    public static final String MESSAGE_TO_SOURCING = "#messageToSourcing";
    public static final String WARRANTY_REQUIREMENTS = "#select2-warrantyrequirementsid-container";
    public static final String WARRANTY_REQUIREMENTS_SEARCH = ".select2-search__field";
    public static final String PRICE_VALIDITY = "#select2-pricevalidityid-container";
    public static final String PRICE_VALIDITY_SEARCH = ".select2-search__field";
    public static final String INSPECTION_REQUIRED = "#inspectRequired";
    public static final String LIQUIDATED_DAMAGES_SELECT = "#isLDStandardNoId";
    public static final String LIQUIDATED_DAMAGES = "#liquidatedamageTextId";
    public static final String ADD_LINE_ITEM_BUTTON = "#addLineRequisitionItems";
    public static final String ITEMS_LIST = "#select2-itemId-results";
    public static final String ITEMS = "#select2-itemid-container";
    public static final String ITEM_SEARCH = ".select2-search__field";
    public static final String QUANTITY = "#quantity";
    public static final String ADD_ITEM_BUTTON = "#saveRequisitionItem";
    public static final String NOTES = "#notes";
    public static final String ATTACHMENTS = "#attachDocs";
    public static final String FILE_UPLOAD = "#formFilePreupload";
    public static final String EXTERNAL_RADIO_BUTTON = "#radioInActive";
    public static final String ATTACH_FILE_BUTTON = "#saveAttachments1";
    public static final String CONTINUE_BUTTON = "#attachmentSaveId";
    public static final String CREATE_DRAFT_BUTTON = "//*[contains(text(), 'Create Draft')]";
    public static final String YES = ".bootbox-accept";
    public static final String BILLING_TYPE = "#select2-billingTypeId-container";
    public static final String BILLABLE_TO_CUSTOMER = "#select2-billableToCustomer-container";



    private LPrCreate() {
    }

//TODO Constructor
    public LPrCreate(Properties properties) {
        this.properties = properties;
    }

//TODO Methods to get dynamic locators
    public static String getPrType(String type) {
        return "//a[@href='/Procurement/Requisitions/NonPOC_" + type + "_Create']";
    }

    public static String getProject(String project) {
        return "//li[contains(text(),'" + project + "')]";
    }

    public static String getSalesOrder(String salesOrder){
        return "//li[contains(text(),'" + salesOrder + "')]";
    }
    public static String getDepartmentPic(String departmentPIC){
        return "//li[contains(text(),'" + departmentPIC + "')]";
    }

    public static String getWBSForMh(String wbs) {
        return "//li[contains(text(), " + wbs + ")]";
    }

    public static String getWBSForCandNC(String wbs) {
        return "//li[contains(text(),'" + wbs + "')]";
    }

    public static String getVendor(String vendor) {
        return "//li[contains(text(),'" + vendor + "')]";
    }

    public static String getRateContract(String rateContract) {
        return "//li[contains(text(),'" + rateContract + "')]";
    }

    public static String getIncoterm(String incoterm) {
        return "//li[contains(text(),'" + incoterm + "')]";
    }

    public static String getShippingAddress(String shippingAddress) {
        return "//*[contains(text(),'" + shippingAddress + "')]";
    }

    public static String getShippingMode(String shippingMode) {
        return "//li[contains(text(),'" + shippingMode + "')]";
    }
    public static String getBillableToCustomer(String billableToCustomer) {
        return "//li[contains(text(),'" + billableToCustomer + "')]";
    }

    public static String getBuyerManager(String buyerManager) {
        return "//li[contains(text(),'" + buyerManager + "')]";
    }

    public static String getProjectManager(String projectManager) {
        return "//li[contains(text(),'" + projectManager + "')]";
    }

    public static String getChecker(String checker) {
        return "//li[contains(text(),'" + checker + "')]";
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

    public static String getBillingType(String billingType) {
        return "//li[contains(text(),'" + billingType + "')]";
    }
}