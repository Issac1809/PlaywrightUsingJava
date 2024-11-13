package com.procurement.poc.constants.requisitions;

public enum LPrCreate {

    SEARCH(".select2-search__field"),

    CREATE_BUTTON("//button[@data-bs-toggle='modal']"),
    TITLE ("#title"),
    SHIP_TO_YOKOGAWA ("#shipToYokogawa"),
    PROJECT ("#select2-projectId-container"),
    WBS("#select2-wbsId-container"),
    SALES_ORDER ("#select2-salesOrderId-container"),
    DEPARTMENT_PIC ("#select2-searchDepartmentPic-container"),
    VENDOR ("#select2-vendorId-container"),
    RATE_CONTRACT ("#select2-rateContractId-container"),
    INCOTERM ("#select2-incoterm-container"),
    SHIPPING_ADDRESS ("#select2-endusersId-container"),
    CATALOG_SHIPPING_MODE ("#select2-shippingModeId-container"),
    NON_CATALOG_MH_SHIPPING_MODE ("#select2-shippingmodeid-container"),
    QUOTATION_REQUIRED_BY ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_PO_ISSUE ("//*[@id='dates']/div[1]/input[2]"),
    EXPECTED_DELIVERY ("//*[@id='dates']/div[2]/input[2]"),
    TODAY ("//span[@class='flatpickr-day today']"),
    BUYER_GROUP ("#select2-buyerGroupId-container"),
    CHECKER ("#select2-checkerId-container"),
    ROHS_COMPLIANCE ("#rohsnotcomplianceid"),
    OI_AND_TP_CURRENCY ("#select2-oiTpCurrencyId-container"),
    ORDER_INTAKE ("#orderInTakeId"),
    TARGET_PRICE ("//input[@id='targetPriceId']"),
    CASE_MARKING ("#caseMarking"),
    MESSAGE_TO_SOURCING ("#messageToSourcing"),
    WARRANTY_REQUIREMENTS ("#select2-warrantyrequirementsid-container"),
    PRICE_VALIDITY ("#select2-pricevalidityid-container"),
    INSPECTION_REQUIRED ("#inspectRequired"),
    LIQUIDATED_DAMAGES_SELECT ("#isLDStandardNoId"),
    LIQUIDATED_DAMAGES ("#liquidatedamageTextId"),
    ADD_LINE_ITEM_BUTTON ("#addLineRequisitionItems"),
    ITEMS ("#select2-rcitemId-container"),
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
        return "//a[@href='/Procurement/Requisitions/POC_" + type + "_Create']";
    }

    public static String getString(String something){
        return "//li[contains(text(),'" + something + "')]";
    }

    public String getLocator(){return locatorsName;}

}