package com.procurement.sales.classes.requisition.type;

import com.procurement.sales.interfaces.requisitions.IPrCreate;
import com.procurement.sales.interfaces.requisitions.IPrType;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PurchaseRequisitionTypeHandler implements IPrType {

    private IPrCreate iPrCreate;
    private Properties properties;
    private String type;

    private PurchaseRequisitionTypeHandler(){
    }

    public PurchaseRequisitionTypeHandler(IPrCreate iPrCreate, Properties properties, String type){
        this.iPrCreate = iPrCreate;
        this.properties = properties;
        this.type = type;
    }

    public void processRequisitionType() {
        try {
            switch (type) {
                case "catalog":
                    iPrCreate.requesterLoginPRCreate();
                    iPrCreate.createButton();
                    iPrCreate.purchaseType();
                    iPrCreate.title();
                    iPrCreate.shipToYokogawa();
                    iPrCreate.plantCode();
                    iPrCreate.businessUnit();
                    iPrCreate.salesReference();
                    Map<String, String> x = iPrCreate.vendor();
                    List<String> y = iPrCreate.rateContract(x);
                    iPrCreate.incoterm();
                    iPrCreate.warrantyRequirements();
                    iPrCreate.priceValidity();
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode();
                    iPrCreate.expectedPOIssue();
                    iPrCreate.expectedDelivery();
                    iPrCreate.rohsCompliance();
                    iPrCreate.inspectionRequired();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice();
                    iPrCreate.addLineRequisitionItemsCatalog(y);
                    iPrCreate.notes();
                    iPrCreate.attachments();
                    iPrCreate.prCreate();
                    break;
                case "noncatalog":
                    iPrCreate.requesterLoginPRCreate();
                    iPrCreate.createButton();
                    iPrCreate.purchaseType();
                    iPrCreate.title();
                    iPrCreate.shipToYokogawa();
                    iPrCreate.plantCode();
                    iPrCreate.businessUnit();
                    iPrCreate.salesReference();
                    iPrCreate.incoterm();
                    iPrCreate.liquidatedDamages();
                    iPrCreate.warrantyRequirements();
                    iPrCreate.priceValidity();
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode();
                    iPrCreate.quotationRequiredBy();
                    iPrCreate.expectedPOIssue();
                    iPrCreate.expectedDelivery();
                    iPrCreate.rohsCompliance();
                    iPrCreate.inspectionRequired();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice();
                    iPrCreate.addLineRequisitionItemsNonCatalog();
                    iPrCreate.notes();
                    iPrCreate.attachments();
                    iPrCreate.prCreate();
                    break;
                default:
                    System.out.println("Enter Proper Purchase Type");
                    break;
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }
}
