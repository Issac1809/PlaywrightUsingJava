package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorSendForApproval {

    POR_NAVIGATION_BAR("//*[contains(text(), 'Purchase Order Requests')]"),
    SEND_FOR_APPROVAL__BUTTON("#btnSendApproval"),
    APPROVAL_POP_UP("//h3[contains(text(), 'Purchase Order Request Send For Approval')]"),
    CFO_DROPDOWN_LOCATOR("#role-7"),
    PRESIDENT_DROPDOWN_LOCATOR("#select2-role-8-container"),
    SUBMIT_BUTTON("btnSendUserFromPM"),
    APPROVERS_LIST("#approvalData tbody tr td"),
    YES(".bootbox-accept");


    private String locatorName;
//TODO Constructor
    LPorSendForApproval(String locatorName){
        this.locatorName = locatorName;
    }

    public static String getString(String string){
        return "//span[contains(text(), '"+ string +"')]";
    }

    public String getLocator() {
        return locatorName;
    }
}
