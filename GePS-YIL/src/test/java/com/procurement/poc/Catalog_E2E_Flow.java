package com.procurement.poc;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;


public class Catalog_E2E_Flow extends BaseTest {
    @Test
    public void Flow() throws InterruptedException {
        PRRejectSuspend();
//        PRPosititve();
        PORSuspendPREdit();
//        PORPositive();
    }

    void PRPosititve(){
        iPrType.processRequisitionType();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerAssign();
    }
    void PRRejectSuspend() throws InterruptedException {
        iPrType.processRequisitionType();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrReject.reject();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrSuspend.suspend();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerAssign();
        iPrSuspend.suspend();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerAssign();
    }

    void PORSuspendPREdit(){
        iPorCreate.catalogPORCreate();
        iPorSuspend.suspend();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerAssign();
        iPorCreate.catalogPORCreate();
        iPorSuspend.suspend();
        iPorEdit.porEdit();
        iPorSendForApproval.sendForApproval();
        iPorReject.porReject();
        iPorEdit.porEdit();
        iPorSendForApproval.sendForApproval();
        iPorApprove.approve();
    }
    void PORPositive(){
        iPorCreate.catalogPORCreate();
        iPorSendForApproval.sendForApproval();
        iPorApprove.approve();
    }

}
