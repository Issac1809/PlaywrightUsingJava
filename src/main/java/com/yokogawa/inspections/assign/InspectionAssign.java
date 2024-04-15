package com.yokogawa.inspections.assign;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.inspections.create.InspectionCreateInterface;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;

public class InspectionAssign implements InspectionAssignInterface {
        Login login = new LoginPage();
        Logout logout = new LogoutPage();
        public void RequesterInspectionAssign(String mailId, String inspectorMailId, String poReferenceId, Page page){
            login.Login(mailId, page);
            page.locator("//*[contains(text(), 'Order Schedules')]").click();
            List<String> containerList = page.locator("#listContainer").allTextContents();
            for(String tr : containerList){
                if(tr.equals(poReferenceId)){
                    Locator details = page.locator("//*[contains(text(), ' Details ')]");
                    details.first().click();
                }
            }
            page.locator("#btnAssignInspector").click();
            page.locator("#select2-InspectionId-container").click();
            page.locator(".select2-search__field").fill(inspectorMailId);
            page.locator("//li[contains(text(), '"+ inspectorMailId +"')]").first().click();
            page.locator("#saveInspector").click();
            page.locator("#btnForCreateInspection").click();
            page.locator("#physicalInspectionNotReq").click();
            page.locator("#btnCreateInspection").click();
            page.locator(".btnCreateInspection").click();
            logout.Logout(page);
        }
}
