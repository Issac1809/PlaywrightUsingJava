package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class MSA_APIHelper {

    Logger logger;

//TODO Constructor
    public MSA_APIHelper() {
        this.logger = LoggerUtil.getLogger(MSA_APIHelper.class);
    }

    public static void updateStatus(String apiUrl) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(apiUrl);
            get.setHeader("Content-Type", "application/json");

            String response = EntityUtils.toString(client.execute(get).getEntity());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}