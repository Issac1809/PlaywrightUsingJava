package com.procurement.sales.interfaces.requisitions;

import java.util.List;
import java.util.Map;

public interface IPrCreate {

    void requesterLoginPRCreate();
    void createButton();
    void purchaseType();
    void title();
    void shipToYokogawa();
    void plantCode();
    void businessUnit();
    void salesReference();
    Map<String, String> vendor();
    List<String> rateContract(Map<String, String> rateContractArray);
    void addLineRequisitionItemsCatalog(List<String> rateContractItems);
    void addLineRequisitionItemsNonCatalog();
    void incoterm();
    void liquidatedDamages();
    void warrantyRequirements();
    void priceValidity();
    void shippingAddress();
    void shippingMode();
    void quotationRequiredBy();
    void expectedPOIssue();
    void expectedDelivery();
    void rohsCompliance();
    void inspectionRequired();
    void orderIntake();
    void targetPrice();
    void notes();
    void attachments();
    void prCreate();
}
