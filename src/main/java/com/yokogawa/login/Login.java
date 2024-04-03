package com.yokogawa.login;
import com.microsoft.playwright.Page;

public interface Login {
    void Login(String mailId, Page page);
}
