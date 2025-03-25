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
    public void requisitionAssign(String type, String purchaseType){
        try {
            int status = iPrAssign.buyerManagerAssign(type, purchaseType);
            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Assign Function for Non-Catalog Type: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Assign Function for Non-Catalog Type: " + exception.getMessage());
        }
    }

//    @Test(priority = 8)
//    @Parameters({"type"})
//    public void requestForQuotationCreate(String type){
//        try {
//            int status = iRfqCreate.buyerRfqCreate(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Request For Quotation Create Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Request For Quotation Create Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 9)
//    @Parameters({"type"})
//    public void requestForQuotationEdit(String type){
//        try {
//            int status = iRfqEdit.rfqEditMethod(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Request For Quotation Edit Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Request For Quotation Edit Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 10)
//    @Parameters({"type"})
//    public void requestForQuotationSuspendRFQEdit(String type){
//        try {
//            int status = iRfqSuspend.suspendRfqEdit(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Request For Quotation Suspend RFQ Edit Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Request For Quotation Suspend RFQ Edit Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 11)
//    @Parameters({"type", "purchaseType"})
//    public void requestForQuotationSuspendPREdit(String type, String purchaseType){
//        try {
//            int status = iRfqSuspend.suspendPREdit(type, purchaseType);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Request For Quotation Suspend PR Edit Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Request For Quotation Suspend PR Edit Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 12)
//    @Parameters({"type"})
//    public void quotationRegret(String type){
//        try {
//            int status = iQuoRegret.regret(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Quotation Regret Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Quotation Regret Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 13)
//    @Parameters({"type"})
//    public void quotationSubmit(String type){
//        try {
//            iQuoSubmit.inviteRegisteredVendor(type);
//            iQuoSubmit.vendorLogin(type);
//            iQuoSubmit.liquidatedDamages();
//            iQuoSubmit.rohsCompliance();
//            iQuoSubmit.warrantyRequirements();
//            iQuoSubmit.quotationItems();
//            iQuoSubmit.gst();
//            iQuoSubmit.quotationAttachments();
//            int status = iQuoSubmit.quotationSubmitButton();
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Quotation Submit Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Quotation Submit Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 14)
//    @Parameters({"type"})
//    public void quotationRequote(String type){
//        try {
//            int status = iQuoRequote.requote(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Quotation Requote Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Quotation Requote Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 15)
//    @Parameters({"type"})
//    public void readyForEvaluation(String type){
//        try {
//            int status = iReadyForEvalutation.readyForEvaluationButton(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Ready For Evaluation Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Ready For Evaluation Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 16)
//    @Parameters({"type"})
//    public void technicalEvaluationCreate(String type){
//        try {
//            int status = iTeCreate.technicalEvaluationCreate(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Technical Evaluation Create Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Technical Evaluation Create Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 17)
//    @Parameters({"type"})
//    public void technicalEvaluationReject(String type){
//        try {
//            int status = iTeReject.technicalEvaluationReject(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Technical Evaluation Reject Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Technical Evaluation Reject Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 18)
//    @Parameters({"type"})
//    public void technicalEvaluationApprove(String type){
//        try {
//            int status = iTeApprove.technicalEvaluationApprove(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Technical Evaluation Approve Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Technical Evaluation Approve Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
//
//    @Test(priority = 19)
//    @Parameters({"type"})
//    public void commercialEvaluationCreate(String type){
//        try {
//            int status = iCeCreate.commercialEvaluationButton(type);
//            Assert.assertEquals(200, status, "API call was not successful; Status Code: " + status);
//        } catch (Exception exception) {
//            logger.error("Exception in Commercial Evaluation Create Function for Non-Catalog Type: {}", exception.getMessage());
//            Assert.fail("Exception in Commercial Evaluation Create Function for Non-Catalog Type: " + exception.getMessage());
//        }
//    }
}