package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class MSA_ExcelHelper {

    static Logger logger;
    static double randomNumber;

    //TODO Constructor
    public MSA_ExcelHelper() {
        this.logger = LoggerUtil.getLogger(MSA_ExcelHelper.class);
    }

    public static void updateExcel(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);

//TODO Update Sheet 1 (RequesterInput)
            Sheet sheet1 = workbook.getSheet("RequesterInput");
            randomNumber = Math.round(Math.random() * 1000);
            updateCell(sheet1, 4, 1, String.valueOf(randomNumber));
            updateCell(sheet1, 4, 2, "POReleasePending");

//TODO Update Sheet 2 (MSA)
            Sheet sheet2 = workbook.getSheet("MSA");
            int rowCount = sheet2.getLastRowNum(); //Get Total row count
            for (int i = 1; i <= rowCount; i++) {
                updateCell(sheet2, i, 11, String.valueOf(randomNumber)); //Tokuchu Number L column (index 11)
                updateCell(sheet2, i, 12, String.valueOf(randomNumber)); //Tokuchu Number for Price M column (index 12)
                updateCell(sheet2, i, 92, String.valueOf(randomNumber)); //PR Number CQ column (index 92)
                updateCell(sheet2, i, 93, String.valueOf(randomNumber)); //PR Item Number CR column (index 93)
                updateCell(sheet2, i, 94, String.valueOf(randomNumber)); //PO Number CS column (index 94)
            }

//TODO Save Changes
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
        } catch (IOException exception) {
            logger.error("Exception in update excel function: {}", exception.getMessage());
        }
    }

    public static void updateCell(Sheet sheet, int rowNum, int colNum, String newValue) {
        try {
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            cell.setCellValue(newValue);
        } catch (Exception exception) {
            logger.error("Exception in update cell function: {}", exception.getMessage());
        }
    }
}