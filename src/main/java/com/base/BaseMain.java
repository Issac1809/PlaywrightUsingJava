package com.base;
import com.interfaces.*;
import com.microsoft.playwright.Page;
import com.factory.PlayWrightFactory;
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
import com.procurement.purchaseorderrequest.edit.PocPorEdit;
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
    protected PorInspectPoInterface porInspectPoInterface;
    protected PurchaseOrderInterface purchaseOrderInterface;
    protected OrderScheduleInterface orderScheduleInterface;
    protected OrderScheduleApproveInterface orderScheduleApproveInterface;
    protected InspectionCreateInterface inspectionCreateInterface;
    protected InspectionAssignInterface inspectionAssignInterface;
    protected DispatchNoteCreateInterface dispatchNoteCreateInterface;
    protected DispatchNotesAssignInterface dispatchNotesAssignInterface;
    protected WorkOrderCreateInterface workOrderCreateInterface;
    protected WOTrackerStatusInterface woTrackerStatusInterface;
    protected POInvoiceCreateInterface poInvoiceCreateInterface;
    protected POSendForApprovalInterface poSendForApprovalInterface;
    protected POInvoiceApprovalInterface poInvoiceApprovalInterface;

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
            porApproval = new PocPorApproval(loginPageInterface, properties, page, logoutPageInterface);
            porInspectPoInterface = new PorInspectPO(loginPageInterface, properties, page, logoutPageInterface);

//TODO Purchase Orders
            purchaseOrderInterface = new BuyerPurchaseOrder(loginPageInterface, properties, page, logoutPageInterface);
            orderScheduleInterface = new OrderScheduleCreate(loginPageInterface, properties, page, logoutPageInterface, playWrightFactory);
            orderScheduleApproveInterface = new OrderScheduleApprove(loginPageInterface, properties, page, logoutPageInterface);
            inspectionCreateInterface = new InspectionCreate(loginPageInterface, properties, page, logoutPageInterface);
            inspectionAssignInterface = new InspectionAssign(loginPageInterface, properties, page, logoutPageInterface);
            dispatchNoteCreateInterface = new DispatchNoteCreate(loginPageInterface, properties, page, logoutPageInterface);
            dispatchNotesAssignInterface = new DispatchNotesAssign(loginPageInterface, properties, page, logoutPageInterface);
            workOrderCreateInterface = new WorkOrderCreate(loginPageInterface, properties, page, logoutPageInterface);
            woTrackerStatusInterface = new WOTrackerStatus(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            poSendForApprovalInterface = new POInvoiceSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            poInvoiceApprovalInterface = new POInvoiceApproval(loginPageInterface, properties, page, logoutPageInterface);
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, loginPageInterface, properties, logoutPageInterface);
    }
}