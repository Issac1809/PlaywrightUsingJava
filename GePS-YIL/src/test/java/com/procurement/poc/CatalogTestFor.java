package com.procurement.poc;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;


public class CatalogTestFor extends BaseTest {
    @Test
    public void Flow() throws InterruptedException {
        iPrType.processRequisitionType();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrReject.reject();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerAssign();
    }
}
