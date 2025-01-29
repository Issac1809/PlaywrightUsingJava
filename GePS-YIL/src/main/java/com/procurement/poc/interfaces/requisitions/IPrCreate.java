package com.procurement.poc.interfaces.requisitions;

import java.util.List;
import java.util.Map;

public interface IPrCreate {
    void requesterLoginPRCreate();
    void createButton();
    void purchaseType();
    void title();
    void shipToYokogawa();
//    void project();
//    void wbs();
    void wbs(List<String> wbs);
    List<String> project();
//    void vendor();
    Map<String, String> vendor();
//    void rateContract();
    List<String> rateContract(Map<String, String> rateContractArray);
    void addLineRequisitionItemsCatalog(List<String> rateContractItems);
    void addLineRequisitionItemsNonCatalog();
    void incoterm();
    void shippingAddress();
    void billingType();
    void shippingMode();
    void quotationRequiredBy();
    void expectedPOIssue();
    void expectedDelivery();
    void buyerGroup();
    void checker();
    void plantCode();
//    void projectManager();
    void rohsCompliance();
    void inspectionRequired();
    void oiAndTpCurrency();
    void orderIntake();
    void targetPrice();
    void typeOfPurchase();
    void warrantyRequirements();
    void priceValidity();
    void liquidatedDamages();
    void addLineRequisitionItems();
    void createItemsFile();
    void importItems();
    void notes();
    void attachments();
    void prCreate();
}