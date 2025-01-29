package com.ps.classes.requisition.suspend;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrEdit;
import com.ps.interfaces.requisitions.IPrBuyerSuspend;
import java.util.Properties;
import static com.constants.requisitions.LPrBuyerSuspend.*;

public class BuyerSuspend implements IPrBuyerSuspend {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrEdit iPrEdit;

    private BuyerSuspend(){
    }

//TODO Constructor
    public BuyerSuspend(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
    }

    public void suspend(){
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

        String title = properties.getProperty("orderTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        titleLocator.first().click();

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        suspendButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS);
        remarksLocator.fill("Buyer Suspended");

        Locator yesButtonLocator = page.locator(YES);
        yesButtonLocator.click();
        iLogout.performLogout();
//        iPrEdit.buyerSuspendEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}