package com.yokogawa.requisition.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
public class PocCatalogPrCreate implements PrCreateCatalog {
    Login login = new LoginPage();
    public void RequesterLoginPRCreate(String emailID, Page page) throws InterruptedException {
        login.Login(emailID, page);
        Thread.sleep(1000);

//TODO Create Button
        page.locator("button.btn.btn-primary:has-text('Create')").click();
    }
    public void CatalogType(Page page){
        Locator prType = page.locator("#createPRModal > div > div > div.modal-body > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > a");
        prType.click();
    }
    public void Title(String Title, Page page) {
        Locator title = page.getByPlaceholder("Please Enter Title");
        title.fill(Title);
    }
    public void ShipToYokogawa(Page page) {
        page.getByLabel("Ship To Yokogawa").check();
    }
    public void Project(String Project, Page page) throws InterruptedException {
        page.locator("#select2-projectId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(Project);
        Locator getProject = page.locator("//li[contains(text(),'" + Project + "')]");
        getProject.click();
        Thread.sleep(1000);
    }
    public void WBS(String Wbs, Page page) {
        page.locator("#select2-wbsId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(Wbs);
        Locator getWbs = page.locator("//li[contains(text(),'" + Wbs + "')]");
        getWbs.click();
    }
    public void Vendor(String Vendor, Page page) throws InterruptedException {
        page.getByText("-- Select Vendor --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(Vendor);
        Locator getVendor = page.locator("//li[contains(text(),'" + Vendor + "')]");
        getVendor.click();
        Thread.sleep(1000);
    }
    public void RateContract(String RateContract, Page page) {
        page.getByLabel("-- Select Rate Contract --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(RateContract);
        Locator getRateContract = page.locator("//li[contains(text(),'" + RateContract + "')]");
        getRateContract.click();
    }
    public void ShippingAddress(Page page) {
        page.locator("#select2-shippingaddressId-container").click();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("C2400000 - Yokogawa")).click();
    }
    public void ShippingMode(String ShippingMode, Page page) {
        page.getByText("-- Select Shipping Mode --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(ShippingMode);
        Locator getShippingMode = page.locator("//li[contains(text(),'" + ShippingMode + "')]");
        getShippingMode.click();
    }
    public void ExpectedPOIssue(Page page) {
        Locator expectedPOIssue = page.locator("//*[@id=\"dates\"]/div[1]/input[2]");
        expectedPOIssue.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
    }
    public void ExpectedDelivery(Page page) {
        Locator expectedDelivery = page.locator("//*[@id=\"dates\"]/div[2]/input[2]");
        expectedDelivery.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
    }
    public void BuyerManager(String BuyerManager, Page page) {
        page.locator("#select2-buyerManagerId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(BuyerManager);
        Locator getBuyerManager = page.locator("//li[contains(text(),'" + BuyerManager + "')]");
        getBuyerManager.click();
    }
    public void ProjectManager(String ProjectManager, Page page) {
        page.getByLabel("-- Select Project Manager --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(ProjectManager);
        Locator getProjectManager = page.locator("//li[contains(text(),'" + ProjectManager + "')]");
        getProjectManager.click();
    }
    public void OrderIntake(int OrderIntake, Page page) {
        page.getByPlaceholder("Enter Order Intake Cost").click();
        page.getByPlaceholder("Enter Order Intake Cost").fill(String.valueOf(OrderIntake));
    }
    public void InspectionRequired(Page page) {
        page.locator("#inspectRequired").check();
    }
    public void AddLineRequisitionItems(String Category, String Item, int Quantity, Page page) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add line Requisition Items")).click();
//TODO Category
        page.locator("#select2-categoryId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(Category);
        Locator getCategory = page.locator("//li[contains(text(),'" + Category + "')]");
        getCategory.click();
//TODO Items
        page.getByLabel("-- Select Item --").click();
        Locator getItem = page.locator("//li[contains(text(),'" + Item + "')]");
        getItem.click();
//TODO Quantity
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("* Quantity :")).click();
        page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("* Quantity :")).fill(String.valueOf(Quantity));
//TODO Add Button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add").setExact(true)).click();
    }
    public void Notes(String Notes, Page page) {
        page.getByPlaceholder("Please Enter Notes").click();
        page.getByPlaceholder("Please Enter Notes").fill(Notes);
    }
    public void PRCreate(Page page) {

//TODO Create Draft Button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Draft")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes")).click();
    }
}