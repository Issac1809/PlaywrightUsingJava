package com.factory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.*;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

public class PlaywrightFactory {

    ObjectMapper objectMapper;
    JsonNode jsonNode;
    static Logger logger;
    Playwright playwright;

//TODO Constructor
    public PlaywrightFactory(ObjectMapper objectMapper, JsonNode jsonNode) {
        this.objectMapper = objectMapper;
        this.jsonNode = jsonNode;
        logger = LoggerUtil.getLogger(PlaywrightFactory.class);
    }

//TODO Thread Local to execute parallel tests
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

    public void initializeBrowser(JsonNode jsonNode) {
        try {
            String browserName = jsonNode.get("configSettings").get("browserName").asText().toUpperCase();
            boolean runConfig = jsonNode.get("configSettings").get("runHeadless").asBoolean();

            switch (browserName) {
                case "CHROMIUM":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(runConfig)));
                    break;
                case "CHROME":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(runConfig)));
                    break;
                case "EDGE":
                    localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(runConfig)));
                    break;
                case "SAFARI":
                    localBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(runConfig)));
                    break;
                case "FIREFOX":
                    localBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(runConfig)));
                    break;
                default:
                    System.out.println("--Enter Proper Browser Name--");
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Error initializing browser: " + exception.getMessage());
        }
    }

    public void initializeBrowserContext() {
        try {
            BrowserContext context = getBrowser().newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("test-output/videos"))
            );
            localBrowserContext.set(context);
        } catch (Exception exception) {
            System.out.println("Error initializing browser context: " + exception.getMessage());
        }
    }

    public Page initializePage(JsonNode jsonNode) {
        try {
            Page page = getBrowserContext().newPage();
            localPage.set(page);
            page.navigate(jsonNode.get("configSettings").get("appUrl").asText().trim());
        } catch (Exception exception) {
            System.out.println("Error initializing page: " + exception.getMessage());
        }
        return getPage();
    }

    public void startTracing(BrowserContext context, String traceFileName) {
        try {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
                    .setTitle("Test Trace")
                    .setName(traceFileName)
            );
        } catch (Exception exception) {
            logger.error("Error in Start Tracing Function: {}", exception.getMessage());
        }
    }

    public void stopTracing(BrowserContext context, String traceFileName) {
        Path traceDir = Paths.get("test-output/traces");
        try {
            Files.createDirectories(traceDir);
            context.tracing().stop(new Tracing.StopOptions().setPath(traceDir.resolve(traceFileName)));
        } catch (Exception exception) {
            logger.error("Error in Stop Tracing Function: {}", exception.getMessage());
        }
    }

    public void savePropertiesIntoJsonFile(String parentKey, String attributeKey, String attributeValue) {
        try {
            if (jsonNode.has(parentKey) && jsonNode.get(parentKey).isObject()) {
                ObjectNode parentNode = (ObjectNode) jsonNode.get(parentKey);
                parentNode.put(attributeKey, attributeValue);

//TODO try is used to close the file writer or the json file will be empty
                try (FileWriter fileWriter = new FileWriter("./src/test/resources/config/test-data.json")) {
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, jsonNode);
                }
            } else {
                logger.warn("Parent key '{}' not found or not an object in JSON", parentKey);
            }
        } catch (Exception exception) {
            logger.error("Error in Save Properties Into Json File Function: {}", exception.getMessage());
        }
    }

    public void savePorApproversIntoJsonFile(String parentKey, String attributeKey, List<String> attributeValue) {
        try {
            if (jsonNode.has(parentKey) && jsonNode.get(parentKey).isObject()) {
                ObjectNode parentNode = (ObjectNode) jsonNode.get(parentKey);

                //TODO Convert List<String> to JsonNode
                JsonNode listNode = objectMapper.valueToTree(attributeValue);
                parentNode.set(attributeKey, listNode);

//TODO try is used to close the file writer or the json file will be empty
                try (FileWriter fileWriter = new FileWriter("./src/test/resources/config/test-data.json")) {
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, jsonNode);
                }
            } else {
                logger.warn("Parent key '{}' not found or not an object in JSON", parentKey);
            }
        } catch (Exception exception) {
            logger.error("Error in Save Properties Into Json File Function: {}", exception.getMessage());
        }
    }

    public static String takeScreenshot(){
        String base64Path = "";
        try {
            String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
            byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
            base64Path = Base64.getEncoder().encodeToString(buffer);
        } catch (Exception exception) {
            logger.error("Error in Take Screenshot Function: {}", exception.getMessage());
        }
        return base64Path;
    }

    public void tearDown(Page page) {
        try {
            getPage().context().browser().close();
        } catch (Exception exception) {
            logger.error("Error in Tear Down Function: {}", exception.getMessage());
        }
    }
}