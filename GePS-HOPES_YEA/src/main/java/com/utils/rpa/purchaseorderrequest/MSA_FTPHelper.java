package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.*;

public class MSA_FTPHelper {

    FTPClient ftpClient;
    Logger logger;
    String excelFileName;
    String excelRemoteFilePath;
    File excelLocalFile;

//TODO Constructor
    private MSA_FTPHelper() {
    }

    public MSA_FTPHelper(FTPClient ftpClient){
        this.ftpClient = ftpClient;
        this.logger = LoggerUtil.getLogger(MSA_FTPHelper.class);
    }

    public void connectionEstablish(String server, int port, String user, String password){
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalActiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (Exception exception) {
            logger.error("Exception in connection establish function: {}", exception.getMessage());
        }
    }

    public String downloadFile(String remotePath, String localPath) {
        try {
            File localDir = new File(localPath);
            if (!localDir.exists() && !localDir.mkdirs());

            excelFileName = "240024POR01423_PT1_GePS-HOPES_R0.xls";
            excelRemoteFilePath = remotePath + excelFileName;
            excelLocalFile = new File(localDir, excelFileName);

            try (FileOutputStream fileOutputStream = new FileOutputStream(excelLocalFile)) {
                ftpClient.retrieveFile(excelRemoteFilePath, fileOutputStream);
            }
        } catch (IOException exception) {
            logger.error("Exception in downloading file function: {}", exception.getMessage());
        }
        closeConnection();
        return excelLocalFile.getAbsolutePath();
    }

    public void connectionEstablishAndUploadFiles(String server, int port, String user, String password, String localPoFilePath, String localXLSFilePath, int poNumber, String remotePathPO, String remotePathXLS) {
        try {
            // Establish Connection
            connectionEstablish(server, port, user, password);

            // Upload PO file
            uploadFile(localPoFilePath, remotePathPO + "PO_" + poNumber + "_R0.pdf");

            // Upload XLS file
            uploadFile(localXLSFilePath, remotePathXLS + "240024POR01423_PT1_GePS-HOPES_R0.xls");

        } catch (IOException exception) {
            logger.error("Exception during FTP operations: {}", exception.getMessage());
        }
        closeConnection();
    }

    private void uploadFile(String localFilePath, String remoteFilePath) throws IOException {

        ftpClient.storeFile(remoteFilePath, new FileInputStream(localFilePath));
    }

    public void closeConnection() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException exception) {
            logger.error("Exception in closing FTP connection function: {}", exception.getMessage());
        }
    }
}