package com.yokogawa.requisition.create;
import com.microsoft.playwright.Page;
public interface PrCreateCatalog {
    void RequesterLoginPRCreate(String EmailID, Page page) throws InterruptedException;
    void CatalogType(Page page);
    void Title(String Title, Page page);
    void ShipToYokogawa(Page page);
    void Project(String Project, Page page) throws InterruptedException;
    void WBS(String Wbs, Page page);
    void Vendor(String Vendor, Page page) throws InterruptedException;
    void RateContract(String RateContract, Page page);
    void ShippingAddress(Page page);
    void ShippingMode(String ShippingMode, Page page);
    void ExpectedPOIssue(Page page);
    void ExpectedDelivery(Page page);
    void BuyerManager(String BuyerManager, Page page);
    void ProjectManager(String ProjectManager, Page page);
    void OrderIntake(int OrderIntake, Page page);
    void InspectionRequired(Page page);
    void AddLineRequisitionItems(String Category, String Item, int Quantity, Page page);
    void Notes(String Notes, Page page);
    void PRCreate(Page page);
}
