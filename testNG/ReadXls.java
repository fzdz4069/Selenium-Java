package java_basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXls {
	public void readExcel(String filePath, String fileName, String sheetName) throws IOException {
	    File file = new File(filePath + "\\" + fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook wb = null;
	    String fileExtension = fileName.substring(fileName.indexOf("."));
	    
	    if(fileExtension.equals(".xlsx")) {
	    wb = new XSSFWorkbook(inputStream);
	    } else if(fileExtension.equals(".xls")) {
	        wb = new HSSFWorkbook(inputStream);
	    }
	    Sheet sheet = wb.getSheet(sheetName);
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

	    for (int i = 0; i <= rowCount; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }
	        System.out.println();
	    } 
	}  

	public static void main(String...strings) throws IOException {
		ReadXls objExcelFile = new ReadXls();
	    String filePath = System.getProperty("user.dir")+"\\src\\java_basics";
	    objExcelFile.readExcel(filePath,"arch.xlsx", "arch");
	}
}
