package com.procurement.poc.constants.requestforquotations;

public enum LTeCreate {

    RFQ_NAVIGATION_BAR("//*[contains(text(), 'Request For Quotations')]"),
    TE_CREATE_BUTTON("#btnCreateTE"),
    VENDOR_SELECT_CHECKBOX(".border-primary"),
    CREATE_TECHNICAL_EVALUATION_BUTTON("#btnCreate"),
    SEND_FOR_APPROVAL("#btnSendApproval"),
    APPROVER_SELECT(".select2-selection--single"),
    SEARCH_FIELD(".select2-search__field"),
    SAVE_APPROVER("#saveApproverAssign"),
    YES(".bootbox-accept"),
    APPROVE_BUTTON("#btnApprove");

    private final String locatorName;
    LTeCreate(String locatorName){ this.locatorName =  locatorName; }

    public String getLocator(){
        return  locatorName;
    }

    public static String getString(String string){
        return "//span[contains(text(), '"+ string +"')]";
    }

    public static String getTeApprover(String approver){
        String approver1 = "//li[contains(text(), '"+ approver +"')]";
        return approver1;
    }


    public String getApi(){
        return locatorName;
    }
}
