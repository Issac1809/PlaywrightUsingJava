package com.base;
import com.fasterxml.jackson.databind.JsonNode;
import com.source.classes.dispatchnotes.assign.DnAssign;
import com.source.classes.dispatchnotes.cancel.DnCancel;
import com.source.classes.dispatchnotes.create.DnCreate;
import com.source.classes.dispatchnotes.dnreturn.DnReturn;
import com.source.classes.dispatchnotes.edit.DnEdit;
import com.source.classes.inspections.assign.InsAssign;
import com.source.classes.inspections.create.InsCreate;
import com.source.classes.inspections.fail.InsFail;
import com.source.classes.inspections.readyforinspection.InsReadyForInspection;
import com.source.classes.login.LoginTest;
import com.source.classes.orderschedules.approve.OsApprove;
import com.source.classes.orderschedules.create.OsCreate;
import com.source.classes.orderschedules.edit.OsEdit;
import com.source.classes.purchaseorderrequests.approvalandapprove.PorSendForApprovalAndApprove;
import com.source.classes.purchaseorderrequests.approvalandapprove.PorSendForApprovalAndApproveTest;
import com.source.classes.purchaseorderrequests.approve.PorApprove;
import com.source.classes.purchaseorderrequests.create.PorCreate;
import com.source.classes.purchaseorderrequests.create.PorCreateTest;
import com.source.classes.purchaseorderrequests.edit.PorEdit;
import com.source.classes.purchaseorderrequests.edit.PorEditTest;
import com.source.classes.purchaseorderrequests.reject.PorReject;
import com.source.classes.purchaseorderrequests.reject.PorRejectTest;
import com.source.classes.purchaseorderrequests.sendforapproval.PorSendForApproval;
import com.source.classes.purchaseorderrequests.sendforapproval.PorSendForApprovalTest;
import com.source.classes.purchaseorderrequests.suspend.PorSuspend;
import com.source.classes.purchaseorderrequests.suspend.SuspendEditTest;
import com.source.classes.purchaseorders.SendForVendor;
import com.source.classes.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.source.classes.requestforquotations.commercialevaluation.CommercialEvaluationTest;
import com.source.classes.requestforquotations.create.RfqCreate;
import com.source.classes.requestforquotations.create.RfqCreateTest;
import com.source.classes.requestforquotations.edit.RfqEdit;
import com.source.classes.requestforquotations.edit.RfqEditTest;
import com.source.classes.requestforquotations.quote.Quote;
import com.source.classes.requestforquotations.quote.QuoteTest;
import com.source.classes.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.source.classes.requestforquotations.readyforevaluation.ReadyForEvaluationTest;
import com.source.classes.requestforquotations.regret.QuotationRegret;
import com.source.classes.requestforquotations.regret.RegretTest;
import com.source.classes.requestforquotations.requote.Requote;
import com.source.classes.requestforquotations.requote.RequoteTest;
import com.source.classes.requestforquotations.suspend.RfqSuspend;
import com.source.classes.requestforquotations.suspend.RfqSuspendPrEditTest;
import com.source.classes.requestforquotations.suspend.RfqSuspendRfqEditTest;
import com.source.classes.requestforquotations.technicalevaluation.*;
import com.source.classes.requisitions.approve.ApproveTest;
import com.source.classes.requisitions.assign.AssignTest;
import com.source.classes.requisitions.create.CreateTest;
import com.source.classes.requisitions.edit.EditTest;
import com.source.classes.requisitions.reject.RejectTest;
import com.source.classes.requisitions.sendforapproval.SendForApprovalTest;
import com.source.classes.requisitions.suspend.BuyerManagerSuspendTest;
import com.source.classes.requisitions.suspend.BuyerSuspendTest;
import com.source.classes.requisitions.create.Create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.source.classes.login.Login;
import com.source.classes.logout.Logout;
import com.source.classes.requisitions.approve.Approve;
import com.source.classes.requisitions.assign.Assign;
import com.source.classes.requisitions.edit.Edit;
import com.source.classes.requisitions.reject.Reject;
import com.source.classes.requisitions.sendforapproval.SendForApproval;
import com.source.classes.requisitions.suspend.BuyerManagerSuspend;
import com.source.classes.requisitions.suspend.BuyerSuspend;
import com.source.classes.requisitions.type.PurchaseRequisitionTypeHandler;
import com.source.interfaces.dispatchnotes.*;
import com.source.interfaces.inspections.IInsAssign;
import com.source.interfaces.inspections.IInsCreate;
import com.source.interfaces.inspections.IInsFail;
import com.source.interfaces.inspections.IInsReadyForInspection;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class BaseTest {

    protected Logger logger;
    protected ToastrUtil toastrUtil;
    protected ObjectMapper objectMapper;
    protected JsonNode jsonNode;
    protected GetTitleUtil getTitleUtil;
    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected LoginTest loginTest;
    protected CreateTest createTest;
    protected IPrType iPrType;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected IPrSendForApproval iPrSendForApproval;
    protected IPrReject iPrReject;
    protected IPrApprove iPrApprove;
    protected IPrBuyerManagerSuspend iPrBuyerManagerSuspend;
    protected IPrAssign iPrAssign;
    protected IPrBuyerSuspend iPrBuyerSuspend;
    protected IRfqCreate iRfqCreate;
    protected IRfqEdit iRfqEdit;
    protected IRfqSuspend iRfqSuspend;
    protected IQuoRegret iQuoRegret;
    protected IQuoSubmit iQuoSubmit;
    protected IQuoRequote iQuoRequote;
    protected IReadyForEvalutation iReadyForEvalutation;
    protected ITeCreate iTeCreate;
    protected ITeReject iTeReject;
    protected ITeApprove iTeApprove;
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
    protected IInsCreate iInsCreate;
    protected IInsReadyForInspection iInsReadyForInspection;
    protected IInsFail iInsFail;
    protected IInsAssign iInsAssign;
    protected IDnCreate iDnCreate;
    protected IDnEdit iDnEdit;
    protected IDnReturn iDnReturn;
    protected IDnAssign iDnAssign;
    protected IDnCancel iDnCancel;


    protected EditTest editTest;
    protected SendForApprovalTest sendForApprovalTest;
    protected RejectTest rejectTest;
    protected ApproveTest approveTest;
    protected BuyerManagerSuspendTest buyerManagerSuspendTest;
    protected BuyerSuspendTest buyerSuspendTest;
    protected AssignTest assignTest;
    protected RfqCreateTest rfqCreateTest;
    protected RfqEditTest rfqEditTest;
    protected RfqSuspendPrEditTest rfqSuspendPrEditTest;
    protected RfqSuspendRfqEditTest rfqSuspendRfqEditTestMethod;
    protected RegretTest regretTest;
    protected QuoteTest quoteTest;
    protected RequoteTest requoteTest;
    protected ReadyForEvaluationTest readyForEvaluationTest;
    protected TechnicalEvaluationCreateTest technicalEvaluationCreateTest;
    protected TechnicalEvaluationRejectTest technicalEvaluationRejectTest;
    protected TechnicalEvaluationApproveTest technicalEvaluationApproveTest;
    protected CommercialEvaluationTest commercialEvaluationTest;
    protected PorCreateTest porCreateTest;
    protected SuspendEditTest suspendEditTest;
    protected PorEditTest porEditTest;
    protected PorSendForApprovalTest porSendForApprovalTest;
    protected PorRejectTest porRejectTest;
    protected PorSendForApprovalAndApproveTest porSendForApprovalAndApproveTest;
//    protected IPorApprove iPorApprove;
//    protected IPoSendForVendor iPoSendForVendor;
//    protected IOsCreate iOsCreate;
//    protected IOsEdit iOsEdit;
//    protected IOsReject iOsReject;
//    protected IOsApprove iOsApprove;
//    protected IInsCreate iInsCreate;
//    protected IInsReadyForInspection iInsReadyForInspection;
//    protected IInsFail iInsFail;
//    protected IInsAssign iInsAssign;
//    protected IDnCreate iDnCreate;
//    protected IDnEdit iDnEdit;
//    protected IDnReturn iDnReturn;
//    protected IDnAssign iDnAssign;
//    protected IDnCancel iDnCancel;

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
            toastrUtil = new ToastrUtil(page);
            getTitleUtil = new GetTitleUtil(jsonNode, logger);

//TODO Requisition
            iLogout = new Logout(page);
            iLogin = new Login(jsonNode, page);
            loginTest = new LoginTest();
            iPrCreate = new Create(playwrightFactory, objectMapper, iLogin, jsonNode, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate);
            createTest = new CreateTest();

            editTest = new EditTest();
            sendForApprovalTest = new SendForApprovalTest();
            rejectTest = new RejectTest();
            approveTest = new ApproveTest();
            buyerManagerSuspendTest = new BuyerManagerSuspendTest();
            buyerSuspendTest = new BuyerSuspendTest();
            assignTest = new AssignTest();

            iPrEdit = new Edit(iLogin, jsonNode, page, iLogout);
            iPrSendForApproval = new SendForApproval(playwrightFactory, objectMapper, iLogin, jsonNode, page, iLogout);
            iPrReject = new Reject(iLogin, jsonNode, page, iLogout);
            iPrApprove = new Approve(objectMapper, iLogin, jsonNode, page, iLogout);
            iPrAssign = new Assign(playwrightFactory, iLogin, jsonNode, page, iLogout, objectMapper);
            iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, jsonNode, page, iLogout, iPrEdit);
            iPrBuyerSuspend = new BuyerSuspend(iLogin, jsonNode, page, iLogout);



//TODO Request For Quotation
            rfqCreateTest = new RfqCreateTest();
            rfqEditTest = new RfqEditTest();
            rfqSuspendPrEditTest = new RfqSuspendPrEditTest();
            rfqSuspendRfqEditTestMethod = new RfqSuspendRfqEditTest();
            regretTest = new RegretTest();
            quoteTest = new QuoteTest();
            requoteTest = new RequoteTest();
            readyForEvaluationTest = new ReadyForEvaluationTest();
            technicalEvaluationCreateTest = new TechnicalEvaluationCreateTest();
            technicalEvaluationRejectTest = new TechnicalEvaluationRejectTest();
            technicalEvaluationApproveTest = new TechnicalEvaluationApproveTest();
            commercialEvaluationTest = new CommercialEvaluationTest();

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
            porCreateTest = new PorCreateTest();
            suspendEditTest = new SuspendEditTest();
            porEditTest = new PorEditTest();
            porSendForApprovalTest = new PorSendForApprovalTest();
            porRejectTest = new PorRejectTest();
            porSendForApprovalAndApproveTest = new PorSendForApprovalAndApproveTest();

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

//TODO Inspections
            iInsReadyForInspection = new InsReadyForInspection(iLogin, jsonNode, page, iLogout);
            iInsCreate = new InsCreate(iLogin, jsonNode, page, iLogout);
            iInsFail = new InsFail(iLogin, jsonNode, page, iLogout, iInsReadyForInspection);
            iInsAssign = new InsAssign(iLogin, jsonNode, page, iLogout);

//TODO Dispatch Notes
            iDnCreate = new DnCreate(iLogin, jsonNode, page, iLogout);
            iDnEdit = new DnEdit(iLogin, jsonNode, page, iLogout);
            iDnAssign = new DnAssign(iLogin, jsonNode, page, iLogout, playwrightFactory);
            iDnReturn = new DnReturn(iLogin, jsonNode, page, iLogout, iDnEdit);
            iDnCancel = new DnCancel(iLogin, jsonNode, page, iLogout, iDnCreate);

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