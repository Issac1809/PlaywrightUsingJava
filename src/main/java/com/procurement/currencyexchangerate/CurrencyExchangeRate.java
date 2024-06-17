package com.procurement.currencyexchangerate;
import com.microsoft.playwright.*;
import com.factory.PlayWrightFactory;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class CurrencyExchangeRate {

    Properties properties;
    PlayWrightFactory playWrightFactory;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Page page;

//TODO Constructor
    private CurrencyExchangeRate() {
    }

    public CurrencyExchangeRate(PlayWrightFactory playWrightFactory, LoginPageInterface loginPageInterface, Properties properties, LogoutPageInterface logoutPageInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.playWrightFactory = playWrightFactory;
        this.logoutPageInterface = logoutPageInterface;
    }

    public double findCurrency() {
        page = playWrightFactory.initializeBrowser(properties);
        loginPageInterface.LoginMethod(properties.getProperty("AdminId"), page);
        page.locator("//*[contains(text(), 'Settings')]").click();
//TODO CurrencyExchangeRate
        page.locator("//*[contains(text(), 'Currency Exchange Rate')]").click();
//TODO SearchBoxCurrencyCode
        String fromCode = properties.getProperty("InvoiceCurrencyCode");
        String invoiceCurrencyCode = fromCode + " " + "SGD";
        Locator searchBox = page.locator("//input[contains(@type,'search')]");
        searchBox.click();
        searchBox.fill(invoiceCurrencyCode);
        List<String> currencyExchangeTable = page.locator("#tableCurrencyExchangeRate tbody tr td").allTextContents();
//TODO Removing 1st and last td element => td:nth-child(n+2):nth-child(-n+4)
            double getCurrencyExchangeRate = Double.parseDouble(currencyExchangeTable.get(3));
            logoutPageInterface.LogoutMethod(page);
            playWrightFactory.TearDown(page);
            return getCurrencyExchangeRate;
    }
}