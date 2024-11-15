package com.procurement.poc.constants.requestforquotations;

public class LTeReject {

    public static final String RFQ_NAVIGATION_BAR = "//*[contains(text(), 'Request For Quotations')]";
    public static final String TE_CREATE_BUTTON = "#btnCreateTE";
    public static final String VENDOR_SELECT_CHECKBOX = ".border-primary";
    public static final String CREATE_TECHNICAL_EVALUATION_BUTTON = "#btnCreate";
    public static final String SEND_FOR_APPROVAL = "#btnSendApproval";
    public static final String APPROVER_SELECT = ".select2-selection--single";
    public static final String SEARCH_FIELD = ".select2-search__field";
    public static final String SAVE_APPROVER = "#saveApproverAssign";
    public static final String REJECT_BUTTON = "#btnReject";
    public static final String YES = ".bootbox-accept";
    public static final String REMARKS_INPUT_LOCATOR = ".bootbox-input";

//TODO Constructor
    private LTeReject(){
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
