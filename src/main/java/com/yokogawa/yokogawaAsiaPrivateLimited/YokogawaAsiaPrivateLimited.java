package com.yokogawa.yokogawaAsiaPrivateLimited;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.yokogawa.currencyexchangerate.CurrencyExchangeRate;
import com.yokogawa.functions.FunctionsCatalog;
import com.yokogawa.functions.FunctionsNonCatalog;
public class YokogawaAsiaPrivateLimited {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        LaunchOptions options = new LaunchOptions().setChannel("chrome").setHeadless(false);
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://yea-test.cormsquare.com/Identity/Account/Login");

//        BrowserContext browser1 = browser.newContext();
//        Page page = browser1.newPage();
//        page.navigate("https://dprocure-test.cormsquare.com/Identity/Account/Login");
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

        FunctionsCatalog functionsCatalog = new FunctionsCatalog();
        FunctionsNonCatalog functionsNonCatalog = new FunctionsNonCatalog();
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
//        functionsCatalog.FunctionsForCatalog(page);
        functionsNonCatalog.FunctionsForNonCatalog(page);
    }
}