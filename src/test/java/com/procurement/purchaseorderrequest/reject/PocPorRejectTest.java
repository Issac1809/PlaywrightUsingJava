package com.procurement.purchaseorderrequest.reject;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PocPorRejectTest extends BaseTest {

    @Test
    public void PocPorRejectTestMethod() throws InterruptedException {
        try {
        porReject.PorReject();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}