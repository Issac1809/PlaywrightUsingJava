package com.procurement.poc.classes.logout;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.procurement.poc.interfaces.logout.ILogout;

import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.logout.LLogout.*;

public class Logout implements ILogout {

    Page page;

//TODO Constructor
    public Logout(Page page) {
        this.page = page;
    }

    public void performLogout() {
        try {
            Locator avatarLocator = page.locator(LOGIN_AVATAR.getLocatorsName());
            waitForLocator(avatarLocator);
            avatarLocator.click();
            Locator signOutLocator = page.locator(SIGN_OUT.getLocatorsName());
            waitForLocator(signOutLocator);
            signOutLocator.click();
        } catch (Exception error) {
            System.out.println("Logout error: " + error.getMessage());
        }
    }
}