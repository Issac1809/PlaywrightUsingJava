package com.utils.rpa.salesordersync;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class PR_List_APIHelper {

    Logger logger;
    Page page;

//TODO Constructor
    public PR_List_APIHelper(Page page) {
        this.page = page;
        this.logger = LoggerUtil.getLogger(PR_List_APIHelper.class);
    }

    public void syncSO(String apiUrl) {
        try {
            page.request().fetch(apiUrl, RequestOptions.create());
        } catch (Exception exception) {
            logger.error("Exception in Sync SO Function: {}", exception.getMessage());
        }
    }

    public void updateStatus(String apiUrl, int id) {
        try {
            page.request().fetch(apiUrl + id, RequestOptions.create());
        } catch (Exception exception) {
            logger.error("Exception in Update Status Function: {}", exception.getMessage());
        }
    }
}
