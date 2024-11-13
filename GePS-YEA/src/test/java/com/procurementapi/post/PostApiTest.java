package com.procurementapi.post;
import com.poc.base.BaseApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PostApiTest extends BaseApiTest {

    @Test
    public void postAPITestMethod() throws IOException {

        Map<String, String> listData = getStringStringMap();

        String jsonPayload = objectMapper.writeValueAsString(listData);

        APIResponse apiResponse = apiRequestContext.post("https://yea-test.cormsquare.com/api/requisitions", RequestOptions.create()
                .setHeader("cookie", ".AspNetCore.Antiforgery.OHnGVqUmqBQ=CfDJ8A1dDNIGbYVOnaPdVS6bxFwzLl-qzEGBl2hbtT0GmmSJctDHdspGIQAByjMqd5A8o8ELuFSqDTgYD24n4mSGtP8UWz-KpVqb4vZjx7cCxX7A1DDSJ-_SvuGIjgU_pq1j4zCE_0OEVmLPOCyOivYfb2E; .AspNetCore.Identity.Application=CfDJ8A1dDNIGbYVOnaPdVS6bxFyC8mygQZx3JEbA_A7neNMYdnA1qWdUZ-aLhJBm2iAekt6AVMeJ4rD8KG0SuQlmK6DtwHgSbqy9T5APr9iDf_7XLKW_4yKpl2nQXXmpnOO_UAhgJ2Hb8KehOil2vGBBXbop9I8PfoB_RISYyh9JrWcme5JKdxwXBYLss8c_rMMziyDo1xotiy3GhHWiYnJ5ew3sTDSn7wzmKB2L01VI1hwbQw0ljXQi9qDjeOz_ha_zRA-EMkEG2m7YIcjedWPErHihxvipHt6MqEIZBOYmznTMMNM-MCfzkwxOXeB86haVM0a0Sz8siabG6x6DOiX2P5A-ItkS_a4pNQnMw2cY_RpVL8pmWe_02d74VwTZQMxotPMeJcT7TouqPZBZAgySehQN5hflc_OyzbmL8gu_e2bfLxiake9fe4GbeG6HVOC_5utrtkshb8c1-K7NTmYjEvF49-XKlHO-4ccjxrISvy-f0E52DY6iaagIHkjOw4eKOgVf7udzeges7xn6_Qwsz3OhE1OEYiaNqRUOFvqsQbFVVlF1rOsJ8qm1sWQYW6vQ4bvg_Oj5QroV4-YFgYNz_PoB0w0BcOOM_PeAnA2gIgIKxWb_zJk26GEvYlRsycfFNTuWIOmB8z9tIw5D5BnwK4tH_hWrW1SjEU_tyu2DFwmZwGiYw7efQ71NJDU3vYeBYczxaFAOOzC10DlpE19qaGoUrAGN4dFDvFzPRt5cZw6d3m47wRskgSXJKFmjNJxB81omCQ17ApkSuboOHwn6omvI22ZkVmLJ5RvkBA_QM1iLSVHqEslY6gCkgBiG48sDlX8YPjkdJkIKClJUTS5TS1_DlGURvf5llIccMlk831LGAgxLcn5GbQAhjHOIONfnZPDC9hNwzVHJUHa44ILwiuoBAozS6QIxz1dCesg1YrBeXmmuum_O76OY2yPK_jnDjuQU9cDkj5qWd_ZigCZUwzbAAlywyIfSGYXHUGrjGjow")
                .setData(jsonPayload));

//TODO API JSON Response
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println("JSON Response Pretty : " + jsonPrettyResponse);

    }

    private static Map<String, String> getStringStringMap() {
        Map<String, String> listData = new HashMap<>();
        listData.put("title" , "POC Catalog");
        listData.put("currencyId", "49");
        listData.put("projectId", "1882");
        listData.put("wbsId", "4171");
        listData.put("companyId", "1");
        listData.put("projectManagerId", "3");
        listData.put("buyerMangerId", "331");
        listData.put("paymentTerm", "L045");
        listData.put("paymentTermId", "20");
        listData.put("billingType", "Timesheet");
        listData.put("vendorId", "213");
        listData.put("fromDate", "2024-07-15");
        listData.put("toDate", "2024-07-20");
        listData.put("expectedMobilization", "2024-07-12");
        listData.put("notes", "tgrsd");

        // Create requisition items
        Map<String, Object> requisitionItem = new HashMap<>();
        requisitionItem.put("description", "wewf");
        requisitionItem.put("unit", "Each");
        requisitionItem.put("itemId", "82582");
        requisitionItem.put("bopCode", "EGRSI100200");
        requisitionItem.put("category", "Services");
        requisitionItem.put("quantity", "500");
        requisitionItem.put("rate", 1);
        requisitionItem.put("name", "Engineering Services");
        requisitionItem.put("storageLocation", "C001");
        requisitionItem.put("lineNumber", 1);
        listData.put("requisitionItems", Arrays.toString(new Map[]{requisitionItem}));
        listData.put("requisitionAttachments", Arrays.toString(new Map[]{}));
        listData.put("purchaseType", "POC");
        listData.put("purchaseMethod", "MH");
        listData.put("subtotal", "500");
        listData.put("incoTermId", "45");
        listData.put("incoTerm", "Y03 - Non-Charged Freight");
        listData.put("shippingAddress", "Yokogawa Engineering Asia Pte Ltd \n5, Bedok South Road, Singapore 469270\t");
        listData.put("shipLocationId", "157");
        listData.put("endUserCode", "");
        listData.put("locationCode", "C2400000");
        listData.put("shipToYokogawa", String.valueOf(true));
        listData.put("tcasCompliance", "false");
        listData.put("tcasComplianceNumber", "E256232Q00");
        listData.put("tcasComplianceAttachment", "");
        listData.put("tcasChecklist", Arrays.toString(new Map[]{}));
        listData.put("itemType", "NA");
        return listData;
    }
}
