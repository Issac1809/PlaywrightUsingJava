package com.utils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class MSA_FTPConnection {

    static FTPClient ftpClient;
    static Logger logger;

//TODO Constructor
    private MSA_FTPConnection(){
    }

    public MSA_FTPConnection(Logger logger){
        this.ftpClient = new FTPClient();
        this.logger = LoggerUtil.getLogger(MSA_FTPConnection.class);
    }

    public static void main(String[] args){
        try {
            ftpClient.connect("ftp.drivehq.com", 21);
            ftpClient.login("yourUsername", "yourPassword");
            ftpClient.enterLocalPassiveMode();

            System.out.println("Connected to FTP Server!");

            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException exception) {
            logger.error("Error in connecting to FTP Server: {}", exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
