package com.yokogawa.dispatchnotes.create;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
public class DispatchNoteCreate implements DispatchNoteCreateInterface {
        Login login = new LoginPage();
        Logout logout = new LogoutPage();
        public void DNCreate(String mailId, String sourceCountry, String destinationCountry, String packagetype, int grossweight, int netWeight, int volume, int quantity, Page page){
            login.Login(mailId, page);
            page.locator("//*[contains(text(), 'Purchase Orders')]").click();
            page.locator("//*[contains(text(), '"+ NonCatalogTitle +"')]").first().click();
            page.locator("#btnCreateDispatchNote").click();
            page.locator("#select2-sourceCountryId-container").click();
            page.locator(".select2-search__field").fill(sourceCountry);
            page.locator("//li[contains(text(), '"+ sourceCountry +"')]").click();
            page.locator("#select2-destinationCountryId-container").click();
            page.locator(".select2-search__field").fill(destinationCountry);
            page.locator("//li[contains(text(), '"+ destinationCountry +"')]").click();
            page.locator("#addDispatchNotePackages").click();
            page.locator("#select2-packagetypeId-container").click();
            page.locator(".select2-search__field").fill(packagetype);
            page.locator("//li[contains(text(), '"+ packagetype +"')]").click();
            page.locator("#grossWeight").fill(String.valueOf(grossweight));
            page.locator("#netWeight").fill(String.valueOf(netWeight));
            page.locator("#volume").fill(String.valueOf(volume));
            page.locator("#quantity").fill(String.valueOf(quantity));
            page.locator("#saveDispatchNotePackages").click();
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
            logout.Logout(page);
        }
}
