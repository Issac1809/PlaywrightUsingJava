package com.utils.rpa.purchaseorderrequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;

public class MSA_PO_PDF_Helper {

    public static String poPdfFileNameUpdate(int newPoNumber, String filePath) {
        File newFilePath = null;
        try {
            String oldFileName = "PO_" + 3489843 + "_R0.PDF";
            File oldFilePath = new File(filePath, oldFileName);

            // Generate new file name and path
            String newFileName = "PO_" + newPoNumber + "_R0.PDF";
            newFilePath = new File(filePath, newFileName);

            try (PDDocument document = PDDocument.load(oldFilePath)) {
                document.setAllSecurityToBeRemoved(true);
                document.save(newFilePath);
            }
        } catch (IOException exception) {
            System.err.println("Exception in PO PDF File Name Update Function: " + exception.getMessage());
        }
        return newFilePath.getAbsolutePath();
    }
}