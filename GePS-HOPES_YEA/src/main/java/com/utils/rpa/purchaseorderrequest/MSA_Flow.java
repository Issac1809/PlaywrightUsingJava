package com.utils.rpa.purchaseorderrequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
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
            String localGepsToHopesFilePath = jsonNode.get("msa").get("localGepsToHopesFilePath").asText();
            String remoteHopesToGepsPath = jsonNode.get("msa").get("remoteHopesToGepsPath").asText();
            String remotePOFilePath = jsonNode.get("msa").get("remotePOFilePath").asText();
            String readPORAPIUrl = jsonNode.get("msa").get("readPORAPIUrl").asText();
            String generatePOAPIUrl = jsonNode.get("msa").get("generatePOAPIUrl").asText();


//TODO Step 1: Connect to FTP and Download File
            MSA_FTPHelper msaFtpHelper = new MSA_FTPHelper(ftpClient);
            String filePath = msaFtpHelper.downloadFile(ftpHost, ftpPort, ftpUser, ftpPassword, remoteGepsToHopesFilePath, localGepsToHopesFilePath);

//TODO Step 2: Update the Excel File
            MSA_ExcelHelper.updateExcel(filePath);

//TODO Step 3: Upload the Updated File
            msaFtpHelper.uploadFile(localGepsToHopesFilePath, remoteHopesToGepsPath);

//TODO Step 4: Call API to Update Status
            MSA_APIHelper.updateStatus(readPORAPIUrl);

            msaFtpHelper.closeConnection();
        } catch (Exception exception) {
            logger.error("Exception in MSA File Automation Flow Function: {}", exception.getMessage());
        }
    }
}