package com.procurement.poc.constants.requestforquotations;

public enum LQuoSubmit {

    RFQ_NAVIGATION_BAR("//*[contains(text(), 'Request For Quotations')]"),
    INVITE_VENDORS("#addRequestForQuotationVendors"),
    VENDOR_SEARCH_FIELD("#select2-vendorId-container"),
    VENDOR_SEARCH(".select2-search__field"),
    INVITE_BUTTON("#saveRequestForQuotationVendor"),
    VENDOR_EMAIL_POP_UP("#vendorSendMailBtnId"),
    SEND_QUOTE_BUTTON("#btnSendQuote"),
    INCOTERM_LOCATION("#incotermLocation"),
    QUOTATION_REFERENCE_NUMBER("#quotationReferenceNumber"),
    VALIDITY_DATE("#dates"),
    TODAY("//span[@class='flatpickr-day today']"),
    NEXT_MONTH_BUTTON("//i[contains(@class,'bi-chevron-right')]"),
    FIRST_DAY_OF_NEXT_MONTH(".flatpickr-day.nextMonthDay"),
    DAY20("//span[contains(text(),'20') and contains(@class,'flatpickr')]"),
    LIQUIDATED_DAMAGES("#liquidatedComplyId"),
    ROHS_COMPLIANCE("#rohsComplyId"),
    WARRANTY_REQUIREMENTS("#warrantyRequirementsComplyId"),
    PACKING_FORWARDING("#packingForwardingComplyId"),
    FREIGHT("#freightComplyId"),
    INSURANCE("#insuranceComplyId"),
    BANK_GUARANTEE("#bankGuaranteeComplyId"),
    RFQ_ITEM_LIST("#rfqVendorItems-container tr td label[id^='lineNumber-']"),
    HS_CODE("#hsnsac-"),
    MAKE("#make-"),
    MODEL("#model-"),
    PART_NUMBER("#partNumber-"),
    COUNTRY_OF_ORIGIN("#countryOfOrigin-"),
    RATE("#rate-"),
    CGST("#cgst-"),
    SGST("#sgst-"),
    IGST("#igst-"),
    DISCOUNT("#discount-"),
    LEAD_TIME("#leadTime-"),
    QUOTATION_NOTES("#notes-"),
    GST("#gstId"),
    ATTACH_FILE("#attachFile"),
    FILE_INPUT("#formFilePreupload"),
    ATTACHMENT_TYPE_DROPDOWN("#select2-attachmentTypeId-container"),
    SAVE_ATTACHMENTS("#attachmentSaveId"),
    CREATE_BUTTON("#btnCreate"),
    ACCEPT_BUTTON_LOCATOR(".bootbox-accept");


    private final String locatorName;


    LQuoSubmit(String locatorName){ this.locatorName =  locatorName;
    }

    public static String getString(String string){
        return "//span[contains(text(), '"+ string +"')]";
    }

    public static String getListOption(String string){
        return "//li[contains(text(), '"+ string +"')]";
    }


    public String getLocator(){
        return  locatorName;
    }

    public String getApi(){
        return locatorName;
    }
}