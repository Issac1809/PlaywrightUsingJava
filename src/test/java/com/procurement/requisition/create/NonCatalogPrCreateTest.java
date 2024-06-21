package com.procurement.requisition.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrCreateTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrCreateMethod(){
        try{
            prCreateNonCatalog.RequesterLoginPRCreate();
            prCreateNonCatalog.CreateButton();
            prCreateNonCatalog.NonCatalog();
            prCreateNonCatalog.Title();
            prCreateNonCatalog.ShipToYokogawa();
            prCreateNonCatalog.Project();
            prCreateNonCatalog.WBS();
            prCreateNonCatalog.Incoterm();
            prCreateNonCatalog.ShippingAddress();
            prCreateNonCatalog.ShippingMode();
            prCreateNonCatalog.QuotationRequiredBy();
            prCreateNonCatalog.ExpectedPOIssue();
            prCreateNonCatalog.ExpectedDelivery();
            prCreateNonCatalog.BuyerManager();
            prCreateNonCatalog.ProjectManager();
            prCreateNonCatalog.OrderIntake();
            prCreateNonCatalog.TargetPrice();
            prCreateNonCatalog.WarrantyRequirements();
            prCreateNonCatalog.PriceValidity();
            prCreateNonCatalog.InspectionRequired();
            prCreateNonCatalog.AddLineRequisitionItems();
            prCreateNonCatalog.Notes();
            prCreateNonCatalog.PRCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}