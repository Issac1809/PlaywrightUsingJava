package com.procurement.purchaseorderrequest.approvalandapprove;
import com.interfaces.por.PorApproval;
import com.interfaces.por.PorApprove;
import com.interfaces.por.ApprovalAndApprove;
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
        try {
        List<String> matchingApprovers = porApproval.SendForApproval();
        porApprove.ApproverLogin(matchingApprovers);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}