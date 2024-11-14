package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorSuspend {

    POR_NAVIGATION_BAR("//*[contains(text(), 'Purchase Order Requests')]"),
    SUSPEND_BUTTON("#btnToSuspendPOR"),
    YES(".bootbox-accept"),
    REMARKS_INPUT_LOCATOR(".bootbox-input");

    private String locatorName;
    //TODO Constructor
    LPorSuspend(String locatorName){ this.locatorName = locatorName;}

    public static String getTitle(String title){
        return "//span[contains(text(), '"+ title +"')]";
    }

    public String getLocator(){
        return locatorName;
    }
}
