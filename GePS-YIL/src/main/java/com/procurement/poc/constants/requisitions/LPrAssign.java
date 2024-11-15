package com.procurement.poc.constants.requisitions;

public enum LPrAssign {

    ASSIGN_USER("#btnAssignUser"),
    SEARCHBOX(".select2-search__field"),
    SELECT_ASSIGN_USER("#select2-bgUser-container"),
    SAVE_USER("#saveBGAssign");


    private final String locatorsName;
//TODO Constructor
    LPrAssign(String locatorsName){
        this.locatorsName = locatorsName;
    }


    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getBuyerMailId(String mailId){
        String mailId1 = "//li[contains(text(), '"+ mailId +"')]";
        return mailId1;
    }
    public String getLocator(){return locatorsName;}
}