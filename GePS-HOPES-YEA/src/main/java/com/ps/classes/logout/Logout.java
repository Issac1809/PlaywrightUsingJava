package com.ps.classes.logout;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.ps.interfaces.logout.ILogout;
import static com.constants.logout.LLogout.*;

public class Logout implements ILogout {

    Page page;

//TODO Constructor
    public Logout(Page page) {
        this.page = page;
    }

    public void performLogout() {
        try {

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator loginAvatarLocator = page.locator(LOGIN_AVATAR);
            loginAvatarLocator.click();

            Locator singOutLocator = page.locator(SIGN_OUT);
            singOutLocator.click();
        } catch (Exception exception) {
            System.out.println("Error in Perform Logout Function: " + exception.getMessage());
        }
    }
}