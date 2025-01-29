package com.factory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import com.microsoft.playwright.*;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    Properties properties;

//TODO Constructor
    private PlaywrightFactory() {
    }

    public PlaywrightFactory(Playwright playwright) {
        this.playwright = playwright;
    }

    public Properties initializeProperties() {
        try {
            fileInputStream = new FileInputStream("./src/test/resources/config/Properties");
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException error) {
            throw new RuntimeException("Error in Initialize Property Function: " + error.getMessage());
        }
        return properties;
    }

    public Page initializePage(Properties properties) {
        try {
            String browserName = properties.getProperty("browserType").trim().toUpperCase();
            String url = properties.getProperty("appUrl");
            switch (browserName.toUpperCase()) {
                case "CHROMIUM":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "CHROME":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                    break;
                case "EDGE":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
                    break;
                case "SAFARI":
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "FIREFOX":
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                default:
                    System.out.println("--Enter Proper Browser Name--");
                    break;
            }
            browserContext = browser.newContext();
            page = browserContext.newPage();
            page.navigate(url);
        } catch (Exception error) {
            System.out.println("Error in Initialize Page Function: " + error.getMessage());
        }
        return page;
    }

    public void saveToPropertiesFile(String attributeKey, String attributeValue) {
        try {
            fileOutputStream = new FileOutputStream("./src/test/resources/config/Properties");
            properties.setProperty(attributeKey, attributeValue);
            properties.store(fileOutputStream, attributeKey);
            fileOutputStream.close();
        } catch (IOException error) {
            throw new RuntimeException("Error in Save To Properties File Function while storing the properties of: " + attributeKey + "&" + attributeValue + error.getMessage());
        }
    }
}