package com.ps.classes.requisition.type;
import com.ps.interfaces.requisitions.IPrCreate;
import com.ps.interfaces.requisitions.IPrType;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PurchaseRequisitionTypeHandler implements IPrType {

    private IPrCreate iPrCreate;
    private Properties properties;

    private PurchaseRequisitionTypeHandler(){
    }

//TODO Constructor
    public PurchaseRequisitionTypeHandler(IPrCreate iPrCreate, Properties properties){
        this.iPrCreate = iPrCreate;
        this.properties = properties;
    }

    public int processRequisitionType(String purchaseType) {
        int status = 0;
        try {
            String requesterEmail = properties.getProperty("requesterEmail");

            iPrCreate.requesterLoginPRCreate(requesterEmail);
            iPrCreate.createButton();
            iPrCreate.purchaseType(purchaseType);
            iPrCreate.title(purchaseType);
            iPrCreate.shipToYokogawa();
            List<String> getWbsJson = iPrCreate.project();
            iPrCreate.wbs(getWbsJson);

            switch (purchaseType.toLowerCase()) {
                case "catalog":
                    Map<String, String> rateContractArray = iPrCreate.vendor();
                    List<String> rateContractItems = iPrCreate.rateContract(rateContractArray);
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode(purchaseType);
                    iPrCreate.expectedPOIssue(purchaseType);
                    iPrCreate.expectedDelivery(purchaseType);
                    iPrCreate.buyerManager();
                    iPrCreate.projectManager();
                    iPrCreate.inspectionRequired(purchaseType);
                    iPrCreate.tcasCompliance();
                    iPrCreate.addLineRequisitionItemsCatalog(rateContractItems);
                    break;
                case "noncatalog":
                    iPrCreate.incoterm();
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode(purchaseType);
                    iPrCreate.quotationRequiredBy();
                    iPrCreate.expectedPOIssue(purchaseType);
                    iPrCreate.expectedDelivery(purchaseType);
                    iPrCreate.buyerManager();
                    iPrCreate.projectManager();
                    iPrCreate.rohsCompliance();
                    iPrCreate.inspectionRequired(purchaseType);
                    iPrCreate.oiAndTpCurrency();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice(purchaseType);
                    iPrCreate.warrantyRequirements();
                    iPrCreate.priceValidity();
                    iPrCreate.liquidatedDamages();
                    iPrCreate.tcasCompliance();
                    iPrCreate.addLineRequisitionItemsNonCatalog();
                    break;
                default:
                    System.out.println("Enter Proper Purchase Type");
                    break;
            }
            iPrCreate.notes();
            iPrCreate.attachments();
            status = iPrCreate.prCreate(purchaseType);
        } catch (Exception error) {
            System.out.println("Error in Purchase Type Function: " + error.getMessage());
        }
        return status;
    }
}