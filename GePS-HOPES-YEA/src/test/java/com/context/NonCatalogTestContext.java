package com.context;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NonCatalogTestContext extends BaseTest {

    @Test(priority = 1)
    @Parameters({"purchaseType"})
    public void createRequisition(String purchaseType) {
        try {
            int status = iPrType.processRequisitionType(purchaseType);

            Assert.assertEquals(200, status, "API call was not successful for: " + status);
        } catch (Exception error) {
            Assert.fail("Error in Create Requisition Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }

    @Test(priority = 2)
    @Parameters({"purchaseType"})
    public void editRequisition(String purchaseType){
        try {
            int status = iPrEdit.edit(purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            Assert.fail("Error in Edit Requisition Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }

    @Test(priority = 3)
    @Parameters({"purchaseType"})
    public void requisitionSendForApproval(String purchaseType){
        try {
            int status = iPrSendForApproval.sendForApproval(purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            Assert.fail("Error in Requisition Send For Approval Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }

    @Test(priority = 4)
    @Parameters({"purchaseType"})
    public void requisitionReject(){
        String[] approvers = properties.getProperty("requisitionApprovers").split(",");
        try{
            if(approvers.length != 0){
                int status = iPrReject.reject();

                Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
            }
        } catch (Exception error) {
            Assert.fail("Error in Requisition Reject Function for Non-Catalog Type: " + error.getMessage());
        }
    }
}