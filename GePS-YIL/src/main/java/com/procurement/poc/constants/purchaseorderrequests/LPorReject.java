package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorReject {

    POR_NAVIGATION_BAR ("//*[contains(text(), 'Purchase Order Requests')]"),
    REJECT_BUTTON("#btnReject"),
    REMARKS_INPUT(".bootbox-input"),
    YES(".bootbox-accept");

    private String locatorName;
    //TODO Constructor
    LPorReject(String locatorName){ this.locatorName = locatorName;}

    public static String getTitle(String title){
        return "//span[contains(text(), '"+ title +"')]";
    }

    public String getLocator(){
        return locatorName;
    }
}