package com.procurement.requisition.create;
import com.interfaces.pr.PrCreate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class POCNonCatalogPrCreate implements PrCreate {

    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    String type;

    private POCNonCatalogPrCreate(){
    }

//TODO Constructor
    public POCNonCatalogPrCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void RequesterLoginPRCreate()  {
        try {
        String Email = properties.getProperty("EmailID");
        loginPageInterface.LoginMethod(Email);
        Thread.sleep(2000);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void CreateButton() {
        try {
        Locator createButton = page.locator("//*[@data-bs-toggle='modal']");
        createButton.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PurchaseType() {
        try {
        type = properties.getProperty("PurchaseType");
        Locator prType = page.locator("//a[@href='/Procurement/Requisitions/POC_"+ type +"_Create']");
        prType.click();
        } catch(Exception error) {
            System.out.println("What is the Error: " + error);
        }
    }

    public void Title() {
        try {
        switch (type){
            case "Catalog" :
                Locator catalogTitle = page.locator("#title");
                String catalogGetTitle = properties.getProperty("Title");
                catalogTitle.fill(catalogGetTitle + "-" +type);
                break;
            case "NonCatalog" :
                Locator nonCatalogTitle = page.locator("#title");
                String nonCatalogGetTitle = properties.getProperty("Title");
                nonCatalogTitle.fill(nonCatalogGetTitle + "-" +type);
                break;
            case "MH" :
                Locator mhTitle = page.locator("#title");
                String mhGetTitle = properties.getProperty("Title");
                mhTitle.fill(mhGetTitle + "-" +type);
                break;
            default:
                System.out.println("--Enter Proper Type--");
                break;
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShipToYokogawa() {
        String getShipToYokogawa = properties.getProperty("ShipToYokogawa").toLowerCase().trim();
        if (getShipToYokogawa.equals("no")) {
            page.locator("#shipToYokogawa").check();
        }
    }

    public void Project() {
        try {
        page.locator("#select2-projectId-container").click();
        String projectCode = properties.getProperty("Project");
        page.locator(".select2-search__field").fill(projectCode);
        Locator projectSelect = page.locator("//li[contains(text(),'" + projectCode + "')]");
        projectSelect.click();
        Thread.sleep(2000);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WBS() {
        try {
            if (type.equals("MH")) {
                page.locator("#select2-wbsId-container").click();
                List<String> wbsResults = page.locator("##select2-wbsId-results").allTextContents();
                for (int i = 0; i < wbsResults.size(); i++) {
                    String wbs = wbsResults.get(i);
                    if (wbs.endsWith("E")) {
                        page.locator("//li[contains(text(), " + wbs + ")]").first().click();
                        break;
                    }
                } if (type.equals("Catalog") || type.equals("NonCatalog")) {
                    page.locator("#select2-wbsId-container").click();
                    String wbsCode = properties.getProperty("Wbs");
                    page.locator(".select2-search__field").fill(wbsCode);
                    Locator wbsSelect = page.locator("//li[contains(text(),'" + wbsCode + "')]");
                    wbsSelect.click();
                }
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Vendor() {
        try {
            page.locator("#select2-vendorId-container").click();
            String vendor = properties.getProperty("Vendor");
            page.locator(".select2-search__field").fill(vendor);
            Locator vendorSelect = page.locator("//li[contains(text(),'" + vendor + "')]");
            vendorSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RateContract() {
        try {
            page.locator("#select2-rateContractId-container").click();
            String rateContract = properties.getProperty("RateContract");
            page.locator(".select2-search__field").fill(rateContract);
            Locator rateContractSelect = page.locator("//li[contains(text(),'" + rateContract + "')]");
            rateContractSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Incoterm() {
        try {
        page.locator("#select2-incoterm-container").click();
        String incoterm = properties.getProperty("Incoterm");
        page.locator(".select2-search__field").fill(incoterm);
        Locator incotermSelect = page.locator("//li[contains(text(),'" + incoterm + "')]");
        incotermSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShippingAddress() {
        try {
        page.locator("#select2-shippingaddressId-container").click();
        String shippingAddress = properties.getProperty("ShippingAddress");
        page.locator("//*[contains(text(), '"+ shippingAddress +"')]").last().click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ShippingMode() {
        try {
            String containerId = type.equals("Catalog") ? "#select2-shippingModeId-container" : "#select2-shippingmodeid-container";
            page.locator(containerId).click();
            String shippingMode = properties.getProperty("ShippingMode");
            page.locator(".select2-search__field").fill(shippingMode);
            Locator shippingModeSelect = page.locator("//li[contains(text(),'" + shippingMode + "')]");
            shippingModeSelect.click();
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
        expectedPOIssue.first().click();
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
        page.locator("#select2-buyerManagerId-container").click();
        String buyerManager = properties.getProperty("BuyerManager");
        page.locator(".select2-search__field").fill(buyerManager);
        Locator buyerManagerSelect = page.locator("//li[contains(text(),'" + buyerManager + "')]");
        buyerManagerSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void ProjectManager(){
        try {
        page.locator("#select2-projectManagerId-container").click();
        String projectManager = properties.getProperty("ProjectManager");
        page.locator(".select2-search__field").fill(projectManager);
        Locator projectManagerSelect = page.locator("//li[contains(text(),'" + projectManager + "')]");
        projectManagerSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RoHSCompliance(){
        String compliance = properties.getProperty("RoHSCompliance").toLowerCase().trim();
        if (compliance.equals("No")) {
            page.locator("#rohsnotcomplianceid").click();
        }
    }

    public void OiAndTpCurrency(){
        try {
            page.locator("#select2-oiTpCurrencyId-container").click();
            String currency = properties.getProperty("OiAndTpCurrency").toLowerCase().trim();
            page.locator(".select2-search__field").fill(currency);
            Locator currencySelect = page.locator("//li[contains(text(),'" + currency + "')]");
            currencySelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void OrderIntake(){
        try {
            String orderIntake = properties.getProperty("OrderIntake");
            page.locator("#orderintakeid").fill(orderIntake);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void TargetPrice(){
        try {
        String targetPrice = properties.getProperty("TargetPrice");
        page.locator("#orderintakeid").fill(targetPrice);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WarrantyRequirements(){
        try {
        page.locator("#select2-warrantyrequirementsid-container").click();
        String warrantyRequirements = properties.getProperty("WarrantyRequirement");
        page.locator(".select2-search__field").fill(warrantyRequirements);
        Locator warrantyRequirementSelect = page.locator("//li[contains(text(),'" + warrantyRequirements + "')]");
        warrantyRequirementSelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PriceValidity(){
        try {
        page.locator("#select2-pricevalidityid-container").click();
        String priceValidity = properties.getProperty("WarrantyRequirement");
        page.locator(".select2-search__field").fill(priceValidity);
        Locator priceValiditySelect = page.locator("//li[contains(text(),'" + priceValidity + "')]");
        priceValiditySelect.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void InspectionRequired() {
        try {
        boolean inspectionRequired = Boolean.parseBoolean(properties.getProperty("InspectionRequired"));
        if (inspectionRequired) {
        page.locator("#inspectrequired").click();
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void LiquidatedDamages(){
        String liquidatedDamages = properties.getProperty("LiquidatedDamages").toLowerCase().trim();
        if (liquidatedDamages.equals("special")) {
            page.locator("#isLDStandardNoId").click();
            page.locator("#liquidatedamageTextId").fill("Special Liquidated Damages");
        }
    }

    public void AddLineRequisitionItemsNonCatalogType() {
        try {
            String itemName = properties.getProperty("Items");
            String[] itemsArray = itemName.split(",");
            String quantity = properties.getProperty("Quantity");
            String[] quantityArray = quantity.split(",");

        for (int i = 0; i < itemsArray.length; i++){
            page.locator("#addLineRequisitionItems").click();
//TODO Items
            page.locator("#select2-itemid-container").click();
            page.locator(".select2-search__field").fill(itemsArray[i]);
            Locator itemSelection = page.locator("//li[contains(text(),'" + itemsArray[i] + "')]").first();
            itemSelection.click();
//TODO Quantities
            page.locator("#quantity").fill(quantityArray[i]);
//TODO Add Button
            page.locator("#saveRequisitionItem").click();
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Notes() {
        try {
        String notes = properties.getProperty("Notes");
        page.locator("#notes").fill(notes);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Attachments(){
        try {
//TODO Internal Attachment
            page.locator("#attachDocs").click();
            Locator internalFile = page.locator("#formFilePreupload");
            internalFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Internal Requisition Attachments.xlsx"));
            page.locator("#saveAttachments1").click();
//TODO External Attachment
            page.locator("#attachDocs").click();
            Locator externalFile = page.locator("#formFilePreupload");
            externalFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//External Requisition Attachments.xlsx"));
            page.locator("#radioInActive").click();
            page.locator("#saveAttachments1").click();
            page.locator("#attachmentSaveId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PRCreate() {
        try {
//TODO Create Draft Button
        page.locator("//*[contains(text(), 'Create Draft')]").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}