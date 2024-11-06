package com.procurement.requisition.create;
import com.base.BaseTest;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

public class NonCatalogPrCreateTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrCreateMethod(){
        try{
            prCreate.RequesterLoginPRCreate();
            prCreate.CreateButton();
            prCreate.PurchaseType();
            prCreate.Title();
            prCreate.ShipToYokogawa();
            prCreate.Project();
            prCreate.WBS();
            prCreate.Incoterm();
            prCreate.ShippingAddress();
            prCreate.ShippingMode();
            prCreate.QuotationRequiredBy();
            prCreate.ExpectedPOIssue();
            prCreate.ExpectedDelivery();
            prCreate.BuyerManager();
            prCreate.ProjectManager();
            prCreate.RoHSCompliance();
            prCreate.OrderIntake();
            prCreate.TargetPrice();
            prCreate.WarrantyRequirements();
            prCreate.PriceValidity();
            prCreate.InspectionRequired();
            prCreate.AddLineRequisitionItemsNonCatalogType();
            prCreate.Notes();
            prCreate.PRCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}