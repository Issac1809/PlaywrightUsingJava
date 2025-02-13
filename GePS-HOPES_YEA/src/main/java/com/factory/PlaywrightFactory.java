package com.factory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.*;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PlaywrightFactory {

    static Logger logger;
    Playwright playwright;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;

//TODO Constructor
    public PlaywrightFactory() {
        logger = LoggerUtil.getLogger(PlaywrightFactory.class);
    }

//TODO Thread Local to avoid flaky tests/to execute parallel tests
    private static final ThreadLocal<Playwright> localPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> localBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> localBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> localPage = new ThreadLocal<>();

    public void setPlaywright() {
        playwright = Playwright.create();
        localPlaywright.set(playwright);
    }

    public Playwright getPlaywright() {
        setPlaywright();
        return localPlaywright.get();
    }

    public Browser getBrowser() {
        return localBrowser.get();
    }

    public BrowserContext getBrowserContext() {
        return localBrowserContext.get();
    }

    public static Page getPage() {
        return localPage.get();
    }

    public Page initializePage(JsonNode jsonNode) {
        try {
            String browserName = jsonNode.get("config").get("browserName").asText().trim().toUpperCase();
            switch (browserName.toUpperCase()) {
                case "CHROMIUM":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                case "CHROME":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                    break;
                case "EDGE":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                    break;
                case "SAFARI":
                    localBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                case "FIREFOX":
                    localBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                default:
                    System.out.println("--Enter Proper Browser Name--");
                    break;
            }
            localBrowserContext.set(getBrowser().newContext());
            localPage.set(getBrowserContext().newPage());
            getPage().navigate(jsonNode.get("config").get("appUrl").asText().trim());
        } catch (Exception error) {
            logger.error("Error in Initialize Page Function: " + error.getMessage());
        }
        return getPage();
    }

    public void saveToJsonFile(String attributeKey, String attributeValue) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("./src/test/resources/config/test-data.json"));

            // Traverse the JSON tree to find and update all occurrences of the key
            updateJsonNode(jsonNode, attributeKey, attributeValue);

            objectMapper.writeValue(new File("./src/test/resources/config/test-data.json"), jsonNode);
        } catch (Exception error) {
            logger.error("Error in Save to JSON File Function: " + error.getMessage());
        }
    }

    private void updateJsonNode(JsonNode jsonNode, String attributeKey, String attributeValue) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            if (objectNode.has(attributeKey)) {
                objectNode.put(attributeKey, attributeValue);
            }
            objectNode.fields().forEachRemaining(entry -> updateJsonNode(entry.getValue(), attributeKey, attributeValue));
        } else if (jsonNode.isArray()) {
            for (JsonNode arrayItem : jsonNode) {
                updateJsonNode(arrayItem, attributeKey, attributeValue);
            }
        }
    }

    public static String takeScreenshot(){
        String base64Path = "";
        try {
            String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
            byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
            base64Path = Base64.getEncoder().encodeToString(buffer);
        } catch (Exception error) {
            logger.error("Error in Take Screenshot Function: " + error.getMessage());
        }
        return base64Path;
    }
}