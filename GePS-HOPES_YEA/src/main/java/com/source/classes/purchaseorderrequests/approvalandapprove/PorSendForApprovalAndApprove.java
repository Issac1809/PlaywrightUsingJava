package com.source.classes.purchaseorderrequests.approvalandapprove;
import com.source.interfaces.purchaseorderrequests.IPorApprove;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.source.interfaces.purchaseorderrequests.IPorSendForApprovalAndApprove;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class PorSendForApprovalAndApprove implements IPorSendForApprovalAndApprove {

    Logger logger;
    IPorApprove iPorApprove;
    IPorSendForApproval iPorSendForApproval;

    private PorSendForApprovalAndApprove(){
    }

//TODO Constructor
    public PorSendForApprovalAndApprove(IPorApprove iPorApprove, IPorSendForApproval iPorSendForApproval){
        this.iPorApprove = iPorApprove;
        this.iPorSendForApproval = iPorSendForApproval;
        this.logger = LoggerUtil.getLogger(PorSendForApprovalAndApprove.class);
    }

    public void approvalAndApprove(String type, String purchaseType){
        try {
        List<String> approversList = iPorSendForApproval.getApprovers(type, purchaseType);
        iPorApprove.approverLogin(type, purchaseType, approversList);
        } catch (Exception exception) {
            logger.error("Exception in POR Approval And Approve function: {}", exception.getMessage());
        }
    }
}