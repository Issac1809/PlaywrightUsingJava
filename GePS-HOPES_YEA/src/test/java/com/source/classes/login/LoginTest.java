package com.source.classes.login;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void login() {
        try {
            String emailId = jsonNode.get("requisition").get("requesterEmail").asText();
            int statusCode = iLogin.performLogin(emailId);

            Assert.assertEquals(statusCode, 200, "Login was not Successful");
        } catch (Exception exception) {
            logger.error("Error in Login Test Function: {}", exception.getMessage());
        }
    }
}