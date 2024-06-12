package com.interfaces;
import java.util.List;

public interface PorApproval {
    List<String> SendForApproval();
    void ApproverLogin(List<String> matchingApprovers);
}
