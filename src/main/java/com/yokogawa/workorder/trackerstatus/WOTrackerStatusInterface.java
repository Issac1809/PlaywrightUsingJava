package com.yokogawa.workorder.trackerstatus;
import com.microsoft.playwright.Page;
public interface WOTrackerStatusInterface {
    void VendorTrackerStatus(String mailId, String poReferenceId, Page page);
}
