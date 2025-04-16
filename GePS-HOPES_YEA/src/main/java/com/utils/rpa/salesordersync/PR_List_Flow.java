package com.utils.rpa.salesordersync;
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

public class PR_List_Flow {

    static Logger logger;

//TODO Constructor
    public PR_List_Flow() {
        this.logger = LoggerUtil.getLogger(PR_List_Flow.class);
    }

    public void prListFlow() {
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
            String localSoFilePath = jsonNode.get("msa").get("localSoFilePath").asText();
            String remoteSoFolderPath = jsonNode.get("msa").get("remoteSoFilePath").asText();
            String remotePrListFilePath = jsonNode.get("msa").get("remoteSoFilePath").asText();
            String localPrListFilePath = jsonNode.get("msa").get("localPrListFilePath").asText();
            int itemCount = jsonNode2.get("requisition").get("requisitionItemCount").asInt();
            String transactionNumber = jsonNode2.get("requisition").get("salesTransactionNumber").asText();
            String readPRListFileForCatalog = jsonNode.get("msa").get("readPRListFileForCatalog").asText();
            String readPRListFileForNonCatalog = jsonNode.get("msa").get("readPRListFileForNonCatalog").asText();

//TODO Step 1: Create SO File
            PR_List_ExcelHelper prListExcelHelper = new PR_List_ExcelHelper();
            int soNumber = prListExcelHelper.createSOFile(itemCount, localSoFilePath);

//TODO Step 2: Connect to FTP and Download File
            PR_List_FTPHelper prListFtpHelper = new PR_List_FTPHelper(ftpClient);
            prListFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = prListFtpHelper.downloadPrListFile(remotePrListFilePath, localPath);

//TODO Step 3: Update the PR List Excel File
            prListExcelHelper.updatePRListExcel(localExcelFilePath, soNumber, itemCount, transactionNumber);

//TODO Step 4: Upload the SO and PR List Files
            prListFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localSoFilePath, remoteSoFolderPath, localPrListFilePath, remotePrListFilePath);

//TODO Step 5: Call API to Update Status
            PR_List_APIHelper prListApiHelper = new PR_List_APIHelper(page);
            prListApiHelper.updateStatus(readPRListFileForCatalog);
            prListApiHelper.updateStatus(readPRListFileForNonCatalog);
        } catch (Exception exception) {
            logger.error("Exception in PR List and SO File Automation Flow Function: {}", exception.getMessage());
        }
    }
}