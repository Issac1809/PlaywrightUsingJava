package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorApprove {

    MY_APPROVALS("//span[contains(text(), 'My Approvals')]"),
    ADD_APPROVERS("#btnAddApprovers"),
    PROJECT_MANAGER_POP_UP("//h3[contains(text(), 'Purchase Order Request Send For Approval')]"),
    PROJECT_MANAGER_DROP_DOWN("#select2-PMBId-container"),
    SEARCH_FIELD("select2-search__field"),
    DEPARTMENT_MANAGER_DROP_DOWN("#select2-departmentManagerId-container"),
    DIVISION_MANAGER("#select2-divisionManagerId-container"),
    SAVE_APPROVAL_USERS("#btnSendUserFromPM"),
    APPROVE_BUTTON("#btnApprove"),
    ACCEPT_BUTTON(".bootbox-accept");

    private final String locatorsName;

    //TODO Constructor
    LPorApprove(String locatorsName) {
        this.locatorsName = locatorsName;
    }

    public static String getString(String title){
        return "//span[contains(text(), '"+ title +"')]";
    }

    public String getLocator(){return locatorsName;}
    
}
