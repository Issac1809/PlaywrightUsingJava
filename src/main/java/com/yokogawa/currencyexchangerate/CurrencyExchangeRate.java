package com.yokogawa.currencyexchangerate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CurrencyExchangeRate {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private CurrencyExchangeRate(){
    }

    public CurrencyExchangeRate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void findCurrency() {
        loginPageInterface.LoginMethod(properties.getProperty("AdminId"));
        page.pause();
        page.locator("//*[contains(text(), 'Settings')]").click();
//TODO CurrencyExchangeRate
        page.locator("//*[contains(text(), 'Currency Exchange Rate')]").click();
//TODO SearchBoxCurrencyCode
        String invoiceCurrencyCode = "INR SGD";
        Locator searchBox = page.locator("//input[contains(@type,'search')]");
        searchBox.click();
        searchBox.fill(invoiceCurrencyCode);
        List<String> getExchangeRate = new ArrayList<>();
        List<String> currencyExchangeTable = page.locator("#tableCurrencyExchangeRate tbody tr td:nth-child(n+2):nth-child(-n+4)").allTextContents();
//TODO Removing 1st and last td element => td:nth-child(n+2):nth-child(-n+4)
        for (int i = 0; i < currencyExchangeTable.size(); i++) {
            String na = currencyExchangeTable.get(i);
            getExchangeRate.add(na);
        }
    }
}