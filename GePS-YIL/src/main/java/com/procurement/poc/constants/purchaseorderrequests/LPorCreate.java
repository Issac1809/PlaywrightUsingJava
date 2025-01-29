package com.procurement.poc.constants.purchaseorderrequests;

public enum LPorCreate {

    RFQ_NAVIGATION_BAR("//*[contains(text(), 'Request For Quotations')]"),
    PR_NAVIGATION_BAR("#ni-requisitions"),
    POR_CREATE_BUTTON("//a[contains(@href, '/Procurement/PurchaseOrderRequests/POC_NonCatalog_Create') and contains(text(), 'Create POR')]"),
    CREATE("#btnCreatePOR"),
    BELOW_5L("#below5L"),
    TAX_CODE("-- Select Tax Codes --"),
    POR_NOTES("#notes"),
    PRESELECTED_YES("#preselectedYes"),
    CREATE_BUTTON("#btnCreate"),
    YES("//button[contains(text(),'Yes')]");


    private String  locatorName;
//TODO Constructor
    LPorCreate(String locatorName){ this.locatorName = locatorName;}

    public static String getString(String string){
        return "//span[contains(text(), '"+ string +"')]";
    }

    public String getLocator(){
        return locatorName;
    }
}