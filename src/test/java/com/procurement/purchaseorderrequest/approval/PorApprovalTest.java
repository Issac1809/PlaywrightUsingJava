package com.procurement.purchaseorderrequest.approval;
import com.base.BaseTest;
import org.testng.annotations.Test;
import java.util.List;

public class PorApprovalTest extends BaseTest {

    @Test
    public void PorApprovalTestMethod(){
        List<String> getApprovers = porApproval.SendForApproval();
        porApproval.ApproverLogin(getApprovers);
    }
}