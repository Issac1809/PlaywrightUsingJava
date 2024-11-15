package com.poc.base;
import com.poc.classes.dispatchnotes.assign.DnAssign;
import com.poc.classes.dispatchnotes.assign.DnAssignTest;
import com.poc.classes.dispatchnotes.cancel.DnCancel;
import com.poc.classes.dispatchnotes.cancel.DnCancelTest;
import com.poc.classes.dispatchnotes.create.DnCreate;
import com.poc.classes.dispatchnotes.create.DnCreateTest;
import com.poc.classes.dispatchnotes.dnreturn.DnReturn;
import com.poc.classes.dispatchnotes.dnreturn.DnReturnTest;
import com.poc.classes.dispatchnotes.edit.DnEdit;
import com.poc.classes.dispatchnotes.edit.DnEditTest;
import com.poc.classes.freightforwarderrequests.invite.FfrInvite;
import com.poc.classes.freightforwarderrequests.invite.FfrInviteTest;
import com.poc.classes.freightforwarderrequests.quote.FfrQuote;
import com.poc.classes.freightforwarderrequests.quote.FfrQuoteTest;
import com.poc.classes.freightforwarderrequests.requote.FfrRequote;
import com.poc.classes.freightforwarderrequests.requote.FfrRequoteTest;
import com.poc.classes.inspections.assign.InsAssign;
import com.poc.classes.inspections.assign.InsAssignTest;
import com.poc.classes.inspections.create.InsCreate;
import com.poc.classes.inspections.create.InsCreateTest;
import com.poc.classes.inspections.fail.InsFail;
import com.poc.classes.inspections.fail.InsFailTest;
import com.poc.classes.inspections.readyforinspection.InsReadyForInspection;
import com.poc.classes.inspections.readyforinspection.InsReadyForInspectionTest;
import com.poc.classes.login.LoginTest;
import com.poc.classes.orderschedule.approval.OsApproveTest;
import com.poc.classes.orderschedule.approve.OsApprove;
import com.poc.classes.orderschedule.create.OsCreate;
import com.poc.classes.orderschedule.create.OsCreateTest;
import com.poc.classes.orderschedule.edit.OsEdit;
import com.poc.classes.orderschedule.edit.OsEditTest;
import com.poc.classes.orderschedule.reject.OsReject;
import com.poc.classes.orderschedule.reject.OsRejectTest;
import com.poc.classes.purchaseorder.create.PoCreateTest;
import com.poc.classes.purchaseorder.sendforapproval.PoSendForVendorTest;
import com.poc.classes.purchaseorderrequest.approvalandapprove.PorSendForApprovalAndApproveTest;
import com.poc.classes.purchaseorderrequest.approve.PorApproveTest;
import com.poc.classes.purchaseorderrequest.create.PorCreateTest;
import com.poc.classes.purchaseorderrequest.edit.PorEditTest;
import com.poc.classes.purchaseorderrequest.reject.PorRejectTest;
import com.poc.classes.purchaseorderrequest.sendforapproval.PorSendForApprovalTest;
import com.poc.classes.purchaseorderrequest.suspend.PorSuspendPorEditTest;
import com.poc.classes.purchaseorderrequest.suspend.PorSuspendRfqEditTest;
import com.poc.classes.requestforquotation.commercialevaluation.CommercialEvaluationTest;
import com.poc.classes.requestforquotation.create.RfqCreateTest;
import com.poc.classes.requestforquotation.edit.RfqEditTest;
import com.poc.classes.requestforquotation.regret.RegretTest;
import com.poc.classes.requestforquotation.requote.RequoteTest;
import com.poc.classes.requestforquotation.quote.QuoteTest;
import com.poc.classes.requestforquotation.readyforevaluation.ReadyForEvaluationTest;
import com.poc.classes.requestforquotation.suspend.RfqSuspendPrEditTest;
import com.poc.classes.requestforquotation.suspend.RfqSuspendRfqEditTest;
import com.poc.classes.requestforquotation.technicalevaluation.TechnicalEvaluationRejectTest;
import com.poc.classes.requestforquotation.technicalevaluation.TechnicalEvaluationTest;
import com.poc.classes.requisition.approve.ApproveTest;
import com.poc.classes.requisition.assign.AssignTest;
import com.poc.classes.requisition.create.CreateTest;
import com.poc.classes.requisition.edit.EditTest;
import com.poc.classes.requisition.reject.RejectTest;
import com.poc.classes.requisition.sendforapproval.SendForApprovalTest;
import com.poc.classes.requisition.suspend.BuyerManagerSuspend;
import com.poc.classes.requisition.suspend.BuyerManagerSuspendTest;
import com.poc.classes.requisition.suspend.BuyerSuspendTest;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.poc.classes.login.Login;
import com.poc.classes.logout.Logout;
import com.poc.classes.purchaseorder.create.PoCreate;
import com.poc.classes.purchaseorder.sendforvendor.SendForVendor;
import com.poc.classes.purchaseorderrequest.approvalandapprove.PorSendForApprovalAndApprove;
import com.poc.classes.purchaseorderrequest.approve.PorApprove;
import com.poc.classes.purchaseorderrequest.create.PorCreate;
import com.poc.classes.purchaseorderrequest.edit.PorEdit;
import com.poc.classes.purchaseorderrequest.reject.PorReject;
import com.poc.classes.purchaseorderrequest.sendforapproval.PorSendForApproval;
import com.poc.classes.purchaseorderrequest.suspend.PorSuspend;
import com.poc.classes.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.poc.classes.requestforquotations.create.RfqCreate;
import com.poc.classes.requestforquotations.edit.RfqEdit;
import com.poc.classes.requestforquotations.quote.Quote;
import com.poc.classes.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.poc.classes.requestforquotations.regret.QuotationRegret;
import com.poc.classes.requestforquotations.requote.Requote;
import com.poc.classes.requestforquotations.suspend.RfqSuspend;
import com.poc.classes.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.poc.classes.requestforquotations.technicalevaluation.TechnicalEvaluationReject;
import com.poc.classes.requisition.approve.Approve;
import com.poc.classes.requisition.assign.Assign;
import com.poc.classes.requisition.create.Create;
import com.poc.classes.requisition.edit.Edit;
import com.poc.classes.requisition.reject.Reject;
import com.poc.classes.requisition.sendforapproval.SendForApproval;
import com.poc.classes.requisition.suspend.BuyerSuspend;
import com.poc.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.poc.classes.workorder.create.WoCreate;
import com.poc.classes.workorder.trackerstatus.WoTrackerStatus;
import com.poc.classes.workorders.create.WoCreateTest;
import com.poc.classes.workorders.trackerstatus.WoTrackerStatusTest;
import com.poc.interfaces.dispatchnotes.*;
import com.poc.interfaces.freightforwarderrequests.IFfrInvite;
import com.poc.interfaces.freightforwarderrequests.IFfrQuote;
import com.poc.interfaces.freightforwarderrequests.IFfrRequote;
import com.poc.interfaces.inspections.IInsAssign;
import com.poc.interfaces.inspections.IInsCreate;
import com.poc.interfaces.inspections.IInsFail;
import com.poc.interfaces.inspections.IInsReadyForInspection;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.orderschedule.IOsApprove;
import com.poc.interfaces.orderschedule.IOsCreate;
import com.poc.interfaces.orderschedule.IOsEdit;
import com.poc.interfaces.orderschedule.IOsReject;
import com.poc.interfaces.purchaseorderrequests.*;
import com.poc.interfaces.purchaseorders.IPoCreate;
import com.poc.interfaces.purchaseorders.IPoSendForVendor;
import com.poc.interfaces.requestforquotation.*;
import com.poc.interfaces.requisitions.*;
import com.poc.interfaces.workorders.IWoCreate;
import com.poc.interfaces.workorders.IWoTrackerStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.Properties;

public class BaseTest {

    protected PlaywrightFactory playwrightFactory;
    protected Properties properties;
    protected Page page;
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
    protected BuyerSuspendTest suspend;
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
    protected TechnicalEvaluationTest technicalEvaluationTest;
    protected ITeCreate iTeCreate;
    protected TechnicalEvaluationRejectTest technicalEvaluationRejectTest;
    protected ITeReject iTeReject;
    protected CommercialEvaluationTest commercialEvaluationTest;
    protected ICeCreate iCeCreate;
    protected PorCreateTest porCreateTest;
    protected IPorCreate iPorCreate;
    protected PorEditTest porEditTest;
    protected IPorEdit iPorEdit;
    protected PorSuspendPorEditTest porSuspendPorEditTest;
    protected PorSuspendRfqEditTest porSuspendRfqEditTest;
    protected IPorSuspend iPorSuspend;
    protected PorSendForApprovalTest porSendForApprovalTest;
    protected IPorSendForApproval iPorSendForApproval;
    protected PorRejectTest porRejectTest;
    protected IPorReject iPorReject;
    protected PorApproveTest porApproveTest;
    protected IPorApprove iPorApprove;
    protected PorSendForApprovalAndApproveTest porSendForApprovalAndApproveTest;
    protected IPorSendForApprovalAndApprove iPorSendForApprovalAndApprove;
    protected PoCreateTest poCreateTest;
    protected IPoCreate iPoCreate;
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
            loginTest = new LoginTest();
            iPrCreate = new Create(iLogin, properties, page, iLogout);
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
            createTest = new CreateTest();
            iPrSendForApproval = new SendForApproval(iLogin, properties, page, iLogout);
            sendForApprovalTest = new SendForApprovalTest();
            iPrApprove = new Approve(iLogin, properties, page, iLogout);
            approveTest = new ApproveTest();
            iPrAssign = new Assign(iLogin, properties, page, iLogout);
            assignTest = new AssignTest();
            iPrEdit = new Edit(iLogin, properties, page, iLogout, iPrSendForApproval, iPrApprove, iPrAssign);
            editTest = new EditTest();
            iPrReject = new Reject(iLogin, properties, page, iLogout, iPrEdit);
            rejectTest = new RejectTest();
            iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, properties, page, iLogout, iPrEdit);
            buyerManagerSuspendTest = new BuyerManagerSuspendTest();
            iPrBuyerSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);
            suspend = new BuyerSuspendTest();

//TODO Request For Quotation
            iRfqCreate = new RfqCreate(iLogin, properties, page, iLogout);
            rfqCreateTest = new RfqCreateTest();
            iRfqEdit = new RfqEdit(iLogin, properties, page, iLogout);
            rfqEditTest = new RfqEditTest();
            iRfqSuspend = new RfqSuspend(iLogin, properties, page, iLogout, iRfqEdit, iPrEdit, iPrSendForApproval, iPrApprove, iPrAssign, iRfqCreate);
            rfqSuspendPrEditTest = new RfqSuspendPrEditTest();
            rfqSuspendRfqEditTest = new RfqSuspendRfqEditTest();
            iQuoSubmit = new Quote(iLogin, properties, page, iLogout);
            quoteTest = new QuoteTest();
            iQuoRegret = new QuotationRegret(iQuoSubmit, iLogin, properties, page, iLogout);
            regretTest = new RegretTest();
            iQuoRequote = new Requote(iLogin, properties, page, iLogout);
            requoteTest = new RequoteTest();
            iReadyForEvalutation = new ReadyForEvaluation(iLogin, properties, page, iLogout);
            readyForEvaluationTest = new ReadyForEvaluationTest();
            iTeCreate = new TechnicalEvaluation(iLogin, properties, page, iLogout);
            technicalEvaluationTest = new TechnicalEvaluationTest();
            iTeReject = new TechnicalEvaluationReject(iLogin, properties, page, iLogout);
            technicalEvaluationRejectTest = new TechnicalEvaluationRejectTest();
            iCeCreate = new CommercialEvaluation(iLogin, properties, page, iLogout);
            commercialEvaluationTest = new CommercialEvaluationTest();

//TODO Purchase Order Request
            iPorCreate = new PorCreate(iLogin, properties, page, iLogout);
            porCreateTest = new PorCreateTest();
            iPorEdit = new PorEdit(iLogin, properties, page, iLogout);
            porEditTest = new PorEditTest();
            iPorSuspend = new PorSuspend(iLogin, properties, page, iLogout, iPorEdit, iCeCreate, iPorCreate);
            porSuspendPorEditTest = new PorSuspendPorEditTest();
            porSuspendRfqEditTest = new PorSuspendRfqEditTest();
            iPorSendForApproval = new PorSendForApproval(iLogin, properties, page, iLogout);
            porSendForApprovalTest = new PorSendForApprovalTest();
            iPorApprove = new PorApprove(iLogin, properties, page, iLogout);
            porApproveTest = new PorApproveTest();
            iPorReject = new PorReject(iLogin, properties, page, iLogout, iPorEdit, iPorSendForApproval);
            porRejectTest = new PorRejectTest();
            iPorSendForApprovalAndApprove = new PorSendForApprovalAndApprove(iPorApprove, iPorSendForApproval);
            porSendForApprovalAndApproveTest = new PorSendForApprovalAndApproveTest();

//TODO Purchase Orders
            iPoCreate = new PoCreate(iLogin, properties, page, iLogout);
            poCreateTest = new PoCreateTest();
            iPoSendForVendor = new SendForVendor(iLogin, properties, page, iLogout);
            poSendForVendorTest = new PoSendForVendorTest();

//TODO Order Schedule
            iOsCreate = new OsCreate(iLogin, properties, page, iLogout, playwrightFactory);
            osCreateTest = new OsCreateTest();
            iOsEdit = new OsEdit(iLogin, properties, page, iLogout);
            osEditTest = new OsEditTest();
            iOsReject = new OsReject(iLogin, properties, page, iLogout);
            osRejectTest = new OsRejectTest();
            iOsApprove = new OsApprove(iLogin, properties, page, iLogout);
            osApproveTest = new OsApproveTest();

//TODO Inspection
            iInsReadyForInspection = new InsReadyForInspection(iLogin, properties, page, iLogout);
            insReadyForInspectionTest = new InsReadyForInspectionTest();
            iInsCreate = new InsCreate(iLogin, properties, page, iLogout);
            insCreateTest = new InsCreateTest();
            iInsAssign = new InsAssign(iLogin, properties, page, iLogout);
            insAssignTest = new InsAssignTest();
            iInsFail = new InsFail(iLogin, properties, page, iLogout, iInsReadyForInspection);
            insFailTest = new InsFailTest();

//TODO Dispatch Notes
            iDnCreate = new DnCreate(iLogin, properties, page, iLogout);
            dnCreateTest = new DnCreateTest();
            iDnEdit = new DnEdit(iLogin, properties, page, iLogout);
            dnEditTest = new DnEditTest();
            iDnReturn = new DnReturn(iLogin, properties, page, iLogout, iDnEdit);
            dnReturnTest = new DnReturnTest();
            iDnCancel = new DnCancel(iLogin, properties, page, iLogout, iDnCreate);
            dnCancelTest = new DnCancelTest();
            iDnAssign = new DnAssign(iLogin, properties, page, iLogout, playwrightFactory);
            dnAssignTest = new DnAssignTest();

//TODO Freight Forwarder Requests
            iFfrInvite = new FfrInvite(iLogin, properties, page, iLogout);
            ffrInviteTest = new FfrInviteTest();
            iFfrQuote = new FfrQuote(iLogin, properties, page, iLogout);
            ffrQuoteTest = new FfrQuoteTest();
            iFfrRequote = new FfrRequote(iLogin, properties, iFfrQuote, iLogout, page);
            ffrRequoteTest = new FfrRequoteTest();

//TODO Work Orders
            iWoCreate = new WoCreate(iLogin, properties, page, iLogout);
            woCreateTest = new WoCreateTest();
            iWoTrackerStatus = new WoTrackerStatus(iLogin, properties, page, iLogout, playwrightFactory);
            woTrackerStatusTest = new WoTrackerStatusTest();

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