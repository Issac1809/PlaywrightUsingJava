package com.ps.base;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.ps.classes.logout.Logout;
import com.ps.classes.requisition.reject.Reject;
import com.ps.interfaces.requisitions.*;
import com.ps.classes.requisition.approve.Approve;
import com.ps.classes.requisition.assign.Assign;
import com.ps.classes.requisition.create.Create;
import com.ps.classes.requisition.edit.Edit;
import com.ps.classes.requisition.sendforapproval.SendForApproval;
import com.ps.classes.requisition.suspend.BuyerSuspend;
import com.ps.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.ps.interfaces.login.ILogin;
import com.ps.classes.login.Login;
import com.ps.interfaces.logout.ILogout;
import java.util.Properties;

public class BaseMain {

    protected ObjectMapper objectMapper;
    protected Playwright playwright;
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
    protected IPrBuyerSuspend iPrSuspend;

//TODO Constructor
    public BaseMain(){
        try {
            objectMapper = new ObjectMapper();
            playwright = Playwright.create();
            playwrightFactory = new PlaywrightFactory(playwright);
            properties = playwrightFactory.initializeProperties();
            page = playwrightFactory.initializePage(properties);

//TODO Requisition
            iLogin = new Login(playwright, properties, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, properties, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
            iPrEdit = new Edit(iLogin, properties, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, properties, page, iLogout);
            iPrReject = new Reject(iLogin, properties, page, iLogout);

            iPrApprove = new Approve(objectMapper, iLogin, properties, page, iLogout);

            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            iPrSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);

        } catch (Exception exception) {
            System.out.println("Error Initializing BaseMain Constructor: " + exception.getMessage());
        }
    }
}