package com.yokogawa.logout;
import com.microsoft.playwright.Page;
public class LogoutPage implements Logout{
    public void Logout(Page page){
        page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
        page.locator("//a[@onclick='user_logout()']").click();
    }
}