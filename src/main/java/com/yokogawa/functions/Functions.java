package org.functions;
import com.microsoft.playwright.Page;
import org.login.Login;
import org.login.LoginPage;
import org.requisition.assign.PocPrAssign;
import org.requisition.assign.PrAssign;
import org.purchaseorderrequest.create.PocCatalogPorCreate;
import org.purchaseorderrequest.create.PorCreateCatalog;
import org.purchaseorderrequest.approval.PocPorApproval;
import org.purchaseorderrequest.approval.PorApproval;
import org.requisition.create.PocCatalogPrCreate;
import org.requisition.create.PocNonCatalogPrCreate;
import org.requisition.create.PrCreateCatalog;
import org.requisition.create.PrCreateNonCatalog;
import org.requisition.sendforapproval.PocPrSendForApproval;
import org.requisition.sendforapproval.PrSendForApproval;
import org.variables.VariablesForCatalog;
import org.variables.VariablesForNonCatalog;
public class Functions {
    VariablesForCatalog variablesForCatalog = new VariablesForCatalog();
    VariablesForNonCatalog variablesForNonCatalog = new VariablesForNonCatalog();
    Login login = new LoginPage();
    PrCreateCatalog prCreateCatalog = new PocCatalogPrCreate();
    PrCreateNonCatalog prCreateNonCatalog = new PocNonCatalogPrCreate();
    PrSendForApproval prSendForApproval = new PocPrSendForApproval();
    PrAssign prAssign = new PocPrAssign();
    PorCreateCatalog porCreateCatalog = new PocCatalogPorCreate();
    PorApproval porApproval = new PocPorApproval();
    public void FunctionsForAllTypes(Page page) throws InterruptedException {

//TODO Requester PR Create Catalog
        login.Login(variablesForCatalog.EmailID, page);
        prCreateCatalog.RequesterLoginPRCreate(variablesForCatalog.EmailID, page);
        prCreateCatalog.CatalogType(page);
        prCreateCatalog.Title(variablesForCatalog.Title, page);
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
        prAssign.BuyerManagerLogin(variablesForCatalog.BuyerManager, variablesForCatalog.Password, page);
        prAssign.BuyerManagerAssign(variablesForCatalog.Title, variablesForCatalog.BuyerManager, variablesForCatalog.Buyer, page);

//TODO Buyer POR Create Non-Catalog
        porCreateCatalog.BuyerLogin(variablesForCatalog.Buyer, variablesForCatalog.Password, page);
        porCreateCatalog.BuyerPORCreate(variablesForCatalog.Title, page);
        porCreateCatalog.TaxCode(page);
        porCreateCatalog.PORNotes(variablesForCatalog.PorNotes, page);
        porCreateCatalog.PORCreate(page);

//TODO Buyer Send For Approval
//        porApproval.SendForApproval(variablesForCatalog.ChiefFinancialOfficer, variablesForCatalog.PresidentDirectorCorporate);
//        List<String> returnValue = porApproval.GetPorApprovers(page);
//        porApproval.ApproverLogin(returnValue, variablesForCatalog.Password, page);


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


