package com.yokogawa.orderschedule.approve;
import com.microsoft.playwright.Page;
public interface OrderScheduleApproveInterface {
    void OSApprove(String mailId, String poReferenceId, Page page);
}
