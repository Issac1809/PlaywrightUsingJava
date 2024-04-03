package com.yokogawa.login;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.yokogawa.variables.VariablesForCatalog;
public class LoginPage implements Login{
    public void Login(String EmailID, Page page){
        Locator mailId = page.getByPlaceholder("email@address.com");
        mailId.click();
        mailId.fill(EmailID);
        Locator password = page.getByPlaceholder("+ characters required");
        password.click();
        password.fill(VariablesForCatalog.Password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }
}
