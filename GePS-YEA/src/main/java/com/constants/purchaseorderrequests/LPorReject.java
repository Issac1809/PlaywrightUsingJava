package com.constants.purchaseorderrequests;

public class LPorReject {

    public static final String POR_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Order Requests')]";
    public static final String REJECT_BUTTON = "#btnReject";
    public static final String REMARKS_INPUT = ".bootbox-input";
    public static final String YES = ".bootbox-accept";

//TODO Constructor
    private LPorReject(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }
}