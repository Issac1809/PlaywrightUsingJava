package com.utils;

import com.microsoft.playwright.*;
import java.util.concurrent.*;

public class ToastrUtil {
    private final Page page;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> toastrTask;
    private boolean messageCaptured = false;

    public ToastrUtil(Page page) {
        this.page = page;
    }

    public void startMonitoring() {
        toastrTask = scheduler.scheduleAtFixedRate(() -> {
            try {
                if (!messageCaptured && page.isVisible(".toast-message")) {
                    String toastrMessage = page.textContent(".toast-message");
                    if (!toastrMessage.isEmpty()) {
                        System.out.println("Toastr message detected: " + toastrMessage);
                        messageCaptured = true;
                    }
                }
            } catch (PlaywrightException e) {
                if (!e.getMessage().contains("Object doesn't exist")) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void stopMonitoring() {
        if (toastrTask != null) {
            toastrTask.cancel(true);
        }
        scheduler.shutdown();
    }
}
