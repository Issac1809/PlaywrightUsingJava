package com.constants.disptchnotes;

public class LDnCreate {

    public static final String PO_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Orders')]";
    public static final String DN_CREATE_BUTTON = "#btnCreateDispatchNote";
    public static final String SOURCE_COUNTRY_CODE = "#select2-sourceCountryId-container";
    public static final String SEARCH_FIELD = ".select2-search__field";
    public static final String DESTINATION_COUNTRY_CODE = "#select2-destinationCountryId-container";
    public static final String ADD_DISPATCH_NOTE_PACKAGES_BUTTON = "#addDispatchNotePackages";
    public static final String PACKAGE_TYPE = "#select2-packagetypeId-container";
    public static final String GROSS_WEIGHT = "#grossWeight";
    public static final String NET_WEIGHT = "#netWeight";
    public static final String VOLUME = "#volume";
    public static final String QUANTITY = "#quantity";
    public static final String SAVE_DISPATCH_NOTE_PACKAGES_BUTTON = "#saveDispatchNotePackages";
    public static final String CREATE_BUTTON = "#btnCreate";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";


//TODO Constructor
    private LDnCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getSourceCountry(String sourceCountry){
        String sourceCountryCode = "//li[contains(text(), '"+ sourceCountry +"')]";
        return sourceCountryCode;
    }

    public static String getDestinationCountry(String destinationCountry){
        String destinationCountryCode = "//li[contains(text(), '"+ destinationCountry +"')]";
        return destinationCountryCode;
    }

    public static String getPackageType(String packageType){
        String packageType1 = "//li[contains(text(), '"+ packageType +"')]";
        return packageType1;
    }
}
