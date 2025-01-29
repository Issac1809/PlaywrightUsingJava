package com.ps.interfaces.requisitions;
import java.util.List;
import java.util.Map;

public interface IPrCreate {
    void requesterLoginPRCreate(String emailId);
    void createButton();
    void purchaseType(String purchaseType);
    void title(String purchaseType);
    void shipToYokogawa();
    List<String> project();
    void wbs(List<String> wbs);
    Map<String, String> vendor();
    List<String> rateContract(Map<String, String> rateContractArray);
    void incoterm();
    void shippingAddress();
    void shippingMode(String purchaseType);
    void quotationRequiredBy();
    void expectedPOIssue(String purchaseType);
    void expectedDelivery(String purchaseType);
    void buyerManager();
    void projectManager();
    void rohsCompliance();
    void inspectionRequired(String purchaseType);
    void oiAndTpCurrency();
    void orderIntake();
    void targetPrice(String purchaseType);
    void warrantyRequirements();
    void priceValidity();
    void liquidatedDamages();
    void tcasCompliance();
    void addLineRequisitionItemsCatalog(List<String> rateContract);
    void addLineRequisitionItemsNonCatalog();
    void notes();
    void attachments();
    int prCreate(String purchaseType);
}