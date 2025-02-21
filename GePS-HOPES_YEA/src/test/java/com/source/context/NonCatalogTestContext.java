package com.source.context;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NonCatalogTestContext extends BaseTest {

    @Test(priority = 1)
    @Parameters({"type", "purchaseType"})
    public void createRequisition(String type, String purchaseType) {
        try {
            int status = iPrType.processRequisitionType(type, purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            logger.error("Error in Create Requisition Context Function for Non-Catalog Type: {}", error.getMessage());
            Assert.fail("Error in Create Requisition Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }

    @Test(priority = 2)
    @Parameters({"type", "purchaseType"})
    public void editRequisition(String type, String purchaseType){
        try {
            int status = iPrEdit.edit(type, purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            logger.error("Error in Edit Requisition Context Function for Non-Catalog Type: {}", error.getMessage());
            Assert.fail("Error in Edit Requisition Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }

    @Test(priority = 3)
    @Parameters({"type", "purchaseType"})
    public void requisitionSendForApproval(String type, String purchaseType){
        try {
            int status = iPrSendForApproval.sendForApproval(type, purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            logger.error("Error in Requisition Send For Approval Context Function for Non-Catalog Type: {}", error.getMessage());
            Assert.fail("Error in Requisition Send For Approval Context Function for Non-Catalog Type: " + error.getMessage());
        }
    }
//
//    @Test(priority = 4)
//    @Parameters({"purchaseType"})
//    public void requisitionReject(String purchaseType){
//        String[] approvers = properties.getProperty("requisitionApprovers").split(",");
//        try{
//            if(approvers.length != 0){
//                int status = iPrReject.reject(purchaseType);
//
//                Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//            }
//        } catch (Exception error) {
//            logger.error("Error in Requisition Reject Function for Non-Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Requisition Reject Function for Non-Catalog Type: " + error.getMessage());
//        }
//    }
//
//    @Test(priority = 5)
//    @Parameters({"purchaseType"})
//    public void requisitionEditAfterReject(String purchaseType){
//        String[] approvers = properties.getProperty("requisitionApprovers").split(",");
//        try{
//            if(approvers.length != 0){
//                int status = iPrEdit.edit(purchaseType);
//
//                Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//            }
//        } catch (Exception error) {
//            logger.error("Error in Requisition Reject Edit Function for Non-Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Requisition Reject Edit Function for Non-Catalog Type: " + error.getMessage());
//        }
//    }
}