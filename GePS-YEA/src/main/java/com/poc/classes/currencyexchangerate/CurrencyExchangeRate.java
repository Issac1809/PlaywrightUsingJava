package com.poc.classes.currencyexchangerate;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.*;
import com.poc.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.currencyexchangerate.LCurrencyExchangeRate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class CurrencyExchangeRate implements ICurrencyExchangeRate {

    Properties properties;
    PlaywrightFactory playwrightFactory;
    ILogin iLogin;
    ILogout iLogout;
    Page page;

//TODO Constructor
    private CurrencyExchangeRate() {
    }

    public CurrencyExchangeRate(PlaywrightFactory playwrightFactory, ILogin iLogin, Properties properties, ILogout iLogout) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.playwrightFactory = playwrightFactory;
        this.iLogout = iLogout;
    }

    public double findCurrency() {
        page = playwrightFactory.initializePage(properties);
        String adminMailId = properties.getProperty("AdminId");
        iLogin.performLogin(adminMailId, page);

        Locator setingsNavigationBarLocator = page.locator(SETTING_NAVIGATION_BAR_LOCATOR);
        waitForLocator(setingsNavigationBarLocator);
        setingsNavigationBarLocator.click();

//TODO CurrencyExchangeRate
        Locator currencyExchangeRateLocator = page.locator(CURRENCY_EXCHANGE_RATE_LOCATOR);
        waitForLocator(currencyExchangeRateLocator);
        currencyExchangeRateLocator.click();

//TODO SearchBoxCurrencyCode
        String fromCode = properties.getProperty("InvoiceCurrencyCode");
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