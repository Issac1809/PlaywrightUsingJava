package com.procurement.poc;
import com.procurement.poc.base.BaseTest;
import com.procurement.poc.interfaces.requestforquotation.IQuoSubmit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class NonCatalog_E2E_Flow extends BaseTest {

    @Test
    @Parameters({"purchaseType"})
    public void Flow() throws IOException, InterruptedException {
//        PRRejectSuspend();
        PRPosititve();
        RFQFlow();
//        PORSuspendPREdit();
        PORPositive();
        CreatePO();
    }



    void PRPosititve() throws IOException {
        iPrType.processRequisitionType();
        String approver = iPrSendForApproval.sendForApproval();
        iPrApprove.completeApprove(approver);
        iPrAssign.buyerManagerAssign();
    }
    void PRRejectSuspend() throws InterruptedException, IOException {
        iPrType.processRequisitionType();
        iPrEdit.edit();
        String approver = iPrSendForApproval.sendForApproval();
        iPrReject.reject(approver);
        iPrEdit.edit();
        approver = iPrSendForApproval.sendForApproval();
        iPrApprove.completeApprove(approver);
        iPrSuspend.suspend();
        iPrEdit.edit();
        approver = iPrSendForApproval.sendForApproval();
        iPrApprove.completeApprove(approver);
        iPrAssign.buyerManagerAssign();
        iPrSuspend.suspend();
        iPrEdit.edit();
        approver = iPrSendForApproval.sendForApproval();
        iPrApprove.completeApprove(approver);
        iPrAssign.buyerManagerAssign();
    }
    void RFQFlow(){
        iRfqCreate.createRFQ();
//        iRfqEdit.rfqEditMethod();
        iQuoSubmit.inviteRegisteredVendor();
        iQuoSubmit.quotationCreateAndSubmit();
        iReadyForEvalutation.readyForEvaluationButton();
//        iTeReject.technicalEvaluationRejectMethod();
        iTeCreate.technicalEvaluationButton();
        iCeCreate.commercialEvaluationButton();
    }
    void PORSuspendPREdit() throws IOException {
        iPorCreate.nonCatalogPORCreate();
        iPorSuspend.suspend();
        iCeCreate.commercialEvaluationButton();
        iPorCreate.nonCatalogPORCreate();
        String approver = iPorSendForApproval.sendForApproval();
        iPorReject.porReject(approver);
        iPorEdit.porEdit();
        approver = iPorSendForApproval.sendForApproval();
        iPorApprove.completeApprove(approver);
    }
    void PORPositive(){
        iPorCreate.nonCatalogPORCreate();
        String approver = iPorSendForApproval.sendForApproval();
        iPorApprove.completeApprove(approver);
    }

    void CreatePO(){
        iPoCreate.createPO();
    }
}
