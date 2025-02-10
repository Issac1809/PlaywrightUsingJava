package com.procurement.sales.constants.requisitions;

public enum LPrBuyerSuspend {

    SUSPEND_BUTTON("#btnSuspend"),
    REMARKS(".bootbox-input"),
    YES(".bootbox-accept"),
    STATUS("//span[@id='status']//span");

    private final String locatorName;
    //TODO Constructor
    LPrBuyerSuspend(String locatorName){
        this.locatorName = locatorName;
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '" + title + "')]";
        return title1;
    }

    public String getLocator(){
        return locatorName;
    }
}