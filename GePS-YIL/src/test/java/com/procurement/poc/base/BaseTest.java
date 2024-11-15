package com.procurement.poc.base;
import com.procurement.poc.login.LoginTest;
import com.procurement.poc.purchaseorder.create.PoCreateTest;
import com.procurement.poc.purchaseorder.sendforapproval.PoSendForVendorTest;
import com.procurement.poc.purchaseorderrequest.approvalandapprove.PorSendForApprovalAndApproveTest;
import com.procurement.poc.purchaseorderrequest.approve.PorApproveTest;
import com.procurement.poc.purchaseorderrequest.create.PorCreateTest;
import com.procurement.poc.purchaseorderrequest.edit.PorEditTest;
import com.procurement.poc.purchaseorderrequest.reject.PorRejectTest;
import com.procurement.poc.purchaseorderrequest.sendforapproval.PorSendForApprovalTest;
import com.procurement.poc.purchaseorderrequest.suspend.PorSuspendPorEditTest;
import com.procurement.poc.purchaseorderrequest.suspend.PorSuspendRfqEditTest;
import com.procurement.poc.requestforquotation.commercialevaluation.CommercialEvaluationTest;
import com.procurement.poc.requestforquotation.create.RfqCreateTest;
import com.procurement.poc.requestforquotation.edit.RfqEditTest;
import com.procurement.poc.requestforquotation.regret.RegretTest;
import com.procurement.poc.requestforquotation.requote.RequoteTest;
import com.procurement.poc.requestforquotation.quote.QuoteTest;
import com.procurement.poc.requestforquotation.readyforevaluation.ReadyForEvaluationTest;
import com.procurement.poc.requestforquotation.suspend.RfqSuspendPrEditTest;
import com.procurement.poc.requestforquotation.suspend.RfqSuspendRfqEditTest;
import com.procurement.poc.requestforquotation.technicalevaluation.TechnicalEvaluationRejectTest;
import com.procurement.poc.requestforquotation.technicalevaluation.TechnicalEvaluationTest;
import com.procurement.poc.requisition.approve.ApproveTest;
import com.procurement.poc.requisition.assign.AssignTest;
import com.procurement.poc.requisition.create.CreateTest;
import com.procurement.poc.requisition.edit.EditTest;
import com.procurement.poc.requisition.reject.RejectTest;
import com.procurement.poc.requisition.sendforapproval.SendForApprovalTest;
import com.procurement.poc.requisition.suspend.SuspendTest;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.procurement.poc.classes.login.Login;
import com.procurement.poc.classes.logout.Logout;
import com.procurement.poc.classes.purchaseorder.create.PoCreate;
import com.procurement.poc.classes.purchaseorder.sendforvendor.SendForVendor;
import com.procurement.poc.classes.purchaseorderrequest.approvalandapprove.PorSendForApprovalAndApprove;
import com.procurement.poc.classes.purchaseorderrequest.approve.PorApprove;
import com.procurement.poc.classes.purchaseorderrequest.create.PorCreate;
import com.procurement.poc.classes.purchaseorderrequest.edit.PorEdit;
import com.procurement.poc.classes.purchaseorderrequest.reject.PorReject;
import com.procurement.poc.classes.purchaseorderrequest.sendforapproval.PorSendForApproval;
import com.procurement.poc.classes.purchaseorderrequest.suspend.PorSuspend;
import com.procurement.poc.classes.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.procurement.poc.classes.requestforquotations.create.RfqCreate;
import com.procurement.poc.classes.requestforquotations.edit.RfqEdit;
import com.procurement.poc.classes.requestforquotations.quote.Quote;
import com.procurement.poc.classes.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.procurement.poc.classes.requestforquotations.regret.QuotationRegret;
import com.procurement.poc.classes.requestforquotations.requote.Requote;
import com.procurement.poc.classes.requestforquotations.suspend.RfqSuspend;
import com.procurement.poc.classes.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.procurement.poc.classes.requestforquotations.technicalevaluation.TechnicalEvaluationReject;
import com.procurement.poc.classes.requisition.approve.Approve;
import com.procurement.poc.classes.requisition.assign.Assign;
import com.procurement.poc.classes.requisition.create.Create;
import com.procurement.poc.classes.requisition.edit.Edit;
import com.procurement.poc.classes.requisition.reject.Reject;
import com.procurement.poc.classes.requisition.sendforapproval.SendForApproval;
import com.procurement.poc.classes.requisition.suspend.Suspend;
import com.procurement.poc.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.*;
import com.procurement.poc.interfaces.purchaseorders.IPoCreate;
import com.procurement.poc.interfaces.purchaseorders.IPoSendForVendor;
import com.procurement.poc.interfaces.requestforquotation.*;
import com.procurement.poc.interfaces.requisitions.*;
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
    protected SuspendTest suspend;
    protected IPrSuspend iPrSuspend;
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
            iPrSuspend = new Suspend(iLogin, properties, page, iLogout, iPrEdit);
            suspend = new SuspendTest();

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