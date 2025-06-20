package com.source.classes.logout;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.logout.LLogout.*;

public class Logout implements ILogout {

    Logger logger;
    Page page;

//TODO Constructor
    public Logout(Page page) {
        this.page = page;
        logger = LoggerUtil.getLogger(Logout.class);
    }

    public void performLogout() {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator loginAvatarLocator = page.locator(LOGIN_AVATAR);
            loginAvatarLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator singOutLocator = page.locator(SIGN_OUT);
            singOutLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Error in Perform Logout Function: {}", exception.getMessage());
        }
    }

    //TODO This function is only used for currency Exchange Rate
    public void performLogout(Page page) {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator loginAvatarLocator = page.locator(LOGIN_AVATAR);
            loginAvatarLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator singOutLocator = page.locator(SIGN_OUT);
            singOutLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception exception) {
            logger.error("Error in Perform Logout Function for Admin (Currency Exchange Rate): {}", exception.getMessage());
        }
    }
}