package com.procurement.poc.constants.requisitions;
import java.util.Properties;
public enum LPrCreate {

    CREATE_BUTTON("//button[@data-bs-toggle='modal']"),
    TITLE ("#title"),
    SHIP_TO_YOKOGAWA ("#shipToYokogawa"),
    SALES_ORDER ("#select2-salesOrderId-container"),
    PROJECT ("#select2-projectId-container"),
    PROJECT_SEARCH (".select2-search__field"),
    SALESORDER_SEARCH (".select2-search__field"),
    DEPARTMENT_PIC ("#select2-searchDepartmentPic-container"),
    DEPARTMENT_PIC_SEARCH (".select2-search__field"),
    WBS ("#select2-wbsId-container"),
    WBS_SEARCH (".select2-search__field"),
    WBS_LIST ("#select2-wbsId-results"),
    VENDOR ("#select2-vendor-container"),
    VENDOR_SEARCH (".select2-search__field"),
    RATE_CONTRACT ("#select2-rateContract-container"),
    RATE_CONTRACT_SEARCH (".select2-search__field"),
    INCOTERM ("#select2-incoterm-container"),
    INCOTERM_SEARCH (".select2-search__field"),
    SHIPPING_ADDRESS ("#select2-endusersId-container"),
    CATALOG_SHIPPING_MODE ("#select2-shippingMode-container"),
    NON_CATALOG_MH_SHIPPING_MODE ("#select2-shippingmodeid-container"),
    SHIPPING_MODE_SEARCH (".select2-search__field"),
    QUOTATION_REQUIRED_BY ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_PO_ISSUE ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_DELIVERY ("//*[@id='dates']/div[2]/input[2]"),
    TODAY ("//span[@class='flatpickr-day today']"),
    BUYER_MANAGER ("#select2-buyerManagerId-container"),
    BUYER_MANAGER_SEARCH (".select2-search__field"),
    PROJECT_MANAGER ("#select2-projectManagerId-container"),
    BUYER_GROUP ("#select2-buyerGroup-container"),
    BUYER_GROUP_SEARCH (".select2-search__field"),
    CHECKER ("#select2-checker-container"),
    CHECKER_SEARCH (".select2-search__field"),
    PROJECT_MANAGER_SEARCH (".select2-search__field"),
    ROHS_COMPLIANCE ("#rohsnotcomplianceid"),
    OI_AND_TP_CURRENCY ("#select2-oiTpCurrencyId-container"),
    OI_AND_TP_CURRENCY_SEARCH (".select2-search__field"),
    ORDER_INTAKE ("#orderIntake"),
    TARGET_PRICE ("#targetPrice"),
    CASE_MARKING ("#caseMarking"),
    MESSAGE_TO_SOURCING ("#messageToSourcing"),
    WARRANTY_REQUIREMENTS ("#select2-warrantyrequirementsid-container"),
    WARRANTY_REQUIREMENTS_SEARCH (".select2-search__field"),
    PRICE_VALIDITY ("#select2-pricevalidityid-container"),
    PRICE_VALIDITY_SEARCH (".select2-search__field"),
    INSPECTION_REQUIRED ("#inspectRequired"),
    LIQUIDATED_DAMAGES_SELECT ("#isLDStandardNoId"),
    LIQUIDATED_DAMAGES ("#liquidatedamageTextId"),
    ADD_LINE_ITEM_BUTTON ("#addLineRequisitionItems"),
    ITEMS_LIST ("#select2-itemId-results"),
    ITEMS ("#select2-item-container"),
    ITEM_SEARCH (".select2-search__field"),
    QUANTITY ("#quantity"),
    ADD_ITEM_BUTTON ("#saveRequisitionItem"),
    NOTES ("#notes"),
    ATTACHMENTS ("#attachDocs"),
    FILE_UPLOAD ("#formFilePreupload"),
    EXTERNAL_RADIO_BUTTON ("#radioInActive"),
    ATTACH_FILE_BUTTON ("#saveAttachments1"),
    CONTINUE_BUTTON ("#submitAttachmentsId"),
    CREATE_DRAFT_BUTTON ("#btnCreate"),
    YES (".bootbox-accept"),
    BILLING_TYPE ("#select2-billingTypeId-container"),
    BILLABLE_TO_CUSTOMER("#select2-billableToCustomer-container");

    private final String locatorsName;

    //TODO Constructor
    LPrCreate(String locatorsName) {
        this.locatorsName = locatorsName;
    }

    //TODO Methods to get dynamic locators
    public static String getPrType(String type) {
        return "//a[@href='/Procurement/Requisitions/NonPOC_" + type + "_Create']";
    }

    public static String getString(String something){
        return "//li[contains(text(),'" + something + "')]";
    }


    public String getLocator(){return locatorsName;}

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