package com.procurementapi.get;
import com.base.BaseApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetApiTest extends BaseApiTest {

    @Test
    public void getAPITestMethod() throws IOException {

//TODO API Response
        APIResponse apiResponse1 = apiRequestContext.get("https://dprocure-test.cormsquare.com/api/Requisitions/Listing");

//TODO API Response With Filter
//        APIResponse apiResponse2 = apiRequestContext.get("https://yea-test.cormsquare.com/api/Requisitions/Listing", RequestOptions.create()
//                .setQueryParam("name", "POC Catalog Requisition")
//                .setQueryParam("companyId", "0"));

        String ask = apiResponse1.url();

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
//        String responseBody = Arrays.toString(apiResponse1.body());
//        String jsonString = extractJson(responseBody);
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

//    public String extractJson(String responseBody) {
//        String jsonPattern = "\\\\{[^\\\\{\\\\}]*\\\\}";
//        Pattern pattern = Pattern.compile(jsonPattern);
//        Matcher matcher = pattern.matcher(responseBody);
//        if (matcher.find()) {
//            return matcher.group();
//        } else {
//        return null;
//        }
//    }
}
