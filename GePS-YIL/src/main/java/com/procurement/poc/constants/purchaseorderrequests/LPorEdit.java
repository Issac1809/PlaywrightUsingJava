package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorEdit {

    POR_NAVIGATION_BAR("//*[contains(text(), 'Purchase Order Requests')]"),
    EDIT_BUTTON("#btnEdit"),
    UPDATE_BUTTON("#btnUpdate"),
    REMARKS_INPUT(".bootbox-input"),
    YES(".bootbox-accept");


    private String locatorName;
//TODO Constructor
    LPorEdit(String locatorName){ this.locatorName = locatorName;}

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }

    public String getLocator(){
        return locatorName;
    }
}
