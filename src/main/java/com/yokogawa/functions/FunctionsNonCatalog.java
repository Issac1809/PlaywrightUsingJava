package com.yokogawa.functions;
import com.microsoft.playwright.Page;
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
    PurchaseOrderInterface purchaseOrderInterface = new BuyerPurchaseOrder();
    public void FunctionsForNonCatalog(Page page, Page page1) throws InterruptedException{

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
        prCreateNonCatalog.PRCreate(page);//TODO Request Sends PR For Approval
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
        quotationSubmit.VendorLogin(variablesForNonCatalog.VendorMailId, variablesForNonCatalog.IncotermLocation, variablesForNonCatalog.QuotationReferenceNumber, page1);
        quotationSubmit.LiquidatedDamages(page1);
        quotationSubmit.RoHSCompliance(page1);
        quotationSubmit.WarrantyRequirements(page1);
        quotationSubmit.QuotationItems(variablesForNonCatalog.HSCode, variablesForNonCatalog.Make, variablesForNonCatalog.Model, variablesForNonCatalog.PartNumber, variablesForNonCatalog.CountryOfOrigin, variablesForNonCatalog.Rate, variablesForNonCatalog.Discount, variablesForNonCatalog.LeadTime, variablesForNonCatalog.QuotationNotes, page1);
        quotationSubmit.Gst(variablesForNonCatalog.GST, page1);
        quotationSubmit.QuotationAttachments(page1);
        quotationSubmit.QuotationSubmitButton(page1);

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
        porApproval.SendForApproval(variablesForNonCatalog.ChiefFinancialOfficer, page);
        List<String> returnApprover = porApproval.GetPorApprovers(page);
        porApproval.ApproverLogin(returnApprover, variablesForNonCatalog.PRApproverGroupB, variablesForNonCatalog.PRApproverGroupC, variablesForNonCatalog.PRApproverGroupD, page);

//TODO Buyer Send For Vendor
        purchaseOrderInterface.SendForVendor(variablesForNonCatalog.Buyer, page);
    }
}