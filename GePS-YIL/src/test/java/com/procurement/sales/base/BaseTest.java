package com.procurement.sales.base;

import com.factory.Login;
import com.factory.Logout;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.sales.classes.requisition.create.Create;
import com.procurement.sales.classes.requisition.edit.Edit;
import com.procurement.sales.classes.requisition.type.PurchaseRequisitionTypeHandler;
import com.procurement.sales.interfaces.requisitions.IPrCreate;
import com.procurement.sales.interfaces.requisitions.IPrEdit;
import com.procurement.sales.interfaces.requisitions.IPrType;
import com.procurement.sales.requisition.create.CreateTest;
import com.procurement.sales.requisition.edit.EditTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    protected PlaywrightFactory playwrightFactory;
    protected Properties properties;
    protected Page page;
    protected ObjectMapper objectmapper;
    protected ILogin iLogin;
    protected ILogout iLogout;
    protected IPrType iPrType;
    protected CreateTest createTest;
    protected IPrCreate iPrCreate;
    protected IPrEdit iPrEdit;
    protected EditTest editTest;


    public BaseTest() {
    }

    @BeforeTest
    @Parameters("purchaseType")
    public void setUp(@Optional("catalog") String purchaseType) {
        try {
            playwrightFactory = new PlaywrightFactory();
            properties = playwrightFactory.initializeProperties();
            page = playwrightFactory.initializePage(properties);
            objectmapper = new ObjectMapper();
            iLogin = new Login(properties, page);
            iLogout = new Logout(page);
            iPrCreate = new Create(iLogin, properties, page, iLogout,objectmapper, purchaseType);
//            createTest = new CreateTest();
            iPrType = new PurchaseRequisitionTypeHandler(iPrCreate, properties, purchaseType);
            iPrEdit = new Edit(iLogin, properties, page,iLogout);
//            editTest = new EditTest();
        } catch (Exception exception) {
            System.out.println("Error :" + exception);
        }
    }
}
