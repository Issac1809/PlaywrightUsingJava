package com.factory;
import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlayWrightFactory {

    Playwright playwright;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    Properties properties;

//TODO Constructor
    public PlayWrightFactory() {
    }

//TODO Thread Local
    private static final ThreadLocal<Browser> localBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> localBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> localPage = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> localPlaywright = new ThreadLocal<>();

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
                localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                localBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "safari":
                localBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                localBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "edge":
                localBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;
            default:
                System.out.println("--Enter Proper Browser Name--");
                break;
        }
        localBrowserContext.set(getBrowser().newContext());
        localPage.set(getBrowser().newPage());
        getPage().navigate(properties.getProperty("url").trim());
        return getPage();
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

    public void savePropertiesToFile3(String getDnReferenceId) {
        try {
            fileOutputStream = new FileOutputStream("./src/test/resources/config/Properties");
            properties.setProperty("DispatchNoteReferenceId", getDnReferenceId);
            properties.store(fileOutputStream, "DispatchNoteReferenceId");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public void savePropertiesToFile4(String getWoReferenceId) {
        try {
            fileOutputStream = new FileOutputStream("./src/test/resources/config/Properties");
            properties.setProperty("WorkOrderReferenceId", getWoReferenceId);
            properties.store(fileOutputStream, "WorkOrderReferenceId");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public void TearDown(Page page) {
        try {
            getPage().context().browser().close();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }

    public static String takeScreenshot(){
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);
        return base64Path;
    }
}