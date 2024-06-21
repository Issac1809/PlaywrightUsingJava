package com.procurement.YokogawaAsiaPrivateLimited;
import com.base.BaseMain;

public class YokogawaAsiaPrivateLimited {

    static BaseMain baseMain;

    public static void main(String[] args) {
        try {
        baseMain = new BaseMain();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}