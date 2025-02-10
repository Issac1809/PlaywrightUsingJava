package com.procurement.poc.constants.requisitions;

public enum LPrEdit {

    EDIT_BUTTON("#btnEdit"),
    UPDATE_BUTTON("#btnUpdate"),
    ACCEPT("//button[contains(@class, 'bootbox-accept')]");
    private final String locatorsName;
//TODO Constructor
    LPrEdit(String locatorsName){
        this.locatorsName = locatorsName;
    }

    public static String getTitle(String title){
        return "//*[contains(text(), '" + title + "')]";
    }

    public String getLocator(){return locatorsName;}
}