package com.yokogawa.yokogawaAsiaPrivateLimited;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.yokogawa.functions.Functions;
import java.io.IOException;
public class YokogawaAsiaPrivateLimited {
    public static void main(String[] args) throws InterruptedException, IOException {
        Playwright playwright = Playwright.create();
        LaunchOptions options = new LaunchOptions().setChannel("chrome").setHeadless(false);
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://yea-test.cormsquare.com/Identity/Account/Login");

//        BrowserContext browser1 = browser.newContext();
//        Page page1 = browser1.newPage();
//        page1.navigate("https://yea-test.cormsquare.com/Identity/Account/Login");

//        BrowserContext browser1 = browser.newContext();
//        Page page1 = browser1.newPage();
//        page1.navigate("https://dprocure-test.cormsquare.com/Identity/Account/Login");
//
//        BrowserContext browser2 = browser.newContext();
//        Page page2 = browser2.newPage();
//        page2.navigate("https://yef-test.cormsquare.com/Identity/Account/Login");
//
//        BrowserContext browser3 = browser.newContext();
//        Page page3 = browser3.newPage();
//        page3.navigate("https://ght-test.cormsquare.com/Identity/Account/Login");

//        GetCatalogProperties getCatalogProperties = new GetCatalogProperties();
//        getCatalogProperties.method();

        Functions functions = new Functions();
        functions.FunctionsForAllTypes(page);
    }
}