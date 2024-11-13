package com.procurement.poc.constants.requisitions;

public enum LPrSendForApproval {

    SEND_FOR_APPROVAL_BUTTON("#btnSendApproval"),
    YES("//button[contains(text(), 'Yes')]");

    private final String locatorsName;
    //TODO Constructor
    LPrSendForApproval(String locatorsName){
        this.locatorsName = locatorsName;
    }

    public static String getTitle(String title){
        return "//*[contains(text(), '" + title + "')]";
    }

    public String getLocator(){return locatorsName;}
}