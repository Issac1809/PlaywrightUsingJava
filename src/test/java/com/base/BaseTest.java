package com.base;
import com.interfaces.*;
import com.procurement.currencyexchangerate.CurrencyExchangeRateTest;
import com.procurement.dispatchnotes.create.DispatchNotesCreateTest;
import com.procurement.dispatchnotes.assign.DispatchNotesAssignTest;
import com.procurement.inspections.assign.InspectionAssignTest;
import com.procurement.inspections.create.InspectionCreateTest;
import com.procurement.invoice.approve.POInvoiceApprovalTest;
import com.procurement.invoice.create.InvoiceCreateTest;
import com.procurement.invoice.sendforapproval.POInvoiceSendForApprovalTest;
import com.microsoft.playwright.Page;
import com.procurement.msa.InspectPOTest;
import com.procurement.orderschedule.approval.OSApproveTest;
import com.procurement.orderschedule.create.OSCreateTest;
import com.factory.PlayWrightFactory;
import com.procurement.purchaseorder.POSendForVendorTest;
import com.procurement.purchaseorderrequest.approval.PorApprovalTest;
import com.procurement.purchaseorderrequest.create.PorCreateTest;
import com.procurement.purchaseorderrequest.edit.PocPorEdit;
import com.procurement.purchaseorderrequest.edit.PorEditTest;
import com.procurement.purchaseorderrequest.suspend.PocPorSuspend;
import com.procurement.purchaseorderrequest.suspend.PorSuspendPorEditTest;
import com.procurement.purchaseorderrequest.suspend.PorSuspendRfqEditTest;
import com.procurement.requestforquotation.edit.RfqEditTest;
import com.procurement.requestforquotation.quotationregret.RegretBeforeRequoteTest;
import com.procurement.requestforquotation.quotationrequote.QuotationRequoteTest;
import com.procurement.requestforquotation.suspend.RfqSuspendPrEditTest;
import com.procurement.requestforquotation.suspend.RfqSuspendRfqEditTest;
import com.procurement.requestforquotation.technicalevaluation.TechnicalEvaluationRejectTest;
import com.procurement.requestforquotations.quotationregret.RegisteredVendorQuotationRegret;
import com.procurement.requestforquotations.quotationrequote.RegisteredVendorQuotationRequote;
import com.procurement.requestforquotations.technicalevaluation.TechnicalEvaluationReject;
import com.procurement.requisition.approve.NonCatalogPrApproveTest;
import com.procurement.requisition.assign.RequisitionAssignTest;
import com.procurement.requisition.edit.NonCatalogPrEditTest;
import com.procurement.requisition.reject.NonCatalogPrRejectTest;
import com.procurement.requisition.sendforapproval.NonCatalogPrSendForApprovalTest;
import com.procurement.requisition.create.NonCatalogPrCreateTest;
import com.procurement.requestforquotation.commercialevaluation.CommercialEvaluationTest;
import com.procurement.requestforquotation.create.RFQCreateTest;
import com.procurement.requestforquotation.quotationsubmit.QuotationSubmitTest;
import com.procurement.requestforquotation.readyforevaluation.ReadyForEvaluationTest;
import com.procurement.requestforquotation.technicalevaluation.TechnicalEvaluationTest;
import com.procurement.requisition.suspend.BuyerManagerNonCatalogPrSuspendTest;
import com.procurement.requisition.suspend.BuyerNonCatalogPrSuspendTest;
import com.procurement.workorders.trackerstatus.WOTrackerStatusTest;
import com.procurement.workorders.create.WorkOrdersCreateTest;
import com.procurement.currencyexchangerate.CurrencyExchangeRate;
import com.procurement.dispatchnotes.assign.DispatchNotesAssign;
import com.procurement.dispatchnotes.create.DispatchNoteCreate;
import com.procurement.inspections.assign.InspectionAssign;
import com.procurement.inspections.create.InspectionCreate;
import com.procurement.invoice.approve.POInvoiceApproval;
import com.procurement.invoice.create.POInvoiceCreate;
import com.procurement.invoice.sendforapproval.POInvoiceSendForApproval;
import com.procurement.login.LoginPage;
import com.procurement.logout.LogoutPage;
import com.procurement.msa.PorInspectPO;
import com.procurement.orderschedule.approve.OrderScheduleApprove;
import com.procurement.orderschedule.create.OrderScheduleCreate;
import com.procurement.purchaseorder.BuyerPurchaseOrder;
import com.procurement.purchaseorderrequest.approval.PocPorApproval;
import com.procurement.purchaseorderrequest.create.PocNonCatalogPorCreate;
import com.procurement.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.procurement.requestforquotations.create.PocRfqCreate;
import com.procurement.requestforquotations.edit.PocRfqEdit;
import com.procurement.requestforquotations.quotationsubmit.RegisteredVendorQuotationSubmit;
import com.procurement.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.procurement.requestforquotations.suspend.PocRfqSuspend;
import com.procurement.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.procurement.requisition.approve.PocPrApprove;
import com.procurement.requisition.assign.PocPrAssign;
import com.procurement.requisition.create.PocNonCatalogPrCreate;
import com.procurement.requisition.edit.PocPrEdit;
import com.procurement.requisition.reject.PocPrReject;
import com.procurement.requisition.sendforapproval.PocPrSendForApproval;
import com.procurement.requisition.suspend.PocPrBuyerManagerSuspend;
import com.procurement.requisition.suspend.PocPrBuyerSuspend;
import com.procurement.workorder.create.WorkOrderCreate;
import com.procurement.workorder.trackerstatus.WOTrackerStatus;
import com.reports.ExtendReportListener;
import org.testng.ITestListener;
import org.testng.annotations.*;
import java.util.Properties;

public class BaseTest {

    protected PlayWrightFactory playWrightFactory;
    protected Page page;
    protected Properties properties;
    protected CurrencyExchangeRate currencyExchangeRate;
    protected CurrencyExchangeRateTest currencyExchangeRateTest;
    protected LoginPageInterface loginPageInterface;
    protected LogoutPageInterface logoutPageInterface;
    protected NonCatalogPrCreateTest nonCatalogPrCreateTest;
    protected PrCreateNonCatalog prCreateNonCatalog;
    protected NonCatalogPrEditTest nonCatalogPrEditTest;
    protected PrEdit prEdit;
    protected NonCatalogPrSendForApprovalTest nonCatalogPrSendForApprovalTest;
    protected PrSendForApproval prSendForApproval;
    protected NonCatalogPrRejectTest nonCatalogPrRejectTest;
    protected PrApprove prApprove;
    protected NonCatalogPrApproveTest nonCatalogPrApproveTest;
    protected PrReject prReject;
    protected BuyerManagerNonCatalogPrSuspendTest buyerManagerNonCatalogPrSuspendTest;
    protected PrSuspend prSuspendBuyerManager;
    protected BuyerNonCatalogPrSuspendTest buyerNonCatalogPrSuspendTest;
    protected PrSuspend prSuspendBuyer;
    protected RequisitionAssignTest requisitionAssignTest;
    protected PrAssign prAssign;
    protected RFQCreateTest rfqCreateTest;
    protected RfqCreate rfqCreate;
    protected RfqEditTest rfqEditTest;
    protected RfqEdit rfqEdit;
    protected RfqSuspendRfqEditTest rfqSuspendRfqEditTest;
    protected RfqSuspendPrEditTest rfqSuspendPrEditTest;
    protected RfqSuspend rfqSuspend;
    protected QuotationSubmitTest quotationSubmitTest;
    protected QuotationSubmit quotationSubmit;
    protected RegretBeforeRequoteTest regretBeforeRequoteTest;
    protected QuotationRequote quotationRequote;
    protected QuotationRegret quotationRegret;
    protected QuotationRequoteTest quotationRequoteTest;
    protected ReadyForEvaluationTest readyForEvaluationTest;
    protected ReadyForEvalutationInterface readyForEvalutationInterface;
    protected TechnicalEvaluationRejectTest technicalEvaluationRejectTest;
    protected TEReject teReject;
    protected TechnicalEvaluationTest technicalEvaluationTest;
    protected TechnicalEvaluationInterface technicalEvaluationInterface;
    protected CommercialEvaluationTest commercialEvaluationTest;
    protected CommercialEvaluationInterface commercialEvaluationInterface;
    protected PorCreateTest porCreateTest;
    protected PorCreateNonCatalog porCreateNonCatalog;
    protected PorEditTest porEditTest;
    protected PorEdit porEdit;
    protected PorSuspendPorEditTest porSuspendPorEditTest;
    protected PorSuspendRfqEditTest porSuspendRfqEditTest;
    protected PorSuspend porSuspend;
    protected PorApprovalTest porApprovalTest;
    protected PorApproval porApproval;
    protected InspectPOTest inspectPOTest;
    protected PorInspectPoInterface porInspectPoInterface;
    protected POSendForVendorTest poSendForVendorTest;
    protected PurchaseOrderInterface purchaseOrderInterface;
    protected OSCreateTest osCreateTest;
    protected OrderScheduleInterface orderScheduleInterface;
    protected OSApproveTest osApproveTest;
    protected OrderScheduleApproveInterface orderScheduleApproveInterface;
    protected InspectionCreateTest inspectionCreateTest;
    protected InspectionCreateInterface inspectionCreateInterface;
    protected InspectionAssignTest inspectionAssignTest;
    protected InspectionAssignInterface inspectionAssignInterface;
    protected DispatchNotesCreateTest dispatchNotesCreateTest;
    protected DispatchNoteCreateInterface dispatchNoteCreateInterface;
    protected DispatchNotesAssignTest dispatchNotesAssignTest;
    protected DispatchNotesAssignInterface dispatchNotesAssignInterface;
    protected WorkOrdersCreateTest workOrdersCreateTest;
    protected WorkOrderCreateInterface workOrderCreateInterface;
    protected WOTrackerStatusTest woTrackerStatusTest;
    protected WOTrackerStatusInterface woTrackerStatusInterface;
    protected InvoiceCreateTest invoiceCreateTest;
    protected POInvoiceCreateInterface poInvoiceCreateInterface;
    protected POInvoiceSendForApprovalTest poInvoiceSendForApprovalTest;
    protected POSendForApprovalInterface poSendForApprovalInterface;
    protected POInvoiceApprovalTest poInvoiceApprovalTest;
    protected POInvoiceApprovalInterface poInvoiceApprovalInterface;
    protected ITestListener iTestListener;

    @BeforeTest
    public void Setup() {
        try {
            playWrightFactory = new PlayWrightFactory();
            properties = playWrightFactory.initializeProperties();
            page = playWrightFactory.initializeBrowser(properties);

            loginPageInterface = new LoginPage(properties, page);
            logoutPageInterface = new LogoutPage(page);

//TODO Requisition
            prCreateNonCatalog = new PocNonCatalogPrCreate(loginPageInterface, properties, page, logoutPageInterface);
            nonCatalogPrCreateTest = new NonCatalogPrCreateTest();
            prSendForApproval = new PocPrSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            nonCatalogPrSendForApprovalTest = new NonCatalogPrSendForApprovalTest();
            prApprove = new PocPrApprove(loginPageInterface, properties, page, logoutPageInterface);
            nonCatalogPrApproveTest = new NonCatalogPrApproveTest();
            prAssign = new PocPrAssign(loginPageInterface, properties, page, logoutPageInterface);
            requisitionAssignTest = new RequisitionAssignTest();
            prEdit = new PocPrEdit(loginPageInterface, properties, page, logoutPageInterface, prSendForApproval, prApprove, prAssign);
            nonCatalogPrEditTest = new NonCatalogPrEditTest();
            prReject = new PocPrReject(loginPageInterface, properties, page, logoutPageInterface, prEdit);
            nonCatalogPrRejectTest = new NonCatalogPrRejectTest();
            prSuspendBuyerManager = new PocPrBuyerManagerSuspend(loginPageInterface, properties, page, logoutPageInterface, prEdit);
            buyerManagerNonCatalogPrSuspendTest = new BuyerManagerNonCatalogPrSuspendTest();
            prSuspendBuyer = new PocPrBuyerSuspend(loginPageInterface, properties, page, logoutPageInterface, prEdit);
            buyerNonCatalogPrSuspendTest = new BuyerNonCatalogPrSuspendTest();

//TODO Request For Quotation
            rfqCreate = new PocRfqCreate(loginPageInterface, properties, page, logoutPageInterface);
            rfqCreateTest = new RFQCreateTest();
            rfqEdit = new PocRfqEdit(loginPageInterface, properties, page, logoutPageInterface);
            rfqEditTest = new RfqEditTest();
            rfqSuspend = new PocRfqSuspend(loginPageInterface, properties, page, logoutPageInterface, rfqEdit, prEdit, prSendForApproval, prApprove, prAssign, rfqCreate);
            rfqSuspendRfqEditTest = new RfqSuspendRfqEditTest();
            rfqSuspendPrEditTest = new RfqSuspendPrEditTest();
            quotationSubmit = new RegisteredVendorQuotationSubmit(loginPageInterface, properties, page, logoutPageInterface);
            quotationSubmitTest = new QuotationSubmitTest();
            quotationRegret = new RegisteredVendorQuotationRegret(quotationSubmit, loginPageInterface, properties, page, logoutPageInterface);
            regretBeforeRequoteTest = new RegretBeforeRequoteTest();
            quotationRequote = new RegisteredVendorQuotationRequote(loginPageInterface, properties, page, logoutPageInterface);
            quotationRequoteTest = new QuotationRequoteTest();
            readyForEvalutationInterface = new ReadyForEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            readyForEvaluationTest = new ReadyForEvaluationTest();
            teReject = new TechnicalEvaluationReject(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationTest = new TechnicalEvaluationTest();
            technicalEvaluationInterface = new TechnicalEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationTest = new TechnicalEvaluationTest();
            commercialEvaluationInterface = new CommercialEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            commercialEvaluationTest = new CommercialEvaluationTest();

//TODO Purchase Order Request
            porCreateNonCatalog = new PocNonCatalogPorCreate(loginPageInterface, properties, page, logoutPageInterface);
            porCreateTest = new PorCreateTest();
            porEdit = new PocPorEdit(loginPageInterface, properties, page, logoutPageInterface);
            porEditTest = new PorEditTest();
            porSuspend = new PocPorSuspend(loginPageInterface, properties, page, logoutPageInterface, porEdit, commercialEvaluationInterface, porCreateNonCatalog);
            porSuspendPorEditTest = new PorSuspendPorEditTest();
            porSuspendRfqEditTest = new PorSuspendRfqEditTest();
            porApproval = new PocPorApproval(loginPageInterface, properties, page, logoutPageInterface);
            porApprovalTest = new PorApprovalTest();
            porInspectPoInterface = new PorInspectPO(loginPageInterface, properties, page, logoutPageInterface);
            inspectPOTest = new InspectPOTest();
            purchaseOrderInterface = new BuyerPurchaseOrder(loginPageInterface, properties, page, logoutPageInterface);
            poSendForVendorTest = new POSendForVendorTest();
            orderScheduleInterface = new OrderScheduleCreate(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            osCreateTest = new OSCreateTest();
            orderScheduleApproveInterface = new OrderScheduleApprove(loginPageInterface, properties, page, logoutPageInterface);
            osApproveTest = new OSApproveTest();
            inspectionCreateInterface = new InspectionCreate(loginPageInterface, properties, page, logoutPageInterface);
            inspectionCreateTest = new InspectionCreateTest();
            inspectionAssignInterface = new InspectionAssign(loginPageInterface, properties, page, logoutPageInterface);
            inspectionAssignTest = new InspectionAssignTest();
            dispatchNoteCreateInterface = new DispatchNoteCreate(loginPageInterface, properties, page, logoutPageInterface);
            dispatchNotesCreateTest = new DispatchNotesCreateTest();
            dispatchNotesAssignInterface = new DispatchNotesAssign(loginPageInterface, properties, page, logoutPageInterface);
            dispatchNotesAssignTest = new DispatchNotesAssignTest();
            workOrderCreateInterface = new WorkOrderCreate(loginPageInterface, properties, page, logoutPageInterface);
            workOrdersCreateTest = new WorkOrdersCreateTest();
            woTrackerStatusInterface = new WOTrackerStatus(loginPageInterface, properties, page, logoutPageInterface);
            woTrackerStatusTest = new WOTrackerStatusTest();
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, loginPageInterface, properties, logoutPageInterface);
            currencyExchangeRateTest = new CurrencyExchangeRateTest();
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            invoiceCreateTest = new InvoiceCreateTest();
            poSendForApprovalInterface = new POInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceSendForApprovalTest = new POInvoiceSendForApprovalTest();
            poInvoiceApprovalInterface = new POInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceApprovalTest = new POInvoiceApprovalTest();
            iTestListener = new ExtendReportListener();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }

    @AfterTest
    public void TearDown() {
        try {
            page.context().browser().close();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }

    @AfterTest
    public void TearDown(Page page) {
        try {
            page.context().browser().close();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }
}