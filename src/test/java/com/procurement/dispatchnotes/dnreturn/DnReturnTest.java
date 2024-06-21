package com.procurement.dispatchnotes.dnreturn;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class DnReturnTest extends BaseTest {

    @Test
    public void DnReturnTestMethod(){
        try {
         dnReturn.PocDnReturnMethod();
        }  catch (Exception error) {
            System.out.println(error);
        }
    }
}