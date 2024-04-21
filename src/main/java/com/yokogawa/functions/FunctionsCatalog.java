package com.yokogawa.functions;
import com.microsoft.playwright.Page;
import com.yokogawa.purchaseorderrequest.approval.PocPorApproval;
import com.yokogawa.purchaseorderrequest.approval.PorApproval;
import com.yokogawa.purchaseorderrequest.create.PocCatalogPorCreate;
import com.yokogawa.purchaseorderrequest.create.PorCreateCatalog;
import com.yokogawa.requisition.assign.PocPrAssign;
import com.yokogawa.requisition.assign.PrAssign;
import com.yokogawa.requisition.create.PocCatalogPrCreate;
import com.yokogawa.requisition.create.PrCreateCatalog;
import com.yokogawa.requisition.sendforapproval.PocPrSendForApproval;
import com.yokogawa.requisition.sendforapproval.PrSendForApproval;
import com.yokogawa.variables.VariablesForCatalog;
import java.util.List;
import static com.yokogawa.variables.VariablesForCatalog.CatalogTitle;

public class FunctionsCatalog {
    VariablesForCatalog variablesForCatalog = new VariablesForCatalog();
    PrCreateCatalog prCreateCatalog = new PocCatalogPrCreate();
    PrSendForApproval prSendForApproval = new PocPrSendForApproval();
    PrAssign prAssign = new PocPrAssign();
    PorCreateCatalog porCreateCatalog = new PocCatalogPorCreate();
    PorApproval porApproval = new PocPorApproval();
    public void FunctionsForCatalog(Page page) throws InterruptedException{

//TODO Requester PR Create Catalog
        prCreateCatalog.RequesterLoginPRCreate(variablesForCatalog.EmailID, page);
        prCreateCatalog.CatalogType(page);
        prCreateCatalog.Title(CatalogTitle, page);
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
        prAssign.BuyerManagerAssign(CatalogTitle, variablesForCatalog.Buyer, page);

//TODO Buyer POR Create Catalog
        porCreateCatalog.BuyerLogin(variablesForCatalog.Buyer, page);
        porCreateCatalog.BuyerPORCreate(CatalogTitle, page);
        porCreateCatalog.TaxCode(variablesForCatalog.TaxCode, page);
        porCreateCatalog.PORNotes(variablesForCatalog.PorNotes, page);
        porCreateCatalog.PORCreate(page);

//TODO Buyer Send For Approval
        List<String> returnApprover = porApproval.SendForApproval(variablesForCatalog.ChiefFinancialOfficer, variablesForCatalog.PresidentDirectorCorporate, page);
        //List<String> returnApprover = porApproval.GetPorApprovers(page);
        porApproval.ApproverLogin(returnApprover, variablesForCatalog.PRApproverGroupB, variablesForCatalog.PRApproverGroupC, variablesForCatalog.PRApproverGroupD, page);
    }
}