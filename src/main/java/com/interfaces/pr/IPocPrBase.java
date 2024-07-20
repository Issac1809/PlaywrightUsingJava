package com.interfaces.pr;
public interface IPocPrBase {

    void RequesterLoginPRCreate() throws InterruptedException;
    void CreateButton();
    String PurchaseType() ;
    void Title();
    void ShipToYokogawa();
    void Project() throws InterruptedException;
    void WBS();
    void Vendor();
    void RateContract();
    void Incoterm();
    void ShippingAddress();
    void ShippingMode();
    void QuotationRequiredBy();
    void ExpectedPOIssue();
    void ExpectedDelivery();
    void BuyerManager();
    void ProjectManager();
    void OiAndTpCurrency();
    void RoHSCompliance();
    void OrderIntake();
    void TargetPrice();
    void WarrantyRequirements();
    void PriceValidity();
    void InspectionRequired();
    void LiquidatedDamages();
    void BillingType();
    void AddLineRequisitionItemsCatalogType();
    void AddLineRequisitionItemsNonCatalogType();
    void AddLineRequisitionItemsMahHoursType();
    void Notes();
    void Attachments();
    void PRCreate();
}
