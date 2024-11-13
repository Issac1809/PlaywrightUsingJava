package com.procurement.freightforwarderrequests.invite;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FFRInviteTest extends BaseTest {

    @Test
    public void FFRInviteTestMethod(){
        try {
            ffrInvite.InviteMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}