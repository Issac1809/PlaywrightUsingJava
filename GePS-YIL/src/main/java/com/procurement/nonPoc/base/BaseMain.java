package com.procurement.nonPoc.base;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.procurement.nonPoc.classes.login.Login;
import com.procurement.nonPoc.classes.logout.Logout;
import com.procurement.nonPoc.classes.requisition.approve.Approve;
import com.procurement.nonPoc.classes.requisition.assign.Assign;
import com.procurement.nonPoc.classes.requisition.create.Create;
import com.procurement.nonPoc.classes.requisition.edit.Edit;
import com.procurement.nonPoc.classes.requisition.reject.Reject;
import com.procurement.nonPoc.classes.requisition.sendforapproval.SendForApproval;
import com.procurement.nonPoc.classes.requisition.suspend.BuyerSuspend;
import com.procurement.nonPoc.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.procurement.nonPoc.interfaces.login.ILogin;
import com.procurement.nonPoc.interfaces.logout.ILogout;
import com.procurement.nonPoc.interfaces.requisitions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class BaseMain {

    protected Logger logger;
    protected PlaywrightFactory playwrightFactory;
    protected Properties properties;
    protected Page page;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected IPrSendForApproval iPrSendForApproval;
    protected IPrReject iPrReject;
    protected IPrApprove iPrApprove;
    protected IPrAssign iPrAssign;
    protected IPrSuspend iPrSuspend;

//TODO Constructor
    public BaseMain(){
        try {
            logger = LoggerFactory.getLogger(BaseMain.class);
            playwrightFactory = new PlaywrightFactory();
            properties = playwrightFactory.initializeProperties();
            page = playwrightFactory.initializePage(properties);

//TODO Requisition
            iLogin = (ILogin) new Login(properties, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(iLogin, properties, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
            iPrSendForApproval = new SendForApproval(iLogin, properties, page, iLogout);
            iPrApprove = new Approve(iLogin, properties, page, iLogout);
            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            iPrEdit = new Edit(iLogin, properties, page, iLogout, iPrSendForApproval, iPrApprove, iPrAssign);
            iPrReject = new Reject(iLogin, properties, page, iLogout, iPrEdit);
            iPrSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);

        } catch (Exception exception) {
            logger.error("Error initializing BaseMain", exception);
        }
    }
}