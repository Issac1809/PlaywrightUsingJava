package com.yokogawa.dispatchnotes.assign;
import com.microsoft.playwright.Page;
public interface DispatchNotesAssignInterface {
    void DNAssign(String mailId, String poReferenceId, String logisticsManager, Page page);
}
