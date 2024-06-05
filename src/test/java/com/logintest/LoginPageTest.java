package com.logintest;
import com.base.BaseTest;
import com.microsoft.playwright.Page;
import com.playwrightfactory.PlayWrightFactory;
import com.yokogawa.login.LoginPage;
import com.yokogawa.login.LoginPageInterface;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class LoginPageTest extends BaseTest{


//    @DataProvider
//    public Object[][] DataProvider(){
//        return new Object[][]{
//                {"requester@cormsquare.com"},
//                {"projectmanager@cormsquare.com"},
//                {"buyermanager@cormsquare.com"},
//                {"buyer@cormsquare.com"},
//                {"logisticsmanager@cormsquare.com"},
//                {"storemanager@cormsquare.com"}
//        };
//    }
//
//    @Test (dataProvider = "DataProvider")
//    public void LoginTestMethod(String id) {
//        try {
//            loginPageInterface.LoginMethod(id);
//        } catch (Exception error) {
//            System.out.println("Error :" + error);
//        }
//    }
    @Test
    public void LoginTestMethod() {
        try {
            loginPageInterface.LoginMethod();
        } catch (Exception error) {
            System.out.println("Error :" + error);
        }
    }


}