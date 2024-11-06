package com.procurement.freightforwarder.requote;
import com.interfaces.ffr.FFRQuotation;
import com.interfaces.ffr.FFRRequote;

public class FreightForwarderRequote implements FFRRequote {

    FFRQuotation ffrQuotation;

    private FreightForwarderRequote(){
    }

//TODO Constructor
    public FreightForwarderRequote(FFRQuotation ffrQuotation){
        this.ffrQuotation = ffrQuotation;
    }

    public void RequoteMethod(){
        try {
        ffrQuotation.QuoteMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}