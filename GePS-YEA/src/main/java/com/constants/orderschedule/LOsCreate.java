package com.constants.orderschedule;

public class LOsCreate {

    public static final String PO_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Orders')]";
    public static final String PO_REFERENCE_ID = "#referenceId";
    public static final String CREATE_OS_BUTTON = "#btnCreateOR";
    public static final String TODAY = "//span[@class='flatpickr-day today']";
    public static final String SCHEDULE_DATE = ".scheduleDate-1";
    public static final String CREATE_BUTTON = "#btnCreate";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";

//TODO Constructor
    private LOsCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }
}
