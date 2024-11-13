package com.poc.interfaces.login;
import com.microsoft.playwright.Page;

public interface ILogin {
    void performLogin();
    void performLogin(String mailId);
    void performLogin(String mailId, Page page);
}