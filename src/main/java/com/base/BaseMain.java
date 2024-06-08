package com.base;
import com.microsoft.playwright.Page;
import com.playwrightfactory.PlayWrightFactory;
import com.yokogawa.currencyexchangerate.CurrencyExchangeRate;
import com.yokogawa.dispatchnotes.assign.DispatchNotesAssign;
import com.yokogawa.dispatchnotes.assign.DispatchNotesAssignInterface;
import com.yokogawa.dispatchnotes.create.DispatchNoteCreate;
import com.yokogawa.dispatchnotes.create.DispatchNoteCreateInterface;
import com.yokogawa.functions.FunctionsNonCatalog;
import com.yokogawa.inspections.assign.InspectionAssign;
import com.yokogawa.inspections.assign.InspectionAssignInterface;
import com.yokogawa.inspections.create.InspectionCreate;
import com.yokogawa.inspections.create.InspectionCreateInterface;
import com.yokogawa.invoice.approve.POInvoiceApproval;
import com.yokogawa.invoice.approve.POInvoiceApprovalInterface;
import com.yokogawa.invoice.create.POInvoiceCreate;
import com.yokogawa.invoice.create.POInvoiceCreateInterface;
import com.yokogawa.invoice.sendforapproval.POInvoiceSendForApproval;
import com.yokogawa.invoice.sendforapproval.POSendForApprovalInterface;
import com.yokogawa.login.LoginPage;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.logout.LogoutPage;
import com.yokogawa.msa.PorInspectPO;
import com.yokogawa.msa.PorInspectPoInterface;
import com.yokogawa.orderschedule.approve.OrderScheduleApprove;
import com.yokogawa.orderschedule.approve.OrderScheduleApproveInterface;
import com.yokogawa.orderschedule.create.OrderScheduleCreate;
import com.yokogawa.orderschedule.create.OrderScheduleInterface;
import com.yokogawa.purchaseorder.BuyerPurchaseOrder;
import com.yokogawa.purchaseorder.PurchaseOrderInterface;
import com.yokogawa.purchaseorderrequest.approval.PocPorApproval;
import com.yokogawa.purchaseorderrequest.approval.PorApproval;
import com.yokogawa.purchaseorderrequest.create.PocNonCatalogPorCreate;
import com.yokogawa.purchaseorderrequest.create.PorCreateNonCatalog;
import com.yokogawa.requestforquotations.commercialevaluation.CommercialEvaluation;
import com.yokogawa.requestforquotations.commercialevaluation.CommercialEvaluationInterface;
import com.yokogawa.requestforquotations.create.PocRfqCreate;
import com.yokogawa.requestforquotations.create.RfqCreate;
import com.yokogawa.requestforquotations.quotationsubmit.QuotationSubmit;
import com.yokogawa.requestforquotations.quotationsubmit.RegisteredVendorQuotationSubmit;
import com.yokogawa.requestforquotations.readyforevaluation.ReadyForEvaluation;
import com.yokogawa.requestforquotations.readyforevaluation.ReadyForEvalutationInterface;
import com.yokogawa.requestforquotations.technicalevaluation.TechnicalEvaluation;
import com.yokogawa.requestforquotations.technicalevaluation.TechnicalEvaluationInterface;
import com.yokogawa.requisition.assign.PocPrAssign;
import com.yokogawa.requisition.assign.PrAssign;
import com.yokogawa.requisition.create.PocNonCatalogPrCreate;
import com.yokogawa.requisition.create.PrCreateNonCatalog;
import com.yokogawa.requisition.sendforapproval.PocPrSendForApproval;
import com.yokogawa.requisition.sendforapproval.PrSendForApproval;
import com.yokogawa.workorder.create.WorkOrderCreate;
import com.yokogawa.workorder.create.WorkOrderCreateInterface;
import com.yokogawa.workorder.trackerstatus.WOTrackerStatus;
import com.yokogawa.workorder.trackerstatus.WOTrackerStatusInterface;
import java.util.Properties;


public class BaseMain {
    protected PlayWrightFactory playWrightFactory;
    protected Page page;
    protected Properties properties;
    protected FunctionsNonCatalog functionsNonCatalog;
    protected CurrencyExchangeRate currencyExchangeRate;
    protected LoginPageInterface loginPageInterface;
    protected LogoutPageInterface logoutPageInterface;
    protected PrCreateNonCatalog prCreateNonCatalog;
    protected PrSendForApproval prSendForApproval;
    protected PrAssign prAssign;
    protected RfqCreate rfqCreate;
    protected QuotationSubmit quotationSubmit;
    protected ReadyForEvalutationInterface readyForEvalutationInterface;
    protected TechnicalEvaluationInterface technicalEvaluationInterface;
    protected CommercialEvaluationInterface commercialEvaluationInterface;
    protected PorCreateNonCatalog porCreateNonCatalog;
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

    public BaseMain() {
            playWrightFactory = new PlayWrightFactory();
            properties = playWrightFactory.initializeProperties();
            page = playWrightFactory.initializeBrowser(properties);

            loginPageInterface = new LoginPage(properties, page);
            logoutPageInterface = new LogoutPage(page);
            prCreateNonCatalog = new PocNonCatalogPrCreate(loginPageInterface, properties, page, logoutPageInterface);
            prSendForApproval = new PocPrSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            prAssign = new PocPrAssign(loginPageInterface, properties, page, logoutPageInterface);
            rfqCreate = new PocRfqCreate(loginPageInterface, properties, page, logoutPageInterface);
            quotationSubmit = new RegisteredVendorQuotationSubmit(loginPageInterface, properties, page, logoutPageInterface);
            readyForEvalutationInterface = new ReadyForEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationInterface = new TechnicalEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            commercialEvaluationInterface = new CommercialEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            porCreateNonCatalog = new PocNonCatalogPorCreate(loginPageInterface, properties, page, logoutPageInterface);
            porApproval = new PocPorApproval(loginPageInterface, properties, page, logoutPageInterface);
            porInspectPoInterface = new PorInspectPO(loginPageInterface, properties, page, logoutPageInterface);
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
            functionsNonCatalog = new FunctionsNonCatalog(playWrightFactory, properties, page, loginPageInterface, logoutPageInterface,
                    prCreateNonCatalog, prSendForApproval, prAssign, rfqCreate, quotationSubmit, readyForEvalutationInterface,
                    technicalEvaluationInterface, commercialEvaluationInterface, porCreateNonCatalog, porApproval, porInspectPoInterface,
                    purchaseOrderInterface, orderScheduleInterface, orderScheduleApproveInterface, inspectionCreateInterface,
                    inspectionAssignInterface, dispatchNoteCreateInterface, dispatchNotesAssignInterface, workOrderCreateInterface,
                    woTrackerStatusInterface, poInvoiceCreateInterface);
            currencyExchangeRate = new CurrencyExchangeRate(playWrightFactory, loginPageInterface, properties, logoutPageInterface);
    }

    public void functions() throws InterruptedException {
        functionsNonCatalog.FunctionsForNonCatalog();
    }
}