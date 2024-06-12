package com.procurement.logout;
import com.interfaces.LogoutPageInterface;
import com.microsoft.playwright.Page;

public class LogoutPage implements LogoutPageInterface {

    Page page;

    private LogoutPage(){
    }

//TODO Constructor
    public LogoutPage(Page page){
        this.page = page;
    }

    public void LogoutMethod(){
        page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
        page.locator("//a[@onclick='user_logout()']").click();
    }

    public void LogoutMethod(Page page){
        page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
        page.locator("//a[@onclick='user_logout()']").click();
    }
}