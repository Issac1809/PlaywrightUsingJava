package com.procurement.requisition.create;
import com.interfaces.PrCreateNonCatalog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class PocNonCatalogPrCreate implements PrCreateNonCatalog {

    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;

    private PocNonCatalogPrCreate(){
    }

//TODO Constructor
    public PocNonCatalogPrCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void RequesterLoginPRCreate() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("EmailID"));
        Thread.sleep(2000);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void CreateButton() {
        try {
        Locator prType = page.locator("//*[@id=\"content\"]/div[1]/div[2]/div/div[2]/button");
        prType.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void NonCatalog(){
        try {
        Locator prType = page.locator("//*[@id=\"createPRModal\"]/div/div/div[2]/div/div/div/table/tbody/tr[2]/td[1]/a");
        prType.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Title() {
        try {
        Locator title = page.getByPlaceholder("Please Enter Title");
        title.fill(properties.getProperty("Title"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShipToYokogawa() {
        page.getByLabel("Ship To Yokogawa").check();
    }

    public void Project() throws InterruptedException {
        try {
        page.waitForSelector("#select2-projectId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("Project"));
        Locator getProject = page.locator("//li[contains(text(),'" + properties.getProperty("Project") + "')]");
        getProject.click();
        Thread.sleep(2000);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WBS() {
        try {
        page.waitForSelector("#select2-wbsId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("Wbs"));
        Locator getWbs = page.locator("//li[contains(text(),'" + properties.getProperty("Wbs") + "')]");
        getWbs.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Incoterm() {
        try {
        page.waitForSelector("#select2-incoterm-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("Incoterm"));
        Locator getIncoterm = page.locator("//li[contains(text(),'" + properties.getProperty("Incoterm") + "')]");
        getIncoterm.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShippingAddress() {
        try {
        page.waitForSelector("#select2-shippingaddressId-container").click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("C2400000 - Yokogawa")).click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShippingMode() {
        try {
        page.getByText("-- Select Shipping Mode --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("ShippingMode"));
        Locator getShippingMode = page.locator("//li[contains(text(),'" + properties.getProperty("ShippingMode") + "')]");
        getShippingMode.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationRequiredBy() {
        try {
        Locator quotationRequiredBy = page.locator("//*[@id=\"dates\"]/div[1]/input[2]");
        quotationRequiredBy.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ExpectedPOIssue() {
        try {
        Locator expectedPOIssue = page.locator("//*[@id=\"dates\"]/div[2]/input[2]");
        expectedPOIssue.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ExpectedDelivery() {
        try {
        Locator expectedDelivery = page.locator("//*[@id=\"dates\"]/div[3]/input[2]");
        expectedDelivery.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void BuyerManager(){
        try {
        page.waitForSelector("#select2-buyerManagerId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("BuyerManager"));
        Locator getBuyerManager = page.locator("//li[contains(text(),'" + properties.getProperty("BuyerManager") + "')]");
        getBuyerManager.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ProjectManager(){
        try {
        page.getByLabel("-- Select Project Manager --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("ProjectManager"));
        Locator getProjectManager = page.locator("//li[contains(text(),'" + properties.getProperty("ProjectManager") + "')]");
        getProjectManager.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void OrderIntake(){
        try {
        page.getByPlaceholder("Enter Order Intake Cost").click();
        page.getByPlaceholder("Enter Order Intake Cost").fill(String.valueOf(properties.getProperty("OrderIntake")));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void TargetPrice(){
        try {
        page.getByPlaceholder("Enter Target Price").click();
        page.getByPlaceholder("Enter Target Price").fill(String.valueOf(properties.getProperty("TargetPrice")));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WarrantyRequirements(){
        try {
        page.getByLabel("-- Select Warranty Requirements --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("WarrantyRequirement"));
        Locator getWarrantyRequirement = page.locator("//li[contains(text(),'" + properties.getProperty("WarrantyRequirement") + "')]");
        getWarrantyRequirement.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PriceValidity(){
        try {
        page.getByLabel("-- Select Price Validity --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(properties.getProperty("PriceValidity"));
        Locator getPriceValidity = page.locator("//li[contains(text(),'" + properties.getProperty("PriceValidity") + "')]");
        getPriceValidity.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void InspectionRequired() {
        try {
        page.waitForSelector("#inspectrequired").check();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void AddLineRequisitionItems() {
        try {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add line Requisition Items")).click();
//TODO Items
        page.waitForSelector("#select2-itemid-container").click();
        String itemName = properties.getProperty("Item");
        page.waitForSelector(".select2-search__field").fill(itemName);
        Locator getItem = page.locator("//li[contains(text(),'" + itemName + "')]").first();
        getItem.click();
//TODO Quantity
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("* Quantity :")).click();
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("* Quantity :")).fill(String.valueOf(properties.getProperty("Quantity")));
//TODO Add Button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add").setExact(true)).click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Notes() {
        try {
        page.getByPlaceholder("Please Enter Notes").click();
        page.getByPlaceholder("Please Enter Notes").fill(properties.getProperty("Notes"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PRCreate() {
        try {
//TODO Create Draft Button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Draft")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes")).click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}