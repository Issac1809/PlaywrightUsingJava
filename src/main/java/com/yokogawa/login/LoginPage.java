package com.yokogawa.login;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.util.Properties;

public class LoginPage implements LoginPageInterface {

    Page page;
    Properties properties;

    private LoginPage(){
    }

//TODO Constructor
    public LoginPage(Properties properties, Page page){
        this.properties = properties;
        this.page = page;
    }

    public void LoginMethod() {
        Locator mailId = page.getByPlaceholder("email@address.com");
        mailId.click();
        mailId.fill(properties.getProperty("EmailID"));
        Locator password = page.getByPlaceholder("+ characters required");
        password.click();
        password.fill(properties.getProperty("Password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    public void LoginMethod(String getMailId) {
        Locator mailId = page.getByPlaceholder("email@address.com");
        mailId.click();
        mailId.fill(getMailId);
        Locator password = page.getByPlaceholder("+ characters required");
        password.click();
        password.fill(properties.getProperty("Password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    public void LoginMethod(String getMailId, Page page) {
        Locator mailId = page.getByPlaceholder("email@address.com");
        mailId.click();
        mailId.fill(getMailId);
        Locator password = page.getByPlaceholder("+ characters required");
        password.click();
        password.fill(properties.getProperty("Password"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }
}