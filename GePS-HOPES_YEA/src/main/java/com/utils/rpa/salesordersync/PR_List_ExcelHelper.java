package com.utils.rpa.salesordersync;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PR_List_ExcelHelper {

    Logger logger;
    int randomNumber;

//TODO Constructor
    public PR_List_ExcelHelper() {
        this.logger = LoggerUtil.getLogger(PR_List_ExcelHelper.class);
    }

    public void createSOFile(int itemCount, String filePath){
        Workbook workbook = null;
        try {
            try(FileInputStream fileInputStream = new FileInputStream(filePath)){
                if (filePath.endsWith(".xls")) {
                    workbook = new HSSFWorkbook(fileInputStream); //TODO For .xls files
                } else {
                    workbook = new XSSFWorkbook(fileInputStream); //TODO For .xlsx files
                }
            }

//TODO Update Sheet 1 (Sheet 1)
            Sheet sheet1 = workbook.getSheet("Sheet1");

            randomNumber = (int) Math.round(Math.random() * 10000);

            for (int i = 1; i <= itemCount; i++) {
                if(sheet1.getRow(i).getCell(0) != null && sheet1.getRow(i).getCell(0).getCellType() != CellType.BLANK) {
                    //TODO All details are hardcoded!!
                    updateCell(sheet1, i, 1, "SO-" + randomNumber); //TODO Sales Document Number A column (index 1)
                    updateCell(sheet1, i, 2, String.valueOf(10 + i)); //TODO Sales Order Item Number B column (index 2)
                    updateCell(sheet1, i, 4, "0"); //TODO Quotation Item No D column (index 4)
                    updateCell(sheet1, i, 7, "Y101"); //TODO Billing Document No HK column (index 7)
                    updateCell(sheet1, i, 8, "Sales Order(Prd)"); //TODO Sales Document Type HK column (index 8)
                    updateCell(sheet1, i, 9, "2400"); //TODO Sales Organization HK column (index 9)
                    updateCell(sheet1, i, 10, "YEA"); //TODO Text: Sales Organization HK column (index 10)
                    updateCell(sheet1, i, 11, "30"); //TODO Distribution Channel HK column (index 11)
                    updateCell(sheet1, i, 12, "Inter-company"); //TODO Text: Distribution Channel HK column (index 12)
                    updateCell(sheet1, i, 13, "GV20"); //TODO Sales office HK column (index 13)
                    updateCell(sheet1, i, 14, "Overseas Int.Company"); //TODO Text: Sales Office HK column (index 14)
                    updateCell(sheet1, i, 19, "5800- TEST 16"); //TODO Sales Group HK column (index 19)
                    updateCell(sheet1, i, 21, "12/09/2025"); //TODO PO date HK column (index 21.00)
                    updateCell(sheet1, i, 22, "31/10/2024"); //TODO Requested deliv.date HK column (index 22.00)
                    updateCell(sheet1, i, 24, "Y12"); //TODO Order reason HK column (index 24)
                    updateCell(sheet1, i, 25, "Y12 Order Intake with PO"); //TODO Text: Order reason HK column (index 25)
                    updateCell(sheet1, i, 26, "0.00"); //TODO Net value of the order HK column (index 26)
                    updateCell(sheet1, i, 27, "EUR"); //TODO SD document currency HK column (index 27)
                    updateCell(sheet1, i, 28, "31/10/2024"); //TODO Pricing date HK column (index 28.00)
                    updateCell(sheet1, i, 37, "Y2U00027"); //TODO Sold-to party HK column (index 37)
                    updateCell(sheet1, i, 39, "Y2U00027"); //TODO Name: Sold-to party HK column (index 39)
                    updateCell(sheet1, i, 41, "200132028"); //TODO Payer HK column (index 41)
                    updateCell(sheet1, i, 42, "Yokogawa India (Pty) Ltd."); //TODO Name: Payer HK column (index 42)
                    updateCell(sheet1, i, 43, "0"); //TODO Name: End User HK column (index 43)
                    updateCell(sheet1, i, 53, "End User"); //TODO SD User Status HK column (index 53)
                    updateCell(sheet1, i, 54, "Name: End User"); //TODO Text: SD User Status HK column (index 54)
                    updateCell(sheet1, i, 59, "2"); //TODO Customer Group HK column (index 59)
                    updateCell(sheet1, i, 61, "0"); //TODO Text: Customer Group HK column (index 61)
                    updateCell(sheet1, i, 65, "0"); //TODO SAP User HK column (index 65)
                    updateCell(sheet1, i, 67, "2"); //TODO Serial Sales document item No HK column (index 67)
                    updateCell(sheet1, i, 75, "SAPP"); //TODO Confirm Quantity HK column (index 75)
                    updateCell(sheet1, i, 76, "SO: Approved"); //TODO Net value of the order item HK column (index 76)
                    updateCell(sheet1, i, 79, "0"); //TODO Net price per unit of measure HK column (index 79)
                    updateCell(sheet1, i, 83, "Customer Group"); //TODO Standard Price HK column (index 83)
                    updateCell(sheet1, i, 84, "Text: Customer Group"); //TODO Domestic Price (Japan) HK column (index 84)
                    updateCell(sheet1, i, 92, "30059222"); //TODO Direct PO to MFG Flag HK column (index 92)
                    updateCell(sheet1, i, 93, "Rohit Bharad"); //TODO Reason for rejection HK column (index 93)
                    updateCell(sheet1, i, 94, "12/09/2024"); //TODO Text: Reason for rejection HK column (index 94.00)
                    updateCell(sheet1, i, 98, "10"); //TODO Delivery Quantity in sales unit HK column (index 98)
                    updateCell(sheet1, i, 101, "F3XD64_F000000001"); //TODO Delivery Basis Indicator HK column (index 101)
                    updateCell(sheet1, i, 106, "10.000"); //TODO Value to be Billed in Billing Plan HK column (index 106.000)
                    updateCell(sheet1, i, 108, String.valueOf((10 + i) * 100)); //TODO Billing Currency HK column (index 108.000)
                    updateCell(sheet1, i, 109, "661.00"); //TODO Terms of Payment HK column (index 109)
                    updateCell(sheet1, i, 110, "100.00"); //TODO Text: Terms of Payment HK column (index 110)
                    updateCell(sheet1, i, 111, "0"); //TODO Usage HK column (index 111)
                    updateCell(sheet1, i, 113, "0.00"); //TODO Text: Usage HK column (index 113)
                    updateCell(sheet1, i, 114, "0.00"); //TODO Msg for Own ShipSect(Header) HK column (index 114)
                    updateCell(sheet1, i, 116, "0"); //TODO Msg for Packing (Header) HK column (index 116)
                    updateCell(sheet1, i, 117, "%"); //TODO Msg for All ShipSect(Header) HK column (index 117)
                    updateCell(sheet1, i, 120, "0.00"); //TODO Msg for Own ShipSect(Item) HK column (index 120)
                    updateCell(sheet1, i, 123, "0.00"); //TODO Msg for Packing (Item) HK column (index 123)
                    updateCell(sheet1, i, 126, "0.00"); //TODO Msg for All ShipSect(Item) HK column (index 126)
                    updateCell(sheet1, i, 129, "0.00"); //TODO Msg. on Delivery Note (Item) HK column (index 129)
                    updateCell(sheet1, i, 132, "0.00"); //TODO SO H:Msg. on Invoice HK column (index 132)
                    updateCell(sheet1, i, 140, "0.00"); //TODO Billing Status HK column (index 140)
                    updateCell(sheet1, i, 143, "0.00"); //TODO Billing date HK column (index 143)
                    updateCell(sheet1, i, 146, "0.00"); //TODO Tax classific.1 HK column (index 146)
                    updateCell(sheet1, i, 149, "0.00"); //TODO Text: Tax classific.1 HK column (index 149)
                    updateCell(sheet1, i, 152, "0.00"); //TODO Combined MS-Code HK column (index 152)
                    updateCell(sheet1, i, 153, "0"); //TODO Combined MS-Code Indicator HK column (index 153)
                    updateCell(sheet1, i, 155, "0.00"); //TODO Inquiry ID HK column (index 155)
                    updateCell(sheet1, i, 156, "0"); //TODO Service Order Number HK column (index 156)
                    updateCell(sheet1, i, 158, "0.00"); //TODO Your Reference HK column (index 158)
                    updateCell(sheet1, i, 159, "0"); //TODO Your Reference (Ship-to party) HK column (index 159)
                    updateCell(sheet1, i, 161, "0.00"); //TODO Shipping Point HK column (index 161)
                    updateCell(sheet1, i, 162, "0"); //TODO Text: Shipping Point HK column (index 162)
                    updateCell(sheet1, i, 164, "0.00"); //TODO Incoterms1 HK column (index 164)
                    updateCell(sheet1, i, 165, "0"); //TODO Incoterms2 HK column (index 165)
                    updateCell(sheet1, i, 167, "0.00"); //TODO Billing Gr. HK column (index 167)
                    updateCell(sheet1, i, 168, "0.00"); //TODO Sales Turnover Date (Plan) HK column (index 168)
                    updateCell(sheet1, i, 169, "0.00"); //TODO Sales Turnover Date (Actual) HK column (index 169)
                    updateCell(sheet1, i, 170, "0.00"); //TODO Project definition(level 0) HK column (index 170)
                    updateCell(sheet1, i, 171, "0.00"); //TODO Project text (Level 0) HK column (index 171)
                    updateCell(sheet1, i, 172, "0.00"); //TODO WBS Element (Level 2) HK column (index 172)
                    updateCell(sheet1, i, 173, "0.00"); //TODO WBS text (Level 2) HK column (index 173)
                    updateCell(sheet1, i, 174, "0.000"); //TODO WBS System status HK column (index 174)
                    updateCell(sheet1, i, 175, "%"); //TODO Text: WBS System status HK column (index 175)
                    updateCell(sheet1, i, 176, "0.00"); //TODO WBS User Status HK column (index 176)
                    updateCell(sheet1, i, 184, "F3XD64-3F/K2/CT"); //TODO Overall Status HK column (index 184)
                    updateCell(sheet1, i, 214, "987654321"); //TODO Billing Status HK column (index 214)
                    updateCell(sheet1, i, 218, "15/12/2025"); //TODO Actual Billing Date HK column (index 218)
                    updateCell(sheet1, i, 219, "2002"); //TODO Planned Billing Date HK column (index 219)
                    updateCell(sheet1, i, 221, "FCA"); //TODO Automatic Billing Style HK column (index 221)
                    updateCell(sheet1, i, 222, "TOKYO,JAPAN"); //TODO Text: Automatic Billing Style HK column (index 222)
                    updateCell(sheet1, i, 223, "0"); //TODO Incoterms1 HK column (index 223)
                    updateCell(sheet1, i, 241, "P"); //TODO Incoterms2 HK column (index 241)
                    updateCell(sheet1, i, 242, "N"); //TODO Project Category HK column (index 242)
                    updateCell(sheet1, i, 243, "P"); //TODO Text: Project Category HK column (index 243)
                    updateCell(sheet1, i, 244, "N"); //TODO Project definition(level 0) HK column (index 244)
                    updateCell(sheet1, i, 245, "N"); //TODO Project text (Level 0) HK column (index 245)
                    updateCell(sheet1, i, 246, "1.000"); //TODO WBS Element (Level 2) HK column (index 246.000)
                    updateCell(sheet1, i, 248, "N"); //TODO WBS text (Level 2) HK column (index 248)
                    updateCell(sheet1, i, 249, "N"); //TODO WBS System status HK column (index 249)
                    updateCell(sheet1, i, 250, "661.00"); //TODO Text: WBS System status HK column (index 250)
                    updateCell(sheet1, i, 251, "USD"); //TODO WBS User Status HK column (index 251)
                    updateCell(sheet1, i, 252, "Not Performed"); //TODO Text: WBS User Status HK column (index 252)
                    updateCell(sheet1, i, 280, "0.00"); //TODO Value to be Billed in Billing Plan HK column (index 280)
                }
            }
//TODO Save Changes
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
                workbook.close();
            }
        } catch (IOException exception) {
            logger.error("Exception in creating SO file function: {}", exception.getMessage());
        }
    }

    public int updateExcel(String filePath) {
        Workbook workbook;
        try {
            try(FileInputStream fileInputStream = new FileInputStream(filePath)){
                if (filePath.endsWith(".xls")) {
                    workbook = new HSSFWorkbook(fileInputStream); //TODO For .xls files
                } else {
                    workbook = new XSSFWorkbook(fileInputStream); //TODO For .xlsx files
                }
            }

//TODO Update Sheet 1 (RequesterInput)
            Sheet sheet1 = workbook.getSheet("RequesterInput");
            randomNumber = (int) Math.round(Math.random() * 10000);
            updateCell(sheet1, 3, 1, String.valueOf(randomNumber));
            updateCell(sheet1, 3, 2, "POReleasePending");

//TODO Update Sheet 2 (MSA)
            Sheet sheet2 = workbook.getSheet("MSA");
            int rowCount = sheet2.getLastRowNum(); //TODO Get Total row count
            for (int i = 1; i <= rowCount; i++) {
                if(sheet2.getRow(i).getCell(0) != null && sheet2.getRow(i).getCell(0).getCellType() != CellType.BLANK) {
                    updateCell(sheet2, i, 11, String.valueOf(randomNumber)); //TODO Tokuchu Number L column (index 11)
                    updateCell(sheet2, i, 12, String.valueOf(randomNumber)); //TODO Tokuchu Number for Price M column (index 12)
                    updateCell(sheet2, i, 94, String.valueOf(randomNumber)); //TODO PR Number CQ column (index 94)
                    updateCell(sheet2, i, 95, String.valueOf(randomNumber)); //TODO PR Item Number CR column (index 95)
                    updateCell(sheet2, i, 96, String.valueOf(randomNumber)); //TODO PO Number CS column (index 96)
                }
            }

//TODO Save Changes
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }
        } catch (IOException exception) {
            logger.error("Exception in update excel function: {}", exception.getMessage());
        }
        return randomNumber;
    }

    public void updateCell(Sheet sheet, int rowNum, int colNum, String newValue) {
        try {
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum); //TODO Create the cell if it doesn't exist
            }
            cell.setCellValue(newValue); //TODO Set the value
        } catch (Exception exception) {
            logger.error("Exception in update cell function: {}", exception.getMessage());
        }
    }


}
