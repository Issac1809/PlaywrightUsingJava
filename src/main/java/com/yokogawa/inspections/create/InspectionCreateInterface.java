package com.yokogawa.inspections.create;
import com.microsoft.playwright.Page;
public interface InspectionCreateInterface {
    void VendorInspectionCreate(String mailId, String poReferenceId, Page page);
}