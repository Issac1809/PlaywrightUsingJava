package com.yokogawa.functions;
import com.microsoft.playwright.Page;
import com.yokogawa.purchaseorderrequest.approval.PocPorApproval;
import com.yokogawa.purchaseorderrequest.approval.PorApproval;
import com.yokogawa.purchaseorderrequest.create.PocCatalogPorCreate;
import com.yokogawa.purchaseorderrequest.create.PorCreateCatalog;
import com.yokogawa.requisition.assign.PocPrAssign;
import com.yokogawa.requisition.assign.PrAssign;
import com.yokogawa.requisition.create.PocCatalogPrCreate;
import com.yokogawa.requisition.create.PocNonCatalogPrCreate;
import com.yokogawa.requisition.create.PrCreateCatalog;
import com.yokogawa.requisition.create.PrCreateNonCatalog;
import com.yokogawa.requisition.sendforapproval.PocPrSendForApproval;
import com.yokogawa.requisition.sendforapproval.PrSendForApproval;
import com.yokogawa.variables.VariablesForCatalog;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.List;
import static com.yokogawa.variables.VariablesForCatalog.Title;

public class Functions {

    VariablesForCatalog variablesForCatalog = new VariablesForCatalog();
    VariablesForNonCatalog variablesForNonCatalog = new VariablesForNonCatalog();
    PrCreateCatalog prCreateCatalog = new PocCatalogPrCreate();
    PrCreateNonCatalog prCreateNonCatalog = new PocNonCatalogPrCreate();
    PrSendForApproval prSendForApproval = new PocPrSendForApproval();
    PrAssign prAssign = new PocPrAssign();
    PorCreateCatalog porCreateCatalog = new PocCatalogPorCreate();
    PorApproval porApproval = new PocPorApproval();
    public void FunctionsForAllTypes(Page page) throws InterruptedException{

//TODO Requester PR Create Catalog
        prCreateCatalog.RequesterLoginPRCreate(variablesForCatalog.EmailID, page);
        prCreateCatalog.CatalogType(page);
        prCreateCatalog.Title(Title, page);
        prCreateCatalog.ShipToYokogawa(page);
        prCreateCatalog.Project(variablesForCatalog.Project, page);
        prCreateCatalog.WBS(variablesForCatalog.Wbs, page);
        prCreateCatalog.Vendor(variablesForCatalog.Vendor, page);
        prCreateCatalog.RateContract(variablesForCatalog.RateContract, page);
        prCreateCatalog.ShippingAddress(page);
        prCreateCatalog.ShippingMode(variablesForCatalog.ShippingMode, page);
        prCreateCatalog.ExpectedPOIssue(page);
        prCreateCatalog.ExpectedDelivery(page);
        prCreateCatalog.BuyerManager(variablesForCatalog.BuyerManager, page);
        prCreateCatalog.ProjectManager(variablesForCatalog.ProjectManager, page);
        prCreateCatalog.OrderIntake(variablesForCatalog.OrderIntake, page);
        prCreateCatalog.InspectionRequired(page);
        prCreateCatalog.AddLineRequisitionItems(variablesForCatalog.Category, variablesForCatalog.Item, variablesForCatalog.Quantity, page);
        prCreateCatalog.Notes(variablesForCatalog.Notes, page);
        prCreateCatalog.PRCreate(page);

//TODO Request Sends PR For Approval
        prSendForApproval.PrSendForApproval(page);

//TODO BuyerManager PR Assign Catalog
        prAssign.BuyerManagerLogin(variablesForCatalog.BuyerManager, page);
        prAssign.BuyerManagerAssign(Title, variablesForCatalog.Buyer, page);

//TODO Buyer POR Create Non-Catalog
        porCreateCatalog.BuyerLogin(variablesForCatalog.Buyer, page);
        porCreateCatalog.BuyerPORCreate(Title, page);
        porCreateCatalog.TaxCode(variablesForCatalog.TaxCode, page);
        porCreateCatalog.PORNotes(variablesForCatalog.PorNotes, page);
        porCreateCatalog.PORCreate(page);

//TODO Buyer Send For Approval
        porApproval.SendForApproval(variablesForCatalog.ChiefFinancialOfficer, page);
        List<String> returnApprover = porApproval.GetPorApprovers(page);
        porApproval.ApproverLogin(returnApprover, variablesForCatalog.PRApproverGroupB, variablesForCatalog.PRApproverGroupC, variablesForCatalog.PRApproverGroupD, page);


//TODO Requester PR Create Non-Catalog
//        prCreateNonCatalog.RequesterLoginPRCreate(variablesForNonCatalog.EmailID, variablesForNonCatalog.Password, page);
//        prCreateNonCatalog.PRType(page);
//        prCreateNonCatalog.Title(variablesForNonCatalog.Title, page);
//        prCreateNonCatalog.ShipToYokogawa(page);
//        prCreateNonCatalog.Project(variablesForNonCatalog.Project, page);
//        prCreateNonCatalog.WBS(variablesForNonCatalog.Wbs, page);
//        prCreateNonCatalog.Incoterm(variablesForNonCatalog.Incoterm, page);
//        prCreateNonCatalog.ShippingAddress(page);
//        prCreateNonCatalog.ShippingMode(variablesForNonCatalog.ShippingMode, page);
//        prCreateNonCatalog.QuotationRequiredBy(page);
//        prCreateNonCatalog.ExpectedPOIssue(page);
//        prCreateNonCatalog.ExpectedDelivery(page);
//        prCreateNonCatalog.BuyerManager(variablesForNonCatalog.BuyerManager, page);
//        prCreateNonCatalog.ProjectManager(variablesForNonCatalog.ProjectManager, page);
//        prCreateNonCatalog.OrderIntake(variablesForNonCatalog.OrderIntake, page);
//        prCreateNonCatalog.TargetPrice(variablesForNonCatalog.TargetPrice, page);
//        prCreateNonCatalog.WarrantyRequirements(variablesForNonCatalog.WarrantyRequirement, page);
//        prCreateNonCatalog.PriceValidity(variablesForNonCatalog.PriceValidity, page);
//        prCreateNonCatalog.InspectionRequired(page);
//        prCreateNonCatalog.AddLineRequisitionItems(variablesForNonCatalog.Category, variablesForNonCatalog.Item, variablesForNonCatalog.Quantity, page);
//        prCreateNonCatalog.Notes(variablesForNonCatalog.Notes, page);
//        prCreateNonCatalog.PRCreate(page);
        }
    }


