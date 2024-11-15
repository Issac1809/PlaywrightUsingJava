package com.constants.disptchnotes;

public class LDnEdit {

    public static final String DN_NAVIGATION_BAR = "//*[contains(text(), 'Dispatch Notes')]";
    public static final String LIST_CONTAINER = "#listContainer tr td";
    public static final String DETAILS_BUTTON = ".btn-link";
    public static final String EDIT_BUTTON = "#btnEdit";
    public static final String UPDATE_BUTTON = "#btnUpdate";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";


//TODO Constructor
    private LDnEdit(){
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '"+ title +"')]";
        return title1;
    }
}
