package com.poc.classes.freightforwarderrequests.invite;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FfrInviteTest extends BaseTest {

    @Test
    public void invite(){
        try {
            iFfrInvite.invite();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}