package com.source.classes.freightforwarderrequests.requote;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class FfrRequoteTest extends BaseTest {

    @Test
    public void requote(){
        try {
            iFfrRequote.requote();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Requote Test function: {}", exception.getMessage());
        }
    }
}