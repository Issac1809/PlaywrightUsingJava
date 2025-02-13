package com.base;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.source.classes.login.Login;
import com.source.classes.logout.Logout;
import com.source.classes.requisition.approve.Approve;
import com.source.classes.requisition.assign.Assign;
import com.source.classes.requisition.create.Create;
import com.source.classes.requisition.edit.Edit;
import com.source.classes.requisition.reject.Reject;
import com.source.classes.requisition.sendforapproval.SendForApproval;
import com.source.classes.requisition.suspend.BuyerSuspend;
import com.source.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.*;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;

public class BaseMain {

    protected Logger logger;
    protected ObjectMapper objectMapper;
    protected JsonNode jsonNode;
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
            logger = LoggerUtil.getLogger(BaseMain.class);
            objectMapper = new ObjectMapper();
            playwrightFactory = new PlaywrightFactory();
            jsonNode = objectMapper.readTree(new File("./src/test/resources/config/test-data.json"));
            page = playwrightFactory.initializePage(jsonNode);

//TODO Requisition
            iLogin = new Login(jsonNode, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, jsonNode, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate);
            iPrEdit = new Edit(iLogin, properties, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, properties, page, iLogout);
            iPrReject = new Reject(iLogin, properties, page, iLogout);
            iPrApprove = new Approve(objectMapper, iLogin, properties, page, iLogout);
            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            iPrSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);

        } catch (Exception error) {
            logger.error("Error Initializing BaseMain Constructor: {}", error.getMessage());
        }
    }
}