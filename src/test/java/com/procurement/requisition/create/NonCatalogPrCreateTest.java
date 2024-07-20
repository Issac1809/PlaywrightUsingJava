package com.procurement.requisition.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrCreateTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrCreateMethod(){
        try{
            iPocPrBase.RequesterLoginPRCreate();
            iPocPrBase.CreateButton();
            iPocPrBase.PurchaseType();
            iPocPrBase.Title();
            iPocPrBase.ShipToYokogawa();
            iPocPrBase.Project();
            iPocPrBase.WBS();
            iPocPrBase.Incoterm();
            iPocPrBase.ShippingAddress();
            iPocPrBase.ShippingMode();
            iPocPrBase.QuotationRequiredBy();
            iPocPrBase.ExpectedPOIssue();
            iPocPrBase.ExpectedDelivery();
            iPocPrBase.BuyerManager();
            iPocPrBase.ProjectManager();
            iPocPrBase.RoHSCompliance();
            iPocPrBase.OrderIntake();
            iPocPrBase.TargetPrice();
            iPocPrBase.WarrantyRequirements();
            iPocPrBase.PriceValidity();
            iPocPrBase.InspectionRequired();
            iPocPrBase.AddLineRequisitionItemsNonCatalogType();
            iPocPrBase.Notes();
            iPocPrBase.PRCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}