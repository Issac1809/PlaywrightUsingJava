package com.source.classes.requisition.type;
import com.source.interfaces.requisitions.IPrCreate;
import com.source.interfaces.requisitions.IPrType;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PurchaseRequisitionTypeHandler implements IPrType {

    private IPrCreate iPrCreate;
    private Properties properties;
    Logger logger;

    private PurchaseRequisitionTypeHandler(){
    }

//TODO Constructor
    public PurchaseRequisitionTypeHandler(IPrCreate iPrCreate, Properties properties){
        this.logger = LoggerUtil.getLogger(PurchaseRequisitionTypeHandler.class);
        this.iPrCreate = iPrCreate;
        this.properties = properties;
    }

    public int processRequisitionType(String type, String purchaseType) {
        int status = 0;
        try {
            iPrCreate.requesterLoginPRCreate();
            iPrCreate.createButton();
            iPrCreate.purchaseType(type, purchaseType);
            iPrCreate.title(type, purchaseType);
            iPrCreate.shipToYokogawa();

            if(type.equalsIgnoreCase("Sales")){
                iPrCreate.company();
                iPrCreate.businessUnit();
                iPrCreate.salesReferenceId();
            } else {
                List<String> getWbsJson = iPrCreate.project();
                iPrCreate.wbs(getWbsJson);
            }

            switch (purchaseType.toLowerCase()) {
                case "catalog":
                    Map<String, String> rateContractArray = iPrCreate.vendor();
                    List<String> rateContractItems = iPrCreate.rateContract(rateContractArray);
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode(purchaseType);
                    iPrCreate.expectedPOIssue(purchaseType);
                    iPrCreate.expectedDelivery(purchaseType);
                    if(type.equalsIgnoreCase("PS")) {
                        iPrCreate.buyerManager();
                        iPrCreate.projectManager();
                    }
                    iPrCreate.inspectionRequired(purchaseType);
                    if(type.equalsIgnoreCase("PS")) {
                        iPrCreate.tcasCompliance();
                    }
                    iPrCreate.addLineRequisitionItemsCatalog(rateContractItems);
                    break;
                case "noncatalog":
                    iPrCreate.incoterm();
                    iPrCreate.shippingAddress();
                    iPrCreate.shippingMode(purchaseType);
                    iPrCreate.quotationRequiredBy();
                    iPrCreate.expectedPOIssue(purchaseType);
                    iPrCreate.expectedDelivery(purchaseType);
                    if(type.equalsIgnoreCase("PS")) {
                        iPrCreate.buyerManager();
                        iPrCreate.projectManager();
                    }
                    iPrCreate.rohsCompliance();
                    iPrCreate.inspectionRequired(purchaseType);
                    iPrCreate.oiAndTpCurrency();
                    iPrCreate.orderIntake();
                    iPrCreate.targetPrice(purchaseType);
                    iPrCreate.warrantyRequirements();
                    iPrCreate.priceValidity();
                    iPrCreate.liquidatedDamages();
                    if(type.equalsIgnoreCase("PS")) {
                        iPrCreate.tcasCompliance();
                    }
                    iPrCreate.addLineRequisitionItemsNonCatalog();
                    break;
                default:
                    System.out.println("Enter Proper Purchase Type");
                    break;
            }
            iPrCreate.notes();
            iPrCreate.attachments();
            status = iPrCreate.prCreate(purchaseType);
        } catch (Exception exception) {
            logger.error("Error in Purchase Type Function: {}", exception.getMessage());
        }
        return status;
    }
}