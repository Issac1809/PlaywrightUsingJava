package com.utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.Logger;

public class GetTitleUtil {

    static Logger logger;
    static JsonNode jsonNode;

    private GetTitleUtil(){
    }

    public GetTitleUtil(JsonNode jsonNode, Logger logger){
        this.jsonNode = jsonNode;
        this.logger = logger;
    }

    public static String getTransactionTitle(String type, String purchaseType) {
        String title = "";
        try {
            String jsonTitleKey;
            if(type.equalsIgnoreCase("PS")){
                jsonTitleKey = purchaseType.equalsIgnoreCase("Catalog") ? "psCatalogTitle" : "psNonCatalogTitle";
            } else {
                jsonTitleKey = purchaseType.equalsIgnoreCase("Catalog") ? "salesCatalogTitle" : "salesNonCatalogTitle";
            }

            title = jsonNode.get("requisition").get(jsonTitleKey).asText();
        } catch (Exception exception) {
            logger.error("Exception Initializing Get Transaction Title Function: {}", exception.getMessage());
        }
        return title;
    }
}
