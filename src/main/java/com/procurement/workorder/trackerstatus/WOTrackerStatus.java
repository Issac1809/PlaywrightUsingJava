package com.procurement.workorder.trackerstatus;
import com.factory.PlayWrightFactory;
import com.interfaces.WOTrackerStatusInterface;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class WOTrackerStatus implements WOTrackerStatusInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    PlayWrightFactory playWrightFactory;

    private WOTrackerStatus(){
    }

//TODO Constructor
    public WOTrackerStatus(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PlayWrightFactory playWrightFactory){
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
        this.playWrightFactory = playWrightFactory;
    }

    public void VendorTrackerStatus() {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.waitForSelector("//*[contains(text(), 'Work Orders')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        String woReferenceId = page.waitForSelector("#referenceId").innerText();
        playWrightFactory.savePropertiesToFile4(woReferenceId);
        String[] status = {"Material_Pick_Up", "ETD", "Arrival_Notification", "Import_Clearance", "Out_for_Delivery", "Delivery_Completed"};
        for(int i = 0; i < status.length; i++) {
            page.waitForSelector(".form-control.form-control-sm.flatpickr-custom.form-control.input").click();
            Locator today = page.locator("//span[@class='flatpickr-day today']").first();
            today.click();
            page.locator("#select2-statusId-container").last().click();
            page.waitForSelector("//li[contains(text(), '"+ status[i] +"')]").click();
            page.waitForSelector("#btnSubmit").click();
            page.waitForSelector(".bootbox-accept").click();
        }
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}