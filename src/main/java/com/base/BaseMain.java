package com.base;
import com.interfaces.*;
import com.microsoft.playwright.Page;
import com.factory.PlayWrightFactory;
import com.procurement.currencyexchangerate.CurrencyExchangeRate;
import com.procurement.dispatchnotes.assign.DispatchNotesAssign;
import com.procurement.dispatchnotes.cancel.PocDnCancel;
import com.procurement.dispatchnotes.create.DispatchNoteCreate;
import com.procurement.dispatchnotes.dnreturn.PocDnReturn;
import com.procurement.dispatchnotes.edit.PocDnEdit;
import com.procurement.freightforwarder.invite.FreightForwarderInvite;
import com.procurement.freightforwarder.quote.FreightQuotation;
import com.procurement.freightforwarder.requote.FreightForwarderRequote;
import com.procurement.inspections.assign.InspectionAssign;
import com.procurement.inspections.create.InspectionCreate;
import com.procurement.inspections.fail.InspectionFail;
import com.procurement.invoice.poinvoice.approve.POInvoiceApproval;
import com.procurement.invoice.poinvoice.cancel.PoInvoiceCancel;
import com.procurement.invoice.poinvoice.checklist.ChecklistAccept;
import com.procurement.invoice.poinvoice.checklist.ChecklistReject;
import com.procurement.invoice.poinvoice.create.POInvoiceCreate;
import com.procurement.invoice.poinvoice.edit.POInvoiceEdit;
import com.procurement.invoice.poinvoice.hold.POInvoiceHold;
import com.procurement.invoice.poinvoice.invreturn.POInvoiceReturn;
import com.procurement.invoice.poinvoice.reject.POInvoiceReject;
import com.procurement.invoice.poinvoice.revert.POInvoiceRevert;
import com.procurement.invoice.poinvoice.sendforapproval.POInvoiceSendForApproval;
import com.procurement.invoice.poinvoice.verify.POInvoiceVerify;
import com.procurement.invoice.woinvoice.approve.WOInvoiceApproval;
import com.procurement.invoice.woinvoice.cancel.WoInvoiceCancel;
import com.procurement.invoice.woinvoice.checklist.WOChecklistAccept;
import com.procurement.invoice.woinvoice.checklist.WOChecklistReject;
import com.procurement.invoice.woinvoice.create.WOInvoiceCreate;
import com.procurement.invoice.woinvoice.edit.WOInvoiceEdit;
import com.procurement.invoice.woinvoice.hold.WOInvoiceHold;
import com.procurement.invoice.woinvoice.invreturn.WOInvoiceReturn;
import com.procurement.invoice.woinvoice.reject.WOInvoiceReject;
import com.procurement.invoice.woinvoice.revert.WOInvoiceRevert;
import com.procurement.invoice.woinvoice.sendforapproval.WOInvoiceSendForApproval;
import com.procurement.invoice.woinvoice.verify.WOInvoiceVerify;
import com.procurement.login.LoginPage;
import com.procurement.logout.LogoutPage;
import com.procurement.msa.PorInspectPO;
import com.procurement.orderschedule.approve.OrderScheduleApprove;
import com.procurement.orderschedule.create.OrderScheduleCreate;
import com.procurement.orderschedule.edit.OrderScheduleEdit;
import com.procurement.orderschedule.reject.OrderScheduleReject;
import com.procurement.purchaseorder.BuyerPurchaseOrder;
import com.procurement.purchaseorderrequest.approval.Approve;
import com.procurement.purchaseorderrequest.approval.PocPorSendForApproval;
import com.procurement.purchaseorderrequest.create.PocNonCatalogPorCreate;
import com.procurement.purchaseorderrequest.edit.PocPorEdit;
import com.procurement.purchaseorderrequest.approvalandapprove.PorApprovalAndApprove;
import com.procurement.purchaseorderrequest.reject.PocPorReject;
import com.procurement.purchaseorderrequest.suspend.PocPorSuspend;
import com.procurement.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.procurement.requestforquotations.create.PocRfqCreate;
import com.procurement.requestforquotations.edit.PocRfqEdit;
import com.procurement.requestforquotations.quotationregret.RegisteredVendorQuotationRegret;
import com.procurement.requestforquotations.quotationrequote.RegisteredVendorQuotationRequote;
import com.procurement.requestforquotations.quotationsubmit.RegisteredVendorQuotationSubmit;
import com.procurement.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.procurement.requestforquotations.suspend.PocRfqSuspend;
import com.procurement.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.procurement.requestforquotations.technicalevaluation.TechnicalEvaluationReject;
import com.procurement.requisition.approve.PocPrApprove;
import com.procurement.requisition.edit.PocPrEdit;
import com.procurement.requisition.reject.PocPrReject;
import com.procurement.requisition.assign.PocPrAssign;
import com.procurement.requisition.create.PocNonCatalogPrCreate;
import com.procurement.requisition.sendforapproval.PocPrSendForApproval;
import com.procurement.requisition.suspend.PocPrBuyerManagerSuspend;
import com.procurement.requisition.suspend.PocPrBuyerSuspend;
import com.procurement.workorder.create.WorkOrderCreate;
import com.procurement.workorder.trackerstatus.WOTrackerStatus;
import java.util.Properties;

public class BaseMain {

    protected PlayWrightFactory playWrightFactory;
    protected Page page;
    protected Properties properties;
    protected CurrencyExchangeRate currencyExchangeRate;
    protected LoginPageInterface loginPageInterface;
    protected LogoutPageInterface logoutPageInterface;
    protected PrCreateNonCatalog prCreateNonCatalog;
    protected PrEdit prEdit;
    protected PrSendForApproval prSendForApproval;
    protected PrApprove prApprove;
    protected PrReject prReject;
    protected PrSuspend prSuspendBuyerManager;
    protected PrSuspend prSuspendBuyer;
    protected PrAssign prAssign;
    protected RfqCreate rfqCreate;
    protected RfqEdit rfqEdit;
    protected RfqSuspend rfqSuspend;
    protected QuotationRegret quotationRegret;
    protected QuotationSubmit quotationSubmit;
    protected QuotationRequote quotationRequote;
    protected ReadyForEvalutationInterface readyForEvalutationInterface;
    protected TEReject teReject;
    protected TechnicalEvaluationInterface technicalEvaluationInterface;
    protected CommercialEvaluationInterface commercialEvaluationInterface;
    protected PorCreateNonCatalog porCreateNonCatalog;
    protected PorEdit porEdit;
    protected PorSuspend porSuspend;
    protected PorApproval porApproval;
    protected PorReject porReject;
    protected PorApprove porApprove;
    protected ApprovalAndApprove approvalAndApprove;
    protected PorInspectPoInterface porInspectPoInterface;
    protected PurchaseOrderInterface purchaseOrderInterface;
    protected OrderScheduleInterface orderScheduleInterface;
    protected OSEdit osEdit;
    protected OSReject osReject;
    protected OrderScheduleApproveInterface orderScheduleApproveInterface;
    protected InspectionCreateInterface inspectionCreateInterface;
    protected InsFail insFail;
    protected InspectionAssignInterface inspectionAssignInterface;
    protected DispatchNoteCreateInterface dispatchNoteCreateInterface;
    protected DnReturn dnReturn;
    protected DnEdit dnEdit;
    protected DispatchNotesAssignInterface dispatchNotesAssignInterface;
    protected DnCancel dnCancel;
    protected FFRInvite ffrInvite;
    protected FFRQuotation ffrQuotation;
    protected FFRRequote ffrRequote;
    protected WorkOrderCreateInterface workOrderCreateInterface;
    protected WOTrackerStatusInterface woTrackerStatusInterface;
    protected POInvoiceCreateInterface poInvoiceCreateInterface;
    protected PoInvHold poInvHold;
    protected PoInvRevert poInvRevert;
    protected PoInvCancel poInvCancel;
    protected POSendForApprovalInterface poSendForApprovalInterface;
    protected PoInvAccept poInvAccept;
    protected PoInvChecklistReject poInvChecklistReject;
    protected PoInvEdit poInvEdit;
    protected PoInvReturn poInvReturn;
    protected PoInvVerify poInvVerify;
    protected PoInvReject poInvReject;
    protected POInvoiceApprovalInterface poInvoiceApprovalInterface;
    protected WOInvoiceCreateInterface woInvoiceCreateInterface;
    protected WoInvHold woInvHold;
    protected WoInvRevert woInvRevert;
    protected WoInvCancel woInvCancel;
    protected WOSendForApprovalInterface woSendForApprovalInterface;
    protected WoInvAccept woInvAccept;
    protected WoInvChecklistReject woInvChecklistReject;
    protected WoInvEdit woInvEdit;
    protected WoInvReturn woInvReturn;
    protected WoInvVerify woInvVerify;
    protected WoInvReject woInvReject;
    protected WOInvoiceApprovalInterface woInvoiceApprovalInterface;

//TODO Constructor
    public BaseMain() {
            playWrightFactory = new PlayWrightFactory();
            properties = playWrightFactory.initializeProperties();
            page = playWrightFactory.initializeBrowser(properties);

            loginPageInterface = new LoginPage(properties, page);
            logoutPageInterface = new LogoutPage(page);

//TODO Requisition
            prCreateNonCatalog = new PocNonCatalogPrCreate(loginPageInterface, properties, page, logoutPageInterface);
            prApprove = new PocPrApprove(loginPageInterface, properties, page, logoutPageInterface);
            prSendForApproval = new PocPrSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            prAssign = new PocPrAssign(loginPageInterface, properties, page, logoutPageInterface);
            prEdit = new PocPrEdit(loginPageInterface, properties, page, logoutPageInterface, prSendForApproval, prApprove, prAssign);
            prReject = new PocPrReject(loginPageInterface, properties, page, logoutPageInterface, prEdit);
            prSuspendBuyerManager = new PocPrBuyerManagerSuspend(loginPageInterface, properties, page, logoutPageInterface, prEdit);
            prSuspendBuyer = new PocPrBuyerSuspend(loginPageInterface, properties, page, logoutPageInterface, prEdit);

//TODO Request For Quotation
            rfqCreate = new PocRfqCreate(loginPageInterface, properties, page, logoutPageInterface);
            rfqEdit = new PocRfqEdit(loginPageInterface, properties, page, logoutPageInterface);
            rfqSuspend = new PocRfqSuspend(loginPageInterface, properties, page, logoutPageInterface, rfqEdit, prEdit, prSendForApproval, prApprove, prAssign, rfqCreate);
            quotationSubmit = new RegisteredVendorQuotationSubmit(loginPageInterface, properties, page, logoutPageInterface);
            quotationRegret = new RegisteredVendorQuotationRegret(quotationSubmit, loginPageInterface, properties, page, logoutPageInterface);
            quotationRequote = new RegisteredVendorQuotationRequote(loginPageInterface, properties, page, logoutPageInterface);
            readyForEvalutationInterface = new ReadyForEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            teReject = new TechnicalEvaluationReject(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationInterface = new TechnicalEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            commercialEvaluationInterface = new CommercialEvaluation(loginPageInterface, properties, page, logoutPageInterface);

//TODO Purchase Order Request
            porCreateNonCatalog = new PocNonCatalogPorCreate(loginPageInterface, properties, page, logoutPageInterface);
            porEdit = new PocPorEdit(loginPageInterface, properties, page, logoutPageInterface);
            porSuspend = new PocPorSuspend(loginPageInterface, properties, page, logoutPageInterface, porEdit, commercialEvaluationInterface, porCreateNonCatalog);
            porApproval = new PocPorSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            porApprove = new Approve(loginPageInterface, properties, page, logoutPageInterface);
            porReject = new PocPorReject(loginPageInterface, properties, page, logoutPageInterface, porEdit, porApproval);
            approvalAndApprove = new PorApprovalAndApprove(porApprove, porApproval);
            porInspectPoInterface = new PorInspectPO(loginPageInterface, properties, page, logoutPageInterface);

//TODO Purchase Orders
            purchaseOrderInterface = new BuyerPurchaseOrder(loginPageInterface, properties, page, logoutPageInterface);

//TODO Order Schedule
            orderScheduleInterface = new OrderScheduleCreate(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            osEdit = new OrderScheduleEdit(loginPageInterface, properties, page, logoutPageInterface);
            osReject = new OrderScheduleReject(loginPageInterface, properties, page, logoutPageInterface);
            orderScheduleApproveInterface = new OrderScheduleApprove(loginPageInterface, properties, page, logoutPageInterface);

//TODO Inspection
            inspectionCreateInterface = new InspectionCreate(loginPageInterface, properties, page, logoutPageInterface);
            insFail = new InspectionFail(loginPageInterface, properties, page, logoutPageInterface, inspectionCreateInterface);
            inspectionAssignInterface = new InspectionAssign(loginPageInterface, properties, page, logoutPageInterface);

//TODO Dispatch Notes
            dispatchNoteCreateInterface = new DispatchNoteCreate(loginPageInterface, properties, page, logoutPageInterface);
            dnEdit = new PocDnEdit(loginPageInterface, properties, page, logoutPageInterface);
            dnReturn = new PocDnReturn(loginPageInterface, properties, page, logoutPageInterface, dnEdit);
            dnCancel = new PocDnCancel(loginPageInterface, properties, page, logoutPageInterface, dispatchNoteCreateInterface);
            dispatchNotesAssignInterface = new DispatchNotesAssign(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);

//TODO Freight Forwarder Requests
            ffrInvite = new FreightForwarderInvite(loginPageInterface, properties, page, logoutPageInterface);
            ffrQuotation = new FreightQuotation(loginPageInterface, properties, page, logoutPageInterface);
            ffrRequote = new FreightForwarderRequote(ffrQuotation);

//TODO Work Orders
            workOrderCreateInterface = new WorkOrderCreate(loginPageInterface, properties, page, logoutPageInterface);
            woTrackerStatusInterface = new WOTrackerStatus(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);

//TODO POInvoice
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            poInvCancel = new PoInvoiceCancel(loginPageInterface, properties, page, logoutPageInterface, poInvoiceCreateInterface);
            poInvHold = new POInvoiceHold(loginPageInterface, properties, page, logoutPageInterface);
            poInvRevert = new POInvoiceRevert(loginPageInterface, properties, page, logoutPageInterface);
            poInvAccept = new ChecklistAccept(loginPageInterface, properties, page, logoutPageInterface);
            poInvChecklistReject = new ChecklistReject(loginPageInterface, properties, page, logoutPageInterface);
            poSendForApprovalInterface = new POInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvReturn = new POInvoiceReturn(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            poInvVerify = new POInvoiceVerify(loginPageInterface, properties, page, logoutPageInterface);
            poInvEdit = new POInvoiceEdit(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            poInvReject = new POInvoiceReject(loginPageInterface, properties, page, logoutPageInterface, poSendForApprovalInterface);
            poInvoiceApprovalInterface = new POInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);

//TODO WOInvoice
            woInvoiceCreateInterface = new WOInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            woInvCancel = new WoInvoiceCancel(loginPageInterface, properties, page, logoutPageInterface, woInvoiceCreateInterface);
            woInvHold = new WOInvoiceHold(loginPageInterface, properties, page, logoutPageInterface);
            woInvRevert = new WOInvoiceRevert(loginPageInterface, properties, page, logoutPageInterface);
            woInvAccept = new WOChecklistAccept(loginPageInterface, properties, page, logoutPageInterface);
            woInvChecklistReject = new WOChecklistReject(loginPageInterface, properties, page, logoutPageInterface);
            woSendForApprovalInterface = new WOInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            woInvReturn = new WOInvoiceReturn(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvVerify = new WOInvoiceVerify(loginPageInterface, properties, page, logoutPageInterface);
            woInvEdit = new WOInvoiceEdit(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvReject = new WOInvoiceReject(loginPageInterface, properties, page, logoutPageInterface, woSendForApprovalInterface);
            woInvoiceApprovalInterface = new WOInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);

//TODO Others
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, loginPageInterface, properties, logoutPageInterface);
    }
}