package com.base;
import com.interfaces.dn.*;
import com.interfaces.ffr.FFRInvite;
import com.interfaces.ffr.FFRQuotation;
import com.interfaces.ffr.FFRRequote;
import com.interfaces.ins.InsFail;
import com.interfaces.ins.InspectionAssignInterface;
import com.interfaces.ins.InspectionCreateInterface;
import com.interfaces.inv.poinv.*;
import com.interfaces.inv.woinv.*;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.os.OSEdit;
import com.interfaces.os.OSReject;
import com.interfaces.os.OrderScheduleApproveInterface;
import com.interfaces.os.OrderScheduleInterface;
import com.interfaces.po.POSendForApprovalInterface;
import com.interfaces.po.PurchaseOrderInterface;
import com.interfaces.por.*;
import com.interfaces.pr.*;
import com.interfaces.rfq.*;
import com.interfaces.wo.WOSendForApprovalInterface;
import com.interfaces.wo.WOTrackerStatusInterface;
import com.interfaces.wo.WorkOrderCreateInterface;
import com.procurement.currencyexchangerate.CurrencyExchangeRateTest;
import com.procurement.dispatchnotes.cancel.DnCancelTest;
import com.procurement.dispatchnotes.cancel.PocDnCancel;
import com.procurement.dispatchnotes.create.DispatchNotesCreateTest;
import com.procurement.dispatchnotes.assign.DispatchNotesAssignTest;
import com.procurement.dispatchnotes.dnreturn.DnReturnTest;
import com.procurement.dispatchnotes.dnreturn.PocDnReturn;
import com.procurement.dispatchnotes.edit.DnEditTest;
import com.procurement.dispatchnotes.edit.PocDnEdit;
import com.procurement.freightforwarder.invite.FreightForwarderInvite;
import com.procurement.freightforwarder.quote.FreightQuotation;
import com.procurement.freightforwarder.requote.FreightForwarderRequote;
import com.procurement.freightforwarderrequests.invite.FFRInviteTest;
import com.procurement.freightforwarderrequests.quote.FFRQuoteTest;
import com.procurement.freightforwarderrequests.requote.FFRRequoteTest;
import com.procurement.inspections.assign.InspectionAssignTest;
import com.procurement.inspections.create.InspectionCreateTest;
import com.procurement.inspections.fail.InspectionFail;
import com.procurement.inspections.fail.InspectionFailTest;
import com.procurement.invoice.poinvoice.approve.POInvoiceApprovalTest;
import com.procurement.invoice.poinvoice.cancel.InvoiceCancelTest;
import com.procurement.invoice.poinvoice.checklist.ChecklistAcceptTest;
import com.procurement.invoice.poinvoice.checklist.ChecklistRejectTest;
import com.procurement.invoice.poinvoice.create.InvoiceCreateTest;
import com.procurement.invoice.poinvoice.edit.InvoiceEditTest;
import com.procurement.invoice.poinvoice.hold.InvoiceHoldTest;
import com.procurement.invoice.poinvoice.invreturn.InvoiceReturnTest;
import com.procurement.invoice.poinvoice.cancel.PoInvoiceCancel;
import com.procurement.invoice.poinvoice.checklist.ChecklistAccept;
import com.procurement.invoice.poinvoice.checklist.ChecklistReject;
import com.procurement.invoice.poinvoice.edit.POInvoiceEdit;
import com.procurement.invoice.poinvoice.hold.POInvoiceHold;
import com.procurement.invoice.poinvoice.invreturn.POInvoiceReturn;
import com.procurement.invoice.poinvoice.reject.POInvoiceReject;
import com.procurement.invoice.poinvoice.revert.POInvoiceRevert;
import com.procurement.invoice.poinvoice.verify.POInvoiceVerify;
import com.procurement.invoice.poinvoice.reject.InvoiceRejectTest;
import com.procurement.invoice.poinvoice.revert.InvoiceRevertTest;
import com.procurement.invoice.poinvoice.sendforapproval.POInvoiceSendForApprovalTest;
import com.microsoft.playwright.Page;
import com.procurement.invoice.poinvoice.verify.InvoiceVerifyTest;
import com.procurement.invoice.woinvoice.approve.WOInvoiceApproval;
import com.procurement.invoice.woinvoice.approve.WOInvoiceApprovalTest;
import com.procurement.invoice.woinvoice.cancel.WOInvoiceCancelTest;
import com.procurement.invoice.woinvoice.cancel.WoInvoiceCancel;
import com.procurement.invoice.woinvoice.checklist.WOChecklistAccept;
import com.procurement.invoice.woinvoice.checklist.WOChecklistAcceptTest;
import com.procurement.invoice.woinvoice.checklist.WOChecklistReject;
import com.procurement.invoice.woinvoice.checklist.WOChecklistRejectTest;
import com.procurement.invoice.woinvoice.create.WOInvoiceCreate;
import com.procurement.invoice.woinvoice.create.WOInvoiceCreateTest;
import com.procurement.invoice.woinvoice.edit.WOInvoiceEdit;
import com.procurement.invoice.woinvoice.edit.WOInvoiceEditTest;
import com.procurement.invoice.woinvoice.hold.WOInvoiceHold;
import com.procurement.invoice.woinvoice.hold.WOInvoiceHoldTest;
import com.procurement.invoice.woinvoice.invreturn.WOInvoiceReturn;
import com.procurement.invoice.woinvoice.invreturn.WOInvoiceReturnTest;
import com.procurement.invoice.woinvoice.reject.WOInvoiceReject;
import com.procurement.invoice.woinvoice.reject.WOInvoiceRejectTest;
import com.procurement.invoice.woinvoice.revert.WOInvoiceRevert;
import com.procurement.invoice.woinvoice.revert.WOInvoiceRevertTest;
import com.procurement.invoice.woinvoice.sendforapproval.WOInvoiceSendForApproval;
import com.procurement.invoice.woinvoice.sendforapproval.WOInvoiceSendForApprovalTest;
import com.procurement.invoice.woinvoice.verify.WOInvoiceVerify;
import com.procurement.invoice.woinvoice.verify.WOInvoiceVerifyTest;
import com.procurement.msa.InspectPOTest;
import com.procurement.orderschedule.approval.OSApproveTest;
import com.procurement.orderschedule.create.OSCreateTest;
import com.factory.PlayWrightFactory;
import com.procurement.orderschedule.edit.OSEditTest;
import com.procurement.orderschedule.edit.OrderScheduleEdit;
import com.procurement.orderschedule.reject.OSRejectTest;
import com.procurement.orderschedule.reject.OrderScheduleReject;
import com.procurement.purchaseorder.POSendForVendorTest;
import com.procurement.purchaseorderrequest.approval.Approve;
import com.procurement.purchaseorderrequest.approvalandapprove.PorApprovalAndApproveTest;
import com.procurement.purchaseorderrequest.create.PorCreateTest;
import com.procurement.purchaseorderrequest.edit.PocPorEdit;
import com.procurement.purchaseorderrequest.edit.PorEditTest;
import com.procurement.purchaseorderrequest.approvalandapprove.PorApprovalAndApprove;
import com.procurement.purchaseorderrequest.reject.PocPorReject;
import com.procurement.purchaseorderrequest.reject.PocPorRejectTest;
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
import com.procurement.invoice.poinvoice.approve.POInvoiceApproval;
import com.procurement.invoice.poinvoice.create.POInvoiceCreate;
import com.procurement.invoice.poinvoice.sendforapproval.POInvoiceSendForApproval;
import com.procurement.login.LoginPage;
import com.procurement.logout.LogoutPage;
import com.procurement.msa.PorInspectPO;
import com.procurement.orderschedule.approve.OrderScheduleApprove;
import com.procurement.orderschedule.create.OrderScheduleCreate;
import com.procurement.purchaseorder.BuyerPurchaseOrder;
import com.procurement.purchaseorderrequest.approval.PocPorSendForApproval;
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
import com.procurement.requisition.create.POCNonCatalogPrCreate;
import com.procurement.requisition.edit.PocPrEdit;
import com.procurement.requisition.reject.PocPrReject;
import com.procurement.requisition.sendforapproval.PocPrSendForApproval;
import com.procurement.requisition.suspend.PocPrBuyerManagerSuspend;
import com.procurement.requisition.suspend.PocPrBuyerSuspend;
import com.procurement.workorder.create.WorkOrderCreate;
import com.procurement.workorder.trackerstatus.WOTrackerStatus;
import com.reports.ExtentReportListener;
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
    protected PrCreate prCreate;
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
    protected PorApproval porApproval;
    protected PorApprove porApprove;
    protected PorReject porReject;
    protected PocPorRejectTest pocPorRejectTest;
    protected ApprovalAndApprove approvalAndApprove;
    protected PorApprovalAndApproveTest porApprovalAndApproveTest;
    protected InspectPOTest inspectPOTest;
    protected PorInspectPoInterface porInspectPoInterface;
    protected POSendForVendorTest poSendForVendorTest;
    protected PurchaseOrderInterface purchaseOrderInterface;
    protected OSCreateTest osCreateTest;
    protected OrderScheduleInterface orderScheduleInterface;
    protected OSEditTest osEditTest;
    protected OSEdit osEdit;
    protected OSRejectTest osRejectTest;
    protected OSReject osReject;
    protected OSApproveTest osApproveTest;
    protected OrderScheduleApproveInterface orderScheduleApproveInterface;
    protected InspectionCreateTest inspectionCreateTest;
    protected InspectionCreateInterface inspectionCreateInterface;
    protected InspectionFailTest inspectionFailTest;
    protected InsFail insFail;
    protected InspectionAssignTest inspectionAssignTest;
    protected InspectionAssignInterface inspectionAssignInterface;
    protected DispatchNotesCreateTest dispatchNotesCreateTest;
    protected DispatchNoteCreateInterface dispatchNoteCreateInterface;
    protected DispatchNotesAssignTest dispatchNotesAssignTest;
    protected DispatchNotesAssignInterface dispatchNotesAssignInterface;
    protected WorkOrdersCreateTest workOrdersCreateTest;
    protected DnEdit dnEdit;
    protected DnEditTest dnEditTest;
    protected DnReturn dnReturn;
    protected DnReturnTest dnReturnTest;
    protected DnCancel dnCancel;
    protected DnCancelTest dnCanceltest;
    protected FFRInvite ffrInvite;
    protected FFRInviteTest ffrInviteTest;
    protected FFRQuotation ffrQuotation;
    protected FFRQuoteTest ffrQuoteTest;
    protected FFRRequote ffrRequote;
    protected FFRRequoteTest ffrRequoteTest;
    protected WorkOrderCreateInterface workOrderCreateInterface;
    protected WOTrackerStatusTest woTrackerStatusTest;
    protected WOTrackerStatusInterface woTrackerStatusInterface;
    protected InvoiceCreateTest invoiceCreateTest;
    protected POInvoiceCreateInterface poInvoiceCreateInterface;
    protected POInvoiceSendForApprovalTest poInvoiceSendForApprovalTest;
    protected POSendForApprovalInterface poSendForApprovalInterface;
    protected POInvoiceApprovalTest poInvoiceApprovalTest;
    protected POInvoiceApprovalInterface poInvoiceApprovalInterface;
    protected PoInvHold poInvHold;
    protected InvoiceHoldTest invoiceHoldTest;
    protected PoInvRevert poInvRevert;
    protected InvoiceRevertTest invoiceRevertTest;
    protected PoInvCancel poInvCancel;
    protected InvoiceCancelTest invoiceCancelTest;
    protected PoInvAccept poInvAccept;
    protected ChecklistAcceptTest checklistAcceptTest;
    protected PoInvChecklistReject poInvChecklistReject;
    protected ChecklistRejectTest checklistRejectTest;
    protected PoInvEdit poInvEdit;
    protected InvoiceEditTest invoiceEditTest;
    protected PoInvReturn poInvReturn;
    protected InvoiceReturnTest invoiceReturnTest;
    protected PoInvVerify poInvVerify;
    protected InvoiceVerifyTest invoiceVerifyTest;
    protected PoInvReject poInvReject;
    protected InvoiceRejectTest invoiceRejectTest;
    protected WOInvoiceCreateInterface woInvoiceCreateInterface;
    protected WOInvoiceCreateTest woInvoiceCreateTest;
    protected WoInvHold woInvHold;
    protected WOInvoiceHoldTest woInvoiceHoldTest;
    protected WoInvRevert woInvRevert;
    protected WOInvoiceRevertTest woInvoiceRevertTest;
    protected WoInvCancel woInvCancel;
    protected WOInvoiceCancelTest woInvoiceCancelTest;
    protected WOSendForApprovalInterface woSendForApprovalInterface;
    protected WOInvoiceSendForApprovalTest woInvoiceSendForApprovalTest;
    protected WoInvAccept woInvAccept;
    protected WOChecklistAcceptTest woChecklistAcceptTest;
    protected WoInvChecklistReject woInvChecklistReject;
    protected WOChecklistRejectTest woChecklistRejectTest;
    protected WoInvEdit woInvEdit;
    protected WOInvoiceEditTest woInvoiceEditTest;
    protected WoInvReturn woInvReturn;
    protected WOInvoiceReturnTest woInvoiceReturnTest;
    protected WoInvVerify woInvVerify;
    protected WOInvoiceVerifyTest woInvoiceVerifyTest;
    protected WoInvReject woInvReject;
    protected WOInvoiceRejectTest woInvoiceRejectTest;
    protected WOInvoiceApprovalInterface woInvoiceApprovalInterface;
    protected WOInvoiceApprovalTest woInvoiceApprovalTest;
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
            prCreate = new POCNonCatalogPrCreate(loginPageInterface, properties, page, logoutPageInterface);
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
            technicalEvaluationRejectTest = new TechnicalEvaluationRejectTest();
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
            porApproval = new PocPorSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            porApprove = new Approve(loginPageInterface, properties, page, logoutPageInterface);
            porReject = new PocPorReject(loginPageInterface, properties, page, logoutPageInterface, porEdit, porApproval);
            pocPorRejectTest = new PocPorRejectTest();
            approvalAndApprove = new PorApprovalAndApprove(porApprove, porApproval);
            porApprovalAndApproveTest = new PorApprovalAndApproveTest();
            porInspectPoInterface = new PorInspectPO(loginPageInterface, properties, page, logoutPageInterface);
            inspectPOTest = new InspectPOTest();

//TODO Purchase Order
            purchaseOrderInterface = new BuyerPurchaseOrder(loginPageInterface, properties, page, logoutPageInterface);
            poSendForVendorTest = new POSendForVendorTest();
            orderScheduleInterface = new OrderScheduleCreate(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            osCreateTest = new OSCreateTest();
            osEdit = new OrderScheduleEdit(loginPageInterface, properties, page, logoutPageInterface);
            osEditTest = new OSEditTest();
            osReject = new OrderScheduleReject(loginPageInterface, properties, page, logoutPageInterface);
            osRejectTest = new OSRejectTest();
            orderScheduleApproveInterface = new OrderScheduleApprove(loginPageInterface, properties, page, logoutPageInterface);
            osApproveTest = new OSApproveTest();

//TODO Inspections
            inspectionCreateInterface = new InspectionCreate(loginPageInterface, properties, page, logoutPageInterface);
            inspectionCreateTest = new InspectionCreateTest();
            insFail = new InspectionFail(loginPageInterface, properties, page, logoutPageInterface, inspectionCreateInterface);
            inspectionFailTest = new InspectionFailTest();
            inspectionAssignInterface = new InspectionAssign(loginPageInterface, properties, page, logoutPageInterface);
            inspectionAssignTest = new InspectionAssignTest();

//TODO Dispatch Notes
            dispatchNoteCreateInterface = new DispatchNoteCreate(loginPageInterface, properties, page, logoutPageInterface);
            dispatchNotesCreateTest = new DispatchNotesCreateTest();
            dnEdit = new PocDnEdit(loginPageInterface, properties, page, logoutPageInterface);
            dnEditTest = new DnEditTest();
            dnReturn = new PocDnReturn(loginPageInterface, properties, page, logoutPageInterface, dnEdit);
            dnReturnTest = new DnReturnTest();
            dnCancel = new PocDnCancel(loginPageInterface, properties, page, logoutPageInterface, dispatchNoteCreateInterface);
            dnCanceltest = new DnCancelTest();
            dispatchNotesAssignInterface = new DispatchNotesAssign(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            dispatchNotesAssignTest = new DispatchNotesAssignTest();

//TODO Freight Forwarder Requests
            ffrInvite = new FreightForwarderInvite(loginPageInterface, properties, page, logoutPageInterface);
            ffrInviteTest = new FFRInviteTest();
            ffrQuotation = new FreightQuotation(loginPageInterface, properties, page, logoutPageInterface);
            ffrQuoteTest = new FFRQuoteTest();
            ffrRequote = new FreightForwarderRequote(ffrQuotation);
            ffrRequoteTest = new FFRRequoteTest();

//TODO Work Orders
            workOrderCreateInterface = new WorkOrderCreate(loginPageInterface, properties, page, logoutPageInterface);
            workOrdersCreateTest = new WorkOrdersCreateTest();
            woTrackerStatusInterface = new WOTrackerStatus(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            woTrackerStatusTest = new WOTrackerStatusTest();

//TODO POInvoice
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            invoiceCreateTest = new InvoiceCreateTest();
            poSendForApprovalInterface = new POInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceSendForApprovalTest = new POInvoiceSendForApprovalTest();
            poInvoiceApprovalInterface = new POInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceApprovalTest = new POInvoiceApprovalTest();
            invoiceCancelTest = new InvoiceCancelTest();
            poInvCancel = new PoInvoiceCancel(loginPageInterface, properties, page, logoutPageInterface, poInvoiceCreateInterface);
            invoiceCancelTest = new InvoiceCancelTest();
            poInvHold = new POInvoiceHold(loginPageInterface, properties, page, logoutPageInterface);
            invoiceHoldTest = new InvoiceHoldTest();
            poInvRevert = new POInvoiceRevert(loginPageInterface, properties, page, logoutPageInterface);
            invoiceRevertTest = new InvoiceRevertTest();
            poInvAccept = new ChecklistAccept(loginPageInterface, properties, page, logoutPageInterface);
            checklistAcceptTest = new ChecklistAcceptTest();
            poInvChecklistReject = new ChecklistReject(loginPageInterface, properties, page, logoutPageInterface);
            checklistRejectTest = new ChecklistRejectTest();
            poInvReturn = new POInvoiceReturn(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            invoiceReturnTest = new InvoiceReturnTest();
            poInvVerify = new POInvoiceVerify(loginPageInterface, properties, page, logoutPageInterface);
            invoiceVerifyTest = new InvoiceVerifyTest();
            poInvEdit = new POInvoiceEdit(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            invoiceEditTest = new InvoiceEditTest();
            poInvReject = new POInvoiceReject(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            invoiceRejectTest = new InvoiceRejectTest();

//TODO WOInvoice
            woInvoiceCreateInterface = new WOInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            woInvoiceCreateTest = new WOInvoiceCreateTest();
            woInvCancel = new WoInvoiceCancel(loginPageInterface, properties, page, logoutPageInterface, woInvoiceCreateInterface);
            woInvoiceCancelTest = new WOInvoiceCancelTest();
            woInvHold = new WOInvoiceHold(loginPageInterface, properties, page, logoutPageInterface);
            woInvoiceHoldTest = new WOInvoiceHoldTest();
            woInvRevert = new WOInvoiceRevert(loginPageInterface, properties, page, logoutPageInterface);
            woInvoiceRevertTest = new WOInvoiceRevertTest();
            woInvAccept = new WOChecklistAccept(loginPageInterface, properties, page, logoutPageInterface);
            woChecklistAcceptTest = new WOChecklistAcceptTest();
            woInvChecklistReject = new WOChecklistReject(loginPageInterface, properties, page, logoutPageInterface);
            woChecklistRejectTest = new WOChecklistRejectTest();
            woSendForApprovalInterface = new WOInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            woInvoiceSendForApprovalTest = new WOInvoiceSendForApprovalTest();
            woInvReturn = new WOInvoiceReturn(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvoiceReturnTest = new WOInvoiceReturnTest();
            woInvVerify = new WOInvoiceVerify(loginPageInterface, properties, page, logoutPageInterface);
            woInvoiceVerifyTest = new WOInvoiceVerifyTest();
            woInvEdit = new WOInvoiceEdit(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvoiceEditTest = new WOInvoiceEditTest();
            woInvReject = new WOInvoiceReject(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvoiceRejectTest = new WOInvoiceRejectTest();
            woInvoiceApprovalInterface = new WOInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);
            woInvoiceApprovalTest = new WOInvoiceApprovalTest();

//TODO Others
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, loginPageInterface, properties, logoutPageInterface);
            currencyExchangeRateTest = new CurrencyExchangeRateTest();
            iTestListener = new ExtentReportListener();

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