package com.procurement.poc.constants.requisitions;

public enum LPrReject {

    REJECT_BUTTON("#btnReject"),
    REMARKS(".bootbox-input"),
    YES(".bootbox-accept");

    private final String locatorsName;
    //TODO Constructor
    LPrReject(String locatorsName){
        this.locatorsName = locatorsName;
    }

    public static String getTitle(String title){
        return "//*[contains(text(), '" + title + "')]";
    }

    public String getLocator(){return locatorsName;}
}
