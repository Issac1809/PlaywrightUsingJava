package com.constants.purchaseorders;

public class LPoCreate {

    public static final String POR_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Order Requests')]";
    public static final String CREATE_PO_BUTTON = "#createPOContainer";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";

//TODO Constructor
    private LPoCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }
}
