package com.constants.requestforquotations;

public class LRfqCreate {

//TODO Constructor
    private LRfqCreate(){
    }

    public static String getTitle(String title){
        String title1 = "//*[contains(text(), '" + title + "')]";
        return title1;
    }
}
