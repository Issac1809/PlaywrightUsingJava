package com.constants.invoices.woinvoice;

public class LInvCreate {

    public static final String INVOICE_NAVIGATION_BAR = "//*[contains(text(), 'Invoices')]";
    public static final String INVOICE_SELECT = ".btn.btn-primary";
    public static final String SELECT_COMPANY_LOCATOR = "#select2-companyId-container";
    public static final String COMPANY_RESULTS_LIST = "#select2-companyId-results";
    public static final String COMPANY_ID_2400 = "2400";
    public static final String COMPANY_ID_5K00 = "5K00";
    public static final String COMPANY_ID_2U00 = "2U00";
    public static final String COMPANY_ID_2W00 = "2W00";
    public static final String SELECT_TYPE = "#select2-typeId-container";
    public static final String SEARCH_FIELD = ".select2-search__field";
    public static final String PO_LOCATOR = "//li[contains(text(), 'Purchase Order')]";
    public static final String INVOICE_NUMBER_LOCATOR = "#invoiceNumber";
    public static final String DATE_PLACE_HOLDER = "Select Invoice Date";
    public static final String TODAY = "//span[@class='flatpickr-day today']";
    public static final String PO_NUMBER_INPUT = "#select2-poId-container";
    public static final String CURRENCY_CODE = "#currencyCode";
    public static final String GST_LOCATOR = "#USDgstId";
    public static final String DOM_TRIGGER = "document.getElementById('USDgstId').value";
    public static final String SGD_SUB_TOTAL_INPUT = "#SGDsubtotalInput";
    public static final String DOM_TRIGGER_SGD_INPUT = "el => { el.dispatchEvent(new Event('keyup', { bubbles: true })); }";
    public static final String DOCUMENT_ID = "#doc1";
    public static final String INVOICE_DOCUMENT_PATH = "C://Cormsquare//GePS-YEA//Downloads//Invoice Document.xlsx";
    public static final String CREATE_BUTTON = "#btnCreate";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";

    private LInvCreate(){
    }

    public static String get2400Id(){
        String id = "//li[contains(text(), '2400')]";
        return id;
    }

    public static String get5K00Id(){
        String id = "//li[contains(text(), '5K00')]";
        return id;
    }

    public static String get2U00Id(){
        String id = "//li[contains(text(), '2U00')]";
        return id;
    }

    public static String get2W00Id(){
        String id = "//li[contains(text(), '2W00')]";
        return id;
    }

    public static String getWoReferenceId(String woReferenceId){
        String id = "//li[contains(text(), '" + woReferenceId + "')]";
        return id;
    }
}