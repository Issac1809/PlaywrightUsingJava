package com.procurement.poc.constants.requestforquotations;

public enum LReadyForEvaluation {

    RFQ_NAVIGATION_BAR("//*[contains(text(), 'Request For Quotations')]"),
    READY_FOR_EVALUATION_BUTTON("#btnReadyForEvalution"),
    YES(".bootbox-accept");


    private final String locatorName;
    LReadyForEvaluation(String locatorName){ this.locatorName =  locatorName;
    }

    public static String getString(String string){
    return "//span[contains(text(), '"+ string +"')]";
    }

    public String getLocator(){
    return  locatorName;
    }

}