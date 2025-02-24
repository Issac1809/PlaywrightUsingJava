package com.source.context;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NonCatalogTestContext extends BaseTest {

    boolean rejectStatusFlag; //TODO Variable to check if requisition is rejected or not!!

    @Test(priority = 1)
    @Parameters({"type", "purchaseType"})
    public void createRequisition(String type, String purchaseType) {
        try {
            int status = iPrType.processRequisitionType(type, purchaseType);
            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception exception) {
            logger.error("Error in Create Requisition Context Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Error in Create Requisition Context Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 2)
    @Parameters({"type", "purchaseType"})
    public void editRequisition(String type, String purchaseType){
        try {
            int status = iPrEdit.edit(type, purchaseType);
            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception exception) {
            logger.error("Exception in Edit Requisition Context Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Edit Requisition Context Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 3)
    @Parameters({"type", "purchaseType"})
    public void requisitionSendForApproval(String type, String purchaseType){
        try {
            int status = iPrSendForApproval.sendForApproval(type, purchaseType);
            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Send For Approval Context Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Send For Approval Context Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 4, dependsOnMethods = {"requisitionSendForApproval"})
    @Parameters({"type", "purchaseType"})
    public void requisitionReject(String type, String purchaseType){
        String[] approvers = jsonNode.get("requisition").get("requisitionApprovers").asText().split(",");
        try {
            if(approvers.length != 0){
                int status = iPrReject.reject(type, purchaseType);
                Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
                rejectStatusFlag = true;
            }
        } catch (Exception exception) {
            logger.error("Exception in Requisition Reject Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Reject Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 5, dependsOnMethods = {"requisitionReject"})
    @Parameters({"type", "purchaseType"})
    public void requisitionEditAfterReject(String type, String purchaseType){
        try {
            if(rejectStatusFlag){
                int rejectEditStatus = iPrEdit.edit(type, purchaseType);
                Assert.assertEquals(200, rejectEditStatus, "API call was not successful; Status Code: " + rejectEditStatus);

                int sendForApprovalStatus = iPrSendForApproval.sendForApproval(type, purchaseType);
                Assert.assertEquals(200, sendForApprovalStatus, "API call was not successful; Status Code: " + sendForApprovalStatus);
            }
        } catch (Exception exception) {
            logger.error("Exception in Requisition Reject Edit Function for Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Reject Function for Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 6)
    @Parameters({"type", "purchaseType"})
    public void requisitionApprove(String type, String purchaseType){
        String[] approvers = jsonNode.get("requisition").get("requisitionApprovers").asText().split(",");
        try {
            if(approvers.length != 0){
                int status = iPrApprove.approve(type, purchaseType);
                Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
            }
        } catch (Exception exception) {
            logger.error("Exception in Requisition Approve Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Approve Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

    @Test(priority = 7)
    @Parameters({"type", "purchaseType"})
    public void requisitionAAssign(String type, String purchaseType){
        try {
            int status = iPrAssign.buyerManagerAssign(type, purchaseType);
            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Assign Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Assign Function for Non-Catalog Type: " + exception.getMessage());
        }
    }
}