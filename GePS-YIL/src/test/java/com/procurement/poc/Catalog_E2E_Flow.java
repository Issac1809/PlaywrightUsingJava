package com.procurement.poc;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;


public class Catalog_E2E_Flow extends BaseTest {
    @Test
    public void Flow() throws InterruptedException {
        PRRejectSuspend();
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
    }
}
