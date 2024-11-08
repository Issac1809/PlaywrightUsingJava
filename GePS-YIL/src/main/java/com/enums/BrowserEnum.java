package com.enums;

public enum BrowserEnum {
    CHROMIUM(""),
    CHROME("chrome"),
    EDGE("msedge"),
    FIREFOX(""),
    SAFARI("");

    private final String browserName;

    // Constructor
    BrowserEnum(String browserName) {
        this.browserName = browserName;
    }

    // Getter method
    public String getBrowserName() {
        return browserName;
    }
}
