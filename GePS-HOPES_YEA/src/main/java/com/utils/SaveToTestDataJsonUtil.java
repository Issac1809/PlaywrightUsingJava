package com.utils;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import org.apache.logging.log4j.Logger;

public class SaveToTestDataJsonUtil {

    static PlaywrightFactory playwrightFactory;
    static Logger logger;
    static ObjectMapper objectMapper;

//TODO Constructor
    private SaveToTestDataJsonUtil(){
    }

    public SaveToTestDataJsonUtil(PlaywrightFactory playwrightFactory, ObjectMapper objectMapper) {
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.logger = LoggerUtil.getLogger(SaveToTestDataJsonUtil.class);
    }


    public static void saveReferenceIdFromResponse(Response response, String parentKey, String attributeKey) {
        try {
            String responseBody = response.text();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            String referenceId = responseJson.get("referenceId").asText();
            playwrightFactory.savePropertiesIntoJsonFile(parentKey, attributeKey, referenceId);
        } catch(Exception exception) {
            logger.error("Exception in Save Reference Id from Response function: {}", exception.getMessage());
        }
    }

    public static void saveReferenceIdFromResponse(Response response, String node, String parentKey, String attributeKey) {
        try {
            String responseBody = response.text();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            String referenceId = responseJson.get(node).get(0).get("referenceId").asText();
            playwrightFactory.savePropertiesIntoJsonFile(parentKey, attributeKey, referenceId);
        } catch(Exception exception) {
            logger.error("Exception in Save Reference Id from Response function (First Index Node): {}", exception.getMessage());
        }
    }

    public static void saveReferenceIdFromApiResponse(APIResponse response, String parentKey, String attributeKey) {
        try {
            String responseBody = response.text();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            String referenceId = responseJson.get("referenceId").asText();
            playwrightFactory.savePropertiesIntoJsonFile(parentKey,attributeKey,referenceId);
        } catch (Exception exception) {
            logger.error("Exception in Save Reference Id from API Response function: {}", exception.getMessage());
        }
    }

    public static String saveAndReturNextApprover(Response response) {
        String nextApproverEmail = "";
        try {
            JsonNode responseJson = objectMapper.readTree(response.body());
            for(int i = 0; i < responseJson.get("approvers").size(); i++) {
                String approverStatus = responseJson.get("approvers").get(i).get("approverStatus").asText();
                if(approverStatus.equalsIgnoreCase("Pending")) {
                    nextApproverEmail = responseJson.get("approvers").get(i).get("email").asText();
                    break;
                }
            }
            playwrightFactory.savePropertiesIntoJsonFile("invoices", "nextApprover", nextApproverEmail);
        } catch(Exception exception) {
            logger.error("Exception in Save Reference Id from Response function (First Index Node): {}", exception.getMessage());
        }
        return nextApproverEmail;
    }
}