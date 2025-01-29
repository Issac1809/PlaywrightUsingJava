package com.procurement.poc.interfaces.requisitions;

import java.io.IOException;

public interface IPrApprove {
    void approve();
    void completeApprove(String email) throws IOException;
    void ApproveMethod(String ApproverMailId , String PRReferenceNumber) throws InterruptedException;
}