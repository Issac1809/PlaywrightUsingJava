package com.procurement.poc.classes;

import com.procurement.poc.interfaces.IMSA;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class msa implements IMSA {

    public void msamodify() throws IOException {
        String folderPath = "Downloads/MSA/Output";
        String outputFolderPath = "Downloads/MSA/Input";

        File excelFile = getExcelFile(folderPath);
        if (excelFile == null) {
            System.out.println("No Excel files found in the folder.");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(excelFile);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            String cellValue = getCellValue(sheet, 1, 1);
            String editedText = cellValue.replace("POR", "PO");

            writeCellValue(sheet, 3, 1, editedText);
            writeCellValue(sheet, 3, 2, "POReleasePending");

            Sheet sheet2 = workbook.getSheetAt(1); // Switch to the second sheet (index 1)
            int startRow = 1; // Skip the header row

            int initialCQ = 123123;
            int initialCR = 100;
            int initialCS = 10;

            for (int i = startRow; i <= sheet2.getLastRowNum(); i++) { // Include the last row
                Row row = sheet2.getRow(i);
                if (row == null) {
                    row = sheet2.createRow(i);
                }

                Cell cellA = row.getCell(0); // Column A (index 0)
                if (cellA != null && cellA.getCellType() != CellType.BLANK) {
                    row.createCell(94).setCellValue(initialCQ); // Column CQ (index 94)
                    row.createCell(95).setCellValue(initialCR); // Column CR (index 95)
                    row.createCell(96).setCellValue(initialCS); // Column CS (index 96)

                    initialCQ += 10;
                    initialCR += 10;
                    initialCS += 10;
                }
            }


            saveWorkbook(workbook, outputFolderPath, excelFile.getName());
        }
    }

    private File getExcelFile(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("The specified folder does not exist or is not a directory: " + folderPath);
            return null;
        }
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".xls"));
        return (files == null || files.length == 0) ? null : files[0];
    }

    private String getCellValue(Sheet sheet, int rowIndex, int cellIndex) {
        Row row = sheet.getRow(rowIndex);
        return (row != null && row.getCell(cellIndex) != null) ? row.getCell(cellIndex).getStringCellValue() : "";
    }

    private void writeCellValue(Sheet sheet, int rowIndex, int cellIndex, String value) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        row.createCell(cellIndex).setCellValue(value);
    }

    private void saveWorkbook(Workbook workbook, String outputFolderPath, String fileName) throws IOException {
        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFolder, fileName))) {
            workbook.write(fileOutputStream);
        }
    }
}
