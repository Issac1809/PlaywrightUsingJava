package com.enums;

public enum LLogin {
      EMAIL("#Input_Email"),
      PASSWORD("#Input_Password"),
      LOGIN_BUTTON("#login-submit"),
      RequisitionsNav_Button("#ni-requisitions");

    private final String locatorsName;

    // Constructor
    LLogin(String locatorName) {
        this.locatorsName = locatorName;
    }

    // Getter method
    public String getLocatorsName() {
        return locatorsName;
    }
}