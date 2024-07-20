package com.procurement.requisition.create;
import com.interfaces.pr.IPrType;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.pr.IPocPrBase;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class PRType implements IPrType {

    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    String purchaseType;
    IPocPrBase iPocPrBase;

    private PRType(){
    }

//TODO Constructor
    public PRType(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, IPocPrBase iPocPrBase){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
        this.iPocPrBase = iPocPrBase;
    }

    public void RequesterLoginPRCreate() {
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

    public String PurchaseType() {
        try {
            page.pause();
            String type = properties.getProperty("PurchaseType").toLowerCase().trim();
            if (type.equals("catalog")) {
                Locator prType = page.locator("//a[@href='/Procurement/Requisitions/POC_Catalog_Create']");
                purchaseType = prType.textContent();
                purchaseType = purchaseType.replaceAll("\\s", "");
                prType.click();
            } else if (type.equals("noncatalog")) {
                Locator prType = page.locator("//a[@href='/Procurement/Requisitions/POC_NonCatalog_Create']");
                purchaseType = prType.textContent();
                purchaseType = purchaseType.replaceAll("\\s", "");
                prType.click();
            } else if (type.equals("mh")) {
                Locator prType = page.locator("//a[@href='/Procurement/Requisitions/POC_MH_Create']");
                purchaseType = prType.textContent();
                purchaseType = purchaseType.replaceAll("\\s", "");
                prType.click();
            }
        } catch(Exception error) {
            System.out.println("What is the Error: " + error);
        }
        return purchaseType;
    }

    public void Title() {
        try {
            Locator title = page.locator("#title");
            String getTitle = properties.getProperty("Title");
            if (purchaseType.equals("Catalog")) {
                title.fill(getTitle + purchaseType);
            } else if (purchaseType.equals("Non-Catalog")) {
                title.fill(getTitle + purchaseType);
            } else if (purchaseType.equals("MH")) {
                title.fill(getTitle + purchaseType);
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void TransactionType() throws InterruptedException {
        if (purchaseType.equals("Catalog")) {
            iPocPrBase.Project();
            iPocPrBase.WBS();
            iPocPrBase.Vendor();
            iPocPrBase.RateContract();
            iPocPrBase.ShippingAddress();
            iPocPrBase.ShippingMode();
            iPocPrBase.ExpectedPOIssue();
            iPocPrBase.ExpectedDelivery();
            iPocPrBase.BuyerManager();
            iPocPrBase.ProjectManager();
            iPocPrBase.OrderIntake();
            iPocPrBase.InspectionRequired();
            iPocPrBase.AddLineRequisitionItemsCatalogType();
            iPocPrBase.Notes();
            iPocPrBase.Attachments();
            iPocPrBase.PRCreate();
        } else if (purchaseType.equals("Non-Catalog")) {
            iPocPrBase.Project();
            iPocPrBase.WBS();
            iPocPrBase.Incoterm();
            iPocPrBase.ShippingAddress();
            iPocPrBase.ShippingMode();
            iPocPrBase.QuotationRequiredBy();
            iPocPrBase.ExpectedPOIssue();
            iPocPrBase.ExpectedDelivery();
            iPocPrBase.BuyerManager();
            iPocPrBase.ProjectManager();
            iPocPrBase.RoHSCompliance();
            iPocPrBase.InspectionRequired();
            iPocPrBase.OiAndTpCurrency();
            iPocPrBase.OrderIntake();
            iPocPrBase.TargetPrice();
            iPocPrBase.WarrantyRequirements();
            iPocPrBase.PriceValidity();
            iPocPrBase.LiquidatedDamages();
            iPocPrBase.AddLineRequisitionItemsNonCatalogType();
            iPocPrBase.Notes();
            iPocPrBase.Attachments();
            iPocPrBase.PRCreate();
        } else if (purchaseType.equals("MH")) {
            iPocPrBase.Project();
            iPocPrBase.WBS();
            iPocPrBase.ShippingAddress();
            iPocPrBase.BillingType();
            iPocPrBase.Vendor();
            iPocPrBase.BuyerManager();
            iPocPrBase.ProjectManager();
            iPocPrBase.AddLineRequisitionItemsMahHoursType();
            iPocPrBase.Notes();
            iPocPrBase.Attachments();
            iPocPrBase.PRCreate();
        }
    }
}
