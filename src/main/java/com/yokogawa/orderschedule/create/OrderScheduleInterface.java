package com.yokogawa.orderschedule.create;
import com.microsoft.playwright.Page;
public interface OrderScheduleInterface {
    void OSCreate(String mailId, Page page);
}
