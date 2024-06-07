package com.yokogawa.functions;
import com.microsoft.playwright.Page;
import com.playwrightfactory.PlayWrightFactory;
import com.yokogawa.dispatchnotes.assign.DispatchNotesAssignInterface;
import com.yokogawa.dispatchnotes.create.DispatchNoteCreateInterface;
import com.yokogawa.inspections.assign.InspectionAssignInterface;
import com.yokogawa.inspections.create.InspectionCreateInterface;
import com.yokogawa.invoice.create.POInvoiceCreateInterface;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.msa.PorInspectPoInterface;
import com.yokogawa.orderschedule.approve.OrderScheduleApproveInterface;
import com.yokogawa.orderschedule.create.OrderScheduleInterface;
import com.yokogawa.purchaseorder.PurchaseOrderInterface;
import com.yokogawa.purchaseorderrequest.approval.PorApproval;
import com.yokogawa.purchaseorderrequest.create.PorCreateNonCatalog;
import com.yokogawa.requestforquotations.commercialevaluation.CommercialEvaluationInterface;
import com.yokogawa.requestforquotations.create.RfqCreate;
import com.yokogawa.requestforquotations.quotationsubmit.QuotationSubmit;
import com.yokogawa.requestforquotations.readyforevaluation.ReadyForEvalutationInterface;
import com.yokogawa.requestforquotations.technicalevaluation.TechnicalEvaluationInterface;
import com.yokogawa.requisition.assign.PrAssign;
import com.yokogawa.requisition.create.PrCreateNonCatalog;
import com.yokogawa.requisition.sendforapproval.PrSendForApproval;
import com.yokogawa.workorder.create.WorkOrderCreateInterface;
import com.yokogawa.workorder.trackerstatus.WOTrackerStatusInterface;
import java.util.List;
import java.util.Properties;

public class FunctionsNonCatalog {

    protected PlayWrightFactory playWrightFactory;
    protected Page page;
    protected Properties properties;
    protected FunctionsNonCatalog functionsNonCatalog;
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

    private FunctionsNonCatalog(){
    }

    public FunctionsNonCatalog(PlayWrightFactory playWrightFactory, Properties properties, Page page, LoginPageInterface loginPageInterface,
                               LogoutPageInterface logoutPageInterface, PrCreateNonCatalog prCreateNonCatalog, PrSendForApproval prSendForApproval,
                               PrAssign prAssign, RfqCreate rfqCreate, QuotationSubmit quotationSubmit, ReadyForEvalutationInterface readyForEvalutationInterface,
                               TechnicalEvaluationInterface technicalEvaluationInterface, CommercialEvaluationInterface commercialEvaluationInterface,
                               PorCreateNonCatalog porCreateNonCatalog, PorApproval porApproval, PorInspectPoInterface porInspectPoInterface,
                               PurchaseOrderInterface purchaseOrderInterface, OrderScheduleInterface orderScheduleInterface,
                               OrderScheduleApproveInterface orderScheduleApproveInterface, InspectionCreateInterface inspectionCreateInterface,
                               InspectionAssignInterface inspectionAssignInterface, DispatchNoteCreateInterface dispatchNoteCreateInterface,
                               DispatchNotesAssignInterface dispatchNotesAssignInterface, WorkOrderCreateInterface workOrderCreateInterface,
                               WOTrackerStatusInterface woTrackerStatusInterface, POInvoiceCreateInterface poInvoiceCreateInterface){
        this.playWrightFactory = playWrightFactory;
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
        this.prCreateNonCatalog = prCreateNonCatalog;
        this.prSendForApproval = prSendForApproval;
        this.prAssign = prAssign;
        this.rfqCreate = rfqCreate;
        this.quotationSubmit = quotationSubmit;
        this.readyForEvalutationInterface = readyForEvalutationInterface;
        this.technicalEvaluationInterface = technicalEvaluationInterface;
        this.commercialEvaluationInterface = commercialEvaluationInterface;
        this.porCreateNonCatalog = porCreateNonCatalog;
        this.porApproval = porApproval;
        this.porInspectPoInterface = porInspectPoInterface;
        this.purchaseOrderInterface = purchaseOrderInterface;
        this.orderScheduleInterface = orderScheduleInterface;
        this.orderScheduleApproveInterface = orderScheduleApproveInterface;
        this.inspectionCreateInterface = inspectionCreateInterface;
        this.inspectionAssignInterface = inspectionAssignInterface;
        this.dispatchNoteCreateInterface = dispatchNoteCreateInterface;
        this.dispatchNotesAssignInterface = dispatchNotesAssignInterface;
        this.workOrderCreateInterface = workOrderCreateInterface;
        this.woTrackerStatusInterface = woTrackerStatusInterface;
        this.poInvoiceCreateInterface = poInvoiceCreateInterface;
    }

    public void FunctionsForNonCatalog() throws InterruptedException {

//TODO Requester PR Create Non-Catalog
        prCreateNonCatalog.RequesterLoginPRCreate();
        prCreateNonCatalog.CreateButton();
        prCreateNonCatalog.NonCatalog();
        prCreateNonCatalog.Title();
        prCreateNonCatalog.ShipToYokogawa();
        prCreateNonCatalog.Project();
        prCreateNonCatalog.WBS();
        prCreateNonCatalog.Incoterm();
        prCreateNonCatalog.ShippingAddress();
        prCreateNonCatalog.ShippingMode();
        prCreateNonCatalog.QuotationRequiredBy();
        prCreateNonCatalog.ExpectedPOIssue();
        prCreateNonCatalog.ExpectedDelivery();
        prCreateNonCatalog.BuyerManager();
        prCreateNonCatalog.ProjectManager();
        prCreateNonCatalog.OrderIntake();
        prCreateNonCatalog.TargetPrice();
        prCreateNonCatalog.WarrantyRequirements();
        prCreateNonCatalog.PriceValidity();
        prCreateNonCatalog.InspectionRequired();
        prCreateNonCatalog.AddLineRequisitionItems();
        prCreateNonCatalog.Notes();
        prCreateNonCatalog.PRCreate();

//TODO Request Sends PR For Approval
        prSendForApproval.NonCatalogPrSendForApproval();

//TODO BuyerManager PR Assign Non-Catalog
        prAssign.BuyerManagerLogin();
        prAssign.BuyerManagerAssign();

//TODO Buyer RFQ Create Non-Catalog
        rfqCreate.BuyerLogin();
        rfqCreate.BuyerRfqCreate();
        rfqCreate.RfQNotes();
        rfqCreate.RFQCreate();

//TODO Buyer Invites Registered Vendor
        quotationSubmit.InviteRegisteredVendor();
        quotationSubmit.VendorLogin();
        quotationSubmit.LiquidatedDamages();
        quotationSubmit.RoHSCompliance();
        quotationSubmit.WarrantyRequirements();
        quotationSubmit.QuotationItems();
        quotationSubmit.Gst();
        quotationSubmit.QuotationAttachments();
        quotationSubmit.QuotationSubmitButton();

//TODO Buyer Ready For Evaluation
        readyForEvalutationInterface.ReadyForEvaluationButton();

//TODO Requester Technical Evaluation
        technicalEvaluationInterface.TechnicalEvaluationButton();

//TODO Buyer Commercial Evaluation
        commercialEvaluationInterface.CommercialEvaluationButton();

//TODO Buyer POR Create
        porCreateNonCatalog.BuyerPORCreate();
        porCreateNonCatalog.Justification();
        porCreateNonCatalog.TaxCode();
        porCreateNonCatalog.PORNotes();
        porCreateNonCatalog.PORCreate();

//TODO Buyer Send For Approval
        List<String> returnApprover = porApproval.SendForApproval();
        porApproval.ApproverLogin(returnApprover);

//TODO Inspect Create PO
        porInspectPoInterface.InspectCreatePO();

//TODO Buyer Send For Vendor
        purchaseOrderInterface.SendForVendor();

//TODO Vendor Order Schedule Create
        orderScheduleInterface.OSCreate();

//TODO Buyer Order Schedule Approve
        orderScheduleApproveInterface.OSApprove();

//TODO Vendor Send For Inspection
        inspectionCreateInterface.VendorInspectionCreate();

//TODO Requester Assign && Create Inspection
        inspectionAssignInterface.RequesterInspectionAssign();

//TODO Vendor Create Dispatch Notes
        dispatchNoteCreateInterface.DNCreate();

//TODO Logistics Manager Assign Dispatch Notes
        dispatchNotesAssignInterface.DNAssign();

//TODO Logistics Manager Work Order Create
        workOrderCreateInterface.WOCreate();

//TODO Vendor Update Tracker Status
        woTrackerStatusInterface.VendorTrackerStatus();

//TODO Vendor Invoice Create
        poInvoiceCreateInterface.VendorCreatePOInvoice();
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST();
        poInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
    }
}