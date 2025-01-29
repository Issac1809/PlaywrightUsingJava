package com.procurement.poc.constants.requestforquotations;

public enum LRfqCreate {

    CREATE_RFQ_BUTTON("#btnCreateRFQ"),
    LOAD_PROJECT("https://geps_hopes_yil.cormsquare.com/api/Locations/SearchByProjectId?id"),
    NOTES("#notes"),
    CREATE_BUTTON("#btnCreate"),
    YES_BUTTON("//button[contains(text(),'Yes')]"),

    LOADPAGE("https://geps_hopes_yil.cormsquare.com/Procurement/RequestForQuotations/POC_Create?uid")
    ;


    private final String locatorName;
//TODO Constructor
    LRfqCreate(String locatorName){
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
