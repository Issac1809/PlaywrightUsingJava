package com.utils.rpa.orderacknowledgement;
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

public class OA_Flow {

    static Logger logger;

//TODO Constructor
    public OA_Flow() {
        this.logger = LoggerUtil.getLogger(OA_Flow.class);
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
            String poReferenceId = jsonNode2.get("purchaseOrders").get("poReferenceId").asText();
            String poRevisionNumber = jsonNode2.get("purchaseOrders").get("poRevisionNumber").asText();
            String remoteOaDownloadFilePath = jsonNode.get("msa").get("remoteOaDownloadFilePath").asText();
            String remoteOaUploadFilePath = jsonNode.get("msa").get("remoteOaUploadFilePath").asText();
            String readOrderScheduleUrl = jsonNode.get("msa").get("readOrderScheduleUrl").asText();

//TODO Step 1: Connect to FTP and Download File
            OA_FTPHelper oaFtpHelper = new OA_FTPHelper(ftpClient);
            oaFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = oaFtpHelper.downloadOaFile(remoteOaDownloadFilePath, localPath, poReferenceId, poRevisionNumber);

//TODO Step 2: Update the OA File
            OA_ExcelHelper oaExcelHelper = new OA_ExcelHelper();
            oaExcelHelper.updateExcel(localExcelFilePath);

//TODO Step 3: Upload the OA File
            oaFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localExcelFilePath, remoteOaUploadFilePath);

//TODO Step 4: Call API to Update Status
            OA_APIHelper ivApiHelper = new OA_APIHelper(page);
            ivApiHelper.updateStatus(readOrderScheduleUrl);
        } catch (Exception exception) {
            logger.error("Exception in Order Acknowledgement Flow Function: {}", exception.getMessage());
        }
    }
}