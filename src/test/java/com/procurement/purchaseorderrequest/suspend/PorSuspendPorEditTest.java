package com.procurement.purchaseorderrequest.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendPorEditTest extends BaseTest {

    @Test
    public void PorSuspendPorEditTestMethod() throws InterruptedException{
        porSuspend.SuspendPorEdit();
    }
}