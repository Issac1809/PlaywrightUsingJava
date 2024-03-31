package org.yokogawaAsiaPrivateLimited;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import org.functions.Functions;
public class YokogawaAsiaPrivateLimited {
    public static void main(String[] args) throws InterruptedException {
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

        Functions functions = new Functions();
        functions.FunctionsForAllTypes(page);
    }
}