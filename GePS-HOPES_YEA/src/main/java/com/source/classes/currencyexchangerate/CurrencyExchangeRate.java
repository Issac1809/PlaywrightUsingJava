package com.source.classes.currencyexchangerate;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.currencyexchangerate.LCurrencyExchangeRate.*;

public class CurrencyExchangeRate implements ICurrencyExchangeRate {

    Logger logger;
    JsonNode jsonNode;
    PlaywrightFactory playwrightFactory;
    ILogin iLogin;
    ILogout iLogout;
    Page page;

//TODO Constructor
    private CurrencyExchangeRate() {
    }

    public CurrencyExchangeRate(PlaywrightFactory playwrightFactory, ILogin iLogin, JsonNode jsonNode, ILogout iLogout) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.playwrightFactory = playwrightFactory;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(CurrencyExchangeRate.class);
    }

    public double findCurrency() {
        page = playwrightFactory.initializePage(jsonNode);
        String adminMailId = jsonNode.get("mailIds").get("adminEmail").asText();
        iLogin.performLogin(adminMailId, page);

        Locator setingsNavigationBarLocator = page.locator(SETTING_NAVIGATION_BAR_LOCATOR);
        setingsNavigationBarLocator.click();

//TODO CurrencyExchangeRate
        Locator currencyExchangeRateLocator = page.locator(CURRENCY_EXCHANGE_RATE_LOCATOR);
        currencyExchangeRateLocator.click();

//TODO SearchBoxCurrencyCode
        String fromCode = jsonNode.get("configSettings").get("currencyCode").asText();
        String invoiceCurrencyCode = fromCode + " " + "SGD";
        Locator searchBoxLocator = page.locator(SEARCH_BOX);
        searchBoxLocator.click();
        searchBoxLocator.fill(invoiceCurrencyCode);

        List<String> currencyExchangeTable = page.locator(LIST_CONTAINER).allTextContents();
//TODO Removing 1st and last td element => td:nth-child(n+2):nth-child(-n+4)

        double getCurrencyExchangeRate = Double.parseDouble(currencyExchangeTable.get(3));
        iLogout.performLogout(page);
        playwrightFactory.tearDown(page);
        return getCurrencyExchangeRate;
    }
}