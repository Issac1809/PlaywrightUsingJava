package com.poc.interfaces.invoices.poinvoices;

public interface IInvCreate {

    void create();
    double gst();
    void ifSgdEnable(double finalGSTPercentage);
}