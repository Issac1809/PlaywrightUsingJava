package com.procurement.purchaseorderrequest.approvalandapprove;
import com.interfaces.PorApproval;
import com.interfaces.PorApprove;
import com.interfaces.ApprovalAndApprove;
import java.util.List;

public class PorApprovalAndApprove implements ApprovalAndApprove {

    PorApprove porApprove;
    PorApproval porApproval;

    private PorApprovalAndApprove(){
    }

//TODO Constructor
    public PorApprovalAndApprove(PorApprove porApprove, PorApproval porApproval){
        this.porApprove = porApprove;
        this.porApproval = porApproval;
    }

    public void PorApprovalAndApproveMethod(){
        List<String> matchingApprovers = porApproval.SendForApproval();
        porApprove.ApproverLogin(matchingApprovers);
    }
}