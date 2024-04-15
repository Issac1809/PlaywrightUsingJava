package com.yokogawa.orderschedule.create;
import com.microsoft.playwright.Page;
public interface OrderScheduleInterface {
    String OSCreate(String mailId, Page page);
}
