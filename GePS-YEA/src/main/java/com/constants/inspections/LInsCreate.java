package com.constants.inspections;

public class LInsCreate {

    public static final String OS_NAVIGATION_BAR = "//*[contains(text(), 'Order Schedules')]";
    public static final String LIST_CONTAINER = "#listContainer tr td";
    public static final String DETAILS_BUTTON = ".btn-link";
    public static final String CREATE_INSPECTION_BUTTON = "#btnForCreateInspection";
    public static final String PHYSICAL_INSPECTION_NOT_REQUIRED = "#physicalInspectionNotReq";
    public static final String CREATE_BUTTON = "#btnCreateInspection";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";

//TODO Constructor
    private LInsCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }
}