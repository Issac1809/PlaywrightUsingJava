package com.procurement.poc.constants.requestforquotations;

public enum LRfqEdit {

    RFQ_NAVIGATION_BAR("//*[contains(text(), 'Request For Quotations')]"),
    EDIT_BUTTON("#btnEditRfq"),
    UPDATE_BUTTON("#btnUpdate"),
    REMARKS_POP_UP(".bootbox-input"),
    REMARKS("Updated"),
    ACCEPT_REMARKS_POP_UP(".bootbox-accept"),
    ;


    private final String locatorName;
    //TODO Constructor
    LRfqEdit(String locatorName){
        this.locatorName = locatorName;
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '" + title + "')]";
        return title1;
    }

    public String getLocator(){
        return locatorName;
    }
    public String getAPI(){
        return locatorName;
    }
}
