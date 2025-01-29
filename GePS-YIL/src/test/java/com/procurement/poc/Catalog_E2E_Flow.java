package com.procurement.poc;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class Catalog_E2E_Flow extends BaseTest {
    @Test
    @Parameters("purchaseType")
    public void Flow()
    {
//        PRRejectSuspend();
        PRPosititve();
//        PORSuspendPREdit();
        PORPositive();
        CreatePO();
    }

    void PRPosititve() {
        try {
            iPrType.processRequisitionType();
            String approver = iPrSendForApproval.sendForApproval();
            iPrApprove.completeApprove(approver);
            iPrAssign.buyerManagerAssign();
        }
        catch (Exception error) {
                System.out.println(error.getMessage());
            }
    }
    void PRRejectSuspend() {
        try {
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
        iPrSendForApproval.sendForApproval();
        iPrApprove.completeApprove(approver);
        iPrAssign.buyerManagerAssign();
        }
        catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
    void PORSuspendPREdit(){
        try {
            iPorCreate.catalogPORCreate();
            iPorEdit.porEdit();
            iPorSuspend.suspend();
            iPrEdit.edit();
            String approver = iPrSendForApproval.sendForApproval();
            iPrApprove.completeApprove(approver);
            iPrAssign.buyerManagerAssign();
            iPorCreate.catalogPORCreate();
            iPorSuspend.suspend();
            iPorEdit.porEdit();
            approver = iPorSendForApproval.sendForApproval();
            iPorReject.porReject(approver);
            iPorEdit.porEdit();
            approver = iPorSendForApproval.sendForApproval();
            iPorApprove.completeApprove(approver);
        }
        catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
    void PORPositive(){
        iPorCreate.catalogPORCreate();
        String approver = iPorSendForApproval.sendForApproval();
        iPorApprove.completeApprove(approver);
    }
    void CreatePO(){
        iPoCreate.createPO();
    }
}
