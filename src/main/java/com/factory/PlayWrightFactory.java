package com.factory;
import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlayWrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    Properties properties;

//TODO Constructor
    public PlayWrightFactory() {
        this.playwright = Playwright.create();
    }

    public Properties initializeProperties() {
        try {
            fileInputStream = new FileInputStream("./src/test/resources/config/Properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
        return properties;
    }

    public Page initializeBrowser(Properties properties) {
        String browserName = properties.getProperty("browser").trim();
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println("--Enter Proper Browser Name--");
                break;
        }
        browserContext = browser.newContext();
        page = browser.newPage();
        page.navigate(properties.getProperty("url").trim());
        return page;
    }

    public void savePropertiesToFile(String poReferenceId) {
        try {
            fileOutputStream = new FileOutputStream("./src/test/resources/config/Properties");
            properties.setProperty("PoReferenceId", poReferenceId);
            properties.store(fileOutputStream, "PoReferenceId");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public void savePropertiesToFile2(String getCurrencyCode) {
        try {

            fileOutputStream = new FileOutputStream("./src/test/resources/config/Properties");
            properties.setProperty("InvoiceCurrencyCode", getCurrencyCode);
            properties.store(fileOutputStream, "InvoiceCurrencyCode");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public void TearDown(Page page) {
        try {
            page.context().browser().close();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }

    public String takeScreenshot(){
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return path;
    }
}