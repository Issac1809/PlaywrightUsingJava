package com.utils.rpa.salesordersync;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.utils.LoggerUtil;
import com.utils.rpa.purchaseorderrequest.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class PR_List_Flow {

    static Logger logger;

    //TODO Constructor
    public PR_List_Flow() {
        this.logger = LoggerUtil.getLogger(MSA_Flow.class);
    }

    public static void main(String[] args) {
        try {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            FTPClient ftpClient = new FTPClient();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\My_Personal_Folder\\GePS-Testing\\GePS-HOPES_YEA\\src\\test\\resources\\config\\msa-config.json"));
            String ftpHost = jsonNode.get("msa").get("ftpHost").asText();
            int ftpPort = jsonNode.get("msa").get("ftpPort").asInt();
            String ftpUser = jsonNode.get("msa").get("ftpUser").asText();
            String ftpPassword = jsonNode.get("msa").get("ftpPassword").asText();
            String localPath = jsonNode.get("msa").get("localPath").asText();
            String remoteBwFolderPath = jsonNode.get("msa").get("remoteBwFolderPath").asText();
            String remoteBwFilePath = jsonNode.get("msa").get("remotePOFilePath").asText();
            String readPRListFileForCatalog = jsonNode.get("msa").get("readPRListFileForCatalog").asText();
            String readPRListFileForNonCatalog = jsonNode.get("msa").get("readPRListFileForNonCatalog").asText();


//TODO Step 1: Connect to FTP and Download File
            PR_List_FTPHelper prListFtpHelper = new PR_List_FTPHelper(ftpClient);
            prListFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = prListFtpHelper.downloadFile(remoteBwFolderPath, localPath);

//TODO Step 2: Update the Excel File
            MSA_ExcelHelper msaExcelHelper = new MSA_ExcelHelper();
            int poNumber = msaExcelHelper.updateExcel(localExcelFilePath);

//TODO Step 3: Update the PO PDF File
            MSA_PO_PDF_Helper msaPoPdfHelper = new MSA_PO_PDF_Helper();
            String localPoFilePath = msaPoPdfHelper.poPdfFileNameUpdate(poNumber, localPath);

//TODO Step 4: Upload the Updated File
//            prListFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localPoFilePath, localExcelFilePath, poNumber, remotePOFilePathPO, remoteHopesToGepsPathXLS);

//TODO Step 5: Call API to Update Status
            MSA_APIHelper msaApiHelper = new MSA_APIHelper(page);
            msaApiHelper.updateStatus(readPRListFileForCatalog);
            msaApiHelper.updateStatus(readPRListFileForNonCatalog);
        } catch (Exception exception) {
            logger.error("Exception in MSA File Automation Flow Function: {}", exception.getMessage());
        }
    }
}
