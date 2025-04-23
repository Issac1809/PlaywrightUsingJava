package com.source.classes.requestforquotations.requote;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RequoteTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void requote(String type) {
        try {
            int status = iQuoRequote.requote(type);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Quotation Requote Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Quotation Requote Test Function: " + exception.getMessage());
        }
    }
}