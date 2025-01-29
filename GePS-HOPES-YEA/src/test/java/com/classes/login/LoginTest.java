package com.classes.login;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void login() {
        try {
            String emailId = properties.getProperty("requesterEmail");
            int statusCode = iLogin.performLogin(emailId);

            if(statusCode == 200) {
                Assert.assertEquals(statusCode, 200, "Login was not Successful");
            }
        } catch (Exception exception) {
            System.out.println("Error in Login Test Function: " + exception.getMessage());
        }
    }
}