package com.source.classes.purchaseorderrequests.approvalandapprove;
import com.source.interfaces.purchaseorderrequests.IPorApprove;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.source.interfaces.purchaseorderrequests.IPorSendForApprovalAndApprove;
import com.utils.LoggerUtil;
import com.utils.rpa.purchaseorderrequest.MSA_Flow;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class PorSendForApprovalAndApprove implements IPorSendForApprovalAndApprove {

    Logger logger;
    IPorApprove iPorApprove;
    IPorSendForApproval iPorSendForApproval;
    MSA_Flow msaFlow;

    private PorSendForApprovalAndApprove(){
    }

//TODO Constructor
    public PorSendForApprovalAndApprove(IPorApprove iPorApprove, IPorSendForApproval iPorSendForApproval, MSA_Flow msaFlow){
        this.iPorApprove = iPorApprove;
        this.iPorSendForApproval = iPorSendForApproval;
        this.msaFlow = msaFlow;
        this.logger = LoggerUtil.getLogger(PorSendForApprovalAndApprove.class);
    }

    public void approvalAndApprove(String type, String purchaseType){
        try {
//        List<String> approversList = iPorSendForApproval.getApprovers(type, purchaseType);
//        iPorApprove.approverLogin(type, purchaseType, approversList);
        msaFlow.msaFlow();
        } catch (Exception exception) {
            logger.error("Exception in POR Approval And Approve function: {}", exception.getMessage());
        }
    }
}