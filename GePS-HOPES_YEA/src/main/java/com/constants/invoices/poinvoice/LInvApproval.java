package com.constants.invoices.poinvoice;

public class LInvApproval {

    public static final String INVOICE_NAVIGATION_BAR = "//a[@href='/OrderProcessing/Invoices']";
    public static final String LIST_CONTAINER = "#listContainer tr td";
    public static final String INVOICE_SELECT = ".btn btn-sm btn-link p-0 text-primary";
    public static final String APPROVE_BUTTON = "#btnApprove";
    public static final String ACCEPT_BUTTON = ".bootbox-accept";
    public static final String UPDATE_FINANCE_FIELDS = "#btnfinfields";
    public static final String ACCOUNT_TYPE = "#select2-accountTypeId-container";
    public static final String DOCUMENT_TYPE = "#select2-documentTypeId-container";
    public static final String GENERAL_LEDGER = "#select2-generalLedgerId-container";
    public static final String SAVE_FINANCE_FIELDS = "#updateFinanceFieldsDetails";
    public static final String ACCOUNT_TYPE_DATA = "//li[contains(text(), 'A-Assets')]";
    public static final String DOCUMENT_TYPE_DATA = "//li[contains(text(), 'document1-doc')]";
    public static final String GENERAL_LEDGER_DATA = "//li[contains(text(), 'A-Down Payments')]";
    public static final String BANK_ACCOUNT = "#select2-bankAccountId-container";
    public static final String BANK_ACCOUNT_DATA = "//li[contains(text(), 'Cash on hand-11110000')]";
    public static final String TEXT = "#text";
    public static final String TAX_CODE = "#select2-taxCodeId-container";
    public static final String TAX_CODE_DATA = "//li[contains(text(), 'Permanent Account Numbers-7% Foreign currency standard rate purchases')]";

//TODO Constructor
    private LInvApproval(){
    }
}