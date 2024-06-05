package com.base;
import com.currencyexchangerate.CurrencyExchangeRateTest;
import com.dispatchnotes.create.DispatchNotesCreateTest;
import com.dispatchnotes.assign.DispatchNotesAssignTest;
import com.inspections.assign.InspectionAssignTest;
import com.inspections.create.InspectionCreateTest;
import com.invoice.InvoiceCreateTest;
import com.microsoft.playwright.Page;
import com.msa.InspectPOTest;
import com.orderschedule.approval.OSApproveTest;
import com.orderschedule.create.OSCreateTest;
import com.playwrightfactory.PlayWrightFactory;
import com.purchaseorder.POSendForVendorTest;
import com.purchaseorderrequest.approval.PorApprovalTest;
import com.purchaseorderrequest.create.PorCreateTest;
import com.requisition.assign.RequisitionAssignTest;
import com.requisition.sendforapproval.NonCatalogPrSendForApprovalTest;
import com.requisition.create.NonCatalogPrCreateTest;
import com.requestforquotation.commercialevaluation.CommercialEvaluationTest;
import com.requestforquotation.create.RFQCreateTest;
import com.requestforquotation.quotationsubmit.QuotationSubmitTest;
import com.requestforquotation.readyforevaluation.ReadyForEvaluationTest;
import com.requestforquotation.technicalevaluation.TechnicalEvaluationTest;
import com.workorders.trackerstatus.WOTrackerStatusTest;
import com.workorders.create.WorkOrdersCreateTest;
import com.yokogawa.currencyexchangerate.CurrencyExchangeRate;
import com.yokogawa.dispatchnotes.assign.DispatchNotesAssign;
import com.yokogawa.dispatchnotes.assign.DispatchNotesAssignInterface;
import com.yokogawa.dispatchnotes.create.DispatchNoteCreate;
import com.yokogawa.dispatchnotes.create.DispatchNoteCreateInterface;
import com.yokogawa.inspections.assign.InspectionAssign;
import com.yokogawa.inspections.assign.InspectionAssignInterface;
import com.yokogawa.inspections.create.InspectionCreate;
import com.yokogawa.inspections.create.InspectionCreateInterface;
import com.yokogawa.invoice.POInvoiceCreate;
import com.yokogawa.invoice.POInvoiceCreateInterface;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
    protected NonCatalogPrSendForApprovalTest nonCatalogPrSendForApprovalTest;
    protected PrSendForApproval prSendForApproval;
    protected RequisitionAssignTest requisitionAssignTest;
    protected PrAssign prAssign;
    protected RFQCreateTest rfqCreateTest;
    protected RfqCreate rfqCreate;
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

    @BeforeTest
    public void Setup() {
        try {
            playWrightFactory = new PlayWrightFactory();
            properties = playWrightFactory.initializeProperties();

            page = playWrightFactory.initializeBrowser(properties);

            loginPageInterface = new LoginPage(properties, page);
            logoutPageInterface = new LogoutPage(page);
            prCreateNonCatalog = new PocNonCatalogPrCreate(loginPageInterface, properties, page, logoutPageInterface);
            nonCatalogPrCreateTest = new NonCatalogPrCreateTest();
            prSendForApproval = new PocPrSendForApproval(loginPageInterface, properties, page, logoutPageInterface);
            nonCatalogPrSendForApprovalTest = new NonCatalogPrSendForApprovalTest();
            prAssign = new PocPrAssign(loginPageInterface, properties, page, logoutPageInterface);
            requisitionAssignTest = new RequisitionAssignTest();
            rfqCreate = new PocRfqCreate(loginPageInterface, properties, page, logoutPageInterface);
            rfqCreateTest = new RFQCreateTest();
            quotationSubmit = new RegisteredVendorQuotationSubmit(loginPageInterface, properties, page, logoutPageInterface);
            quotationSubmitTest = new QuotationSubmitTest();
            readyForEvalutationInterface = new ReadyForEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            readyForEvaluationTest = new ReadyForEvaluationTest();
            technicalEvaluationInterface = new TechnicalEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            technicalEvaluationTest = new TechnicalEvaluationTest();
            commercialEvaluationInterface = new CommercialEvaluation(loginPageInterface, properties, page, logoutPageInterface);
            commercialEvaluationTest = new CommercialEvaluationTest();
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
            currencyExchangeRate = new CurrencyExchangeRate(loginPageInterface, properties, page, logoutPageInterface);
            currencyExchangeRateTest = new CurrencyExchangeRateTest();
            poInvoiceCreateInterface = new POInvoiceCreate(playWrightFactory, loginPageInterface, properties, page, logoutPageInterface, currencyExchangeRate);
            invoiceCreateTest = new InvoiceCreateTest();
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
}