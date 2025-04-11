package com.utils.rpa.invoiceverification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class IV_Flow {

    static Logger logger;

    //TODO Constructor
    public IV_Flow() {
        this.logger = LoggerUtil.getLogger(IV_Flow.class);
    }

    public static void main(String[] args) {
        try {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            FTPClient ftpClient = new FTPClient();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\My_Personal_Folder\\GePS-Testing\\GePS-HOPES_YEA\\src\\test\\resources\\config\\msa-config.json"));
            JsonNode jsonNode2 = objectMapper.readTree(new File("C:\\My_Personal_Folder\\GePS-Testing\\GePS-HOPES_YEA\\src\\test\\resources\\config\\test-data.json"));
            String ftpHost = jsonNode.get("msa").get("ftpHost").asText();
            int ftpPort = jsonNode.get("msa").get("ftpPort").asInt();
            String ftpUser = jsonNode.get("msa").get("ftpUser").asText();
            String ftpPassword = jsonNode.get("msa").get("ftpPassword").asText();
            String localPath = jsonNode.get("msa").get("localPath").asText();
            String vendorReferenceId = jsonNode2.get("invoices").get("vendorReferenceId").asText();
            String poReferenceId = jsonNode2.get("purchaseOrders").get("poReferenceId").asText();
            String invoiceReferenceId = jsonNode2.get("invoices").get("invoiceReferenceId").asText();
            String remoteIvDownloadFilePath = jsonNode.get("msa").get("remoteIvDownloadFilePath").asText();
            String remoteIvUploadFilePath = jsonNode.get("msa").get("remoteIvUploadFilePath").asText();
            String readIvNumberUrl = jsonNode.get("msa").get("readIvNumberUrl").asText();

//TODO Step 1: Connect to FTP and Download File
            IV_FTPHelper ivFtpHelper = new IV_FTPHelper(ftpClient);
            ivFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = ivFtpHelper.downloadIvFile(remoteIvDownloadFilePath, localPath, vendorReferenceId, poReferenceId, invoiceReferenceId);

//TODO Step 2: Update the IV File
            IV_ExcelHelper ivExcelHelper = new IV_ExcelHelper();
            ivExcelHelper.updateExcel(localExcelFilePath);

//TODO Step 3: Upload the IV File
            ivFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localPath, remoteIvUploadFilePath);

//TODO Step 5: Call API to Update Status
            IV_APIHelper ivApiHelper = new IV_APIHelper(page);
            ivApiHelper.updateStatus(readIvNumberUrl);
        } catch (Exception exception) {
            logger.error("Exception in PR List and SO File Automation Flow Function: {}", exception.getMessage());
        }
    }
}