package com.procurementapi.get;
import com.poc.base.BaseApiTest;
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

        APIResponse apiResponse = apiRequestContext.get("https://dprocure-test.cormsquare.com/api/GetKnowledgeBaseFiles/Requisition", RequestOptions.create()
                .setHeader("cookie", ".AspNetCore.Antiforgery.HicnYevTp_w=CfDJ8A1dDNIGbYVOnaPdVS6bxFwbeyfyKk4FBQyNvFZkLvgcVQXolu69T6zSvuCD_63qG7zWifkXyyziFzjdq025ALipTxoj6lcwr1AUAjx24rwKVWpxQhMvDK8TA3miEyA1P9ctyCb9XwEFtFQ9rEGTfxg; .AspNetCore.Identity.Application=CfDJ8A1dDNIGbYVOnaPdVS6bxFxn0rcBfJXA6eADaVJxlGewxV2DWTfTUGapMIQaST46ZTXWsnagLft_AeS2ryayrf73zthIkspwyH1_mtq8F1MpL9IGMvbuOKPyN-MmJNvoJbEk5Ia2ASW_A6wJm0Vn2pADy_aRcf2sgyxPq4Q-7BDkW6LsqYd61_qEYYaND8sjvxpOD3y7njbtAjQTx6o3q450J6BbDpm2zaVRY9TAKbXD3IDJ05cGMLYHcFg-VMzmxoWPOUI-s8i4E5R28Me-pXiJa3QTdPUBfrc45yawNFHKKhR2Pn0oz5xEO080I-X580qzJ-69NPGJzepv2-AzjmhQSbQpexTl67ITYX3LC1cO1UGckSIBBeQ8jfeGEl70z6DNC1C-KTS5K6Askhp9KMToGDZlUm5FGxVneU6iYLeG9E-XQDNUgCSv-0SReL_uyyh5JX8Sxw6Ubo3my3keDnVNgm0mNiq3jYIx4xvnoP6zgIecc2RI-IgsGFxYkrAkyleeq1_55cJJas25Cv7bXwTZGV3yhFli7_xG4zdU9rPxopx0Hw6NUFd8_pr-a63XHpvn7OVUOK9thBkcJbK62DQSwzbM7VQHmkBfThu3T3gPtXnXXEnirzAl4yL9X6ITZoU89rS1q2eXcsU0OIrDj074pon8X3GYeX-iZBmfoc7oRbEZCHYNenMJY-4RDHIRoMro942mF-VeZfXjmRJtNTQxBcxR26jYAyorF4ua618IMnT5dZXpmZzORu-xyl-wbmmLkpWoZX_7l7kqcwnzJFjQvDjr6iEnYPph5fY-uFyHFEvurF_s6-9Jf-icWg00CfOju4rYuxXJqZvu2IdEQ5Bmth2e0U7imSPGuSa3RHNyWaaZqZ38CRK0aT7zXNkTwyruQRRRoNb5_ssFh95SN8cPb1FYnL14wT-aJSiDGLTVvtTIkz5YYHYqOY72oEdVdCsy0eSgHnF9CeOf1LuXKpM"));


//TODO API Status Code
        int statusCode = apiResponse.status();
        System.out.println("Response Status Code : " + statusCode);
        Assert.assertEquals(statusCode, 200);

//TODO API Response Text
        boolean responseText = apiResponse.ok();
        System.out.println("Response Text : " + responseText);
        Assert.assertEquals(apiResponse.ok(), true);

//TODO API Status Response Text
        String statusResponseText = apiResponse.statusText();
        System.out.println("Status Response Text : " + statusResponseText);

//TODO API Response Plain Text
        String statusResponsePlainText = apiResponse.text();
        System.out.println("Status Response Plain Text : " + statusResponsePlainText);

//TODO API JSON Response
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println("JSON Response Pretty : " + jsonPrettyResponse);

//TODO API Response URL
        String url = apiResponse.url();
        System.out.println("Response URL : " + url);

//TODO API Response Headers
        Map<String, String> headersMap = apiResponse.headers();
        System.out.println("Response Headers : " + headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
        Assert.assertEquals(headersMap.get("pragma"), "no-cache");
    }
}
