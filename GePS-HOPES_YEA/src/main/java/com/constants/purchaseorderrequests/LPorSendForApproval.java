package com.constants.purchaseorderrequests;

public class LPorSendForApproval {

    public static final String POR_NAVIGATION_BAR = "//*[contains(text(), 'Purchase Order Requests')]";
    public static final String SEND_FOR_APPROVAL__BUTTON = "#btnNewSendApproval";
    public static final String APPROVAL_POP_UP = "//h3[contains(text(), 'Purchase Order Request Send For Approval')]";
    public static final String CFO_DROPDOWN_LOCATOR = "#role-7";
    public static final String PRESIDENT_DROPDOWN_LOCATOR = "#select2-role-8-container";
    public static final String SUBMIT_BUTTON = "btnSendUserFromPM";
    public static final String APPROVERS_LIST = "#approvalData tbody tr td";
    public static final String YES = ".bootbox-accept";

//TODO Constructor
    private LPorSendForApproval(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getCfoId(String id){
        String cfoId = "//span[contains(text(), '"+ id +"')]";
        return cfoId;
    }

    public static String getPresidentId(String id){
        String presidentId = "//li[contains(text(), '" + id + "')]";
        return presidentId;
    }
}
