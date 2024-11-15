package com.constants.disptchnotes;

public class LDnAssign {

    public static final String DN_NAVIGATION_BAR = "//*[contains(text(), 'Dispatch Notes')]";
    public static final String LIST_CONTAINER = "#listContainer tr td";
    public static final String DETAILS_BUTTON = ".btn-link";
    public static final String DISPATCH_NOTES_REFERENCE_ID = "#dispatchNote";
    public static final String DROP_DOWN = "#dnActionDropdown";
    public static final String ASSIGN_BUTTON = "#btnToAssign";
    public static final String SELECT_LOGISTICS_MANAGER_DROP_DOWN = "#select2-assignerId-container";
    public static final String SEARCH_FIELD = ".select2-search__field";
    public static final String SAVE_BUTTON = "#saveAssine";

//TODO Constructor
    private LDnAssign(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getLogisticsManagerId(String id){
        String logisticsManager = "//li[contains(text(), '" + id + "')]";
        return logisticsManager;
    }
}