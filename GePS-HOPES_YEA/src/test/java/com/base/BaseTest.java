package com.base;
import com.fasterxml.jackson.databind.JsonNode;
import com.source.classes.login.LoginTest;
import com.source.classes.requisition.approve.ApproveTest;
import com.source.classes.requisition.assign.AssignTest;
import com.source.classes.requisition.create.Create;
import com.source.classes.requisition.suspend.BuyerManagerSuspendTest;
import com.source.classes.requisition.suspend.BuyerSuspendTest;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.source.classes.login.Login;
import com.source.classes.logout.Logout;
import com.source.classes.requisition.approve.Approve;
import com.source.classes.requisition.assign.Assign;
import com.source.classes.requisition.edit.Edit;
import com.source.classes.requisition.reject.Reject;
import com.source.classes.requisition.sendforapproval.SendForApproval;
import com.source.classes.requisition.suspend.BuyerManagerSuspend;
import com.source.classes.requisition.suspend.BuyerSuspend;
import com.source.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.*;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.Properties;

public class BaseTest {

    protected Logger logger;
    protected ObjectMapper objectMapper;
    protected JsonNode jsonNode;
    protected Playwright playwright;
    protected PlaywrightFactory playwrightFactory;
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
            logger = LoggerUtil.getLogger(BaseTest.class);
            objectMapper = new ObjectMapper();
            playwrightFactory = new PlaywrightFactory();
            jsonNode = objectMapper.readTree(new File("src/test/resources/config/test-data.json"));
            page = playwrightFactory.initializePage(jsonNode);

//TODO Requisition
            iLogin = new Login(jsonNode, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, jsonNode, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate);
//            iPrEdit = new Edit(iLogin, properties, page, iLogout);
//            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, properties, page, iLogout);
//            iPrReject = new Reject(iLogin, properties, page, iLogout);
//
//            iPrApprove = new Approve(objectMapper, iLogin, properties, page, iLogout);
//            approveTest = new ApproveTest();
//
//            iPrAssign = new Assign(iLogin, properties, page, iLogout);
//            assign = new AssignTest();
//            iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, properties, page, iLogout, iPrEdit);
//            buyerManagerSuspendTest = new BuyerManagerSuspendTest();
//            iPrBuyerSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);
//            buyerSuspendTest = new BuyerSuspendTest();
        } catch (Exception error) {
            logger.error("Error Initializing SetUp Function: " + error.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            page.context().browser().close();
        } catch (Exception error) {
            logger.error("Error Initializing Tear Down Function: " + error.getMessage());
        }
    }
}