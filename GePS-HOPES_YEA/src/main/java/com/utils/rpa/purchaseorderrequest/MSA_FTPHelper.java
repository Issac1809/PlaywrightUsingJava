package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.InetAddress;

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

            excelFileName = "240024POR01340_PT1_GePS-HOPES_R0.xls";
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

            // Command Listener for Debugging
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            // Set Active Mode and Bind IP/Port
            InetAddress serverAddress = InetAddress.getByName("106.51.109.59"); //TODO Use your public IP address
            ftpClient.enterRemoteActiveMode(serverAddress, 64849); //TODO 192.168.0.120.253.81 --> 253 * 256 + 81 = 64849. It’s used for establishing an active mode connection where the server connects to your client. Use the calculated port

            // Upload PO file
            uploadFile(localPoFilePath, remotePathPO + "PO_" + poNumber + "_R0.pdf");

            // Upload XLS file
            uploadFile(localXLSFilePath, remotePathXLS + "240024POR01340_PT1_GePS-HOPES_R0.xls");

        } catch (IOException exception) {
            logger.error("Exception during FTP operations: {}", exception.getMessage());
        }
        closeConnection();
    }

    private void uploadFile(String localFilePath, String remoteFilePath) throws IOException {

        ftpClient.storeFile(remoteFilePath, new FileInputStream(localFilePath));


//        try (FileInputStream fileInputStream = new FileInputStream(localFilePath)) {
//            System.out.println("Uploading file to: " + remoteFilePath);
//
//            // Attempt file upload
//            boolean success = ftpClient.storeFile(remoteFilePath, fileInputStream);
//            if (success) {
//                System.out.println("File uploaded successfully: " + remoteFilePath);
//            } else {
//                System.err.println("File upload failed: " + remoteFilePath);
//                System.out.println("FTP Reply Code: " + ftpClient.getReplyCode());
//                System.out.println("FTP Server Reply String: " + ftpClient.getReplyString());
//            }
//        } catch (IOException e) {
//            System.err.println("Error uploading file: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

//    public void uploadFile(String server, int port, String user, String password, String localPoFilePath, String localXLSFilePath, int poNumber, String remotePathPO, String remotePathXLS) throws IOException {
//        connectionEstablish(server, port, user, password);
//
//        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
//
//        InetAddress serverAddress = InetAddress.getByName("106.51.109.59"); //TODO Use your public IP address
//        ftpClient.enterRemoteActiveMode(serverAddress, 64849); //TODO 192.168.0.120.253.81 --> 253 * 256 + 81 = 64849. It’s used for establishing an active mode connection where the server connects to your client. Use the calculated port
//
//        // Upload PO file
//        try (FileInputStream fileInputStream = new FileInputStream(localPoFilePath)) {
//            System.out.println("FileInputStream is ready");
//
//            boolean success = ftpClient.storeFile(remotePathPO + "/PO_" + poNumber + "_R0.PDF", fileInputStream);
//            if (success) {
//                System.out.println("File uploaded successfully.");
//            } else {
//                System.err.println("File upload failed.");
//            }
//
//            System.out.println(remotePathPO + "/PO_" + poNumber + "_R0.PDF");
//        } catch (IOException exception) {
//            System.err.println("Failed to open file: " + exception.getMessage());
//            logger.error("Exception in uploading PO file: {}", exception.getMessage());
//        }
//
//        // Upload XLS file
//        try (FileInputStream fileInputStream = new FileInputStream(localXLSFilePath)) {
//            boolean success = ftpClient.storeFile(remotePathXLS, fileInputStream);
//            if (success) {
//                System.out.println("File uploaded successfully.");
//            } else {
//                System.err.println("File upload failed.");
//            }
//
//        } catch (IOException exception) {
//            logger.error("Exception in uploading XLS file: {}", exception.getMessage());
//        }
//        closeConnection();
//    }

    public void closeConnection() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException exception) {
            logger.error("Exception in closing FTP connection function: {}", exception.getMessage());
        }
    }
}