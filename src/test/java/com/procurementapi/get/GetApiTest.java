package com.procurementapi.get;
import com.base.BaseApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class GetApiTest extends BaseApiTest {

    @Test
    public void getAPITestMethod() throws IOException {

//TODO API Response
        APIResponse apiResponse1 = apiRequestContext.get("https://yef-test.cormsquare.com/api/PurchaseOrders/d0c263fa-f55d-4193-be61-1d1187bc0d92");

//TODO API Response With Filter
        APIResponse apiResponse2 = apiRequestContext.get("https://yef-test.cormsquare.com/api/PurchaseOrders/d0c263fa-f55d-4193-be61-1d1187bc0d92", RequestOptions.create()
                .setQueryParam("name", "POC Catalog Requisition")
                .setQueryParam("companyId", "0"));

//TODO API Status Code
        int statusCode = apiResponse1.status();
        System.out.println("Response Status Code : " + statusCode);
        Assert.assertEquals(statusCode, 200);

//TODO API Response Text
        boolean responseText = apiResponse1.ok();
        System.out.println("Response Text : " + responseText);
        Assert.assertEquals(apiResponse1.ok(), true);

//TODO API Status Response Text
        String statusResponseText = apiResponse1.statusText();
        System.out.println("Status Response Text : " + statusResponseText);

//TODO API Response Plain Text
        String statusResponsePlainText = apiResponse1.text();
        System.out.println("Status Response Plain Text : " + statusResponsePlainText);

//TODO API JSON Response
        JsonNode jsonResponse = objectMapper.readTree(apiResponse1.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println("JSON Response Pretty : " + jsonPrettyResponse);

//TODO API Response URL
        String url = apiResponse1.url();
        System.out.println("Response URL : " + url);

//TODO API Response Headers
        Map<String, String> headersMap = apiResponse1.headers();
        System.out.println("Response Headers : " + headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
        Assert.assertEquals(headersMap.get("x-download-options"), "noopen");
    }
}
