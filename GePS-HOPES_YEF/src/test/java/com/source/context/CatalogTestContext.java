package com.source.context;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CatalogTestContext extends BaseTest {

    @Test(priority = 1)
    @Parameters({"type", "purchaseType"})
    public void createRequisition(String type, String purchaseType) {
        try {
            int status = iPrType.processRequisitionType(type, purchaseType);

            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception error) {
            logger.error("Error in Create Requisition Context Function for Catalog Type: {}", error.getMessage());
            Assert.fail("Error in Create Requisition Context Function for Catalog Type: " + error.getMessage());
        }
    }

//    @Test(priority = 2)
//    @Parameters({"purchaseType"})
//    public void editRequisition(String purchaseType){
//        try {
//            int status = iPrEdit.edit(purchaseType);
//
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception error) {
//            logger.error("Error in Edit Requisition Context Function for Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Edit Requisition Context Function for Catalog Type: " + error.getMessage());
//        }
//    }
//
//    @Test(priority = 3)
//    @Parameters({"purchaseType"})
//    public void requisitionSendForApproval(String purchaseType){
//        try {
//            int status = iPrSendForApproval.sendForApproval(purchaseType);
//
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception error) {
//            logger.error("Error in Requisition Send For Approval Context Function for Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Requisition Send For Approval Context Function for Catalog Type: " + error.getMessage());
//        }
//    }
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
//            logger.error("Error in Requisition Reject Function for Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Requisition Reject Function for Catalog Type: " + error.getMessage());
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
//            logger.error("Error in Requisition Reject Edit Function for Catalog Type: " + error.getMessage());
//            Assert.fail("Error in Requisition Reject Function for Catalog Type: " + error.getMessage());
//        }
//    }

//    @Test(priority = 2, dependsOnMethods = {"createRequisition"})
//    public void sendForApproval(ITestContext context) {
//        String status = (String) context.getAttribute("status");
//        if ("Created".equals(status)) {
//            System.out.println("Sending requisition for approval...");
//            context.setAttribute("status", "PendingApproval");
//        } else {
//            throw new SkipException("Requisition not created, skipping SendForApproval...");
//        }
//    }
//
//    @Test(priority = 3, dependsOnMethods = {"sendForApproval"})
//    public void rejectRequisition(ITestContext context) {
//        String status = (String) context.getAttribute("status");
//        if ("PendingApproval".equals(status)) {
//            System.out.println("Rejecting requisition...");
//            context.setAttribute("status", "Rejected");
//        } else {
//            throw new SkipException("Requisition not in pending state, skipping Reject...");
//        }
//    }
//
//    @Test(priority = 4, dependsOnMethods = {"rejectRequisition"})
//    public void editRequisitionAfterReject(ITestContext context) {
//        String status = (String) context.getAttribute("status");
//        if ("Rejected".equals(status)) {
//            System.out.println("Editing requisition after rejection...");
//        } else {
//            throw new SkipException("Requisition not rejected, skipping Edit...");
//        }
//    }
//}
}
