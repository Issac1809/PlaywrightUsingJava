package com.procurement.poc.constants.logout;

public enum LLogout {
    EMAIL("#Input_Email"),
    LOGIN_AVATAR(".avatar-img"),
    SIGN_OUT( "//a[@onclick='user_logout()']");

    private final String locatorsName;

    LLogout(String locatorName) {
        this.locatorsName = locatorName;
    }
    // Getter method
    public String getLocatorsName() {
        return locatorsName;
    }
}
