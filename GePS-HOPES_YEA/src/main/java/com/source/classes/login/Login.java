package com.source.classes.login;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.*;
import com.source.interfaces.login.ILogin;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import static com.constants.login.LLogin.*;

public class Login implements ILogin {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    String appUrl;
    private Login() {
    }

//TODO Constructor
    public Login(JsonNode jsonNode, Page page) {
        this.jsonNode = jsonNode;
        this.page = page;
        logger = LoggerUtil.getLogger(Login.class);
        this.appUrl = jsonNode.get("config").get("appUrl").asText();
    }

    public int performLogin(String emailId) {
        int status = 0;
        try {
            String password = jsonNode.get("config").get("loginPassword").asText();
            page.locator(EMAIL).fill(emailId);
            page.locator(PASSWORD).fill(password);
            page.locator(LOGIN_BUTTON).click();

            APIResponse apiResponse = page.request().fetch(appUrl + "/api/users/current");
            status = apiResponse.status();
        } catch (Exception error) {
            logger.error("Error in Perform Login Function: " + error.getMessage());
        }
        return status;
    }
}