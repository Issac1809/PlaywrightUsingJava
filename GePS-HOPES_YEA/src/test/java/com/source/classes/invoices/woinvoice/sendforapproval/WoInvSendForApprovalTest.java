package com.source.classes.invoices.woinvoice.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval(){
        try {
            iWoInvSendForApproval.sendForApproval();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Send For Approval Test function: {}", exception.getMessage());
        }
    }
}