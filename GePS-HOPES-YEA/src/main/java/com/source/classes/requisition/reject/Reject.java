package com.source.classes.requisition.reject;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrEdit;
import com.source.interfaces.requisitions.IPrReject;
import com.source.interfaces.requisitions.IPrSendForApproval;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import static com.constants.requisitions.LPrReject.*;

public class Reject implements IPrReject {

    Logger logger;
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
        this.logger = LoggerUtil.getLogger(Reject.class);
    }

    public int reject(String purchaseType) {
        int rejectStatus = 0;
        try {
            String[] approvers = properties.getProperty("requisitionApprovers").split(",");
            String uid = properties.getProperty("requisitionUid");

            for(String approver : approvers){
                iLogin.performLogin(approver);

                String title;
                if(purchaseType.toLowerCase().equals("catalog")){
                    title = properties.getProperty("catalogTitle");
                } else {
                    title = properties.getProperty("nonCatalogTitle");
                }

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
                break;
            }

            iLogout.performLogout();
        } catch (Exception error) {
            logger.error("Error in Requisition Reject Function: " + error.getMessage());
        }
        return rejectStatus;
    }
}