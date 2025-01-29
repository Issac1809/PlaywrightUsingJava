package com.procurement.poc.interfaces.purchaseorderrequests;
import java.util.List;

public interface IPorApprove {

//    void approverLogin(List<String> approvers);
    void approve();
    void approveMethod(String ApproverMailId, String PORReferenceNumber);

    void completeApprove(String mail);
}