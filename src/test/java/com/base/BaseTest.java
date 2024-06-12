package com.base;
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
import com.procurement.requestforquotation.edit.RfqEditTest;
import com.procurement.requestforquotation.suspend.RfqSuspendPrEditTest;
import com.procurement.requestforquotation.suspend.RfqSuspendRfqEditTest;
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
import com.interfaces.DispatchNotesAssignInterface;
import com.procurement.dispatchnotes.create.DispatchNoteCreate;
import com.interfaces.DispatchNoteCreateInterface;
import com.procurement.inspections.assign.InspectionAssign;
import com.interfaces.InspectionAssignInterface;
import com.procurement.inspections.create.InspectionCreate;
import com.interfaces.InspectionCreateInterface;
import com.procurement.invoice.approve.POInvoiceApproval;
import com.interfaces.POInvoiceApprovalInterface;
import com.procurement.invoice.create.POInvoiceCreate;
import com.interfaces.POInvoiceCreateInterface;
import com.procurement.invoice.sendforapproval.POInvoiceSendForApproval;
import com.interfaces.POSendForApprovalInterface;
import com.procurement.login.LoginPage;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.procurement.logout.LogoutPage;
import com.procurement.msa.PorInspectPO;
import com.interfaces.PorInspectPoInterface;
import com.procurement.orderschedule.approve.OrderScheduleApprove;
import com.interfaces.OrderScheduleApproveInterface;
import com.procurement.orderschedule.create.OrderScheduleCreate;
import com.interfaces.OrderScheduleInterface;
import com.procurement.purchaseorder.BuyerPurchaseOrder;
import com.interfaces.PurchaseOrderInterface;
import com.procurement.purchaseorderrequest.approval.PocPorApproval;
import com.interfaces.PorApproval;
import com.procurement.purchaseorderrequest.create.PocNonCatalogPorCreate;
import com.interfaces.PorCreateNonCatalog;
import com.procurement.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.interfaces.CommercialEvaluationInterface;
import com.procurement.requestforquotations.create.PocRfqCreate;
import com.interfaces.RfqCreate;
import com.procurement.requestforquotations.edit.PocRfqEdit;
import com.interfaces.RfqEdit;
import com.interfaces.QuotationSubmit;
import com.procurement.requestforquotations.quotationsubmit.RegisteredVendorQuotationSubmit;
import com.procurement.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.interfaces.ReadyForEvalutationInterface;
import com.procurement.requestforquotations.suspend.PocRfqSuspend;
import com.interfaces.RfqSuspend;
import com.procurement.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.interfaces.TechnicalEvaluationInterface;
import com.procurement.requisition.approve.PocPrApprove;
import com.interfaces.PrApprove;
import com.procurement.requisition.assign.PocPrAssign;
import com.interfaces.PrAssign;
import com.procurement.requisition.create.PocNonCatalogPrCreate;
import com.interfaces.PrCreateNonCatalog;
import com.procurement.requisition.edit.PocPrEdit;
import com.interfaces.PrEdit;
import com.procurement.requisition.reject.PocPrReject;
import com.interfaces.PrReject;
import com.procurement.requisition.sendforapproval.PocPrSendForApproval;
import com.interfaces.PrSendForApproval;
import com.procurement.requisition.suspend.PocPrBuyerManagerSuspend;
import com.procurement.requisition.suspend.PocPrBuyerSuspend;
import com.interfaces.PrSuspend;
import com.procurement.workorder.create.WorkOrderCreate;
import com.interfaces.WorkOrderCreateInterface;
import com.procurement.workorder.trackerstatus.WOTrackerStatus;
import com.interfaces.WOTrackerStatusInterface;
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
    protected ReadyForEvaluationTest readyForEvaluationTest;
    protected ReadyForEvalutationInterface readyForEvalutationInterface;
    protected TechnicalEvaluationTest technicalEvaluationTest;
    protected TechnicalEvaluationInterface technicalEvaluationInterface;
    protected CommercialEvaluationTest commercialEvaluationTest;
    protected CommercialEvaluationInterface commercialEvaluationInterface;
    protected PorCreateTest porCreateTest;
    protected PorCreateNonCatalog porCreateNonCatalog;
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
            readyForEvalutationInterface = new ReadyForEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            readyForEvaluationTest = new ReadyForEvaluationTest();
            technicalEvaluationInterface = new TechnicalEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationTest = new TechnicalEvaluationTest();
            commercialEvaluationInterface = new CommercialEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            commercialEvaluationTest = new CommercialEvaluationTest();

//TODO Purchase Order Request
            porCreateNonCatalog = new PocNonCatalogPorCreate(loginPageInterface, properties, page, logoutPageInterface);
            porCreateTest = new PorCreateTest();
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