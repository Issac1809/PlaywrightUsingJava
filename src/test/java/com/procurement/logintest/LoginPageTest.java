package com.procurement.logintest;
import com.base.BaseTest;
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
            System.out.println(error);
        }
    }


}