package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MSA_FTPHelper {

    FTPClient ftpClient;
    Logger logger;

//TODO Constructor
    private MSA_FTPHelper() {
    }

    public MSA_FTPHelper(FTPClient ftpClient){
        this.ftpClient = ftpClient;
        this.logger = LoggerUtil.getLogger(MSA_FTPHelper.class);
    }

    public void downloadFile(String server, int port, String user, String password, String remotePath, String localPath){
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File localDir = new File(localPath);
            File localFile = new File(localDir, new File(remotePath + "/240024POR01442_PT1_GePS-HOPES_R0.xls").getName());
            System.out.println(localFile.getName());
            System.out.println(localFile.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(localFile);
            ftpClient.retrieveFile(remotePath, fileOutputStream);
        } catch (IOException exception) {
            logger.error("Exception in downloading file function: {}", exception.getMessage());
        }
    }

    public void uploadFile(String localPath, String remotePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(localPath);
            ftpClient.storeFile(remotePath, fileInputStream);
        } catch (IOException exception) {
            logger.error("Exception in uploading file function: {}", exception.getMessage());
        }
    }

    public void closeConnection() {
        try {
            ftpClient.isConnected();
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException exception) {
            logger.error("Exception in closing FTP connection function: {}", exception.getMessage());
        }
    }
}