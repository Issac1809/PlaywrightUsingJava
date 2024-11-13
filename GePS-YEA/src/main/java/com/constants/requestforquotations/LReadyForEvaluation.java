package com.constants.requestforquotations;

public class LReadyForEvaluation {

    public static final String RFQ_NAVIGATION_BAR = "//*[contains(text(), 'Request For Quotations')]";
    public static final String READY_FOR_EVALUATION_BUTTON = "#btnReadyForEvalution";
    public static final String YES = ".bootbox-accept";

//TODO Constructor
    private LReadyForEvaluation(){
    }

    public static String getTitle(String title){
        String title1 = "//span[contains(text(), '" + title + "')]";
        return title1;
    }
}