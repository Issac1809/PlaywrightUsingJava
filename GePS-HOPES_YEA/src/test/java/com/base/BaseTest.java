package com.base;
import com.fasterxml.jackson.databind.JsonNode;
import com.source.classes.requisition.create.Create;
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
import java.io.FileWriter;

public class BaseTest {

    protected FileWriter fileWriter;
    protected Logger logger;
    protected ObjectMapper objectMapper;
    protected JsonNode jsonNode;
    protected Playwright playwright;
    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected IPrSendForApproval iPrSendForApproval;
    protected IPrReject iPrReject;
    protected IPrApprove iPrApprove;
    protected IPrBuyerManagerSuspend iPrBuyerManagerSuspend;
    protected IPrAssign iPrAssign;
    protected IPrBuyerSuspend iPrBuyerSuspend;

//TODO Constructor
    public BaseTest() {
    }

    @BeforeClass
    public void setUp(){
        try {
            this.logger = LoggerUtil.getLogger(BaseTest.class);
            objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(new File("./src/test/resources/config/test-data.json"));
            playwrightFactory = new PlaywrightFactory(objectMapper, jsonNode);
            page = playwrightFactory.initializePage(jsonNode);

//TODO Requisition
            iLogin = new Login(jsonNode, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, jsonNode, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate);
            iPrEdit = new Edit(iLogin, jsonNode, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, jsonNode, page, iLogout);
            iPrReject = new Reject(iLogin, jsonNode, page, iLogout);
            iPrApprove = new Approve(objectMapper, iLogin, jsonNode, page, iLogout);
            iPrAssign = new Assign(iLogin, jsonNode, page, iLogout);
            iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, jsonNode, page, iLogout, iPrEdit);
            iPrBuyerSuspend = new BuyerSuspend(iLogin, jsonNode, page, iLogout);
        } catch (Exception exception) {
            logger.error("Error Initializing SetUp Function: {}", exception.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            page.context().browser().close();
        } catch (Exception exception) {
            logger.error("Error Initializing Tear Down Function: {}", exception.getMessage());
        }
    }
}