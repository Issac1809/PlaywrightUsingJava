package com.base;
import com.fasterxml.jackson.databind.JsonNode;
import com.source.classes.dispatchnotes.assign.DnAssign;
import com.source.classes.dispatchnotes.assign.DnAssignTest;
import com.source.classes.dispatchnotes.cancel.DnCancel;
import com.source.classes.dispatchnotes.cancel.DnCancelTest;
import com.source.classes.dispatchnotes.create.DnCreate;
import com.source.classes.dispatchnotes.create.DnCreateTest;
import com.source.classes.dispatchnotes.dnreturn.DnReturn;
import com.source.classes.dispatchnotes.dnreturn.DnReturnTest;
import com.source.classes.dispatchnotes.edit.DnEdit;
import com.source.classes.dispatchnotes.edit.DnEditTest;
import com.source.classes.freightforwarderrequests.invite.FfrInvite;
import com.source.classes.freightforwarderrequests.invite.FfrInviteTest;
import com.source.classes.freightforwarderrequests.quote.FfrQuote;
import com.source.classes.freightforwarderrequests.quote.FfrQuoteTest;
import com.source.classes.freightforwarderrequests.requote.FfrRequote;
import com.source.classes.freightforwarderrequests.requote.FfrRequoteTest;
import com.source.classes.inspections.assign.InsAssign;
import com.source.classes.inspections.assign.InsAssignTest;
import com.source.classes.inspections.create.InsCreate;
import com.source.classes.inspections.create.InsCreateTest;
import com.source.classes.inspections.fail.InsFail;
import com.source.classes.inspections.fail.InsFailTest;
import com.source.classes.inspections.readyforinspection.InsReadyForInspection;
import com.source.classes.inspections.readyforinspection.InsReadyForInspectionTest;
import com.source.classes.login.LoginTest;
import com.source.classes.orderschedules.approval.OsApproveTest;
import com.source.classes.orderschedules.approve.OsApprove;
import com.source.classes.orderschedules.create.OsCreate;
import com.source.classes.orderschedules.create.OsCreateTest;
import com.source.classes.orderschedules.edit.OsEdit;
import com.source.classes.orderschedules.edit.OsEditTest;
import com.source.classes.orderschedules.reject.OsRejectTest;
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
import com.source.classes.purchaseorders.PoSendForVendorTest;
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
import com.source.classes.workorder.create.WoCreate;
import com.source.classes.workorder.trackerstatus.WoTrackerStatus;
import com.source.classes.workorders.create.WoCreateTest;
import com.source.classes.workorders.trackerstatus.WoTrackerStatusTest;
import com.source.interfaces.dispatchnotes.*;
import com.source.interfaces.freightforwarderrequests.IFfrInvite;
import com.source.interfaces.freightforwarderrequests.IFfrQuote;
import com.source.interfaces.freightforwarderrequests.IFfrRequote;
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
import com.source.interfaces.workorders.IWoCreate;
import com.source.interfaces.workorders.IWoTrackerStatus;
import com.utils.GetTitleUtil;
import com.utils.LoggerUtil;
import com.utils.ToastrUtil;
import com.utils.rpa.salesordersync.PR_List_Flow;
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
    protected PR_List_Flow prListFlow;
    protected LoginTest loginTest;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected CreateTest createTest;
    protected IPrCreate iPrCreate;
    protected EditTest editTest;
    protected IPrEdit iPrEdit;
    protected SendForApprovalTest sendForApprovalTest;
    protected IPrSendForApproval iPrSendForApproval;
    protected RejectTest rejectTest;
    protected IPrReject iPrReject;
    protected ApproveTest approveTest;
    protected IPrApprove iPrApprove;
    protected AssignTest assignTest;
    protected IPrAssign iPrAssign;
    protected BuyerSuspendTest buyerSuspendTest;
    protected IPrBuyerSuspend iPrBuyerSuspend;
    protected BuyerManagerSuspendTest buyerManagerSuspendTest;
    protected IPrBuyerManagerSuspend iPrBuyerManagerSuspend;
    protected RfqCreateTest rfqCreateTest;
    protected IRfqCreate iRfqCreate;
    protected RfqEditTest rfqEditTest;
    protected IRfqEdit iRfqEdit;
    protected RfqSuspendPrEditTest rfqSuspendPrEditTest;
    protected RfqSuspendRfqEditTest rfqSuspendRfqEditTest;
    protected IRfqSuspend iRfqSuspend;
    protected RegretTest regretTest;
    protected IQuoRegret iQuoRegret;
    protected QuoteTest quoteTest;
    protected IQuoSubmit iQuoSubmit;
    protected RequoteTest requoteTest;
    protected IQuoRequote iQuoRequote;
    protected ReadyForEvaluationTest readyForEvaluationTest;
    protected IReadyForEvalutation iReadyForEvalutation;
    protected TechnicalEvaluationCreateTest technicalEvaluationCreateTest;
    protected ITeCreate iTeCreate;
    protected TechnicalEvaluationRejectTest technicalEvaluationRejectTest;
    protected ITeReject iTeReject;
    protected TechnicalEvaluationApproveTest technicalEvaluationApproveTest;
    protected ITeApprove iTeApprove;
    protected CommercialEvaluationTest commercialEvaluationTest;
    protected ICeCreate iCeCreate;
    protected PorCreateTest porCreateTest;
    protected IPorCreate iPorCreate;
    protected PorEditTest porEditTest;
    protected IPorEdit iPorEdit;
    protected SuspendEditTest suspendEditTest;
    protected IPorSuspend iPorSuspend;
    protected PorSendForApprovalTest porSendForApprovalTest;
    protected IPorSendForApproval iPorSendForApproval;
    protected PorRejectTest porRejectTest;
    protected IPorReject iPorReject;
    protected IPorApprove iPorApprove;
    protected PorSendForApprovalAndApproveTest porSendForApprovalAndApproveTest;
    protected IPorSendForApprovalAndApprove iPorSendForApprovalAndApprove;
    protected PoSendForVendorTest poSendForVendorTest;
    protected IPoSendForVendor iPoSendForVendor;
    protected OsCreateTest osCreateTest;
    protected IOsCreate iOsCreate;
    protected OsEditTest osEditTest;
    protected IOsEdit iOsEdit;
    protected OsRejectTest osRejectTest;
    protected IOsReject iOsReject;
    protected OsApproveTest osApproveTest;
    protected IOsApprove iOsApprove;
    protected InsReadyForInspectionTest insReadyForInspectionTest;
    protected IInsReadyForInspection iInsReadyForInspection;
    protected InsCreateTest insCreateTest;
    protected IInsCreate iInsCreate;
    protected InsAssignTest insAssignTest;
    protected IInsAssign iInsAssign;
    protected InsFailTest insFailTest;
    protected IInsFail iInsFail;
    protected DnCreateTest dnCreateTest;
    protected IDnCreate iDnCreate;
    protected DnReturnTest dnReturnTest;
    protected IDnReturn iDnReturn;
    protected DnEditTest dnEditTest;
    protected IDnEdit iDnEdit;
    protected DnAssignTest dnAssignTest;
    protected IDnAssign iDnAssign;
    protected DnCancelTest dnCancelTest;
    protected IDnCancel iDnCancel;
    protected FfrInviteTest ffrInviteTest;
    protected IFfrInvite iFfrInvite;
    protected FfrQuoteTest ffrQuoteTest;
    protected IFfrQuote iFfrQuote;
    protected FfrRequoteTest ffrRequoteTest;
    protected IFfrRequote iFfrRequote;
    protected WoCreateTest woCreateTest;
    protected IWoCreate iWoCreate;
    protected WoTrackerStatusTest woTrackerStatusTest;
    protected IWoTrackerStatus iWoTrackerStatus;
//    protected PoInvCreateTest poInvCreateTest;
//    protected IInvCreate iInvCreate;
//    protected PoInvHoldTest poInvHoldTest;
//    protected IInvHold iInvHold;
//    protected PoInvRevertTest poInvRevertTest;
//    protected IInvRevert iInvRevert;
//    protected PoInvCancelTest poInvCancelTest;
//    protected IInvCancel iInvCancel;
//    protected PoInvSendForApprovalTest poInvSendForApprovalTest;
//    protected IInvSendForApproval iInvSendForApproval;
//    protected PoInvChecklistRejectTest poInvChecklistRejectTest;
//    protected IInvChecklistReject iInvChecklistReject;
//    protected PoInvChecklistAcceptTest poInvChecklistAcceptTest;
//    protected IInvChecklistAccept iInvChecklistAccept;
//    protected PoInvEditTest poInvEditTest;
//    protected IInvEdit iInvEdit;
//    protected PoInvReturnTest poInvReturnTest;
//    protected IInvReturn iInvReturn;
//    protected PoInvVerifyTest poInvVerifyTest;
//    protected IInvVerify iInvVerify;
//    protected PoInvRejectTest poInvRejectTest;
//    protected IInvReject iInvReject;
//    protected PoInvApprovalTest poInvApprovalTest;
//    protected IInvApproval iInvApproval;
//    protected WoInvCreateTest woInvCreateTest;
//    protected IWoInvCreate iWoInvCreate;
//    protected WoInvHoldTest woInvHoldTest;
//    protected IWoInvHold iWoInvHold;
//    protected WoInvRevertTest woInvRevertTest;
//    protected IWoInvRevert iWoInvRevert;
//    protected WoInvCancelTest woInvCancelTest;
//    protected IWoInvCancel iWoInvCancel;
//    protected WoInvSendForApprovalTest woInvSendForApprovalTest;
//    protected IWoInvSendForApproval iWoInvSendForApproval;
//    protected WoInvChecklistAcceptTest woInvChecklistAcceptTest;
//    protected IWoInvChecklistAccept iWoInvChecklistAccept;
//    protected WoInvChecklistRejectTest woInvChecklistRejectTest;
//    protected IWoInvChecklistReject iWoInvChecklistReject;
//    protected WoInvEditTest woInvEditTest;
//    protected IWoInvEdit iWoInvEdit;
//    protected WoInvReturnTest woInvReturnTest;
//    protected IWoInvReturn iWoInvReturn;
//    protected WoInvVerifyTest woInvVerifyTest;
//    protected IWoInvVerify iWoInvVerify;
//    protected WoInvRejectTest woInvRejectTest;
//    protected IWoInvReject iWoInvReject;
//    protected WoInvApprovalTest woInvApprovalTest;
//    protected IWoInvApproval iWoInvApproval;

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
            rfqSuspendRfqEditTest = new RfqSuspendRfqEditTest();
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

            iPorCreate = new PorCreate(iLogin, jsonNode, page, iLogout, prListFlow);
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

//TODO Freight Forwarder Requests
            iFfrInvite = new FfrInvite(iLogin, jsonNode, page, iLogout);
            iFfrQuote = new FfrQuote(iLogin, jsonNode, page, iLogout);
            iFfrRequote = new FfrRequote(iLogin, jsonNode, iFfrQuote, iLogout, page);

//TODO Work Orders
            iWoCreate = new WoCreate(iLogin, jsonNode, page, iLogout);
            iWoTrackerStatus = new WoTrackerStatus(iLogin, jsonNode, page, iLogout, playwrightFactory);

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