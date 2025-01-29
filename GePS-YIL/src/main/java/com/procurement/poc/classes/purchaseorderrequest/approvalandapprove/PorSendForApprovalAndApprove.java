package com.procurement.poc.classes.purchaseorderrequest.approvalandapprove;

import com.procurement.poc.interfaces.purchaseorderrequests.IPorApprove;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorSendForApprovalAndApprove;

import java.util.List;

public class PorSendForApprovalAndApprove implements IPorSendForApprovalAndApprove {

    IPorApprove iPorApprove;
    IPorSendForApproval iPorSendForApproval;

    private PorSendForApprovalAndApprove(){
    }

//TODO Constructor
    public PorSendForApprovalAndApprove(IPorApprove iPorApprove, IPorSendForApproval iPorSendForApproval){
        this.iPorApprove = iPorApprove;
        this.iPorSendForApproval = iPorSendForApproval;
    }

    public void approvalAndApprove(){
        try {
//        List<String> approversList = iPorSendForApproval.getApprovers();
//        iPorApprove.approverLogin(approversList);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}