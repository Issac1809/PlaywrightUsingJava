package com.ps.classes.login;
import java.util.Properties;
import com.microsoft.playwright.*;
import com.ps.interfaces.login.ILogin;
import static com.constants.login.LLogin.*;

public class Login implements ILogin {

    Playwright playwright;
    Page page;
    Properties properties;

    private Login() {
    }

//TODO Constructor
    public Login(Playwright playwright, Properties properties, Page page) {
        this.playwright = playwright;
        this.properties = properties;
        this.page = page;
    }

    public int performLogin(String emailId) {
        int status = 0;
        try {
            String password = properties.getProperty("loginPassword");

            page.locator(EMAIL).fill(emailId);
            page.locator(PASSWORD).fill(password);
            page.locator(LOGIN_BUTTON).click();

            APIRequestContext apiRequestContext = playwright.request().newContext(new APIRequest.NewContextOptions().setBaseURL("https://geps_hopes_yea.cormsquare.com"));
            APIResponse apiResponse = apiRequestContext.get("/api/users/current");
            status = apiResponse.status();
        } catch (Exception exception) {
            System.out.println("Error in Perform Login Function: " + exception.getMessage());
        }
        return status;
    }
}