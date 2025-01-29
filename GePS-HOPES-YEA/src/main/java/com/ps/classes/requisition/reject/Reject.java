package com.ps.classes.requisition.reject;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrEdit;
import com.ps.interfaces.requisitions.IPrReject;
import com.ps.interfaces.requisitions.IPrSendForApproval;
import java.util.Properties;
import static com.constants.requisitions.LPrReject.*;

public class Reject implements IPrReject {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrEdit iPrEdit;
    private IPrSendForApproval iPrSendForApproval;

    private Reject(){
    }

//TODO Constructor
    public Reject(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public int reject() {
        int rejectStatus = 0;
        try {
            String[] approvers = properties.getProperty("requisitionApprovers").split(",");
            String uid = properties.getProperty("requisitionUid");

            for(String approver : approvers){
                iLogin.performLogin(approver);

                String title = properties.getProperty("orderTitle");
                String getTitle = getTitle(title);
                Locator titleLocator = page.locator(getTitle);
                titleLocator.first().click();

                Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
                rejectButtonLocator.click();

                String remarks = properties.getProperty("rejectRemarks");
                Locator rejectRemarksLocator = page.locator(REJECTED_REMARKS);
                rejectRemarksLocator.fill(remarks + " " + "by" + " " + approver);

                Locator yesButtonLocator = page.locator(SUBMIT_BUTTON);
                yesButtonLocator.click();

                APIResponse rejectResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions/" + uid, RequestOptions.create());
                rejectStatus = rejectResponse.status();

                page.waitForLoadState(LoadState.NETWORKIDLE);
                break;
            }

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("Error in Requisition Reject Function: " + error.getMessage());
        }
        return rejectStatus;
    }
}