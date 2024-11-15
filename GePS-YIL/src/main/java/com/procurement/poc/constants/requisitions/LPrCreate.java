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
    QUOTATION_REQUIRED_BY ("//div[@id='dates']/div[label[contains(text(),'Quotation')]]/input[not(@type='hidden')]"),
    EXPECTED_PO_ISSUE ("//div[@id='dates']/div[label[contains(text(),'Issue')]]/input[not(@type='hidden')]"),
    EXPECTED_PO_ISSUE_LABEL("//div[@id='dates']/div[label[contains(text(),'Issue')]]/label"),
    EXPECTED_DELIVERY ("//div[@id='dates']/div[label[contains(text(),'Delivery')]]/input[not(@type='hidden')]"),
    TODAY ("//span[@class='flatpickr-day today']"),
    BUYER_GROUP ("//div[label[contains(text(),'Buyer Group')]]//span[@class='select2-selection__rendered']"),
    CHECKER ("#select2-checkerId-container"),
    ROHS_COMPLIANCE ("#rohsnotcomplianceid"),
    OI_AND_TP_CURRENCY ("#select2-oiTpCurrencyId-container"),
    ORDER_INTAKE ("//input[@name='orderInTakeId']"),
    TARGET_PRICE ("//input[@name='targetPriceId']"),
    CASE_MARKING ("#caseMarking"),
    MESSAGE_TO_SOURCING ("#messageToSourcing"),
    WARRANTY_REQUIREMENTS ("#select2-warrantyrequirementsid-container"),
    PRICE_VALIDITY ("#select2-pricevalidityid-container"),
    INSPECTION_REQUIRED ("//div[label[text()='Inspection Required :']]//input[@value='true']"),
    LIQUIDATED_DAMAGES_SELECT ("#isLDStandardNoId"),
    LIQUIDATED_DAMAGES ("#liquidatedamageTextId"),
    TYPE_OF_PURCHASE("#select2-typeOfPurchaseId-container"),
    ADD_LINE_ITEM_BUTTON ("#addLineRequisitionItems"),
    CANCEL_ITEM_IMPORT("//button[(@aria-label='Close') and (contains(text(),'Cancel'))]"),
    SELECT_RATE_CONTRACT_DOWNLOAD("//table[@id='tableExportCatalog']//a"),
    SEARCH_RC_FOR_DOWNLOAD("//div[@class='dataTables_filter']//input"),
    ITEMS ("//div[label[contains(text(),'Item')]]//span[contains(text(),'Item -')]"),
    IMPORT_ITEM("#importItem"),
    DOWNLOAD_RATE_CONTRACT("#btntoCatalogExport"),
    CHOOSE_FILE("#formFile"),
    UPLOAD("#btnUpload"),
    FIRST_ITEM("//ul[@class='select2-results__options']/li[1]"),
    ADD_ITEM_BUTTON ("#saveRequisitionItem"),
    QUANTITY("#quantity"),
    DESCRIPTION("#description"),
    NOTES ("#notes"),
    ATTACHMENTS ("#attachDocs"),
    FILE_UPLOAD ("#formFilePreupload"),
    EXTERNAL_RADIO_BUTTON ("#radioInActive"),
    ATTACH_FILE_BUTTON ("#saveAttachments1"),
    CONTINUE_BUTTON ("#submitAttachmentsId"),
    CREATE_DRAFT_BUTTON ("#btnCreate"),
    YES (".bootbox-accept"),
    BILLING_TYPE ("#select2-billingTypeId-container"),
    BILLABLE_TO_CUSTOMER("#select2-billableToCustomer-container"),

    GET_WBS_API("https://dprocure-uat.cormsquare.com/api/workBreakdownStructures/search?projectid"),
    GET_RATE_CONTRACTS_API("https://dprocure-uat.cormsquare.com/api/RateContractsByVendorId?vendorId"),
    POC_DETAILS_PAGE_API("https://dprocure-uat.cormsquare.com/Procurement/Requisitions/POC_Details")
    ;

    private final String locatorsName;

    //TODO Constructor
    LPrCreate(String locatorsName) {
        this.locatorsName = locatorsName;
    }
    
    //TODO Methods to get dynamic locators
    public static String getPrType(String type) {
        if(type.equals("catalog"))
            return "//a[@href='/Procurement/Requisitions/POC_Catalog_Create']";
        else
            return "//a[@href='/Procurement/Requisitions/POC_NonCatalog_Create']";
    }

    public static String getString(String something){
        return "//li[contains(text(),'" + something + "')]";
    }

    public String getLocator(){return locatorsName;}

    public String getAPI(){
        return locatorsName;
    }

}