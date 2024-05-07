package com.yokogawa.functions;
import com.microsoft.playwright.Page;
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
import com.yokogawa.requestforquotations.quotationsubmit.QuotationSubmit;
import com.yokogawa.requestforquotations.quotationsubmit.RegisteredVendorQuotationSubmit;
import com.yokogawa.requestforquotations.create.RfqCreate;
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
import com.yokogawa.variables.VariablesForNonCatalog;
import com.yokogawa.workorder.create.WorkOrderCreate;
import com.yokogawa.workorder.create.WorkOrderCreateInterface;
import com.yokogawa.workorder.trackerstatus.WOTrackerStatus;
import com.yokogawa.workorder.trackerstatus.WOTrackerStatusInterface;
import java.util.List;

import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
import static com.yokogawa.variables.VariablesForNonCatalog.Password;
public class FunctionsNonCatalog {
    VariablesForNonCatalog variablesForNonCatalog = new VariablesForNonCatalog();
    PrCreateNonCatalog prCreateNonCatalog = new PocNonCatalogPrCreate();
    PrSendForApproval prSendForApproval = new PocPrSendForApproval();
    PrAssign prAssign = new PocPrAssign();
    RfqCreate rfqCreate = new PocRfqCreate();
    QuotationSubmit quotationSubmit = new RegisteredVendorQuotationSubmit();
    ReadyForEvalutationInterface readyForEvalutationInterface = new ReadyForEvaluation();
    TechnicalEvaluationInterface technicalEvaluationInterface = new TechnicalEvaluation();
    CommercialEvaluationInterface commercialEvaluationInterface = new CommercialEvaluation();
    PorCreateNonCatalog porCreateNonCatalog = new PocNonCatalogPorCreate();
    PorApproval porApproval = new PocPorApproval();
    PorInspectPoInterface porInspectPoInterface = new PorInspectPO();
    PurchaseOrderInterface purchaseOrderInterface = new BuyerPurchaseOrder();
    OrderScheduleInterface orderScheduleInterface = new OrderScheduleCreate();
    OrderScheduleApproveInterface orderScheduleApproveInterface = new OrderScheduleApprove();
    InspectionCreateInterface inspectionCreateInterface = new InspectionCreate();
    InspectionAssignInterface inspectionAssignInterface = new InspectionAssign();
    DispatchNoteCreateInterface dispatchNoteCreateInterface = new DispatchNoteCreate();
    DispatchNotesAssignInterface dispatchNotesAssignInterface = new DispatchNotesAssign();
    WorkOrderCreateInterface workOrderCreateInterface = new WorkOrderCreate();
    WOTrackerStatusInterface woTrackerStatusInterface = new WOTrackerStatus();
    POInvoiceCreateInterface poInvoiceCreateInterface = new POInvoiceCreate();
    public void FunctionsForNonCatalog(Page page) throws InterruptedException{

//TODO Requester PR Create Non-Catalog
        prCreateNonCatalog.RequesterLoginPRCreate(variablesForNonCatalog.EmailID, Password, page);
        prCreateNonCatalog.PRType(page);
        prCreateNonCatalog.Title(NonCatalogTitle, page);
        prCreateNonCatalog.ShipToYokogawa(page);
        prCreateNonCatalog.Project(variablesForNonCatalog.Project, page);
        prCreateNonCatalog.WBS(variablesForNonCatalog.Wbs, page);
        prCreateNonCatalog.Incoterm(variablesForNonCatalog.Incoterm, page);
        prCreateNonCatalog.ShippingAddress(page);
        prCreateNonCatalog.ShippingMode(variablesForNonCatalog.ShippingMode, page);
        prCreateNonCatalog.QuotationRequiredBy(page);
        prCreateNonCatalog.ExpectedPOIssue(page);
        prCreateNonCatalog.ExpectedDelivery(page);
        prCreateNonCatalog.BuyerManager(variablesForNonCatalog.BuyerManager, page);
        prCreateNonCatalog.ProjectManager(variablesForNonCatalog.ProjectManager, page);
        prCreateNonCatalog.OrderIntake(variablesForNonCatalog.OrderIntake, page);
        prCreateNonCatalog.TargetPrice(variablesForNonCatalog.TargetPrice, page);
        prCreateNonCatalog.WarrantyRequirements(variablesForNonCatalog.WarrantyRequirement, page);
        prCreateNonCatalog.PriceValidity(variablesForNonCatalog.PriceValidity, page);
        prCreateNonCatalog.InspectionRequired(page);
        prCreateNonCatalog.AddLineRequisitionItems(variablesForNonCatalog.Category, variablesForNonCatalog.Item, variablesForNonCatalog.Quantity, page);
        prCreateNonCatalog.Notes(variablesForNonCatalog.Notes, page);
        prCreateNonCatalog.PRCreate(page);

//TODO Request Sends PR For Approval
        prSendForApproval.PrSendForApproval(page);

//TODO BuyerManager PR Assign Non-Catalog
        prAssign.BuyerManagerLogin(variablesForNonCatalog.BuyerManager, page);
        prAssign.BuyerManagerAssign(NonCatalogTitle, variablesForNonCatalog.Buyer, page);

//TODO Buyer RFQ Create Non-Catalog
        rfqCreate.BuyerLogin(variablesForNonCatalog.Buyer, page);
        rfqCreate.BuyerRfqCreate(NonCatalogTitle, page);
        rfqCreate.RfQNotes(variablesForNonCatalog.RfQNotes, page);
        rfqCreate.RFQCreate(page);

//TODO Buyer Invites Registered Vendor
        quotationSubmit.InviteRegisteredVendor(variablesForNonCatalog.Vendor, page);
        quotationSubmit.VendorLogin(variablesForNonCatalog.VendorMailId, variablesForNonCatalog.IncotermLocation, variablesForNonCatalog.QuotationReferenceNumber, page);
        quotationSubmit.LiquidatedDamages(page);
        quotationSubmit.RoHSCompliance(page);
        quotationSubmit.WarrantyRequirements(page);
        quotationSubmit.QuotationItems(variablesForNonCatalog.HSCode, variablesForNonCatalog.Make, variablesForNonCatalog.Model, variablesForNonCatalog.PartNumber, variablesForNonCatalog.CountryOfOrigin, variablesForNonCatalog.Rate, variablesForNonCatalog.Discount, variablesForNonCatalog.LeadTime, variablesForNonCatalog.QuotationNotes, page);
        quotationSubmit.Gst(variablesForNonCatalog.GST, page);
        quotationSubmit.QuotationAttachments(page);
        quotationSubmit.QuotationSubmitButton(page);

//TODO Buyer Ready For Evaluation
        readyForEvalutationInterface.ReadyForEvaluationButton(variablesForNonCatalog.Buyer, page);

//TODO Requester Technical Evaluation
        technicalEvaluationInterface.TechnicalEvaluationButton(variablesForNonCatalog.EmailID, variablesForNonCatalog.TEApprover, page);

//TODO Buyer Commercial Evaluation
        commercialEvaluationInterface.CommercialEvaluationButton(variablesForNonCatalog.Buyer, page);

//TODO Buyer POR Create
        porCreateNonCatalog.BuyerPORCreate(page);
        porCreateNonCatalog.Justification(page);
        porCreateNonCatalog.TaxCode(variablesForNonCatalog.TaxCode, page);
        porCreateNonCatalog.PORNotes(variablesForNonCatalog.PorNotes, page);
        porCreateNonCatalog.PORCreate(page);

//TODO Buyer Send For Approval
        List<String> returnApprover = porApproval.SendForApproval(variablesForNonCatalog.ChiefFinancialOfficer, variablesForNonCatalog.PresidentDirectorCorporate, page);
        porApproval.ApproverLogin(returnApprover, variablesForNonCatalog.PRApproverGroupB, variablesForNonCatalog.PRApproverGroupC, variablesForNonCatalog.PRApproverGroupD, page);

//TODO Inspect Create PO
        porInspectPoInterface.InspectCreatePO(variablesForNonCatalog.AdminId, page);

//TODO Buyer Send For Vendor
        purchaseOrderInterface.SendForVendor(variablesForNonCatalog.Buyer, page);

//TODO Vendor Order Schedule Create
        String POReferenceId = orderScheduleInterface.OSCreate(variablesForNonCatalog.VendorMailId, page);

//TODO Buyer Order Schedule Approve
        orderScheduleApproveInterface.OSApprove(variablesForNonCatalog.Buyer, POReferenceId, page);

//TODO Vendor Send For Inspection
        inspectionCreateInterface.VendorInspectionCreate(variablesForNonCatalog.VendorMailId, POReferenceId, page);

//TODO Requester Assign && Create Inspection
        inspectionAssignInterface.RequesterInspectionAssign(variablesForNonCatalog.EmailID, variablesForNonCatalog.EmailID, POReferenceId, page);

//TODO Vendor Create Dispatch Notes
        dispatchNoteCreateInterface.DNCreate(variablesForNonCatalog.VendorMailId, variablesForNonCatalog.SourceCountry, variablesForNonCatalog.DestinationCountry, variablesForNonCatalog.PackageType, variablesForNonCatalog.GrossWeight, variablesForNonCatalog.NetWeight, variablesForNonCatalog.Volume, variablesForNonCatalog.Quantity, page);

//TODO Logistics Manager Assign Dispatch Notes
        dispatchNotesAssignInterface.DNAssign(variablesForNonCatalog.LogisticsManager, POReferenceId, variablesForNonCatalog.LogisticsManager, page);

//TODO Logistics Manager Work Order Create
        workOrderCreateInterface.WOCreate(variablesForNonCatalog.LogisticsManager, POReferenceId, variablesForNonCatalog.Vendor, page);

//TODO Vendor Update Tracker Status
        woTrackerStatusInterface.VendorTrackerStatus(variablesForNonCatalog.VendorMailId, POReferenceId, page);

//TODO Vendor Invoice Create
        String currencyCode = poInvoiceCreateInterface.VendorCreatePOInvoice(variablesForNonCatalog.VendorMailId, POReferenceId, variablesForNonCatalog.InvoiceNumber, page);
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST(page);
        double finalTotalGSTPercentage = poInvoiceCreateInterface.VendorTotalGST(page);
        poInvoiceCreateInterface.SGDEquivalentEnable(currencyCode, finalTotalGSTPercentage, finalGSTPercentage, POReferenceId, page);
//        poInvoiceCreateInterface.GSTEquivalentInputField(finalCurrencyExchangeRate, finalTotalGSTPercentage, page);
    }
}