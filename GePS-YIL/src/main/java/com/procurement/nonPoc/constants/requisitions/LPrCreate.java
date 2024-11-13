package com.procurement.nonPoc.constants.requisitions;

public enum LPrCreate {

    SEARCH(".select2-search__field"),

    CREATE_BUTTON("//button[@data-bs-toggle='modal']"),
    TITLE ("#title"),
    SHIP_TO_YOKOGAWA ("#shipToYokogawa"),
    SALES_ORDER ("#select2-salesOrderId-container"),
    DEPARTMENT_PIC ("#select2-searchDepartmentPic-container"),
    VENDOR ("#select2-vendor-container"),
    RATE_CONTRACT ("#select2-rateContract-container"),
    INCOTERM ("#select2-incoterm-container"),
    SHIPPING_ADDRESS ("#select2-endusersId-container"),
    CATALOG_SHIPPING_MODE ("#select2-shippingMode-container"),
    NON_CATALOG_MH_SHIPPING_MODE ("#select2-shippingmodeid-container"),
    QUOTATION_REQUIRED_BY ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_PO_ISSUE ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_DELIVERY ("//*[@id='dates']/div[2]/input[2]"),
    TODAY ("//span[@class='flatpickr-day today']"),
    BUYER_GROUP ("#select2-buyerGroup-container"),
    CHECKER ("#select2-checker-container"),
    ROHS_COMPLIANCE ("#rohsnotcomplianceid"),
    OI_AND_TP_CURRENCY ("#select2-oiTpCurrencyId-container"),
    ORDER_INTAKE ("#orderIntake"),
    TARGET_PRICE ("#targetPrice"),
    CASE_MARKING ("#caseMarking"),
    MESSAGE_TO_SOURCING ("#messageToSourcing"),
    WARRANTY_REQUIREMENTS ("#select2-warrantyrequirementsid-container"),
    PRICE_VALIDITY ("#select2-pricevalidityid-container"),
    INSPECTION_REQUIRED ("#inspectRequired"),
    LIQUIDATED_DAMAGES_SELECT ("#isLDStandardNoId"),
    LIQUIDATED_DAMAGES ("#liquidatedamageTextId"),
    ADD_LINE_ITEM_BUTTON ("#addLineRequisitionItems"),
    ITEMS ("#select2-item-container"),
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

    // Constructor
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

}