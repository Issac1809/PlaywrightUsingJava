package com.base;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.source.classes.login.Login;
import com.source.classes.logout.Logout;
import com.source.classes.orderschedules.approve.OsApprove;
import com.source.classes.orderschedules.create.OsCreate;
import com.source.classes.orderschedules.edit.OsEdit;
import com.source.classes.purchaseorderrequests.create.PorCreate;
import com.source.classes.purchaseorderrequests.edit.PorEdit;
import com.source.classes.purchaseorderrequests.reject.PorReject;
import com.source.classes.purchaseorderrequests.approve.PorApprove;
import com.source.classes.purchaseorderrequests.sendforapproval.PorSendForApproval;
import com.source.classes.purchaseorderrequests.approvalandapprove.PorSendForApprovalAndApprove;
import com.source.classes.purchaseorderrequests.suspend.PorSuspend;
import com.source.classes.purchaseorders.SendForVendor;
import com.source.classes.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.source.classes.requestforquotations.create.RfqCreate;
import com.source.classes.requestforquotations.edit.RfqEdit;
import com.source.classes.requestforquotations.quote.Quote;
import com.source.classes.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.source.classes.requestforquotations.regret.QuotationRegret;
import com.source.classes.requestforquotations.requote.Requote;
import com.source.classes.requestforquotations.suspend.RfqSuspend;
import com.source.classes.requestforquotations.technicalevaluation.TechnicalEvaluationApprove;
import com.source.classes.requestforquotations.technicalevaluation.TechnicalEvaluationCreate;
import com.source.classes.requestforquotations.technicalevaluation.TechnicalEvaluationReject;
import com.source.classes.requisitions.approve.Approve;
import com.source.classes.requisitions.assign.Assign;
import com.source.classes.requisitions.create.Create;
import com.source.classes.requisitions.edit.Edit;
import com.source.classes.requisitions.reject.Reject;
import com.source.classes.requisitions.sendforapproval.SendForApproval;
import com.source.classes.requisitions.suspend.BuyerSuspend;
import com.source.classes.requisitions.type.PurchaseRequisitionTypeHandler;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsApprove;
import com.source.interfaces.orderschedules.IOsCreate;
import com.source.interfaces.orderschedules.IOsEdit;
import com.source.interfaces.orderschedules.IOsReject;
import com.source.interfaces.purchaseorderrequests.*;
import com.source.interfaces.purchaseorders.IPoSendForVendor;
import com.source.interfaces.requestforquotations.*;
import com.source.interfaces.requisitions.*;
import com.utils.GetTitleUtil;
import com.utils.LoggerUtil;
import com.utils.ToastrUtil;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class BaseMain {

    protected Logger logger;
    protected ToastrUtil toastrUtil;
    protected ObjectMapper objectMapper;
    protected JsonNode jsonNode;
    protected GetTitleUtil getTitleUtil;
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
    protected IPrAssign iPrAssign;
    protected IPrBuyerSuspend iPrSuspend;
    protected IRfqCreate iRfqCreate;
    protected IRfqEdit iRfqEdit;
    protected IRfqSuspend iRfqSuspend;
    protected IQuoRegret iQuoRegret;
    protected IQuoSubmit iQuoSubmit;
    protected IQuoRequote iQuoRequote;
    protected IReadyForEvalutation iReadyForEvalutation;
    protected ITeCreate iTeCreate;
    protected ITeApprove iTeApprove;
    protected ITeReject iTeReject;
    protected ICeCreate iCeCreate;
    protected IPorCreate iPorCreate;
    protected IPorEdit iPorEdit;
    protected IPorSuspend iPorSuspend;
    protected IPorSendForApproval iPorSendForApproval;
    protected IPorReject iPorReject;
    protected IPorSendForApprovalAndApprove iPorSendForApprovalAndApprove;
    protected IPorApprove iPorApprove;
    protected IPoSendForVendor iPoSendForVendor;
    protected IOsCreate iOsCreate;
    protected IOsEdit iOsEdit;
    protected IOsReject iOsReject;
    protected IOsApprove iOsApprove;

//TODO Constructor
    public BaseMain(){
        try {
            this.logger = LoggerUtil.getLogger(BaseMain.class);
            objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(new File("./src/test/resources/config/test-data.json"));
            playwrightFactory = new PlaywrightFactory(objectMapper, jsonNode);
            page = playwrightFactory.initializePage(jsonNode);
            toastrUtil = new ToastrUtil(page);
            getTitleUtil = new GetTitleUtil(jsonNode, logger);

//TODO Requisition
            iLogin = new Login(jsonNode, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(playwrightFactory, objectMapper, playwright, iLogin, jsonNode, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate);
            iPrEdit = new Edit(iLogin, jsonNode, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, jsonNode, page, iLogout);
            iPrReject = new Reject(iLogin, jsonNode, page, iLogout);
            iPrApprove = new Approve(objectMapper, iLogin, jsonNode, page, iLogout);
            iPrAssign = new Assign(playwrightFactory, iLogin, jsonNode, page, iLogout, objectMapper);
            iPrSuspend = new BuyerSuspend(iLogin, jsonNode, page, iLogout);

//TODO Request For Quotation
            iRfqCreate = new RfqCreate(iLogin, jsonNode, page, iLogout, playwrightFactory);
            iRfqEdit = new RfqEdit(iLogin, jsonNode, page, iLogout);
            iRfqSuspend = new RfqSuspend(iLogin, jsonNode, page, iLogout, iRfqEdit, iPrEdit, iPrSendForApproval, iPrApprove, iPrAssign, iRfqCreate);
            iQuoSubmit = new Quote(iLogin, jsonNode, page, iLogout);
            iQuoRegret = new QuotationRegret(iQuoSubmit, iLogin, jsonNode, page, iLogout);
            iQuoRequote = new Requote(iLogin, jsonNode, page, iLogout);
            iReadyForEvalutation = new ReadyForEvaluation(iLogin, jsonNode, page, iLogout);
            iTeCreate = new TechnicalEvaluationCreate(iLogin, jsonNode, page, iLogout);
            iTeReject = new TechnicalEvaluationReject(iLogin, jsonNode, page, iLogout, iTeCreate);
            iTeApprove = new TechnicalEvaluationApprove(iLogin, jsonNode, page, iLogout, iTeCreate);
            iCeCreate = new CommercialEvaluation(iLogin, jsonNode, page, iLogout);

//TODO Purchase Order Requests
            iPorCreate = new PorCreate(iLogin, jsonNode, page, iLogout);
            iPorEdit = new PorEdit(iLogin, jsonNode, page, iLogout);
            iPorSuspend = new PorSuspend(iLogin, jsonNode, page, iLogout, iPorEdit, iCeCreate, iPorCreate);
            iPorSendForApproval = new PorSendForApproval(iLogin, jsonNode, page, iLogout);
            iPorReject = new PorReject(iLogin, jsonNode, page, iLogout, iPorEdit, iPorSendForApproval);
            iPorSendForApprovalAndApprove = new PorSendForApprovalAndApprove(iPorApprove, iPorSendForApproval);
            iPorApprove = new PorApprove(iLogin, jsonNode, page, iLogout);

//TODO Purchase Orders
            iPoSendForVendor = new SendForVendor(iLogin, jsonNode, page, iLogout);

//TODO Order Schedules
            iOsCreate = new OsCreate(iLogin, jsonNode, page, iLogout, playwrightFactory);
            iOsEdit = new OsEdit(iLogin, jsonNode, page, iLogout);
            iOsApprove = new OsApprove(iLogin, jsonNode, page, iLogout);
            iOsApprove = new OsApprove(iLogin, jsonNode, page, iLogout);


        } catch (Exception exception) {
            logger.error("Error Initializing BaseMain Constructor: {}", exception.getMessage());
        }
    }
}