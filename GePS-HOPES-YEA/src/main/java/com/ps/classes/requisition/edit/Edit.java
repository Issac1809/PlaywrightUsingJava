package com.ps.classes.requisition.edit;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrEdit;
import java.util.Properties;
import static com.constants.requisitions.LPrEdit.*;

public class Edit implements IPrEdit {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;

    private Edit(){
    }

//TODO Constructor
    public Edit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public int edit(String purchaseType) {
        int status = 0;
        try {
        String requesterEmailId = properties.getProperty("requesterEmail");
        iLogin.performLogin(requesterEmailId);

        String title;
        if(purchaseType.toLowerCase().equals("catalog")){
            title = properties.getProperty("catalogTitle");
        } else {
            title = properties.getProperty("nonCatalogTitle");
        }

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        titleLocator.first().click();

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        editButtonLocator.click();

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        page.waitForLoadState(LoadState.NETWORKIDLE); //This waits until there are no more ongoing network requests.
        updateButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES);
        yesButtonLocator.click();

        APIResponse updateResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions", RequestOptions.create());
        status = updateResponse.status();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("Error in Requisition Edit Function: " + error.getMessage());
        }
        return status;
    }

//    public void rejectEdit()  {
//        try {
//        edit();
//        iPrSendForApproval.sendForApproval();
//        } catch (Exception error) {
//            System.out.println("Error in Requisition Reject and Edit Function: " + error.getMessage());
//        }
//    }

//    public void buyerManagerSuspendEdit()  {
//        try {
//            edit();
//            iPrSendForApproval.sendForApproval();
////            iPrApprove.approve();
//            iPrAssign.buyerManagerLogin();
//        } catch (Exception error) {
//            System.out.println("Error in Requisition Buyer Manager Suspend Edit Function: " + error.getMessage());
//        }
//    }
//
//    public void buyerSuspendEdit()  {
//        try {
//        edit();
//        iPrSendForApproval.sendForApproval();
////        iPrApprove.approve();
//        iPrAssign.buyerManagerLogin();
//        iPrAssign.buyerManagerAssign();
//        } catch (Exception error) {
//            System.out.println("Error in Requisition Buyer Suspend Edit Function: " + error.getMessage());
//        }
//    }
}