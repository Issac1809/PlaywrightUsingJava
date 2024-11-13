package com.constants.purchaseorderrequests;

public class LPorApprove {

    public static final String MY_APPROVALS = "//span[contains(text(), 'My Approvals')]";
    public static final String ADD_APPROVERS = "#btnAddApprovers";
    public static final String PROJECT_MANAGER_POP_UP = "//h3[contains(text(), 'Purchase Order Request Send For Approval')]";
    public static final String PROJECT_MANAGER_DROP_DOWN = "#select2-PMBId-container";
    public static final String SEARCH_FIELD = "select2-search__field";
    public static final String DEPARTMENT_MANAGER_DROP_DOWN = "#select2-departmentManagerId-container";
    public static final String DIVISION_MANAGER = "#select2-divisionManagerId-container";
    public static final String SAVE_APPROVAL_USERS = "#btnSendUserFromPM";
    public static final String APPROVE_BUTTON = "#btnApprove";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";

//TODO Constructor
    private LPorApprove(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '"+ title +"')]";
        return title1;
    }

    public static String getGroupB(String groupB){
        String groupB1 = "//li[contains(text(), '"+ groupB +"')]";
        return groupB1;
    }

    public static String getGroupC(String groupC){
        String groupC1 = "//li[contains(text(), '"+ groupC +"')]";
        return groupC1;
    }

    public static String getGroupD(String groupD){
        String groupD1 = "//li[contains(text(), '"+ groupD +"')]";
        return groupD1;
    }
}
