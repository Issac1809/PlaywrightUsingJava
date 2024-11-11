package com.procurement;
import com.procurement.nonPoc.base.BaseMain;

public class GePS {

    static BaseMain baseMain;

//TODO Constructor
    private GePS(){
    }

    public static void main(String[] args) {
        try {
            baseMain = new BaseMain();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}