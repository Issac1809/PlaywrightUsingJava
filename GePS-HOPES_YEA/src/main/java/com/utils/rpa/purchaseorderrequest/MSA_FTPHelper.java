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

    public String downloadFile(String server, int port, String user, String password, String remotePath, String localPath) {
        File localFile = null;
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalActiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File localDir = new File(localPath);
            if (!localDir.exists() && !localDir.mkdirs());

            String fileName = "2Q0024POR01799_PT1_SCM-YGS_R0.xls";
            String remoteFilePath = remotePath + "/" + fileName;
            localFile = new File(localDir, fileName);

            try (FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {
                ftpClient.retrieveFile(remoteFilePath, fileOutputStream);
            }
        } catch (IOException exception) {
            logger.error("Exception in downloading file function: {}", exception.getMessage());
        }
        return localFile.getAbsolutePath();
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