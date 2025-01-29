package com.base;
import com.classes.login.LoginTest;
import com.classes.requisition.approve.ApproveTest;
import com.classes.requisition.suspend.BuyerManagerSuspendTest;
import com.classes.requisition.suspend.BuyerSuspendTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.classes.requisition.reject.Reject;
import com.ps.classes.requisition.suspend.BuyerManagerSuspend;
import com.ps.interfaces.requisitions.*;
import com.classes.requisition.assign.AssignTest;
import com.ps.classes.requisition.approve.Approve;
import com.ps.classes.requisition.assign.Assign;
import com.ps.classes.requisition.create.Create;
import com.ps.classes.requisition.edit.Edit;
import com.ps.classes.requisition.sendforapproval.SendForApproval;
import com.ps.classes.requisition.suspend.BuyerSuspend;
import com.ps.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.ps.classes.login.Login;
import com.ps.classes.logout.Logout;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Properties;

public class BaseTest {

    protected ObjectMapper objectMapper;
    protected Playwright playwright;
    protected PlaywrightFactory playwrightFactory;
    protected Properties properties;
    protected Page page;
    protected LoginTest loginTest;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected IPrSendForApproval iPrSendForApproval;
    protected IPrReject iPrReject;
    protected IPrApprove iPrApprove;
    protected ApproveTest approveTest;
    protected BuyerManagerSuspendTest buyerManagerSuspendTest;
    protected IPrBuyerManagerSuspend iPrBuyerManagerSuspend;
    protected AssignTest assign;
    protected IPrAssign iPrAssign;
    protected BuyerSuspendTest buyerSuspendTest;
    protected IPrBuyerSuspend iPrBuyerSuspend;

//TODO Constructor
    public BaseTest() {
    }

    @BeforeClass
    public void setUp(){
        try {
            objectMapper = new ObjectMapper();
            playwright = Playwright.create();
            playwrightFactory = new PlaywrightFactory(playwright);
            properties = playwrightFactory.initializeProperties();
            page = playwrightFactory.initializePage(properties);

//TODO Requisition
            iLogin = new Login(playwright, properties, page);
            iLogout = new Logout(page);
            loginTest = new LoginTest();
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, properties, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
            iPrEdit = new Edit(iLogin, properties, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, properties, page, iLogout);
            iPrReject = new Reject(iLogin, properties, page, iLogout);

            iPrApprove = new Approve(objectMapper, iLogin, properties, page, iLogout);
            approveTest = new ApproveTest();

            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            assign = new AssignTest();
            iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, properties, page, iLogout, iPrEdit);
            buyerManagerSuspendTest = new BuyerManagerSuspendTest();
            iPrBuyerSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);
            buyerSuspendTest = new BuyerSuspendTest();
        } catch (Exception exception) {
            System.out.println("Error Initializing SetUp Function: " + exception.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            page.context().browser().close();
        } catch (Exception exception) {
            System.out.println("Error Initializing TearDown Function: " + exception.getMessage());
        }
    }
}