package com.utils.rpa.salesordersync;
import com.utils.LoggerUtil;
import com.utils.rpa.purchaseorderrequest.*;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PR_List_FTPHelper {

    FTPClient ftpClient;
    Logger logger;
    String excelFileName;
    String excelRemoteFilePath;
    File excelLocalFile;

//TODO Constructor
    private PR_List_FTPHelper() {
    }

    public PR_List_FTPHelper(FTPClient ftpClient){
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

            excelFileName = "PR list for original ID.xls";
            excelRemoteFilePath = remotePath + excelFileName;
            excelLocalFile = new File(localDir, excelFileName);

            try (FileOutputStream fileOutputStream = new FileOutputStream(excelLocalFile)) {
                ftpClient.retrieveFile(excelRemoteFilePath, fileOutputStream);
            }
        } catch (Exception exception) {
            logger.error("Exception in downloading file function: {}", exception.getMessage());
        }
        closeConnection();
        return excelLocalFile.getAbsolutePath();
    }

    public void connectionEstablishAndUploadFiles(String server, int port, String user, String password, String localPoFilePath, String localXLSFilePath, int poNumber, String remotePathPO, String remotePathXLS) {
        try {
            connectionEstablish(server, port, user, password);

//TODO PO File Upload
            uploadFile(localPoFilePath, remotePathPO + "PO_" + poNumber + ".pdf");

//TODO XLS File Upload
            uploadFile(localXLSFilePath, remotePathXLS + "240024POR01457_PT1_GePS-HOPES_R0.xls");
        } catch (Exception exception) {
            logger.error("Exception in call upload file function: {}", exception.getMessage());
        }
        closeConnection();
    }

    public void uploadFile(String localFilePath, String remoteFilePath) {
        try {
            ftpClient.storeFile(remoteFilePath, new FileInputStream(localFilePath));
        } catch (Exception exception) {
            logger.error("Exception in upload file function: {}", exception.getMessage());
        }
    }

    public void closeConnection() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (Exception exception) {
            logger.error("Exception in closing FTP connection function: {}", exception.getMessage());
        }
    }
}
