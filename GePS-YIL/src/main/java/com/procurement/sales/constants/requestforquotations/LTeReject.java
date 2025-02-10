package com.procurement.sales.constants.requestforquotations;

public enum LTeReject {

    RFQ_NAVIGATION_BAR ("//*[contains(text(), 'Request For Quotations')]"),
    TE_CREATE_BUTTON ("#btnCreateTE"),
    VENDOR_SELECT_CHECKBOX (".border-primary"),
    CREATE_TECHNICAL_EVALUATION_BUTTON ("#btnCreate"),
    SEND_FOR_APPROVAL ("#btnSendApproval"),
    APPROVER_SELECT (".select2-selection--single"),
    SEARCH_FIELD (".select2-search__field"),
    SAVE_APPROVER ("#saveApproverAssign"),
    REJECT_BUTTON ("#btnReject"),
    YES (".bootbox-accept"),
    REMARKS_INPUT_LOCATOR (".bootbox-input");

    private final String locatorName;
    //TODO Constructor
    LTeReject(String locatorName){
        this.locatorName = locatorName;
    }

    public String getLocator(){
        return locatorName;
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '" + title + "')]";
        return title1;
    }

    public static String getTeApprover(String approver){
        String approver1 = "//li[contains(text(), '"+ approver +"')]";
        return approver1;
    }
}
