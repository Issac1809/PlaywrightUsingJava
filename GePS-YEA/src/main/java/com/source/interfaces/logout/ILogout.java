package com.source.interfaces.logout;
import com.microsoft.playwright.Page;

public interface ILogout {
    void performLogout();
    void performLogout(Page page);
} 