package com.poc.base;
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
import com.poc.classes.requisition.suspend.BuyerManagerSuspend;
import com.poc.classes.requisition.suspend.BuyerSuspend;
import com.poc.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorderrequests.*;
import com.poc.interfaces.purchaseorders.IPoCreate;
import com.poc.interfaces.purchaseorders.IPoSendForVendor;
import com.poc.interfaces.requestforquotation.*;
import com.poc.interfaces.requisitions.*;
import java.util.Properties;

public class BaseMain {

    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    protected Properties properties;
    protected CurrencyExchangeRate currencyExchangeRate;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected IPrSendForApproval iPrSendForApproval;
    protected IPrApprove iPrApprove;
    protected IPrReject iPrReject;
    protected IPrBuyerManagerSuspend iPrBuyerManagerSuspend;
    protected IPrBuyerSuspend iPrBuyerSuspend;
    protected IPrAssign iPrAssign;
    protected IRfqCreate iRfqCreate;
    protected IRfqEdit iRfqEdit;
    protected IRfqSuspend iRfqSuspend;
    protected IQuoRegret iQuoRegret;
    protected IQuoSubmit iQuoSubmit;
    protected IQuoRequote iQuoRequote;
    protected IReadyForEvalutation iReadyForEvalutation;
    protected ITeCreate iTeCreate;
    protected ITeReject iTeReject;
    protected ICeCreate iCeCreate;
    protected IPorCreate iPorCreate;
    protected IPorEdit iPorEdit;
    protected IPorSuspend iPorSuspend;
    protected IPorSendForApproval iPorSendForApproval;
    protected IPorReject iPorReject;
    protected IPorApprove iPorApprove;
    protected IPorSendForApprovalAndApprove iPorSendForApprovalAndApprove;
    protected IPoCreate iPoCreate;
    protected IPoSendForVendor iPoSendForVendor;
    
    

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
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initializeProperties();
        page = playwrightFactory.initializePage(properties);

//TODO Login && Logout
        iLogin = new Login(properties, page);
        iLogout = new Logout(page);

//TODO Requisition
        iPrCreate = new Create(iLogin, properties, page, iLogout);
        iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties);
        iPrSendForApproval = new SendForApproval(iLogin, properties, page, iLogout);
        iPrApprove = new Approve(iLogin, properties, page, iLogout);
        iPrAssign = new Assign(iLogin, properties, page, iLogout);
        iPrEdit = new Edit(iLogin, properties, page, iLogout, iPrSendForApproval, iPrApprove, iPrAssign);
        iPrReject = new Reject(iLogin, properties, page, iLogout, iPrEdit);
        iPrBuyerManagerSuspend = new BuyerManagerSuspend(iLogin, properties, page, iLogout, iPrEdit);
        iPrBuyerSuspend = new BuyerSuspend(iLogin, properties, page, iLogout, iPrEdit);

//TODO Request For Quotation
        iRfqCreate = new RfqCreate(iLogin, properties, page, iLogout);
        iRfqEdit = new RfqEdit(iLogin, properties, page, iLogout);
        iRfqSuspend = new RfqSuspend(iLogin, properties, page, iLogout, iRfqEdit, iPrEdit, iPrSendForApproval, iPrApprove, iPrAssign, iRfqCreate);
        iQuoSubmit = new Quote(iLogin, properties, page, iLogout);
        iQuoRegret = new QuotationRegret(iQuoSubmit, iLogin, properties, page, iLogout);
        iQuoRequote = new Requote(iLogin, properties, page, iLogout);
        iReadyForEvalutation = new ReadyForEvaluation(iLogin, properties, page, iLogout);
        iTeReject = new TechnicalEvaluationReject(iLogin, properties, page, iLogout);
        iTeCreate = new TechnicalEvaluation(iLogin, properties, page, iLogout);
        iCeCreate = new CommercialEvaluation(iLogin, properties, page, iLogout);

//TODO Purchase Order Request
        iPorCreate = new PorCreate(iLogin, properties, page, iLogout);
        iPorEdit = new PorEdit(iLogin, properties, page, iLogout);
        iPorSuspend = new PorSuspend(iLogin, properties, page, iLogout, iPorEdit, iCeCreate, iPorCreate);
        iPorSendForApproval = new PorSendForApproval(iLogin, properties, page, iLogout);
        iPorApprove = new PorApprove(iLogin, properties, page, iLogout);
        iPorReject = new PorReject(iLogin, properties, page, iLogout, iPorEdit, iPorSendForApproval);
        iPorSendForApprovalAndApprove = new PorSendForApprovalAndApprove(iPorApprove, iPorSendForApproval);

//TODO Purchase Orders
        iPoCreate = new PoCreate(iLogin, properties, page, iLogout);
        iPoSendForVendor = new SendForVendor(iLogin, properties, page, iLogout);

//TODO Order Schedule
            orderScheduleInterface = new OrderScheduleCreate(iLogin, properties, page, iLogout, playWrightFactory);
            osEdit = new OrderScheduleEdit(iLogin, properties, page, iLogout);
            osReject = new OrderScheduleReject(iLogin, properties, page, iLogout);
            orderScheduleApproveInterface = new OrderScheduleApprove(iLogin, properties, page, iLogout);

//TODO Inspection
            inspectionCreateInterface = new InspectionCreate(iLogin, properties, page, iLogout);
            insFail = new InspectionFail(iLogin, properties, page, iLogout, inspectionCreateInterface);
            inspectionAssignInterface = new InspectionAssign(iLogin, properties, page, iLogout);

//TODO Dispatch Notes
            dispatchNoteCreateInterface = new DispatchNoteCreate(iLogin, properties, page, iLogout);
            dnEdit = new PocDnEdit(iLogin, properties, page, iLogout);
            dnReturn = new PocDnReturn(iLogin, properties, page, iLogout, dnEdit);
            dnCancel = new PocDnCancel(iLogin, properties, page, iLogout, dispatchNoteCreateInterface);
            dispatchNotesAssignInterface = new DispatchNotesAssign(iLogin, properties, page, iLogout, playWrightFactory);

//TODO Freight Forwarder Requests
            ffrInvite = new FreightForwarderInvite(iLogin, properties, page, iLogout);
            ffrQuotation = new FreightQuotation(iLogin, properties, page, iLogout);
            ffrRequote = new FreightForwarderRequote(ffrQuotation);

//TODO Work Orders
            workOrderCreateInterface = new WorkOrderCreate(iLogin, properties, page, iLogout);
            woTrackerStatusInterface = new WOTrackerStatus(iLogin, properties, page, iLogout, playWrightFactory);

//TODO POInvoice
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, iLogin, properties, page, iLogout, currencyExchangeRate);
            poInvCancel = new PoInvoiceCancel(iLogin, properties, page, iLogout, poInvoiceCreateInterface);
            poInvHold = new POInvoiceHold(iLogin, properties, page, iLogout);
            poInvRevert = new POInvoiceRevert(iLogin, properties, page, iLogout);
            poInvAccept = new ChecklistAccept(iLogin, properties, page, iLogout);
            poInvChecklistReject = new ChecklistReject(iLogin, properties, page, iLogout);
            poSendForApprovalInterface = new POInvoiceSendForApproval(iLogin, properties, page, iLogout);
            poInvReturn = new POInvoiceReturn(iLogin, properties, page, iLogout, poSendForApprovalInterface);
            poInvVerify = new POInvoiceVerify(iLogin, properties, page, iLogout);
            poInvEdit = new POInvoiceEdit(iLogin, properties, page, iLogout, poSendForApprovalInterface);
            poInvReject = new POInvoiceReject(iLogin, properties, page, iLogout, poSendForApprovalInterface);
            poInvoiceApprovalInterface = new POInvoiceApproval(iLogin, properties, page, iLogout);

//TODO WOInvoice
            woInvoiceCreateInterface = new WOInvoiceCreate(playWrightFactory, iLogin, properties, page, iLogout, currencyExchangeRate);
            woInvCancel = new WoInvoiceCancel(iLogin, properties, page, iLogout, woInvoiceCreateInterface);
            woInvHold = new WOInvoiceHold(iLogin, properties, page, iLogout);
            woInvRevert = new WOInvoiceRevert(iLogin, properties, page, iLogout);
            woInvAccept = new WOChecklistAccept(iLogin, properties, page, iLogout);
            woInvChecklistReject = new WOChecklistReject(iLogin, properties, page, iLogout);
            woSendForApprovalInterface = new WOInvoiceSendForApproval(iLogin, properties, page, iLogout);
            woInvReturn = new WOInvoiceReturn(iLogin, properties, page, iLogout, woSendForApprovalInterface);
            woInvVerify = new WOInvoiceVerify(iLogin, properties, page, iLogout);
            woInvEdit = new WOInvoiceEdit(iLogin, properties, page, iLogout, woSendForApprovalInterface);
            woInvReject = new WOInvoiceReject(iLogin, properties, page, iLogout, woSendForApprovalInterface);
            woInvoiceApprovalInterface = new WOInvoiceApproval(iLogin, properties, page, iLogout);

//TODO Others
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, iLogin, properties, iLogout);
    }
}