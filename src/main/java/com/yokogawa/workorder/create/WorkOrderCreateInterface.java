package com.yokogawa.workorder.create;
import com.microsoft.playwright.Page;

public interface WorkOrderCreateInterface {
    void WOCreate(String mailId, String poReferenceId, String vendor, Page page);
}
