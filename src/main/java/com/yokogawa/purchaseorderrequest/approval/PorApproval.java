package com.yokogawa.purchaseorderrequest.approval;
import java.util.List;

public interface PorApproval {
    List<String> SendForApproval();
    void ApproverLogin(List<String> matchingApprovers);
}
