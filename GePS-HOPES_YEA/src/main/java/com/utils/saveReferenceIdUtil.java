package com.utils;

import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import org.apache.logging.log4j.Logger;

public class saveReferenceIdUtil {
    static PlaywrightFactory playwrightFactory;
    static Logger logger;
    static JsonNode jsonNode;
    static ObjectMapper objectMapper;
    private saveReferenceIdUtil(){
    }

    public saveReferenceIdUtil(PlaywrightFactory playwrightFactory, JsonNode jsonNode, Logger logger, ObjectMapper objectMapper) {
        this.playwrightFactory = playwrightFactory;
        this.jsonNode = jsonNode;
        this.logger = logger;
        this.objectMapper = objectMapper;
    }

    public static void saveToJson(String parentKey, String attributeKey, String attributeValue) {
        playwrightFactory.savePropertiesIntoJsonFile(parentKey, attributeKey, attributeValue);
    }

    // if refID we want is inside json
    public static void saveReferenceIdFromResponse(Response response, String parentKey, String attributeKey ) throws JsonProcessingException {
        String responseBody = response.text();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        String referenceId = responseJson.get("referenceId").asText();
        playwrightFactory.savePropertiesIntoJsonFile(parentKey,attributeKey,referenceId);
        logger.info("Reference ID: {}", referenceId);
    }

    // if refID we want is inside an object in the json
    public static void saveReferenceIdFromResponse(Response response, String node,  String parentKey, String attributeKey) throws JsonProcessingException {
        String responseBody = response.text();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        String referenceId = responseJson.get(node).get(0).get("referenceId").asText();
        playwrightFactory.savePropertiesIntoJsonFile(parentKey,attributeKey,referenceId);
        logger.info("Reference ID: {}", referenceId);
    }


    public static void saveReferenceIdFromApiResponse(APIResponse response, String parentKey, String attributeKey ) throws JsonProcessingException {
        String responseBody = response.text();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);
        String referenceId = responseJson.get("referenceId").asText();
        playwrightFactory.savePropertiesIntoJsonFile(parentKey,attributeKey,referenceId);
        logger.info("Reference ID: {}", referenceId);
    }



}
