package com.source.classes.freightforwarderrequests.invite;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FfrInviteTest extends BaseTest {

    @Test
    public void invite(){
        try {
            iFfrInvite.invite();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Invite Test function: {}", exception.getMessage());
            Assert.fail("Exception in FFR Invite Test Function: " + exception.getMessage());
        }
    }
}