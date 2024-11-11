package com.procurement.nonPoc.base;

import com.procurement.nonPoc.login.LoginTest;
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
import com.procurement.nonPoc.requisition.approve.ApproveTest;
import com.procurement.nonPoc.requisition.assign.AssignTest;
import com.procurement.nonPoc.requisition.reject.RejectTest;
import com.procurement.nonPoc.requisition.create.CreateTest;
import com.procurement.nonPoc.requisition.edit.EditTest;
import com.procurement.nonPoc.requisition.sendforapproval.SendForApprovalTest;
import com.procurement.nonPoc.requisition.suspend.SuspendTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Properties properties;
    protected Page page;
    protected LoginTest login;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected CreateTest create;
    protected IPrCreate iPrCreate;
    protected EditTest edit;
    protected IPrEdit iPrEdit;
    protected SendForApprovalTest sendForApproval;
    protected IPrSendForApproval iPrSendForApproval;
    protected RejectTest reject;
    protected IPrReject iPrReject;
    protected ApproveTest approve;
    protected IPrApprove iPrApprove;
    protected AssignTest assign;
    protected IPrAssign iPrAssign;
    protected SuspendTest suspend;
    protected IPrSuspend iPrSuspend;

//TODO Constructor
    public BaseTest() {
    }

    @BeforeTest
    public void setUp(){
        try {
            playwrightFactory = new PlaywrightFactory();
            properties = playwrightFactory.initializeProperties();
            page = playwrightFactory.initializePage(properties);

//TODO Requisition
            iLogin = new Login(properties, page);
            iLogout = new Logout(page);
            login = new LoginTest();
            iPrCreate = new Create(iLogin, properties, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
            create = new CreateTest();
            iPrSendForApproval = new SendForApproval(iLogin, properties, page, iLogout);
            sendForApproval = new SendForApprovalTest();
            iPrApprove = new Approve(iLogin, properties, page, iLogout);
            approve = new ApproveTest();
            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            assign = new AssignTest();
            iPrEdit = new Edit(iLogin, properties, page, iLogout, iPrSendForApproval, iPrApprove, iPrAssign);
            edit = new EditTest();
            iPrReject = new Reject(iLogin, properties, page, iLogout, iPrEdit);
            reject = new RejectTest();
            iPrSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);
            suspend = new SuspendTest();

        } catch (Exception exception) {
            System.out.println("Error :" + exception);
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            page.context().browser().close();
        } catch (Exception exception) {
            System.out.println("Error :" + exception);
        }
    }

    @AfterTest
    public void tearDown(Page page) {
        try {
            page.context().browser().close();
        } catch (Exception exception) {
            System.out.println("Error :" + exception);
        }
    }
}