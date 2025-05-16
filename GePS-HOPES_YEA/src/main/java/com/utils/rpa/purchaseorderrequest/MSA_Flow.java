package com.utils.rpa.purchaseorderrequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Page;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class MSA_Flow {

    Logger logger;
    Page page;

//TODO Constructor
    private MSA_Flow() {
    }

    public MSA_Flow(Page page) {
        this.page = page;
        this.logger = LoggerUtil.getLogger(MSA_Flow.class);
    }

    public void msaFlow() {
        try {
            FTPClient ftpClient = new FTPClient();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("src\\test\\resources\\config\\msa-config.json"));
            JsonNode jsonNode2 = objectMapper.readTree(new File("src\\test\\resources\\config\\test-data.json"));
            String ftpHost = jsonNode.get("msa").get("ftpHost").asText();
            int ftpPort = jsonNode.get("msa").get("ftpPort").asInt();
            String ftpUser = jsonNode.get("msa").get("ftpUser").asText();
            String ftpPassword = jsonNode.get("msa").get("ftpPassword").asText();
            String remoteGepsToHopesFilePath = jsonNode.get("msa").get("remoteGepsToHopesFilePath").asText();
            String localPath = jsonNode.get("msa").get("localPath").asText();
            String remoteHopesToGepsPathXLS = jsonNode.get("msa").get("remoteHopesToGepsPath").asText();
            String remotePOFilePathPO = jsonNode.get("msa").get("remotePOFilePath").asText();
            String readPORAPIUrl = jsonNode.get("msa").get("readPORAPIUrl").asText();
            String generatePOAPIUrl = jsonNode.get("msa").get("generatePOAPIUrl").asText();
            int id = jsonNode2.get("purchaseOrderRequests").get("purchaseOrderRequestId").asInt();
            String porReferenceNumber = jsonNode2.get("purchaseOrderRequests").get("porReferenceNumber").asText();

//TODO Step 1: Connect to FTP and Download File
            MSA_FTPHelper msaFtpHelper = new MSA_FTPHelper(ftpClient);
            msaFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = msaFtpHelper.downloadFile(remoteGepsToHopesFilePath, localPath, porReferenceNumber, jsonNode2);

//TODO Step 2: Update the Excel File
            MSA_ExcelHelper msaExcelHelper = new MSA_ExcelHelper();
            int poNumber = msaExcelHelper.updateExcel(localExcelFilePath);

//TODO Step 3: Update the PO PDF File
            MSA_PO_PDF_Helper msaPoPdfHelper = new MSA_PO_PDF_Helper();
            String localPoFilePath = msaPoPdfHelper.poPdfFileNameUpdate(poNumber, localPath, jsonNode2);

//TODO Step 4: Upload the Updated File
            msaFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localPoFilePath, localExcelFilePath, poNumber, remotePOFilePathPO, remoteHopesToGepsPathXLS, jsonNode2);

//TODO Step 5: Call API to Update Status
            MSA_APIHelper msaApiHelper = new MSA_APIHelper(page);
            msaApiHelper.updateStatus(readPORAPIUrl, id);
            msaApiHelper.updateStatus(generatePOAPIUrl, id);
        } catch (Exception exception) {
            logger.error("Exception in MSA File Automation Flow Function: {}", exception.getMessage());
        }
    }
}