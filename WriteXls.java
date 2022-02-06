package java_basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteXls {
	public void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook wb = null;
        String fileExtension = fileName.substring(fileName.indexOf("."));
        
        if(fileExtension.equals(".xlsx")){
        wb = new XSSFWorkbook(inputStream);
        } else if(fileExtension.equals(".xls")){
            wb = new HSSFWorkbook(inputStream);
        }    
        Sheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount+1);
        
        for(int j = 0; j < row.getLastCellNum(); j++){
        	Cell cell = newRow.createCell(j);
        	cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        outputStream.close();	
    }

    public static void main(String...strings) throws IOException {
        String[] valueToWrite = {"21_11_05-07","ESN-EYE", "foto", "+", "-", "+"};
        WriteXls objExcelFile = new WriteXls();
        objExcelFile.writeExcel(System.getProperty("user.dir")+"\\src\\java_basics","arch.xlsx","arch", valueToWrite);

    }

}
