package com.poc.base;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.poc.classes.currencyexchangerate.CurrencyExchangeRate;
import com.poc.classes.dispatchnotes.assign.DnAssign;
import com.poc.classes.dispatchnotes.cancel.DnCancel;
import com.poc.classes.dispatchnotes.create.DnCreate;
import com.poc.classes.dispatchnotes.dnreturn.DnReturn;
import com.poc.classes.dispatchnotes.edit.DnEdit;
import com.poc.classes.freightforwarderrequests.invite.FfrInvite;
import com.poc.classes.freightforwarderrequests.quote.FfrQuote;
import com.poc.classes.freightforwarderrequests.requote.FfrRequote;
import com.poc.classes.inspections.assign.InsAssign;
import com.poc.classes.inspections.create.InsCreate;
import com.poc.classes.inspections.fail.InsFail;
import com.poc.classes.inspections.readyforinspection.InsReadyForInspection;
import com.poc.classes.invoice.poinvoice.approve.InvApproval;
import com.poc.classes.invoice.poinvoice.cancel.InvCancel;
import com.poc.classes.invoice.poinvoice.checklist.InvChecklistAccept;
import com.poc.classes.invoice.poinvoice.checklist.InvChecklistReject;
import com.poc.classes.invoice.poinvoice.create.InvCreate;
import com.poc.classes.invoice.poinvoice.edit.InvEdit;
import com.poc.classes.invoice.poinvoice.hold.InvHold;
import com.poc.classes.invoice.poinvoice.invreturn.InvReturn;
import com.poc.classes.invoice.poinvoice.reject.InvReject;
import com.poc.classes.invoice.poinvoice.revert.InvRevert;
import com.poc.classes.invoice.poinvoice.sendforapproval.InvSendForApproval;
import com.poc.classes.invoice.poinvoice.verify.InvVerify;
import com.poc.classes.invoice.woinvoice.approve.WoInvApproval;
import com.poc.classes.invoice.woinvoice.cancel.WoInvCancel;
import com.poc.classes.invoice.woinvoice.checklist.WoInvChecklistAccept;
import com.poc.classes.invoice.woinvoice.checklist.WoInvChecklistReject;
import com.poc.classes.invoice.woinvoice.create.WoInvCreate;
import com.poc.classes.invoice.woinvoice.edit.WoInvEdit;
import com.poc.classes.invoice.woinvoice.hold.WoInvHold;
import com.poc.classes.invoice.woinvoice.invreturn.WoInvReturn;
import com.poc.classes.invoice.woinvoice.reject.WoInvReject;
import com.poc.classes.invoice.woinvoice.revert.WoInvRevert;
import com.poc.classes.invoice.woinvoice.sendforapproval.WoInvSendForApproval;
import com.poc.classes.invoice.woinvoice.verify.WoInvVerify;
import com.poc.classes.login.Login;
import com.poc.classes.logout.Logout;
import com.poc.classes.orderschedule.approve.OsApprove;
import com.poc.classes.orderschedule.create.OsCreate;
import com.poc.classes.orderschedule.edit.OsEdit;
import com.poc.classes.orderschedule.reject.OsReject;
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
import com.poc.classes.workorder.create.WoCreate;
import com.poc.classes.workorder.trackerstatus.WoTrackerStatus;
import com.poc.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.poc.interfaces.dispatchnotes.*;
import com.poc.interfaces.freightforwarderrequests.IFfrInvite;
import com.poc.interfaces.freightforwarderrequests.IFfrQuote;
import com.poc.interfaces.freightforwarderrequests.IFfrRequote;
import com.poc.interfaces.inspections.IInsAssign;
import com.poc.interfaces.inspections.IInsCreate;
import com.poc.interfaces.inspections.IInsFail;
import com.poc.interfaces.inspections.IInsReadyForInspection;
import com.poc.interfaces.invoices.poinvoices.*;
import com.poc.interfaces.invoices.woinvoices.*;
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
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

public class BaseMain {

    protected Logger logger;
    protected PlaywrightFactory playwrightFactory;
    protected Page page;
    protected Properties properties;
    protected ICurrencyExchangeRate iCurrencyExchangeRate;
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
    protected IOsCreate iOsCreate;
    protected IOsEdit iOsEdit;
    protected IOsReject iOsReject;
    protected IOsApprove iOsApprove;
    protected IInsReadyForInspection iInsReadyForInspection;
    protected IInsCreate iInsCreate;
    protected IInsAssign iInsAssign;
    protected IInsFail iInsFail;
    protected IDnCreate iDnCreate;
    protected IDnReturn iDnReturn;
    protected IDnEdit iDnEdit;
    protected IDnAssign iDnAssign;
    protected IDnCancel iDnCancel;
    protected IFfrInvite iFfrInvite;
    protected IFfrQuote iFfrQuote;
    protected IFfrRequote iFfrRequote;
    protected IWoCreate iWoCreate;
    protected IWoTrackerStatus iWoTrackerStatus;
    protected IInvCreate iInvCreate;
    protected IInvHold iInvHold;
    protected IInvRevert iInvRevert;
    protected IInvCancel iInvCancel;
    protected IInvSendForApproval iInvSendForApproval;
    protected IInvChecklistReject iInvChecklistReject;
    protected IInvChecklistAccept iInvChecklistAccept;
    protected IInvEdit iInvEdit;
    protected IInvReturn iInvReturn;
    protected IInvVerify iInvVerify;
    protected IInvReject iInvReject;
    protected IInvApproval iInvApproval;
    protected IWoInvCreate iWoInvCreate;
    protected IWoInvHold iWoInvHold;
    protected IWoInvRevert iWoInvRevert;
    protected IWoInvCancel iWoInvCancel;
    protected IWoInvSendForApproval iWoInvSendForApproval;
    protected IWoInvChecklistAccept iWoInvChecklistAccept;
    protected IWoInvChecklistReject iWoInvChecklistReject;
    protected IWoInvEdit iWoInvEdit;
    protected IWoInvReturn iWoInvReturn;
    protected IWoInvVerify iWoInvVerify;
    protected IWoInvReject iWoInvReject;
    protected IWoInvApproval iWoInvApproval;

    //TODO Constructor
    public BaseMain() {
        try {
            logger = LogManager.getLogger(BaseMain.class);
            logger.info("Creating objects for all the classes");
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
            iOsCreate = new OsCreate(iLogin, properties, page, iLogout, playwrightFactory);
            iOsEdit = new OsEdit(iLogin, properties, page, iLogout);
            iOsReject = new OsReject(iLogin, properties, page, iLogout);
            iOsApprove = new OsApprove(iLogin, properties, page, iLogout);

//TODO Inspection
            iInsReadyForInspection = new InsReadyForInspection(iLogin, properties, page, iLogout);
            iInsCreate = new InsCreate(iLogin, properties, page, iLogout);
            iInsAssign = new InsAssign(iLogin, properties, page, iLogout);
            iInsFail = new InsFail(iLogin, properties, page, iLogout, iInsReadyForInspection);

//TODO Dispatch Notes
            iDnCreate = new DnCreate(iLogin, properties, page, iLogout);
            iDnEdit = new DnEdit(iLogin, properties, page, iLogout);
            iDnReturn = new DnReturn(iLogin, properties, page, iLogout, iDnEdit);
            iDnCancel = new DnCancel(iLogin, properties, page, iLogout, iDnCreate);
            iDnAssign = new DnAssign(iLogin, properties, page, iLogout, playwrightFactory);

//TODO Freight Forwarder Requests
            iFfrInvite = new FfrInvite(iLogin, properties, page, iLogout);
            iFfrQuote = new FfrQuote(iLogin, properties, page, iLogout);
            iFfrRequote = new FfrRequote(iLogin, properties, iFfrQuote, iLogout, page);

//TODO Work Orders
            iWoCreate = new WoCreate(iLogin, properties, page, iLogout);
            iWoTrackerStatus = new WoTrackerStatus(iLogin, properties, page, iLogout, playwrightFactory);

//TODO POInvoice
            iInvCreate = new InvCreate(playwrightFactory, iLogin, properties, page, iLogout, iCurrencyExchangeRate);
            iInvCancel = new InvCancel(iLogin, properties, page, iLogout, iInvCreate);
            iInvHold = new InvHold(iLogin, properties, page, iLogout);
            iInvRevert = new InvRevert(iLogin, properties, page, iLogout);
            iInvChecklistAccept = new InvChecklistAccept(iLogin, properties, page, iLogout);
            iInvChecklistReject = new InvChecklistReject(iLogin, properties, page, iLogout);
            iInvSendForApproval = new InvSendForApproval(iLogin, properties, page, iLogout);
            iInvReturn = new InvReturn(iLogin, properties, page, iLogout, iInvSendForApproval);
            iInvVerify = new InvVerify(iLogin, properties, page, iLogout);
            iInvEdit = new InvEdit(iLogin, properties, page, iLogout);
            iInvReject = new InvReject(iLogin, properties, page, iLogout, iInvSendForApproval);
            iInvApproval = new InvApproval(iLogin, properties, page, iLogout);

//TODO WOInvoice
            iWoInvCreate = new WoInvCreate(playwrightFactory, iLogin, properties, page, iLogout, iCurrencyExchangeRate);
            iWoInvCancel = new WoInvCancel(iLogin, properties, page, iLogout, iWoInvCreate);
            iWoInvHold = new WoInvHold(iLogin, properties, page, iLogout);
            iWoInvRevert = new WoInvRevert(iLogin, properties, page, iLogout);
            iWoInvChecklistAccept = new WoInvChecklistAccept(iLogin, properties, page, iLogout);
            iWoInvChecklistReject = new WoInvChecklistReject(iLogin, properties, page, iLogout);
            iWoInvSendForApproval = new WoInvSendForApproval(iLogin, properties, page, iLogout);
            iWoInvReturn = new WoInvReturn(iLogin, properties, page, iLogout, iWoInvSendForApproval);
            iWoInvVerify = new WoInvVerify(iLogin, properties, page, iLogout);
            iWoInvEdit = new WoInvEdit(iLogin, properties, page, iLogout);
            iWoInvReject = new WoInvReject(iLogin, properties, page, iLogout, iWoInvSendForApproval);
            iWoInvApproval = new WoInvApproval(iLogin, properties, page, iLogout);

//TODO Others
            iCurrencyExchangeRate = new CurrencyExchangeRate(playwrightFactory, iLogin, properties, iLogout);
        } catch (Exception exception) {
            logger.error("Error during object creation: ", exception.getMessage(), exception);
        }
    }
}