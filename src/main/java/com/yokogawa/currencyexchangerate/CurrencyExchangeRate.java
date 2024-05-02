package com.yokogawa.currencyexchangerate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import java.util.ArrayList;
import java.util.List;
public class CurrencyExchangeRate {
    Login login = new LoginPage();
    public void findCurrency(String mailId, Page page) {
        login.Login(mailId, page);
        page.pause();
        page.locator("//*[contains(text(), 'Settings')]").click();
//TODO CurrencyExchangeRate
        page.locator("//*[contains(text(), 'Currency Exchange Rate')]").click();
//TODO EntriesDropDown
        Locator selectionStatus = page.locator(".form-select.form-select-sm");
        selectionStatus.click();
        selectionStatus.waitFor();
        selectionStatus.selectOption("100");
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
