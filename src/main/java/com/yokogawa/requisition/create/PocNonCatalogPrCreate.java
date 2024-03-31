package org.requisition.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PocNonCatalogPrCreate implements PrCreateNonCatalog {
    public void RequesterLoginPRCreate(String EmailID, String Password, Page page) throws InterruptedException {
//TODO Login Page
        Locator mailId = page.getByPlaceholder("email@address.com");
        mailId.click();mailId.fill(EmailID);
        Locator password = page.getByPlaceholder("+ characters required");
        password.click();password.fill(Password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        Thread.sleep(2000);

//TODO Create Button
        page.locator("button.btn.btn-primary:has-text('Create')").click();
    }
    public void PRType(Page page) {
//TODO Type - Catalog
        Locator prType = page.locator("//*[@id=\"createPRModal\"]/div/div/div[2]/div/div/div/table/tbody/tr[2]/td[1]/a");
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
    public void Incoterm(String Incoterm, Page page) {
        page.locator("#select2-incoterm-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(Incoterm);
        Locator getIncoterm = page.locator("//li[contains(text(),'" + Incoterm + "')]");
        getIncoterm.click();
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
    public void QuotationRequiredBy(Page page) {
        Locator quotationRequiredBy = page.locator("//*[@id=\"dates\"]/div[1]/input[2]");
        quotationRequiredBy.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
    }
    public void ExpectedPOIssue(Page page) {
        Locator expectedPOIssue = page.locator("///*[@id=\"dates\"]/div[2]/input[2]");
        expectedPOIssue.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
    }
    public void ExpectedDelivery(Page page) {
        Locator expectedDelivery = page.locator("//*[@id=\"dates\"]/div[3]/input[2]");
        expectedDelivery.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
    }
    public void BuyerManager(String BuyerManager, Page page){
        page.locator("#select2-buyerManagerId-container").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(BuyerManager);
        Locator getBuyerManager = page.locator("//li[contains(text(),'" + BuyerManager + "')]");
        getBuyerManager.click();
    }
    public void ProjectManager(String ProjectManager, Page page){
        page.getByLabel("-- Select Project Manager --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(ProjectManager);
        Locator getProjectManager = page.locator("//li[contains(text(),'" + ProjectManager + "')]");
        getProjectManager.click();
    }
    public void OrderIntake(int OrderIntake, Page page){
        page.getByPlaceholder("Enter Order Intake Cost").click();
        page.getByPlaceholder("Enter Order Intake Cost").fill(String.valueOf(OrderIntake));
    }
    public void TargetPrice(int TargetPrice, Page page){
        page.getByPlaceholder("Enter Target Price").click();
        page.getByPlaceholder("Enter Target Price").fill(String.valueOf(TargetPrice));
    }
    public void WarrantyRequirements(String WarrantyRequirement, Page page){
        page.getByLabel("-- Select Warranty Requirements --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(WarrantyRequirement);
        Locator getWarrantyRequirement = page.locator("//li[contains(text(),'" + WarrantyRequirement + "')]");
        getWarrantyRequirement.click();
    }
    public void PriceValidity(String PriceValidity, Page page){
        page.getByLabel("-- Select Price Validity --").click();
        page.getByRole(AriaRole.SEARCHBOX).fill(PriceValidity);
        Locator getPriceValidity = page.locator("//li[contains(text(),'" + PriceValidity + "')]");
        getPriceValidity.click();
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
