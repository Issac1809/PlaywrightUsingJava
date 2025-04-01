package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MSA_APIHelper {

    Logger logger;

//TODO Constructor
    public MSA_APIHelper(Logger logger) {
        this.logger = LoggerUtil.getLogger(MSA_APIHelper.class);
    }

    public void updateStatus(String apiUrl) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status Code: " + httpResponse.statusCode());
            System.out.println("Response Body: " + httpResponse.body());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}