package com.poc.base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.procurementapi.get.GetApiTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected Playwright playwright;
    protected APIRequest apiRequest;
    protected APIRequestContext apiRequestContext;
    protected ObjectMapper objectMapper;
    protected GetApiTest getApiTest;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();

//TODO GetAPI
        getApiTest = new GetApiTest();
        objectMapper = new ObjectMapper();
    }

    @AfterClass
    public void teardown(){
        playwright.close();
    }
}