package com.source.interfaces.purchaseorderrequests;
import java.util.List;

public interface IPorApprove {

    void approverLogin(String type, String purchaseType, List<String> approvers);
}