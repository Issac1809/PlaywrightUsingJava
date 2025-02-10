package com.procurement.sales.constants.requisitions;

public enum LPrApprove {

    MY_APPROVALS("#ni-my-approvals"),
    APPROVE("#btnApprove"),
    YES(".bootbox-accept");

    private final String locatorName;
//TODO Constructor
    LPrApprove(String locatorName){
        this.locatorName = locatorName;
    }

    public static String getString(String title){
        return "//*[contains(text(), '" + title + "')]";
    }

    public String getLocator(){
        return locatorName;
    }
}