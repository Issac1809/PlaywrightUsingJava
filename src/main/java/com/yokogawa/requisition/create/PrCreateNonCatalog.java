package org.requisition.create;
import com.microsoft.playwright.Page;

public interface PrCreateNonCatalog {

    void RequesterLoginPRCreate(String EmailID, String Password, Page page) throws InterruptedException;
    void PRType(Page page);
    void Title(String Title, Page page);
    void ShipToYokogawa(Page page);
    void Project(String Project, Page page) throws InterruptedException;
    void WBS(String Wbs, Page page);
    void Incoterm(String Incoterm, Page page);
    void ShippingAddress(Page page);
    void ShippingMode(String ShippingMode, Page page);
    void QuotationRequiredBy(Page page);
    void ExpectedPOIssue(Page page);
    void ExpectedDelivery(Page page);
    void BuyerManager(String BuyerManager, Page page);
    void ProjectManager(String ProjectManager, Page page);
    void OrderIntake(int OrderIntake, Page page);
    void TargetPrice(int TargetPrice, Page page);
    void WarrantyRequirements(String WaarantyRequirement, Page page);
    void PriceValidity(String PriceValidity, Page page);
    void InspectionRequired(Page page);
    void AddLineRequisitionItems(String Category, String Item, int Quantity, Page page);
    void Notes(String Notes, Page page);
    void PRCreate(Page page);
}
