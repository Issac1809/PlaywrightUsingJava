package com.source.interfaces.invoices.poinvoices;

public interface IInvSendForApproval {
    void sendForApproval(String referenceId, String transactionId, String uid);
}