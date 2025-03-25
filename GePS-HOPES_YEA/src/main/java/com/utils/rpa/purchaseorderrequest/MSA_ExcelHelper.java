package com.utils.rpa.purchaseorderrequest;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class MSA_ExcelHelper {

    static Logger logger;

//TODO Constructor
    public MSA_ExcelHelper() {
        this.logger = LoggerUtil.getLogger(MSA_ExcelHelper.class);
    }

    public static void updateExcel(String filePath, int rowNum, int colNum, String newValue) {
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);
            Cell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(newValue);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
        } catch (IOException exception) {
            logger.error("Exception in update excel function: {}", exception.getMessage());
        }
    }
}