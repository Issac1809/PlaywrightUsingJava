package com.procurement.poc.classes.requisition.type;
import com.procurement.poc.interfaces.requisitions.IPrCreate;
import com.procurement.poc.interfaces.requisitions.IPrType;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PurchaseRequisitionTypeHandler implements IPrType {

    private IPrCreate iPrCreate;
    private Properties properties;
    private String type;

    private PurchaseRequisitionTypeHandler(){
    }

//TODO Constructor
    public PurchaseRequisitionTypeHandler(IPrCreate iPrCreate, Properties properties, String type){
        this.iPrCreate = iPrCreate;
        this.properties = properties;
        this.type = type;
    }

    public void processRequisitionType() {
        try {
//            String prType = properties.getProperty("purchaseType").toLowerCase();
            List<String> wbs;
            switch (type) {
                case "catalog":
                    iPrCreate.requesterLoginPRCreate();
                    iPrCreate.createButton();
                    iPrCreate.purchaseType();
                    iPrCreate.title();
                    iPrCreate.shipToYokogawa();
                    wbs = iPrCreate.project();
                    iPrCreate.wbs(wbs);
//                    iPrCreate.vendor();
//                    iPrCreate.rateContract();
                    Map<String, String> x = iPrCreate.vendor();
                    List<String> y = iPrCreate.rateContract(x);
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode();
                    iPrCreate.plantCode();
                    iPrCreate.checker();
                    iPrCreate.expectedPOIssue();
                    iPrCreate.expectedDelivery();
                    iPrCreate.buyerGroup();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice();
                    iPrCreate.inspectionRequired();
//                    iPrCreate.addLineRequisitionItems();
                    iPrCreate.addLineRequisitionItemsCatalog(y);
//                    iPrCreate.importItems();
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
                    wbs = iPrCreate.project();
                    iPrCreate.wbs(wbs);
                    iPrCreate.incoterm();
                    iPrCreate.liquidatedDamages();
                    iPrCreate.warrantyRequirements();
                    iPrCreate.priceValidity();
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode();
                    iPrCreate.plantCode();
                    iPrCreate.quotationRequiredBy();
                    iPrCreate.expectedPOIssue();
                    iPrCreate.expectedDelivery();
                    iPrCreate.buyerGroup();
                    iPrCreate.rohsCompliance();
                    iPrCreate.inspectionRequired();
                    iPrCreate.checker();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice();
                    iPrCreate.typeOfPurchase();
//                    iPrCreate.addLineRequisitionItems();
//                    iPrCreate.importItems();
                    iPrCreate.addLineRequisitionItemsNonCatalog();
                    iPrCreate.notes();
                    iPrCreate.attachments();
                    iPrCreate.prCreate();
                    break;
//                case "mh":
//                    iPrCreate.requesterLoginPRCreate();
//                    iPrCreate.createButton();
////                    iPrCreate.purchaseType();
//                    iPrCreate.title();
//                    iPrCreate.shipToYokogawa();
//                    wbs = iPrCreate.project();
//                    iPrCreate.wbs(wbs);
//                    iPrCreate.incoterm();
//                    iPrCreate.shippingAddress();
//                    iPrCreate.billingType();
//                    iPrCreate.vendor();
//                    iPrCreate.quotationRequiredBy();
//                    iPrCreate.expectedPOIssue();
//                    iPrCreate.expectedDelivery();
//                    iPrCreate.addLineRequisitionItems();
//                    iPrCreate.importItems();
//                    iPrCreate.notes();
//                    iPrCreate.attachments();
//                    iPrCreate.prCreate();
//                    break;
                default:
                    System.out.println("Enter Proper Purchase Type");
                    break;
            }
        } catch (Exception error) {
            System.out.println("Error encountered: " + error.getMessage());
        }
    }
}