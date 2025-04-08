package com.source.interfaces.purchaseorderrequests;
import java.util.List;

public interface IPorSendForApproval {

    List<String> getApprovers(String type, String purchaseType);
}