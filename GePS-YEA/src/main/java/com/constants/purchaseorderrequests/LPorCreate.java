package com.constants.purchaseorderrequests;

public class LPorCreate {

    public static final String RFQ_NAVIGATION_BAR = "//*[contains(text(), 'Request For Quotations')]";
    public static final String POR_CREATE_BUTTON = "//a[contains(text(),' Create POR ')]";
    public static final String BELOW_5L = "#below5L";
    public static final String TAX_CODE = "-- Select Tax Codes --";
    public static final String POR_NOTES = "#notes";
    public static final String CREATE_BUTTON = "#btnCreate";
    public static final String YES = "//button[contains(text(),'Yes')]";

//TODO Constructor
    private LPorCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getTaxCode(String taxCode){
        String taxcode1 = "//li[contains(text(), '"+ taxCode +"')]";
        return taxcode1;
    }
}