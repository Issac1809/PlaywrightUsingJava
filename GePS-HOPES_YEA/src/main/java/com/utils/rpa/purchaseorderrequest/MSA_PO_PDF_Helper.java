package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;

public class MSA_PO_PDF_Helper {

    Logger logger;

//TODO Constructor
    public MSA_PO_PDF_Helper() {
        this.logger = LoggerUtil.getLogger(MSA_PO_PDF_Helper.class);
    }

    public String poPdfFileNameUpdate(int newPoNumber, String filePath) {
        File newFilePath = null;
        try {
            String oldFileName = "PO_" + 3489843 + ".pdf";
            File oldFilePath = new File(filePath, oldFileName);

            String newFileName = "PO_" + newPoNumber + ".pdf";
            newFilePath = new File(filePath, newFileName);

            try (PDDocument document = PDDocument.load(oldFilePath)) {
                document.setAllSecurityToBeRemoved(true);
                document.save(newFilePath);
            }
        } catch (Exception exception) {
            System.err.println("Exception in PO PDF File Name Update Function: " + exception.getMessage());
        }
        return newFilePath.getAbsolutePath();
    }
}