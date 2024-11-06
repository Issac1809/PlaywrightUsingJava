package com.procurement.purchaseorderrequest.suspend;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.por.PorCreateNonCatalog;
import com.interfaces.por.PorEdit;
import com.interfaces.por.PorSuspend;
import com.interfaces.rfq.CommercialEvaluationInterface;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class PocPorSuspend implements PorSuspend {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;
    PorEdit porEdit;
    CommercialEvaluationInterface commercialEvaluationInterface;
    PorCreateNonCatalog porCreateNonCatalog;

    private PocPorSuspend(){
    }

    //TODO Constructor
    public PocPorSuspend(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PorEdit porEdit,
                         CommercialEvaluationInterface commercialEvaluationInterface, PorCreateNonCatalog porCreateNonCatalog){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.porEdit = porEdit;
        this.commercialEvaluationInterface = commercialEvaluationInterface;
        this.porCreateNonCatalog = porCreateNonCatalog;
    }

    public void SuspendPorEdit() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnToSuspendPOR").click();
        page.locator(".bootbox-input").fill("Suspended");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        porEdit.PorEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void SuspendRfqEdit(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnToSuspendPOR").click();
        page.locator(".bootbox-input").fill("Suspended");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        commercialEvaluationInterface.CommercialEvaluationButton();
        porCreateNonCatalog.BuyerPORCreate();
        porCreateNonCatalog.Justification();
        porCreateNonCatalog.TaxCode();
        porCreateNonCatalog.PORNotes();
        porCreateNonCatalog.PORCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}