package com.source.classes.requestforquotations.regret;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegretTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void regret(){
        try {
            String type = "";
            iQuoRegret.regret(type);
        } catch (Exception exception) {
            logger.error("Exception in Quotation Regret Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Quotation Regret Test Function: " + exception.getMessage());
        }
    }
}