package com.constants.purchaseorderrequests;

public class LPorEdit {

    public static final String POR_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Order Requests')]";
    public static final String EDIT_BUTTON = "#btnEdit";
    public static final String UPDATE_BUTTON = "#btnUpdate";
    public static final String REMARKS_INPUT = ".bootbox-input";
    public static final String YES = ".bootbox-accept";

//TODO Constructor
    private LPorEdit(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }
}
