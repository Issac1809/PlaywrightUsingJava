package com.utils.rpa.purchaseorderrequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class MSA_Flow {

    static Logger logger;

//TODO Constructor
    public MSA_Flow() {
        this.logger = LoggerUtil.getLogger(MSA_Flow.class);
    }

    public static void main(String[] args) {
        try {
            FTPClient ftpClient = new FTPClient();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\My_Personal_Folder\\GePS-Testing\\GePS-HOPES_YEA\\src\\test\\resources\\config\\msa-config.json"));
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


//TODO Step 1: Connect to FTP and Download File
            MSA_FTPHelper msaFtpHelper = new MSA_FTPHelper(ftpClient);
            msaFtpHelper.connectionEstablish(ftpHost, ftpPort, ftpUser, ftpPassword);
            String localExcelFilePath = msaFtpHelper.downloadFile(remoteGepsToHopesFilePath, localPath);

//TODO Step 2: Update the Excel File
            int poNumber = MSA_ExcelHelper.updateExcel(localExcelFilePath);

//TODO Step 3: Update the PO PDF File
            String localPoFilePath = MSA_PO_PDF_Helper.poPdfFileNameUpdate(poNumber, localPath);

//TODO Step 4: Upload the Updated File
            msaFtpHelper.connectionEstablishAndUploadFiles(ftpHost, ftpPort, ftpUser, ftpPassword, localPoFilePath, localExcelFilePath, poNumber, remotePOFilePathPO, remoteHopesToGepsPathXLS);

//TODO Step 5: Call API to Update Status
            MSA_APIHelper msaApiHelper = new MSA_APIHelper(logger);
            msaApiHelper.updateStatus(readPORAPIUrl);
            msaApiHelper.updateStatus(generatePOAPIUrl);

//TODO Step 6: Close FTP Connection
//            msaFtpHelper.closeConnection();
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Exception in MSA File Automation Flow Function: {}", exception.getMessage());
        }
    }
}