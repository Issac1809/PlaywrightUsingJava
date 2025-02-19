package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class getUtils {

    public static String getTransactionTitle(String type,String purchaseType) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("./src/test/resources/config/test-data.json"));

        String jsonTitleKey;
        if(type.equalsIgnoreCase("PS")){
            jsonTitleKey = purchaseType.equalsIgnoreCase("Catalog") ? "psCatalogTitle" : "psNonCatalogTitle";
        } else {
            jsonTitleKey = purchaseType.equalsIgnoreCase("Catalog") ? "salesCatalogTitle" : "salesNonCatalogTitle";
        }

        String title = jsonNode.get("requisition").get(jsonTitleKey).asText();

        return title;
    }
}
