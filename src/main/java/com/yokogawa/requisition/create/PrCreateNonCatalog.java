package com.yokogawa.requisition.create;
public interface PrCreateNonCatalog {

    void RequesterLoginPRCreate() throws InterruptedException;
    void CreateButton();
    void NonCatalog();
    void Title();
    void ShipToYokogawa();
    void Project();
    void WBS();
    void Incoterm();
    void ShippingAddress();
    void ShippingMode();
    void QuotationRequiredBy();
    void ExpectedPOIssue();
    void ExpectedDelivery();
    void BuyerManager();
    void ProjectManager();
    void OrderIntake();
    void TargetPrice();
    void WarrantyRequirements();
    void PriceValidity();
    void InspectionRequired();
    void AddLineRequisitionItems();
    void Notes();
    void PRCreate();
}
